package impl;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Minion;
import abstracts.Hero;
import identifiers.IdPlayer;

import java.util.ArrayList;

public class Player {

    private Integer id;
    private Game myGame;
    private ConcreteHero myHero;
    private String sessionId;
    private boolean canUseHeroAbility;
    private Player opponent;
    private ArrayList<ConcreteMinion> myMinions  = new ArrayList<>();
    private ArrayList<Card> myHand  = new ArrayList<>();
    private int myManaMax;
    private int myMana;
    private ArrayList<Card> myStock = new ArrayList<>();
    private int myDamageAura; //used for spells that modifies the damage power of the minions
    private int playOrder; //used to know if the player is playing first or second

    /**
     * Default empty Player constructor
     */
    public Player() {
        this.id = new IdPlayer(this).getId();
        this.myManaMax = 0;
        this.myDamageAura = 0;
        boolean canUseHeroAbility = true;
        boolean passTurn = true;
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    public String getSessionId(){
        return sessionId;
    }

    /**
     * Returns value of myDamageAura
     * @return the aura currently applied on the minion
     */
    public int getMyDamageAura() {
        return myDamageAura;
    }

    /**
     * Sets the new value of aura
     * @param aura
     */
    public void setMyDamageAura(int aura) {
        this.myDamageAura = aura;
    }

    /**
     * add a new card to the Player's hand
     */
    public void addCardToHand(Card card) {

        myHand.add(card);

    }

    /**
     * remove a card from the players hand
     * @param removedCard
     */
    public void removeCardFromHand(Card removedCard) {
        myHand.remove(removedCard);
    }

    /**
     * Returns value of myCards
     * @return the list of the cards that are in the player's hand
     */
    public ArrayList<Card> getMyHand() {
        return myHand;
    }

    /**
     * add a new minion to the Player's minions
     */
    public void addMinion(ConcreteMinion minion) {
        myMinions.add(minion);
        myGame.addMinionInPlay(minion);
    }

    public ArrayList<ConcreteMinion> getMyMinions() {
        return myMinions;
    }

    /**
     * Returns value of id
     * @return id the id of the player
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets new value of id
     * @param id the id of the player
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns value of myHero
     * @return myHero the hero of the player
     */
    public ConcreteHero getMyHero() {
        return myHero;
    }

    /**
     * Sets new value of myHero
     * @param myHero the hero of the player
     */
    public void setMyHero(ConcreteHero myHero) {
        this.myHero = myHero;
    }

    /**
     * Returns value of myManaMax
     * @return myManaMax the current mana of a player
     */
    public int getMyManaMax() {
        return myManaMax;
    }

    /**
     * add a value to the current mana count
     * @param moreMana how much we want to increase the players mana
     */
    public void addManaMax(int moreMana) {
        this.myManaMax += moreMana;
    }

    /**
     * Sets new value of myManaMax
     * @param myManaMax the current mana of a player
     */
    public void setMyManaMax(int myManaMax) {
        this.myManaMax = myManaMax;
    }

    public int getMyMana() {
        return myMana;
    }

    public void setMyMana(int myMana) {
        this.myMana = myMana;
    }

    /**
     * Returns value of myGame
     * @return myGame the game in which the player currently is
     */
    public Game getMyGame() {
        return myGame;
    }

    /**
     * Sets new value of myGame
     * @param myGame the game in which the player currently is
     */
    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    /**
     * Returns value of myStock
     * @return myStock a part of the player's draw pile
     */
    public ArrayList<Card> getMyStock() {
        return myStock;
    }

    /**
     * add a new card to a Player's stock
     * @param newCard a card to add to the draw pile's stock
     */
    public void addCardToStock(Card newCard) {
        myStock.add(newCard);
    }

    /**
     * remove a card from the stock
     * @param removedCard
     */
    public void removeFromStock(Card removedCard) {
        myStock.remove(removedCard);
    }

    public int getPlayOrder() {
        return playOrder;
    }

    public void setPlayOrder(int playOrder) {
        this.playOrder = playOrder;
    }

    /**
     * Returns the value of the opponent.
     * @return this.opponent.
     */
    public Player getOpponent() {
        return this.opponent;
    }

    /**
     * Sets the new value of the opponent;
     * @param opponent the new value;
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Increases the aura value.
     * @param additionalAura the bonus to add.
     */
    public void addAura(int additionalAura) {
        this.myDamageAura += additionalAura;
    }

    /**
     * remove a minion from the game ie when it dies
     * @param minion the minion to remove
     */
    public void removeMinionFromPlay(Minion minion){

        myMinions.remove(minion);
        myGame.removeMinionFromGame(minion);

    }

    /**
     * what happens when this player lose
     */
    public void lost(){

        myGame.setGameOver(true);
        myGame.setLoser(this);

    }

    /**
     * what happens when this player wins
     * TODO : write this method
     */
    public void won(){


    }

    public boolean canUseHeroAbility() {
        return canUseHeroAbility;
    }

    public void setCanUseHeroAbility(boolean canUseHeroAbility) {
        this.canUseHeroAbility = canUseHeroAbility;
    }

    /**
     * check if the player has minions that can attack
     * @return true if the player has minions that can attack
     */
    public boolean hasMinionsAwake() {
        return true;
    }

    /**
     * check if the player has minions with taunt
     * @return true if the player has minions with taunt
     */
    public boolean hasTauntMinions() {
        return true;
    }

    public boolean canPlayCard(Card card) {
        return this.getMyMana() > card.getRequiredMana();
    }


}
