package impl;

import abstracts.Hero;
import impl.behaviour.generic.Summon;
import inter.ClientServerInterface;
import inter.Effect;
import repositories.Application;

public class ClientServer implements ClientServerInterface {

    private Application myApplication;

    public ClientServer(Application application) {
        myApplication = application;
    }

    @Override
    /**
     * allows a player to use its hero ability
     */
    public void useHeroPower() {
        Player activePlayer = myApplication.getActivePlayer();
        Player waitingPlayer = myApplication.getWaitingPlayer();
        Hero hero = activePlayer.getMyHero();
        Game game = myApplication.getGame();

        if (activePlayer.canUseHeroAbility()) {

            activePlayer.getMyHero().activateEffect();

            Effect heroPower = hero.getMyEffect();

            if (heroPower instanceof Summon) {

                //some heroPowers could summon multiple minions of the same type at the same time
                int numberOfMinionsToSummon = ((Summon)heroPower).getNumberSummoned();

                for (int i=0; i<numberOfMinionsToSummon; i++) {

                    //we fetch the minion to summon in the database
                    String minionKeyword = ((Summon) hero.getMyEffect()).getMyMinionKeyword();
                    ConcreteMinion minionToSummon = myApplication.findMinionInDatabase(minionKeyword);

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

    @Override
    /**
     * allows a player to play a card
     * TODO : a compléter avec le choix du client
     */
    public void playCard() {
        Player activePlayer = myApplication.getActivePlayer();
        Player opponent = myApplication.getWaitingPlayer();
        Game game = myApplication.getGame();
        //la il y aura le choix du joueur, dans une seule variable
        ConcreteMinion minionToPlay = new ConcreteMinion();
        ConcreteSpell spellToPlay = new ConcreteSpell();

        //if the player has enough mana to play the card
        if(activePlayer.canPlayCard(minionToPlay) || activePlayer.canPlayCard(spellToPlay)) {

            //the player pay the cost of the card
            activePlayer.setMyMana(activePlayer.getMyMana()-minionToPlay.getRequiredMana());

            //we summon the minion
            if (minionToPlay instanceof ConcreteMinion ) {

                myApplication.playMinionCard(minionToPlay, activePlayer, game);

                //the player cast the spell
            } else if (spellToPlay instanceof ConcreteSpell) {

                myApplication.playSpellCard(spellToPlay, activePlayer, game);
            }

        }

    }

    @Override
    /**
     * this method send to the client the list of the targets he can attack
     * TODO : COMPLETER CETTE FONCTION QUAND PN SAURA COMMUNIQUER AVEC LE CLIENT
     */
    public void prepareAttack() {

        Player activePlayer = myApplication.getActivePlayer();
        Player opponent = myApplication.getWaitingPlayer();
        //we check if the player has minions that can attack
        if(activePlayer.hasMinionsAwake()) {

            //if the opponent has minions with taunt, then he has to attack them first
            if(opponent.hasTauntMinions()) {

                //il faut envoyer la liste des minions avec taunt au client

            }

            else {
                //on envoi au client le choix d'attaquer soit le héros soit les minions de l'adversaire

            }
        }
        else {
            //attaque impossible pour le moment
        }

    }

    @Override
    /**
     * this method allows the player to end his turn
     * TODO : COMPLETER CETTE METHODE QUAND ON SAURA COMMENT PASSER LE TOUR DU JOUEUR
     */
    public void endTurn() {

    }

    @Override
    /**
     * this method allows the player to surrender and end the game
     * TODO : COMPLETER CETTE METHODE
     */
    public void surrender() {

    }

    public void createGame() {
        myApplication.createGame();
    }
}
