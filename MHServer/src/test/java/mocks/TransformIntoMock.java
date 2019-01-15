package mocks;

import abstracts.Effect;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import impl.behaviour.generic.targetedEffect.TransformInto;
import inter.Target;

import java.util.ArrayList;

public class TransformIntoMock extends TransformInto {

  private String transformationName;

  public TransformIntoMock(String transformationName) {
    this.transformationName = transformationName;
  }

  @Override
  public void effect(Target target) {
    effect((ConcreteMinion) target);
  }

  private void effect(ConcreteMinion target) {
    EntitiesFactory entitiesFactory = new EntitiesFactory();
    ConcreteMinion transformation = entitiesFactory.createMinion(transformationName);

    if (!target.getMyDeathRattles().isEmpty()) {
      for (Effect targetEffect : target.getMyDeathRattles()) {
        targetEffect.effect();
      }
    }

    target.setHasTaunt(false);
    target.setHasLifesteal(false);
    target.setCanAttack(false);
    target.setName(transformation.getName());
    target.setRequiredMana(transformation.getRequiredMana());
    target.setDamagePoints(transformation.getDamagePoints());
    target.setMaxHealthPoints(transformation.getMaxHealthPoints());
    target.setCurrentHealthPoints(transformation.getCurrentHealthPoints());
    target.setType(transformation.getType());
    target.setText(transformation.getText());
    target.setImgurl(transformation.getImgurl());
    target.setAbilityKeyWord(transformation.getAbilityKeyWord());
    target.setDeathRattleKeyWords(transformation.getDeathRattleKeyWords());
    target.setMyEffects(new ArrayList<Effect>());
    target.setMyDeathRattles(new ArrayList<Effect>());
    target.generateEffect();

    for (Effect transfoEffect : target.getMyEffects()) {
      transfoEffect.effect();
    }
  }
}
