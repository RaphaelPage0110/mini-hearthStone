package impl.behaviour.generic.notTargetedEffect;

import abstracts.Hero;
import abstracts.Spell;
import inter.NotTargetedEffect;


/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Gain 5 armor.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor extends NotTargetedEffect {


    private Spell mySpell;
    private Hero myHero;
    private int bonus;

    public ModifyArmor(Spell mySpell, int bonus) {
        this.mySpell = mySpell;
        this.bonus = bonus;
    }
    public ModifyArmor(Hero myHero, int bonus) {
        this.myHero = myHero;
        this.bonus = bonus;
    }

    @Override
    public void effect() {

        myHero.addArmor(bonus);

    }

    public Spell getMySpell() {
        return mySpell;
    }
}
