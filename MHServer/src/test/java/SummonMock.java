import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import impl.behaviour.generic.notTargetedEffect.Summon;
import inter.Effect;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SummonMock extends Summon {

    @Override
    public String getMyMinionKeyword() {
        return myMinionKeyword;
    }

    private String myMinionKeyword;
    private int numberSummoned;
    private ConcreteHero hero;

    public SummonMock(@NotNull String minionKeyword, ConcreteHero concreteHero) {
        List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
        this.myMinionKeyword = minionKeywords.get(0);
        this.numberSummoned = Integer.parseInt(minionKeywords.get(1));
        this.hero = concreteHero;
    }

    @Override
    public void effect() {
        EntitiesFactory entitiesFactory = new EntitiesFactory();

        for (int i = 0; i < numberSummoned; i++) {

            ConcreteMinion minionToSummon = entitiesFactory.createMinion(myMinionKeyword);
            minionToSummon.setPlayer(hero.getMyPlayer());
            hero.getMyPlayer().addMinion(minionToSummon);

            for (Effect effect : minionToSummon.getMyEffects() ) {
                effect.effect();
            }
        }
    }
}
