package impl.behaviour.minion;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "life steal" used for the "Chillblade Champion"
 * (fr:Champion frisselame) minion.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class LifeSteal extends NotTargetedEffect {

  public LifeSteal(@NotNull ConcreteMinion myMinion) {
    myMinion.setHasLifesteal(true);
  }

  @Override
  public void effect() {
  }
}
