package impl.behaviour.generic.targetedEffect;

import abstracts.TargetedEffect;
import impl.ConcreteMinion;
import inter.Target;

/**
 * Class representing the special action "ModifyDamagePointsOneMinion" used for the "Blessing of
 * might" (fr:Bénédiction de puissance) spell. Give a minion +3 attack.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyDamagePointsOneMinion extends TargetedEffect {
  private int myDamage;

  public ModifyDamagePointsOneMinion(int damage) {
    this.myDamage = damage;
  }

  @Override
  public void effect(Target myTarget) {
    ConcreteMinion targetedMinion = (ConcreteMinion) myTarget;
    targetedMinion.addDamagePoints(myDamage);
  }
}
