package inter;

public interface ClientServerInterface {

      /**
       * Creates a Player, assigns the chosen hero to the Player and puts it in the waiting queue.
       * @param sessionId the id of the player
       * @param heroname the name of the hero the user wants
       */
      void connectToGame(String sessionId, String heroname);

      /**
       * Disconnect from the server. If the player was in a game, he is set as the loser
       * @param sessionId the id of the player
       */
      void disconnectFromGame(String sessionId);

      /**
       * Allows a user to pass its turn
       */
      void passTurn(String sessionId);

      /**
       * Allows a player to forfeit
       * @param sessionId the id of the player
       */
      void gameOver(String sessionId);

      /**
       * Allows a player to play a minion card
       * @param sessionID the id of the player
       * @param minionID the uniqueID of the minion
       */
      void playMinion(String sessionID, String minionID);

      /**
       * Allows a player to play a spell card that doesn't require a target
       * @param sessionID the id of the player
       * @param idSpell the uniqueID of the spell
       */
      void playSpell(String sessionID, String idSpell);

      /**
       * Allows a player to play a spell card requiring a target
       * @param sessionID the id of the player
       * @param message contains a JSON string of this form {"spellID":"121","targetID":"1212"}
       */
      void castSpellOnThisMinion(String sessionID, String message);

      /**
       * Send to the user a list of all the minions his minions can attack
       * @param playerID the id of the player
       */
      void showPossibleTargetsForMinion(String playerID);

      /**
       * Allows a minion to attack another minion
       * @param playerID the id of the player
       * @param message contains a JSON string of this form {"attackerID":"121","targetID":"1212"}
       */
      void attackThisMinion(String playerID, String message);

      /**
       * Allows a minion to attack the opponent's hero
       * @param playerID the id of the player
       * @param attackerID the uniqueID of the minion that's attacking
       */
      void attackHero(String playerID, String attackerID);

      /**
       * Allows a player to use the ability of its hero that doesn't require a target
       * @param playerID the id of the player
       */
      void useHeroPower(String playerID);

      /**
       * Allows a player to use the ability of its hero that requires a target
       * @param playerID the id of the player
       * @param targetID the if of its target
       */
      void useHeroPowerOnTarget(String playerID, String targetID);

}
