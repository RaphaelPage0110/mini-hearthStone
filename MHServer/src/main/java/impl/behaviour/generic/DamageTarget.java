package impl.behaviour.generic;

import abstracts.Hero;
import inter.Effect;
import inter.Target;
import abstracts.Spell;

/**
 * Class representing the special action "DamageTarget" used for the "Fireball" (fr:Boule de feu) spell.
 * Deal 1 damage.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageTarget implements Effect {

    private Spell mySpell;
    private Hero myHero;
    private int damage;

    public DamageTarget(Spell mySpell) {
        this.mySpell = mySpell;
        this.damage = mySpell.getDamagePoints();
    }

    public DamageTarget(Hero myHero, int damage) {
        this.myHero = myHero;
        this.damage = damage;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(damage);
    }
}
