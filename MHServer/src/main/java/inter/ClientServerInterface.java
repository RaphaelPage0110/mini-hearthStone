package inter;

import impl.Game;

public interface ClientServerInterface {

    public abstract void playCard(Game game);

    public abstract void prepareAttack(Game game);

    public abstract void useHeroPower(Game game);

    public abstract void endTurn(Game game);

    public abstract void surrender(Game game);

    public abstract void createGame(Game game);

}
