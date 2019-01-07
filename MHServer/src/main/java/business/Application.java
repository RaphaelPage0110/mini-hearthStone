package business;

import abstracts.*;
import business.messageModels.HisHandMessage;
import business.messageModels.ManaMessage;
import business.messageModels.MyCardMessage;
import business.messageModels.MyHeroMessage;
import business.repositories.HeroRepository;
import business.repositories.MinionRepository;
import business.repositories.SpellRepository;
import impl.*;
import impl.behaviour.generic.notTargetedEffect.DrawCard;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.TransformInto;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;
import inter.TargetedEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application{

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private Game game;

    @Autowired
    public HeroRepository heroRepository;

    @Autowired
    public MinionRepository minionRepository;

    @Autowired
    public SpellRepository spellRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * allows to create a new game of mini-hearthstone
     */
    public void createGame(Game newGame){

        this.game = newGame;
        instanciatePlayers(game);

        while(!game.isGameOver()) {

            //the two players play their turn
            playRound(game);
            game.incrementTurn();

        }

        simpMessagingTemplate.convertAndSend("/queue/reply_gameOver-user"+game.getWinner().getSessionId(), "Vous avez gagné!!");
        simpMessagingTemplate.convertAndSend("/queue/reply_gameOver-user"+game.getLoser().getSessionId(), "Vous avez perdu.");

    }

    private void instanciatePlayers(Game game) {

        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        player1.setMyGame(game);
        player2.setMyGame(game);

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        //the first player draws 3 cards
        for(int i=0;i<3;i++){
            draw(player1);
        }
        sendHand(player1);

        //the second player draws 4 cards
        for(int i=0;i<4;i++){
            draw(player2);
        }
        sendHand(player2);

        //we send to the players their hero and mana
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        for(Player player : playerList) {
            MyHeroMessage myHeroMessage = new MyHeroMessage(player.getMyHero());
            simpMessagingTemplate.convertAndSend("/queue/reply_myHero-user"+player.getSessionId(), myHeroMessage);
            myHeroMessage = new MyHeroMessage(player.getOpponent().getMyHero());
            simpMessagingTemplate.convertAndSend("/queue/reply_hisHero-user"+player.getSessionId(), myHeroMessage);

            sendManaMessage(player);
        }


    }

    /**
     * Send the mana of the player to himself and his opponent
     * @param player
     */
    public void sendManaMessage(Player player){
        //we send their mana to the user and his opponent
        ManaMessage manaMessage = new ManaMessage(player);
        simpMessagingTemplate.convertAndSend("/queue/reply_myMana-user"+player.getSessionId(), manaMessage);
        simpMessagingTemplate.convertAndSend("/queue/reply_hisMana-user"+player.getOpponent().getSessionId(), manaMessage);
    }

    /**
     * manage the players turn
     */
    private void playRound(Game game) {

        Player firstToPlay = game.getPlayer1();
        Player secondToPlay = game.getPlayer2();

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(firstToPlay);
        playerList.add(secondToPlay);

        for (Player player : playerList){
            if (!game.isGameOver()){

                //the players minions can attack again
                for(ConcreteMinion minion : player.getMyMinions()){
                    minion.setCanAttack(true);
                }
                sendMinionsInPlay(player);
                action(player,player.getOpponent(), game);
            }

        }
    }

    public void sendHand(Player player){
        ArrayList<Card> playerHand = player.getMyHand();

        ArrayList<MyCardMessage> myHandMessage = new ArrayList<>();

        for(Card card : playerHand){
            MyCardMessage cardMessage = new MyCardMessage(card);
            myHandMessage.add(cardMessage);
        }
        HisHandMessage hisHandMessage = new HisHandMessage(playerHand.size());
        simpMessagingTemplate.convertAndSend("/queue/reply_myHand-user"+player.getSessionId(), myHandMessage);
        simpMessagingTemplate.convertAndSend("/queue/reply_hisHand-user"+player.getOpponent().getSessionId(), hisHandMessage);
    }

    /**
     * TODO : Compléter cette fonction quand on en saura plus sur comment communiquer avec le client
     * allows a player to play its turn
     * @param activePlayer the player whose turn it is to play
     * @param opponent its opponent
     */
    private void action(Player activePlayer, Player opponent, Game game) {
        simpMessagingTemplate.convertAndSend("/queue/reply_yourTurn-user"+activePlayer.getSessionId(), "yourTurn");
        activePlayer.setPlayOrder(activePlayer.getPlayOrder()+1);
        game.setPassTurn(false);
        //we increase the mana of each player during the first 10 turns
        if (game.getTurn() <= 10 ) {
            activePlayer.addManaMax(1);
        }
        //we refill the players mana
        activePlayer.setMyMana(activePlayer.getMyManaMax());

        //we allow the players to use their heros ability
        activePlayer.setCanUseHeroAbility(true);

        //we send their mana to the user and his opponent
        sendManaMessage(activePlayer);

        draw(activePlayer);
        sendHand(activePlayer);

        while(!game.isPassTurn()){

        }
        simpMessagingTemplate.convertAndSend("/queue/reply_passedTurn-user"+activePlayer.getSessionId(),"Passed turn");
    }

    public void showPossibleTargetsForMinion(){
        Player waitingPlayer = game.getWaitingPlayer();
        ArrayList<ConcreteMinion> targetMinions = waitingPlayer.findTauntMinions();

        if(targetMinions.size()==0){
            targetMinions = waitingPlayer.getMyMinions();
        }

        ArrayList<MyCardMessage> myMinionsMessage = new ArrayList<>();

        for(ConcreteMinion minion : targetMinions){
            MyCardMessage cardMessage = new MyCardMessage(minion);
            myMinionsMessage.add(cardMessage);
        }

        simpMessagingTemplate.convertAndSend("/queue/reply_targetsMinions-user"+waitingPlayer.getOpponent().getSessionId(), myMinionsMessage);
    }

    /**
     * allows to attack a minion
     * TODO : Compléter avec le choix du joueur
     * @param activePlayer
     * @param opponent
     */
    private void attackMinion(Player activePlayer, Target opponent) {

        //envoyer au joueur la liste des minions qu'il peut attaquer
        //ConcreteMinion minionToAttack = choix du joueur;

        //envoyer au joueur la liste des minions avec lesquels il peut attaquer
        //ConcreteMinion minionThatAttacks = choix du joueur;

        //minionThatAttacks.attack(minionToAttack);

    }


    /**
     * allows to attack a hero
     * TODO : Ecrire cette méthode
     * @param activePlayer
     * @param opponent
     */
    private void attackHero(Player activePlayer, Player opponent) {

        //envoyer au joueur la liste des minions avec lesquels il peut attaquer
        //ConcreteMinion minionThatAttacks = choix du joueur;

        Hero heroToAttack = opponent.getMyHero();

        //minionThatAttacks.attack(heroToAttack);
    }

    /**
     * Allows a player to play a spell card
     * @param spellToPlay
     * @param activePlayer
     * @param game
     */
    public void playSpellCard(Spell spellToPlay, Player activePlayer, Game game) {

        for (Effect effect : spellToPlay.getMyEffects() ) {

            //if the card effect is to summon a minion, then we have to treat it separately
            if(effect instanceof Summon) {

                //some spells can summon multiple minions of the same type at the same time
                int numberOfMinionsToSummon = ((Summon)effect).getNumberSummoned();

                for (int i=0; i<numberOfMinionsToSummon; i++) {

                    //we fetch the minions details from the database using it's name
                    String minionKeyword = ((Summon)effect).getMyMinionKeyword();
                    ConcreteMinion minionToSummon = minionRepository.findByName(minionKeyword);
                    minionToSummon.setUniqueID();

                    //we add the newly created minion to the game
                    activePlayer.addMinion(minionToSummon);
                    game.addMinionInPlay(minionToSummon);

                }

            }

            //the transform effect is somewhat similar to the summon effect, except that we don't summon a new minion,
            //we just update the targeted minions parameters
            else if (effect instanceof TransformInto ) {

                //là il faut demander le choix du joueur
                ConcreteMinion minionBeingTransformed = new ConcreteMinion();
                String minionKeyword = ((TransformInto)effect).getMyMinionKeyword();
                ConcreteMinion minionModel = minionRepository.findByName(minionKeyword);
                minionBeingTransformed.setName(minionModel.getName());
                minionBeingTransformed.setRequiredMana(minionModel.getRequiredMana());
                minionBeingTransformed.setDamagePoints(minionModel.getDamagePoints());
                minionBeingTransformed.setMaxHealthPoints(minionModel.getMaxHealthPoints());
                minionBeingTransformed.setCurrentHealthPoints(minionModel.getCurrentHealthPoints());
                minionBeingTransformed.setType(minionModel.getType());
                minionBeingTransformed.setMyEffects(minionModel.getMyEffects());

            }

            else if (effect instanceof DrawCard ) {

                for (int i = 0; i < ((DrawCard) effect).getNumberDraw(); i++) {

                    this.draw(activePlayer);

                }

            }

            else {

                effect.effect();

            }
        }

    }

    /**
     * allows a player to play a minion card
     * @param minionToPlay
     * @param activePlayer
     * @param game
     */
    public void playMinionCard(ConcreteMinion minionToPlay, Player activePlayer, Game game) {

        activePlayer.changeMana(-minionToPlay.getRequiredMana());
        activePlayer.removeCardFromHand(minionToPlay);
        activePlayer.addMinion(minionToPlay);
        game.addMinionInPlay(minionToPlay);

        //we apply all the card's effect
        for (Effect effect : minionToPlay.getMyEffects()) {
            if (effect instanceof NotTargetedEffect) {
                effect.effect();
            }
        }

    }

    /**
     * allows a player to draw a card
     * @param activePlayer
     */
    private void draw(Player activePlayer) {

        ArrayList<Card> playerStock = activePlayer.getMyStock();

        //we first have to check if we should refill the stock
        if(playerStock.size()< 2) {

            this.refillStock(activePlayer);

        }

        //we add the first card that's in the players stock to its hand
        Card cardDrawn = playerStock.get(0);
        activePlayer.addCardToHand(cardDrawn);
        activePlayer.removeFromStock(cardDrawn);

    }

    /**
     * refills the player stock until it's at ten cards again
     * @param activePlayer
     */
    private void refillStock(Player activePlayer) {

        CardType cardType;
        CardType typeCommon = CardType.COMMON;

        switch (activePlayer.getMyHero().getHeroType()){
            case MAGE:
                cardType = CardType.MAGE;
                break;
            case PALADIN:
                cardType = CardType.PALADIN;
                break;
            case WARRIOR:
                cardType =CardType.WARRIOR;
                break;
            default:
                cardType = null;
                break;
        }

        ArrayList<ConcreteMinion> listMinionsCommon =  minionRepository.findByType(typeCommon);
        ArrayList<ConcreteMinion> listMinionsLimited = minionRepository.findByType(cardType);

        //this arraylist contains all the minions this player can have
        ArrayList<ConcreteMinion> listMinions = new ArrayList<>(listMinionsCommon);
        listMinions.addAll(listMinionsLimited);

        List<ConcreteSpell> listSpellsCommon =  spellRepository.findByType(typeCommon);
        List<ConcreteSpell> listSpellsLimited =  spellRepository.findByType(cardType);

        //this arraylist contains all the spells this player can have
        ArrayList<ConcreteSpell> listSpells = new ArrayList<>(listSpellsCommon);
        listSpells.addAll(listSpellsLimited);

        while(activePlayer.getMyStock().size()< 10) {

            //we first have to randomly decide if we have to pick a spell or a minion.
            int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
            switch (randomNum){
                //if randomNum is 0 we will pick a spell
                case 0:

                    //we then randomly pick a spell from all the spells the player can pick from
                    randomNum = ThreadLocalRandom.current().nextInt(0, listSpells.size());
                    ConcreteSpell spellPicked = listSpells.get(randomNum);
                    ConcreteSpell spellToAdd = spellPicked.clone();
                    spellToAdd.setPlayer(activePlayer);
                    spellToAdd.setUniqueID();
                    spellToAdd.generateEffect(spellToAdd.getAbilityKeyWord());
                    activePlayer.addCardToStock(spellToAdd);

                    //if it's 1 we will pick a minion
                case 1:

                    randomNum = ThreadLocalRandom.current().nextInt(0, listMinions.size());
                    ConcreteMinion minionPicked = listMinions.get(randomNum);
                    ConcreteMinion minionToAdd = minionPicked.clone();
                    minionToAdd.setPlayer(activePlayer);
                    minionToAdd.setUniqueID();
                    minionToAdd.generateMinionEffect(minionToAdd.getAbilityKeyWord());
                    minionToAdd.generateMinionDeathRattle(minionToAdd.getDeathRattleKeyWords());
                    activePlayer.addCardToStock(minionToAdd);

            }
        }
    }

    public void sendMinionsInPlay(Player player){

        ArrayList<ConcreteMinion> playerMinions = player.getMyMinions();

        ArrayList<MyCardMessage> myMinionsMessage = new ArrayList<>();

        for(ConcreteMinion minion : playerMinions){
            MyCardMessage cardMessage = new MyCardMessage(minion);
            myMinionsMessage.add(cardMessage);
        }
        simpMessagingTemplate.convertAndSend("/queue/reply_playMinion-user"+player.getSessionId(), myMinionsMessage);
        simpMessagingTemplate.convertAndSend("/queue/reply_playedMinion-user"+player.getOpponent().getSessionId(), myMinionsMessage);

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
