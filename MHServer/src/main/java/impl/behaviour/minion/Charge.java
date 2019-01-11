package impl.behaviour.minion;

import impl.ConcreteMinion;
import inter.NotTargetedEffect;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "charge" used for the "Wolf Rider" (fr:Chevaucheur de loup) minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Charge extends NotTargetedEffect {

    private ConcreteMinion myMinion;

    public Charge(@NotNull ConcreteMinion myMinion) {
        this.myMinion = myMinion;
        myMinion.setCanAttack(true);
    }

    @Override
    public void effect() {

    }

    public ConcreteMinion getMyMinion() {
        return myMinion;
    }
}
