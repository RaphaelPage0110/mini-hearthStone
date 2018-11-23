package impl;

import identifiers.IdMinion;

public class Minion {
    private IdMinion id;

    public Minion() {
        this.id = new IdMinion(this);
    }
}
