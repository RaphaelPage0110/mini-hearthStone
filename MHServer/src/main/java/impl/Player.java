package impl;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Minion;
import abstracts.Hero;
import com.sun.javaws.exceptions.InvalidArgumentException;
import identifiers.IdPlayer;

import java.util.ArrayList;

public class Player {

    private Integer id;
    private Game myGame;
    private ConcreteHero myHero;
    private boolean canUseHeroAbility;
    private Player opponent;
    private ArrayList<Minion> myMinions;
    private ArrayList<Card> myCards;
    private int myManaMax;
	private int myMana;
    private ArrayList<Card> myStock = new ArrayList<>();
    private int myDamageAura; //used for spells that modifies the damage power of the minions

	private int playOrder; //used to know if the player is playing first or second

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
        myGame.addMinionInPlay(minion);
    }

    public ArrayList<Minion> getMyMinions() {
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
  	public Hero getMyHero() {
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

	public int getPlayOrder() {
		return playOrder;
	}

	public void setPlayOrder(int playOrder) {
		this.playOrder = playOrder;
	}

  	/**
  	* Default empty Player constructor
  	*/
  	public Player() {
  	    this.id = new IdPlayer(this).getId();
		this.myManaMax = 0;
  	    this.myDamageAura = 0;
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
     * refill randomly the draw pile stock after it goes below 5 cards
     * @ TODO: 26/11/18  write this method
     */

    public void refillStock() {


		//CardType typeCommon = CardType.COMMON;
		//ConcreteMinion ok = minionRepository.findByName("Sanglier Brocheroc");


        /*if(myStock.size()< 5) {

			CardType typeCommon = CardType.COMMON;
			CardType cardType;

			switch (myHero.getHeroType()){
				case MAGE:
					cardType = CardType.MAGE;
				case PALADIN:
					cardType = CardType.PALADIN;
				case WARRIOR:
					cardType =CardType.WARRIOR;
				default:
					cardType = null;
			}

			ArrayList<ConcreteMinion> listMinionsCommon =  minionRepository.findByType(typeCommon);/*

			System.out.println("testFonction");
			for(ConcreteMinion minion : listMinionsCommon ) {
				System.out.println(minion);
			}

			ArrayList<ConcreteMinion> listMinionsLimited = minionRepository.findByType(cardType);
			//this arraylist contains all the minions this player can have
			ArrayList<ConcreteMinion> listMinions = new ArrayList<>(listMinionsCommon);
			listMinions.addAll(listMinionsLimited);

			List<ConcreteSpell> listSpellsCommon =  spellRepository.findByType(typeCommon);
			List<ConcreteSpell> listSpellsLimited =  spellRepository.findByType(cardType);
			//this arraylist contains all the spells this player can have
			ArrayList<ConcreteSpell> listSpells = new ArrayList<>(listSpellsCommon);
			listSpells.addAll(listSpellsLimited);

			//we first have to randomly decide if we have to pick a spell or a minion.


			while(myStock.size()< 5) {

			int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
				switch (randomNum){
					//if randomNum is 0 we will pick a spell
					case 0:
						//we then randomly pick a spell from all the spells the player can pick from
						randomNum = ThreadLocalRandom.current().nextInt(0, listSpells.size());
						ConcreteSpell spellPicked = listSpells.get(randomNum);
						myStock.addCardToStock(spellPicked);

						//if it's 1 we will pick a minion
					case 1:

						randomNum = ThreadLocalRandom.current().nextInt(0, listMinions.size());
						ConcreteMinion minionPicked = listMinions.get(randomNum);
						myStock.addCardToStock(minionPicked);

				}
			}
        }*/
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
}
