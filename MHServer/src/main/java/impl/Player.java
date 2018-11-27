package impl;

import abstracts.Card;
import abstracts.Minion;
import abstracts.Hero;
import identifiers.IdPlayer;

import java.util.ArrayList;

public class Player {

    private IdPlayer id;
    private Game myGame;
    private Hero myHero;
    private Player opponent;
    private ArrayList<Minion> myMinions;
    private ArrayList<Card> myCards;
    private int myMana;
    private ArrayList<Card> myStock;
    private int myAura; //used for spells that modifies the damage power of the minions

    /**
     * Returns value of myAura
     * @return the aura currently applied on the minion
     */
	public int getMyAura() {
		return myAura;
	}

    /**
     * Sets the new value of aura
     * @param aura
     */
	public void setMyAura(int aura) {
		this.myAura = aura;
	}

	/**
     * add a new card to the Player's hand
     */
    public void addCard(Card card) {
        myCards.add(card);
    }

    /**
     * Returns value of myCards
     * @return the list of the cards that are in the player's hand
     */
    public ArrayList<Card> getMyCards() {
    	return myCards;
    }

    /**
    * add a new minion to the Player's minions
    */
    public void addMinion(Minion minion) {
        myMinions.add(minion);
    }

    public ArrayList<Minion> getMyMinions() {
        return myMinions;
    }

  	/**
  	* Returns value of id
  	* @return id the id of the player
  	*/
  	public IdPlayer getId() {
  	    return id;
  	}

  	/**
  	* Sets new value of id
  	* @param id the id of the player
  	*/
  	public void setId(IdPlayer id) {
  	    this.id = id;
  	}

  	/**
  	* Returns value of myHero
  	* @return myHero the hero of the player
  	*/
  	public Hero getMyHero() {
  	    return myHero;
  	}

  	/**
  	* Sets new value of myHero
  	* @param myHero the hero of the player
  	*/
  	public void setMyHero(Hero myHero) {
  	    this.myHero = myHero;
  	}

  	/**
  	* Returns value of myMana
  	* @return myMana the current mana of a player
  	*/
  	public int getMyMana() {
  	    return myMana;
  	}

  	/**
  	* Sets new value of myMana
  	* @param myMana the current mana of a player
  	*/

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
  	* Default empty Player constructor
  	*/
  	public Player() {
  	    this.id = new IdPlayer(this);
  	}

  	/**
  	* Default Player constructor
  	*/
  	public Player(IdPlayer id, Hero myHero) {
        this.id = new IdPlayer(this);
  		this.myHero = myHero;
  		this.myMana = 0;
  	}

    /**
     * allow a player to draw a new card
     */
    public void draw() {

        Card newCard;
        newCard = this.myStock.get(0);
        myCards.add(newCard);
        myStock.remove(0);

        refillStock();
    }

    /**
     * refill the draw pile stock after it goes below 2 cards
     * @ TODO: 26/11/18  write this method
     */
    private void refillStock() {

        if(myStock.size()< 3) {
            //...
        }

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
}
