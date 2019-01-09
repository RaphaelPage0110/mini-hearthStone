package business.controller;

import abstracts.Hero;
import business.Application;
import business.messageModels.Hello;
import business.repositories.HeroRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.Game;
import impl.Player;
import impl.behaviour.generic.notTargetedEffect.Summon;
import inter.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class GameController {

    @Autowired
    private Application myApplication;

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
            .create(SimpMessageType.MESSAGE);

    private Collection<Player> waitingUsers = new HashSet<>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private HeroRepository heroRepo;

    @MessageMapping("/connectGame")
    @SendTo("user/queue/reply")
    public Object connectToGame(@Header("simpSessionId") String sessionId, String heroname) {
        LOGGER.info("Received a connection from a user");
        Player player = new Player();
        player.setSessionId(sessionId);
        ConcreteHero hero = heroRepo.findByHeroName(heroname);
        player.setMyHero(hero);
        hero.setMyPlayer(player);

        waitingUsers.add(player);
        System.out.println(sessionId);

        heroname = hero.getHeroName();

        simpMessagingTemplate.convertAndSend("/queue/reply-user"+sessionId, new Hello("Vous avez choisi "+heroname+"! En attente d'un autre joueur..."));

        if (waitingUsers.size() == 2) {
            startGame();
            waitingUsers.clear();
        }


        return null;
    }

    @MessageMapping("/disconnectGame")
    @SendTo("user/queue/reply")
    public Object disconnectFromGame(@Header("simpSessionId") String sessionId) {
        LOGGER.info("Received a disconnection from a user");
        boolean done = false;
        for (Player player : waitingUsers) {
            if (player.getSessionId() == sessionId) {
                waitingUsers.remove(player);
                done = true;
                break;
            }
        }
        if (done){
            simpMessagingTemplate.convertAndSend("/queue/reply-user"+sessionId, new Hello("Recherche de partie annulée."));
        }
        else {
            simpMessagingTemplate.convertAndSend("/queue/reply-user"+sessionId, new Hello("Vous ne recherchiez pas de partie."));
        }

        return null;
    }

    private void startGame() {
        LOGGER.log(Level.INFO, "Starting game");
        Player player1 = (Player)waitingUsers.toArray()[0];
        Player player2 = (Player)waitingUsers.toArray()[1];

        String sessionPlayer1 = player1.getSessionId();
        String sessionPlayer2 = player2.getSessionId();

        Game game = new Game(player1, player2);

        simpMessagingTemplate.convertAndSend("/queue/reply_gameFound-user"+sessionPlayer1, new Hello("Vous êtes le joueur 1"));
        simpMessagingTemplate.convertAndSend("/queue/reply_gameFound-user"+sessionPlayer2, new Hello("Vous êtes le joueur 2"));

        LOGGER.log(Level.INFO, "Messages sent");

        myApplication.createGame(game);

    }

    /**
     * Used when a player passes his turn
     * @param sessionId
     * @return
     */
    @MessageMapping("/passTurn")
    @SendTo("user/queue/reply_passTurn")
    public Object passTurn(@Header("simpSessionId") String sessionId) {
        this.myApplication.getGame().setPassTurn(true);
        Player player = this.myApplication.getGame().getActivePlayer();

        return null;
    }

    @MessageMapping("/gameOver")
    @SendTo("user/queue/reply_gameOver")
    public Object gameOver(@Header("simpSessionId") String sessionId) {

        Game game = this.myApplication.getGame();
        Player loser = this.myApplication.getGame().getPlayerByID(sessionId);
        game.setLoser(loser);
        game.setWinner(loser.getOpponent());
        game.setGameOver(true);

        return null;
    }

    @MessageMapping("/playMinion")
    @SendTo({"user/queue/reply_playMinion", "user/queue/reply_hePlayedMinion"})
    public Object playMinion(@Header("simpSessionId") String sessionId, String idMinion) {

        Game game = this.myApplication.getGame();
        Player player = game.getPlayerByID(sessionId);
        ConcreteMinion minionToPlay = (ConcreteMinion) player.findCardById (idMinion);

        myApplication.playMinionCard(minionToPlay, player, game);
        myApplication.sendManaMessage(player);

        myApplication.sendHand(player);
        myApplication.sendMinionsInPlay(player);

        return null;
    }

    @MessageMapping("/showTargetForMinion")
    @SendTo("user/queue/reply_showTargets")
    public Object showPossibleTargetsForMinion(@Header("simpSessionId") String sessionId) {

        myApplication.showPossibleTargetsForMinion();
        return null;
    }

    @MessageMapping("/attackThisMinion")
    public Object attackThisMinion(@Header("simpSessionId") String sessionId, String message) {

        JsonElement jelement = new JsonParser().parse(message);
        JsonObject  jobject = jelement.getAsJsonObject();
        JsonElement attackerIDJson = jobject.get("attackerID");
        JsonElement targetIDJson = jobject.get("targetID");
        String attackerID = attackerIDJson.getAsString();
        String targetID = targetIDJson.getAsString();
        myApplication.attackMinion(attackerID, targetID);
        return null;
    }

    @MessageMapping("/attackHero")
    public Object attackHero(@Header("simpSessionId") String sessionId, String attackerID){

        myApplication.attackHero(attackerID);
        return null;
    }

    /**
     * allows a player to use its hero ability
     */
    public void useHeroPower(Player player) {
        Game game = player.getMyGame();
        Player activePlayer = game.getActivePlayer();
        Player waitingPlayer = game.getWaitingPlayer();
        Hero hero = activePlayer.getMyHero();

        if (activePlayer.canUseHeroAbility()) {

            activePlayer.getMyHero().activateEffect();

            Effect heroPower = hero.getMyEffect();

            if (heroPower instanceof Summon) {

                //some heroPowers could summon multiple minions of the same type at the same time
                int numberOfMinionsToSummon = ((Summon)heroPower).getNumberSummoned();

                for (int i=0; i<numberOfMinionsToSummon; i++) {

                    //we fetch the minion to summon in the database
                    String minionKeyword = ((Summon) hero.getMyEffect()).getMyMinionKeyword();
                    ConcreteMinion minionToSummon = myApplication.minionRepository.findByName(minionKeyword);

                    //we add the minion to the player hand and to the game
                    activePlayer.addMinion(minionToSummon);
                    game.addMinionInPlay(minionToSummon);

                    //we apply its effects
                    for (Effect effect : minionToSummon.getMyEffects() ) {
                        effect.effect();
                    }

                }

            } else {

                activePlayer.getMyHero().activateEffect();

            }

            //a hero can only use its hero ability once
            activePlayer.setCanUseHeroAbility(false);

        }
    }

}