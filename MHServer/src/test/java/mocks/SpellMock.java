package mocks;

import impl.ConcreteSpell;
import impl.behaviour.generic.notTargetedEffect.ModifyArmor;

import java.util.Map;

import static abstracts.ConstsUtils.*;

public class SpellMock extends ConcreteSpell {

  public SpellMock(ConcreteSpell concreteSpell) {
    super(
            concreteSpell.getType(),
            concreteSpell.getRequiredMana(),
            concreteSpell.getBonus(),
            concreteSpell.getAbilityKeyWord(),
            concreteSpell.getName(),
            concreteSpell.getImgurl(),
            concreteSpell.getText());
    setPlayer(concreteSpell.getPlayer());
    concreteSpell.getPlayer().addCardToHand(this);
  }

  @Override
  public void generateSpellEffect(Map<String, String> abilityKeyWord) {
    for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

      switch (entry.getKey()) {
        case SUMMON_ABILITY:
          SummonMock abilitySummon = new SummonMock(entry.getValue(), this);
          this.getMyEffects().add(abilitySummon);
          break;
        case TRANSFORM_INTO_ABILITY:
          TransformIntoMock abilityTransform = new TransformIntoMock(entry.getValue());
          this.getMyEffects().add(abilityTransform);
          break;
        case MODIFY_ARMOR_ABILITY:
          ModifyArmor abilityModifyArmor =
                  new ModifyArmor(this, Integer.parseInt(entry.getValue()));
          this.getMyEffects().add(abilityModifyArmor);
          break;
        case DRAW_CARD_ABILITY:
          DrawCardMock abilityDraw =
                  new DrawCardMock(this, Integer.parseInt(entry.getValue()));
          this.getMyEffects().add(abilityDraw);
          break;
        default:
          break;
      }
    }
  }
}
