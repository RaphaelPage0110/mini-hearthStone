package impl.behaviour.generic.targetedEffect;

import abstracts.TargetedEffect;
import inter.Target;

/**
 * Class representing the special action "DamageTarget" used for the "Fireball" (fr:Boule de feu)
 * spell. Deal 1 damage.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageTarget extends TargetedEffect {

  private int damage;

  public DamageTarget(int damage) {
    this.damage = damage;
  }

  @Override
  public void effect(Target myTarget) {
    myTarget.takeDamage(damage);
  }
}
