package impl.behaviour.minion;

import abstracts.Minion;
import inter.NotTargetedEffect;

/**
 * Class representing the special action "life steal" used for the "Chillblade Champion" (fr:Champion frisselame) minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class LifeSteal extends NotTargetedEffect {

    private Minion myMinion;

    public LifeSteal(Minion myMinion) {
        this.myMinion = myMinion;
        myMinion.setHasLifesteal(true);
    }

    @Override
    public void effect() {
    }

}
