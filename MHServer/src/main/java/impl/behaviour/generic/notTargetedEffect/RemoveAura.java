package impl.behaviour.generic.notTargetedEffect;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import impl.Player;

/**
 * Class representing the special action "RemoveAura" used for the death rattles of the "Raid
 * Leader" (fr:Chef de raid) minion. Your other minions loose the raid leader aura when he dies.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 */
public class RemoveAura extends NotTargetedEffect {

  private ConcreteMinion myMinion;

  public RemoveAura(ConcreteMinion minion) {

    this.myMinion = minion;
  }

  @Override
  public void effect() {

    Player player = myMinion.getPlayer();
    int aura = myMinion.getBonus();

    player.addAura(-aura);
  }
}
