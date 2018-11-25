package impl;

import abstracts.Card;
import abstracts.Minion;
import abstracts.Hero;
import identifiers.IdPlayer;

import java.util.ArrayList;

public class Player {

    private IdPlayer id;
    private IdPlayer idOpponent;
    private Hero myHero;
    private ArrayList<Minion> myMinions;
    private ArrayList<Card> myCards;
    private int myMana;

    /**
    * add a new card to the Player's hand
    */
    public void addCard(Card _card) {
        myCards.add(_card);
    }

    public ArrayList<Card> getMyCards() {
    	return myCards;
    }

    /**
    * add a new minion to the Player's minions
    */
    public void addMinion(Minion _minion) {
        myMinions.add(_minion);
    }

    public ArrayList<Minion> getMyMinions() {
        return myMinions;
    }

  	/**
  	* Returns value of id
  	* @return
  	*/
  	public IdPlayer getId() {
  	    return id;
  	}

  	/**
  	* Sets new value of id
  	* @param
  	*/
  	public void setId(IdPlayer id) {
  	    this.id = id;
  	}

  	/**
  	* Returns value of myHero
  	* @return
  	*/
  	public Hero getMyHero() {
  	    return myHero;
  	}

  	/**
  	* Sets new value of myHero
  	* @param
  	*/
  	public void setMyHero(Hero myHero) {
  	    this.myHero = myHero;
  	}

  	/**
  	* Returns value of myMana
  	* @return
  	*/
  	public int getMyMana() {
  	    return myMana;
  	}

  	/**
  	* Sets new value of myMana
  	* @param
  	*/
  	public void setMyMana(int myMana) {
  	    this.myMana = myMana;
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
  		this.id = id;
  		this.myHero = myHero;
  		this.myMana = 0;
	}
}
