package impl;

import abstracts.Minion;
import abstracts.Hero;
import java.util.ArrayList;

public class Player {

    private IdPlayer id;
    private Hero myHero;
    private <Minion> myMinions;
    private int myMana;

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
		super();
	}

	/**
	* Default Player constructor
	*/
	public Player(IdPlayer id, Hero myHero, int myMana) {
		super();
		this.id = id;
		this.myHero = myHero;
		this.myMana = myMana;
	}

  /**
  * add a new minion to the Player's hand
  */
  public addMinionToHand(Minion mewMinion) {
    myMinions.add(newMinion);
  }

}
