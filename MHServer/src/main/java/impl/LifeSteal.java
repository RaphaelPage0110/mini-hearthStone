package impl;

import inter.CardAction;
import inter.Target;
import abstracts.Minion;

/**
 * Class representing the special action "life steal".
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class LifeSteal implements CardAction {

    private Minion myMinion;

    public LifeSteal(Minion myMinion) {
        this.myMinion = myMinion;
    }

    public void effect(Target myTarget) {
        myMinion.getPlayer().getMyHero().heal(
                myTarget.takeDamage( myMinion.getDamagePoints()) );
    }
}
