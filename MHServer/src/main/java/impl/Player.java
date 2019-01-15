package impl;

import abstracts.Card;

import java.util.ArrayList;

public class Player {

  private Game myGame;
  private ConcreteHero myHero;
  private String sessionId;

  private boolean passTurn;
  private Player opponent;
  private ArrayList<ConcreteMinion> myMinions = new ArrayList<>();
  private ArrayList<Card> myHand = new ArrayList<>();
  private int myManaMax;
  private int myMana;
  private ArrayList<Card> myStock = new ArrayList<>();
  private int myDamageAura; // used for spells that modifies the damage power of the minions
  private int playOrder; // used to know if the player is playing first or second

  /**
   * Default empty Player constructor
   */
  public Player() {
    this.myManaMax = 0;
    this.myDamageAura = 0;
    passTurn = true;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public int getMyDamageAura() {
    return myDamageAura;
  }

  public void setMyDamageAura(int aura) {
    this.myDamageAura = aura;
  }

  /**
   * add a new card to the Player's hand
   */
  public void addCardToHand(Card card) {

    myHand.add(card);
  }

  public void removeCardFromHand(Card removedCard) {
    myHand.remove(removedCard);
  }

  public ArrayList<Card> getMyHand() {
    return myHand;
  }

  /**
   * add a new minion to the Player's minions and add it to the list of minions in play in the game
   */
  public void addMinion(ConcreteMinion minion) {
    myMinions.add(minion);
  }

  public ArrayList<ConcreteMinion> getMyMinions() {
    return myMinions;
  }

  public void setMyMinions(ArrayList<ConcreteMinion> minionsList) {
    myMinions = minionsList;
  }

  /**
   * Returns value of myHero
   *
   * @return myHero the hero of the player
   */
  public ConcreteHero getMyHero() {
    return myHero;
  }

  /**
   * Sets new value of myHero
   *
   * @param myHero the hero of the player
   */
  public void setMyHero(ConcreteHero myHero) {
    this.myHero = myHero;
  }

  /**
   * Returns value of myManaMax
   *
   * @return myManaMax the current mana of a player
   */
  public int getMyManaMax() {
    return myManaMax;
  }

  /**
   * Sets new value of myManaMax
   *
   * @param myManaMax the current mana of a player
   */
  public void setMyManaMax(int myManaMax) {
    this.myManaMax = myManaMax;
  }

  /**
   * add a value to the current mana count
   *
   * @param moreMana how much we want to increase the players mana
   */
  public void addManaMax(int moreMana) {
    this.myManaMax += moreMana;
  }

  public int getMyMana() {
    return myMana;
  }

  public void setMyMana(int myMana) {
    this.myMana = myMana;
  }

  /**
   * Returns value of myGame
   *
   * @return myGame the game in which the player currently is
   */
  public Game getMyGame() {
    return myGame;
  }

  /**
   * Sets new value of myGame
   *
   * @param myGame the game in which the player currently is
   */
  public void setMyGame(Game myGame) {
    this.myGame = myGame;
  }

  /**
   * Returns value of myStock
   *
   * @return myStock a part of the player's draw pile
   */
  public ArrayList<Card> getMyStock() {
    return myStock;
  }

  /**
   * add a new card to a Player's stock
   *
   * @param newCard a card to add to the draw pile's stock
   */
  public void addCardToStock(Card newCard) {
    myStock.add(newCard);
  }

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
   *
   * @return this.opponent.
   */
  public Player getOpponent() {
    return this.opponent;
  }

  /**
   * Sets the new value of the opponent;
   *
   * @param opponent the new value;
   */
  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }

  /**
   * Increases the aura value.
   *
   * @param additionalAura the bonus to add.
   */
  public void addAura(int additionalAura) {
    this.myDamageAura += additionalAura;
  }

  /**
   * remove a minion from the game ie when it dies
   *
   * @param minion the minion to remove
   */
  void removeMinionFromPlay(ConcreteMinion minion) {

    myMinions.remove(minion);
  }

  /**
   * what happens when this player lose
   */
  void lost() {

    myGame.setGameOver(true);
    myGame.setLoser(this);
    myGame.setWinner(this.getOpponent());
  }

  /**
   * return an arrayList containing the list of the players minion that have taunt
   */
  public ArrayList<ConcreteMinion> findTauntMinions() {
    ArrayList<ConcreteMinion> tauntMinions = new ArrayList<>();
    for (ConcreteMinion minion : myMinions) {
      if (minion.isHasTaunt()) {
        tauntMinions.add(minion);
      }
    }
    return tauntMinions;
  }

  public boolean canPlayCard(Card card) {
    return this.getMyMana() > card.getRequiredMana();
  }

  /**
   * Return a card present in the players hand or that is on the board whose uniqueID matches the
   * param
   *
   * @param id the uniqueId of the card
   * @return a card whose uniqueId matches the argument
   */
  public Card findCardById(String id) {
    for (Card card : myHand) {
      if (card.getUniqueID().equals(id)) {
        return card;
      }
    }

    for (Card card : myMinions) {
      if (card.getUniqueID().equals(id)) {
        return card;
      }
    }
    return null;
  }

  /**
   * add some mana to the player current mana
   *
   * @param value the number of mana that should be added
   */
  public void changeMana(int value) {
    myMana += value;
  }

  public boolean isPassTurn() {
    return passTurn;
  }

  public void setPassTurn(boolean passTurn) {
    this.passTurn = passTurn;
  }

  public boolean containsMinion(String minionName) {
    for (ConcreteMinion minion : myMinions) {
      if (minion.getName().equals(minionName)) return true;
    }
    return false;
  }
}
