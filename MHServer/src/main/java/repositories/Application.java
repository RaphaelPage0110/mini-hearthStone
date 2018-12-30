package repositories;

import abstracts.*;
import impl.*;
import impl.behaviour.generic.DamageAllOpponents;
import impl.behaviour.generic.DrawCard;
import impl.behaviour.generic.Summon;
import impl.behaviour.generic.TransformInto;
import inter.Effect;
import inter.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Player player1;
    private Player player2;
    private Game game = new Game();

    @Autowired
    public HeroRepository heroRepository;

    @Autowired
    public MinionRepository minionRepository;

    @Autowired
    public SpellRepository spellRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

    /**
     * allows to create a new game of mini-hearthstone
     */
    public void createGame(){

        game.instanciatePlayers();

        player1 = game.getPlayer1();
        player2 = game.getPlayer2();


        while(true) {

            //we increase the mana of each player during the first 10 turns
            if (game.getTurn() <= 10 ) {
                player1.addManaMax(1);
                player2.addManaMax(1);
            }

            //we refill the players mana
            player1.setMyMana(player1.getMyManaMax());
            player2.setMyMana(player2.getMyManaMax());

            //we allow the players to use their heros ability
            player1.setCanUseHeroAbility(true);
            player2.setCanUseHeroAbility(true);

            //the two players play their turn
            playRound(game);
            game.incrementTurn();

        }

    }
    /**
     * manage the players turn
     */
    private void playRound(Game game) {

        Player firstToPlay = getActivePlayer();
        Player secondToPlay = getWaitingPlayer();

        action(firstToPlay,secondToPlay, game);
        action(secondToPlay,firstToPlay, game);

    }

    /**
     * TODO : Compléter cette fonction quand on en saura plus sur comment communiquer avec le client
     * allows a player to play its turn
     * @param activePlayer the player whose turn it is to play
     * @param opponent its opponent
     */
    private void action(Player activePlayer, Player opponent, Game game) {
        draw(activePlayer);
        boolean endTurn = false;
        while(!endTurn){
            //IL FAUDRA TROUVER UN MOYEN DE RECUPERER LA DECISION DU JOUEUR COTE CLIENT
            String idAction = "";
            switch (idAction) {
                default:
                    break;
            }
        }
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

        activePlayer.removeCardFromHand(minionToPlay);
        activePlayer.addMinion(minionToPlay);
        game.addMinionInPlay(minionToPlay);

        //we apply all the card's effect
        for (Effect effect : minionToPlay.getMyEffects()) {
            effect.effect();
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
            case PALADIN:
                cardType = CardType.PALADIN;
            case WARRIOR:
                cardType =CardType.WARRIOR;
            default:
                cardType = null;
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
                    activePlayer.addCardToStock(spellPicked);

                    //if it's 1 we will pick a minion
                case 1:

                    randomNum = ThreadLocalRandom.current().nextInt(0, listMinions.size());
                    ConcreteMinion minionPicked = listMinions.get(randomNum);
                    activePlayer.addCardToStock(minionPicked);

            }
        }
    }

    /**
     * allows to determine who is the active player
     * @return player
     */
    public Player getActivePlayer() {

        int turnPlayer1 = this.player1.getPlayOrder();
        int turnPlayer2 = this.player2.getPlayOrder();
        if (turnPlayer2 > turnPlayer1) {
            return player1;
        } else {
            return player2;
        }

    }

    /**
     * allows to determine who is the waiting player
     * @return player
     */
    public Player getWaitingPlayer() {
        return getActivePlayer().getOpponent();
    }

    public Game getGame() {
        return game;
    }
}