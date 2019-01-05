package impl.behaviour.minion;

import inter.Effect;
import inter.Target;
import abstracts.Minion;
import inter.TargetedEffect;

/**
 * Class representing the special action "life steal" used for the "Chillblade Champion" (fr:Champion frisselame) minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class LifeSteal extends TargetedEffect {

    private Minion myMinion;

    public LifeSteal(Minion myMinion) {
        this.myMinion = myMinion;
        myMinion.setHasLifesteal(true);
    }

    @Override
    public void effect(Target myTarget) {
        myMinion.getPlayer().getMyHero().heal(
                myTarget.takeDamage( myMinion.getDamagePoints()) );
    }

}
