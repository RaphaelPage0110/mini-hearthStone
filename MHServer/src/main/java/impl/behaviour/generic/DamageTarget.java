package impl.behaviour.generic;

import abstracts.Card;
import abstracts.Hero;
import inter.Effect;
import inter.Target;
import abstracts.Spell;
import inter.TargetedEffect;

/**
 * Class representing the special action "DamageTarget" used for the "Fireball" (fr:Boule de feu) spell.
 * Deal 1 damage.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageTarget extends TargetedEffect {

    private Card myCard;
    private Hero myHero;
    private int damage;

    public DamageTarget(Card myCard, int damage) {
        this.myCard = myCard;
        this.damage = damage;
    }

    public DamageTarget(Hero myHero, int damage) {
        this.myHero = myHero;
        this.damage = damage;
    }

    @Override
    public void effect(Target myTarget) {
        myTarget.takeDamage(damage);
    }
}
