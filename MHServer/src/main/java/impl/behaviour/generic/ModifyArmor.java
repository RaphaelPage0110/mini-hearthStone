package impl.behaviour.generic;

import abstracts.Hero;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Gain 5 armor.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor implements CardAction {


    private Spell mySpell;
    private int armorModifier;

    public ModifyArmor(Spell mySpell, int armorPoints) {
        this.mySpell = mySpell;
        this.armorModifier = armorPoints;
    }

    public void effect(Target myTarget) {

        Hero hero;
        hero = (Hero)myTarget;
        hero.addArmor(armorModifier);

    }
}
