package impl;

import inter.*;

public class Charge implements MinionAction {

    private Minion myMinion;

    public Charge(Minion _myMinion) {
        myMinion = _myMinion;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(myMinion.getDamagePoints());
    }
}
