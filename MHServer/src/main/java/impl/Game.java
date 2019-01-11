package impl;

public class Game {

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

      this.turn = 1;
      gameOver = false;
      this.player1 = player1;
      this.player2 = player2;
      this.player1.setPlayOrder(0);
      this.player2.setPlayOrder(0);
      passTurn = true;
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

	/**
	 * get the winner of the game
	 * @return
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * set the winner of the game
	 * @param winner
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}

	/**
	 * get the loser of the game
	 * @return
	 */
	public Player getLoser() {
		return loser;
	}

	/**
	 * set the loser of the game
	 * @param loser
	 */
	public void setLoser(Player loser) {
		this.loser = loser;
	}

	/**
	 *	return if the game is over
	 * @return
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * set the statue of the game (game over or not)
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	/**
	 * allows two users to create their profile and links them to the game
	 * TODO : A completer quand on saura comment communiquer avec les clients
	 */
	public void instanciatePlayers() {
	}

	/**
	 * get the number of turn since the begining of the game
	 * @return
	 */
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

	/**
	 * retunr the player corresponding of the id (player1 or player2)
	 * @param id
	 * @return
	 */
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
