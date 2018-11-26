package impl;

import abstracts.Minion;
import inter.CardAction;
import inter.Target;

/**
 * Class representing the special action "Taunt".
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Taunt implements CardAction {

    private Minion myMinion;

    public Taunt(Minion myMinion) {
        this.myMinion = myMinion;
    }

    @Override
    public void effect(Target myTarget) {
        myMinion.setHasTaunt(true);
    }
}
