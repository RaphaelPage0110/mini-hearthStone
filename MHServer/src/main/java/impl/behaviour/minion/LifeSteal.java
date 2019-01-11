package impl.behaviour.minion;

import impl.ConcreteMinion;
import inter.NotTargetedEffect;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "life steal" used for the "Chillblade Champion" (fr:Champion frisselame) minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class LifeSteal extends NotTargetedEffect {

    private ConcreteMinion myMinion;

    public LifeSteal(@NotNull ConcreteMinion myMinion) {
        this.myMinion = myMinion;
        myMinion.setHasLifesteal(true);
    }

    @Override
    public void effect() {
    }

    public ConcreteMinion getMyMinion() {
        return myMinion;
    }
}
