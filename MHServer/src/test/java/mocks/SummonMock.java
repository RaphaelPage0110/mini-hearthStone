package mocks;

import abstracts.Effect;
import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import impl.EntitiesFactory;
import impl.behaviour.generic.notTargetedEffect.Summon;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SummonMock extends Summon {

  private String myMinionKeyword;
  private int numberSummoned;
  private ConcreteHero hero;
  private ConcreteSpell spell;

  public SummonMock(@NotNull String minionKeyword, ConcreteHero concreteHero) {
    List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
    this.myMinionKeyword = minionKeywords.get(0);
    this.numberSummoned = Integer.parseInt(minionKeywords.get(1));
    this.hero = concreteHero;
  }

  public SummonMock(@NotNull String minionKeyword, ConcreteSpell concreteSpell) {
    List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
    this.myMinionKeyword = minionKeywords.get(0);
    this.numberSummoned = Integer.parseInt(minionKeywords.get(1));
    this.spell = concreteSpell;
  }

  @Override
  public String getMyMinionKeyword() {
    return myMinionKeyword;
  }

  @Override
  public void effect() {
    EntitiesFactory entitiesFactory = new EntitiesFactory();

    for (int i = 0; i < numberSummoned; i++) {

      ConcreteMinion minionToSummon = entitiesFactory.createMinion(myMinionKeyword);
      if (hero != null) {
        minionToSummon.setPlayer(hero.getMyPlayer());
        hero.getMyPlayer().addMinion(minionToSummon);
      } else {
        minionToSummon.setPlayer(spell.getPlayer());
        spell.getPlayer().addMinion(minionToSummon);
      }

      for (Effect effect : minionToSummon.getMyEffects()) {
        effect.effect();
      }
    }
  }

  public int getNumberSummoned() {
    return numberSummoned;
  }
}
