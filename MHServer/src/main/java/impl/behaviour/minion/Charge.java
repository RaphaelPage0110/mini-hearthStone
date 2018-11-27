package impl.behaviour.minion;

import inter.Target;
import inter.Effect;
import abstracts.Minion;

/**
 * Class representing the special action "charge" used for the "Wolf Rider" (fr:Chevaucheur de loup) minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Charge implements Effect {

    private Minion myMinion;

    public Charge(Minion myMinion) {
        this.myMinion = myMinion;
    }

    public void effect(Target myTarget) {
        myMinion.setCanAttack(true);
    }
}
