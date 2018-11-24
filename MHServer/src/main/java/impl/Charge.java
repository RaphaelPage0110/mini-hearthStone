package impl;

import inter.Target;
import inter.CardAction;
import abstracts.Minion;

public class Charge implements CardAction {

    private Minion myMinion;

    public Charge(Minion _myMinion) {
        myMinion = _myMinion;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(myMinion.getDamagePoints());
    }
}
