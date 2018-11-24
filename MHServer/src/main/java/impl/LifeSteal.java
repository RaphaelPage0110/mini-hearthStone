package impl;

import inter.CardAction;
import inter.Target;
import abstracts.Minion;

public class LifeSteal implements CardAction {

    private Minion myMinion;

    public LifeSteal(Minion _myMinion) {
        myMinion = _myMinion;
    }

    public void effect(Target myTarget) {
        myMinion.getPlayer().getMyHero().heal(
                myTarget.takeDamage( myMinion.getDamagePoints()) );
    }
}
