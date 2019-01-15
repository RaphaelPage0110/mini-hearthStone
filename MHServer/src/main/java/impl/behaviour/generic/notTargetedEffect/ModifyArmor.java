package impl.behaviour.generic.notTargetedEffect;

import abstracts.NotTargetedEffect;
import impl.ConcreteHero;
import impl.ConcreteSpell;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "ModifyArmor" used for the "Shield Block" (fr:Maîtrise du
 * blocage) spell. Gain 5 armor.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyArmor extends NotTargetedEffect {

  private ConcreteHero hero;
  private ConcreteSpell spell;
  private int bonus;

  public ModifyArmor(@NotNull ConcreteSpell mySpell, int bonus) {
    this.spell = mySpell;
    this.bonus = bonus;
  }

  public ModifyArmor(ConcreteHero myHero, int bonus) {
    this.hero = myHero;
    this.bonus = bonus;
  }

  @Override
  public void effect() {

    if (hero == null){
      hero = spell.getPlayer().getMyHero();
    }
    hero.addArmor(bonus);
  }
}
