package impl.behaviour.generic.notTargetedEffect;

import impl.ConcreteHero;
import impl.ConcreteSpell;
import inter.NotTargetedEffect;
import org.jetbrains.annotations.NotNull;


/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Gain 5 armor.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor extends NotTargetedEffect {


    private ConcreteHero myHero;
    private int bonus;

    public ModifyArmor(@NotNull ConcreteSpell mySpell, int bonus) {
        this.myHero = mySpell.getPlayer().getMyHero();
        this.bonus = bonus;
    }

    public ModifyArmor(ConcreteHero myHero, int bonus) {
        this.myHero = myHero;
        this.bonus = bonus;
    }

    @Override
    public void effect() {

        myHero.addArmor(bonus);

    }
}
