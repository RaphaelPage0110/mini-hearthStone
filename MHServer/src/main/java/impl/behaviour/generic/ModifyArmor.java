package impl.behaviour.generic;

import abstracts.Hero;
import abstracts.Spell;
import inter.Effect;
import inter.Target;

/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Gain 5 armor.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor implements Effect {


    private Spell mySpell;

    public ModifyArmor(Spell mySpell) {
        this.mySpell = mySpell;
    }

    public void effect(Target myTarget) {

        Hero hero;
        hero = (Hero)myTarget;
        hero.addArmor(mySpell.getBonus());

    }
}
