package impl;

import abstracts.Minion;
import identifiers.IdGame;
import java.util.ArrayList;

public class Game {

    private Integer id;
    private ArrayList<Minion> minionsInPlay;
    private Player player1;
    private Player player2;
    private Player winner;
    private Player loser;
    private boolean gameOver;
    private int turn;

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public boolean isPassTurn() {
		return passTurn;
	}

	public void setPassTurn(boolean passTurn) {
		this.passTurn = passTurn;
	}

	private boolean passTurn; //used to know if the player has passed his turn

    public Game(Player player1, Player player2) {
      this.id = new IdGame(this).getId();
      this.minionsInPlay = new ArrayList<>();
      this.turn = 1;
      gameOver = false;
      this.player1 = player1;
      this.player2 = player2;
      this.player1.setPlayOrder(0);
      this.player2.setPlayOrder(0);
      passTurn = true;
    }

  	/**
  	* Returns value of id
  	* @return
  	*/
  	public Integer getId() {
  		return id;
  	}

  	/**
  	* Sets new value of id
  	* @param
  	*/
  	public void setId(Integer id) {
  		this.id = id;
  	}

  	/**
  	* Returns value of minionsInPlay
  	* @return
  	*/
  	public ArrayList<Minion> getMinionsInPlay() {
  		return minionsInPlay;
  	}

  	/**
  	* Sets new value of minionsInPlay
  	* @param
  	*/
  	public void setMinionsInPlay(ArrayList<Minion> newMinion) {
  		this.minionsInPlay = newMinion;
  	}

  	public void addMinionInPlay(Minion minion) {
  		minionsInPlay.add(minion);
	}

  	/**
  	* Returns value of player1
  	* @return
  	*/
  	public Player getPlayer1() {
  		return player1;
  	}

  	/**
  	* Sets new value of player1
  	* @param
  	*/
  	public void setPlayer1(Player player1) {
  		this.player1 = player1;
  	}

  	/**
  	* Returns value of player2
  	* @return
  	*/
  	public Player getPlayer2() {
  		return player2;
  	}

  	/**
  	* Sets new value of player2
  	* @param
  	*/
  	public void setPlayer2(Player player2) {
  		this.player2 = player2;
  	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getLoser() {
		return loser;
	}

	public void setLoser(Player loser) {
		this.loser = loser;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean getGameOver() {
  		return this.gameOver;
	}

	/**
	 * allows a minion from the game. i.e when it dies
	 * @param minion the minion to remove
	 */
	public void removeMinionFromGame(Minion minion){
		minionsInPlay.remove(minion);
	}

	/**
	 * allows two users to create their profile and links them to the game
	 * TODO : A completer quand on saura comment communiquer avec les clients
	 */
	public void instanciatePlayers() {
	}

    public int getTurn() {
        return turn;
    }

    /**
     * used to increment the number of the turn
     */
    public void incrementTurn() {

        ++turn;
    }


	/**
	 * allows to determine who is the active player
	 * @return player
	 */
	public Player getActivePlayer() {

		int turnPlayer1 = this.player1.getPlayOrder();
		int turnPlayer2 = this.player2.getPlayOrder();
		if (turnPlayer1 == turnPlayer2) {
			return player2;
		} else {
			return player1;
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
		return this;
	}

	public Player getPlayerByID(String id){
		if (player1.getSessionId().equals(id)){
			return player1;
		}
		else if (player2.getSessionId().equals(id)){
			return player2;
		}
		else return null;
	}

}
