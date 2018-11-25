package identifiers;

import impl.Game;

public class IdGame extends Id {
    private Game game;

    public IdGame(Game game) {
        super();
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
