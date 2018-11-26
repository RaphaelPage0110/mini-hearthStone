package impl.behaviour.generic;

import inter.CardAction;
import inter.Target;
import abstracts.Spell;
import inter.CardAction;

/**
 * Class representing the special action "DamageTarget" used for the "Fireball" (fr:Boule de feu) spell.
 * Deal 1 damage.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageTarget implements CardAction {

    private Spell mySpell;
    private Target myTarget;

    public DamageTarget(Spell mySpell, Target myTarget) {
        this.mySpell = mySpell;
        this.myTarget = myTarget;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(mySpell.getDamagePoints());
    }
}
