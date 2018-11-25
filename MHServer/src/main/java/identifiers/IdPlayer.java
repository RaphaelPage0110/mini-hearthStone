package identifiers;

import impl.Player;

public class IdPlayer extends Id {
    private Player player;

    public IdPlayer(Player player) {
        super();
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
