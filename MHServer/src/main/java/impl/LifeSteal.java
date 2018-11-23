package impl;

import inter.MinionAction;
import inter.Target;

public class LifeSteal implements MinionAction {

    private Minion myMinion;

    public LifeSteal(Minion _myMinion) {
        myMinion = _myMinion;
    }

    public void effect(Target myTarget) {
        myMinion.getHero().heal(
                myTarget.takeDamage( myMinion.getDamagePoints()) );
    }
}
