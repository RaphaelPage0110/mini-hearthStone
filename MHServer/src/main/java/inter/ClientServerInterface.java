package inter;

import impl.Game;

public interface ClientServerInterface {

      void playCard(Game game);

      void prepareAttack(Game game);

      void useHeroPower(Game game);

      void endTurn(Game game);

      void surrender(Game game);

      void createGame(Game game);

}
