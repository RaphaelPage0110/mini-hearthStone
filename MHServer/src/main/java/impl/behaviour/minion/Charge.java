package impl.behaviour.minion;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "charge" used for the "Wolf Rider" (fr:Chevaucheur de loup)
 * minion.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Charge extends NotTargetedEffect {

  public Charge(@NotNull ConcreteMinion myMinion) {
    myMinion.setCanAttack(true);
  }

  @Override
  public void effect() {
  }
}
