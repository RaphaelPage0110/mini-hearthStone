package impl;

public class Game {

  private Player player1;
  private Player player2;
  private Player winner;
  private Player loser;
  private boolean gameOver;
  private int turn;
  private boolean passTurn; // used to know if the player has passed his turn

  public Game(Player player1, Player player2) {

    this.turn = 1;
    gameOver = false;
    this.player1 = player1;
    this.player2 = player2;
    this.player1.setPlayOrder(0);
    this.player2.setPlayOrder(0);
    passTurn = true;
  }

  public boolean isPassTurn() {
    return passTurn;
  }

  public void setPassTurn(boolean passTurn) {
    this.passTurn = passTurn;
  }

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

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

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  /**
   * used to increment the number of the turn
   */
  public void incrementTurn() {

    ++turn;
  }

  /**
   * allows to determine who is the active player
   *
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
   *
   * @return player
   */
  public Player getWaitingPlayer() {
    return getActivePlayer().getOpponent();
  }

  public Game getGame() {
    return this;
  }

  /**
   * return the player corresponding of the id (player1 or player2)
   *
   * @param id the sessionId of the player
   * @return a player whose sessionId id matches the parameter
   */
  public Player getPlayerByID(String id) {
    if (player1.getSessionId().equals(id)) {
      return player1;
    } else if (player2.getSessionId().equals(id)) {
      return player2;
    } else return null;
  }
}
