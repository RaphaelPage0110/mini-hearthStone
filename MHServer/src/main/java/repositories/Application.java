package repositories;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Hero;
import abstracts.Minion;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import impl.Game;
import impl.Player;
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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Player player1;
    private Player player2;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private MinionRepository minionRepository;

    @Autowired
    private SpellRepository spellRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Game game = new Game();

        game.instanciatePlayers();

        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        int turn = 1;

        while(true) {

            //we increase the mana of each player during the first 10 turns
            if (turn <= 10 ) {
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
            turn++;

        }

    }

    /**
     * manage the players turn
     */
    private void playRound(Game game) {
        int turnPlayer1 = this.player1.getPlayOrder();
        int turnPlayer2 = this.player2.getPlayOrder();
        if (turnPlayer2 > turnPlayer1) {
            action(this.player1, this.player2, game);
            action(this.player2, this.player1, game);
        } else {
            action(this.player2, this.player1, game);
            action(this.player1, this.player2, game);
        }
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
                case "playCard":
                    playCard(activePlayer, opponent, game);
                    break;
                case "attack":
                    prepareAttack(activePlayer, opponent);
                    break;
                case "useHeroAbility":
                    useHeroPower(activePlayer, opponent);
                    break;
                case "endTurn":
                    endTurn = true;
                default:
                    break;
            }
        }
    }

    /**
     * allows a player to use its hero ability
     * @param activePlayer the player whose turn it is to play
     * @param opponent its opponent
     */
    private void useHeroPower(Player activePlayer, Player opponent) {

        Hero hero = activePlayer.getMyHero();

        if (activePlayer.canUseHeroAbility()) {

            activePlayer.getMyHero().activateEffect();

            if (hero.getMyEffect() instanceof Summon) {

                String minionKeyword = ((Summon) hero.getMyEffect()).getMyMinionKeyword();
                ConcreteMinion minionToSummon = minionRepository.findByName(minionKeyword);
                activePlayer.addMinion(minionToSummon);

            } else {

                activePlayer.getMyHero().activateEffect();

            }

            activePlayer.setCanUseHeroAbility(false);

        }
    }

    /**
     * this method send to the client the list of the targets he can attack
     * @param activePlayer
     * @param opponent
     */
    private void prepareAttack(Player activePlayer, Player opponent) {

        /*
        //we check if the player has minions that can attack
        if(activePlayer.hasMinionsAwake()) {

            //if the opponent has minions with taunt, then he has to attack them first
            if(opponent.hasTauntMinions()) {

                attack(activePlayer, opponent);

            }

            else {
                //this var will contain the choice of the player concerning his choice of target
                String attackType = "";

                //then the player can either attack the hero or a minion
                switch (attackType) {
                    case "hero" :
                        attack(activePlayer, opponent.getMyHero() );
                        break;
                    case "minion" :
                        attack(activePlayer, opponent.getMyMinions() );
                        break;
                    default :
                        break;
                }
            }
        }
        else {
            //attaque impossible pour le moment
        }*/

    }

    /**
     * allows to attack a minion
     * TODO : Compléter avec le choix du joueur
     * @param activePlayer
     * @param opponent
     */
    private void attack(Player activePlayer, Target opponent) {

        //this var will contain the choice of the player concerning which minion he will attack
        ConcreteMinion minionToAttack = new ConcreteMinion();

        //this var will contain the choice of the player concerning which minion will attack
        ConcreteMinion minionThatAttacks = new ConcreteMinion();

        minionThatAttacks.attack(minionToAttack);


    }


    /**
     * allows to attack a hero
     * @param activePlayer
     * @param opponent
     */
    private void attackHero(Player activePlayer, Player opponent) {



    }

    /**
     * allows a player to play a card
     * TODO : a compléter avec le choix du client
     * @param activePlayer
     * @param opponent
     */
    private void playCard(Player activePlayer, Player opponent, Game game) {

        //la il y aura le choix du joueur, dans une seule variable
        ConcreteMinion minionToPlay = new ConcreteMinion();
        ConcreteSpell spellToPlay = new ConcreteSpell();

        //if the player has enough mana to play the card
        if(activePlayer.canPlayCard(minionToPlay) || activePlayer.canPlayCard(spellToPlay)) {

            //the player pay the cost of the card
            activePlayer.setMyMana(activePlayer.getMyMana()-minionToPlay.getRequiredMana());

            //on invoque le minion
            if (minionToPlay instanceof ConcreteMinion ) {

                activePlayer.removeCardFromHand(minionToPlay);
                activePlayer.addMinion(minionToPlay);
                game.addMinionInPlay(minionToPlay);

            //the player cast the spell
            } else if (spellToPlay instanceof ConcreteSpell) {

                for (Effect effect : spellToPlay.getMyEffects() ) {

                    if(effect instanceof Summon) {

                        String minionKeyword = ((Summon)effect).getMyMinionKeyword();
                        ConcreteMinion minionToSummon = minionRepository.findByName(minionKeyword);
                        activePlayer.addMinion(minionToSummon);
                        game.addMinionInPlay(minionToSummon);

                    }

                    if (effect instanceof TransformInto ) {

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

                    if (effect instanceof DrawCard ) {

                        for (int i = 0; i < ((DrawCard) effect).getNumberDraw(); i++) {

                            this.draw(activePlayer);

                        }

                    }
                }
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
}