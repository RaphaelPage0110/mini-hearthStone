package impl.behaviour.generic;

import abstracts.Hero;
import abstracts.Spell;
import inter.Effect;
import inter.Target;
import inter.TargetedEffect;

/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Gain 5 armor.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor extends TargetedEffect {


    private Spell mySpell;
    private Hero myHero;
    private int bonus;

    public ModifyArmor(Spell mySpell) {
        this.mySpell = mySpell;
        this.bonus = mySpell.getBonus();
    }
    public ModifyArmor(Hero myHero, int bonus) {
        this.myHero = myHero;
        this.bonus = bonus;
    }

    @Override
    public void effect(Target myTarget) {

        Hero hero;
        hero = (Hero)myTarget;
        hero.addArmor(bonus);

    }
}
