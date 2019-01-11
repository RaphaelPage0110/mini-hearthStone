package inter;

public interface ClientServerInterface {

      /**
       * Creates a Player, assigns the chosen hero to the Player and puts it in the waiting queue.
       * @param sessionId
       * @param heroname
       */
      void connectToGame(String sessionId, String heroname);
      void disconnectFromGame(String sessionId);


}
