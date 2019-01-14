import impl.ConcreteHero;

import java.util.Map;

import static abstracts.Consts.SUMMON_ABILITY;

public class HeroMock extends ConcreteHero {

    public HeroMock(ConcreteHero concreteHero) {
        super(concreteHero.getHeroType(),
                concreteHero.getMaxHealthPoints(),
                concreteHero.getArmorPoints(),
                concreteHero.getAbilityKeyWord(),
                concreteHero.getHeroName(),
                concreteHero.getImgurl());

        setMyPlayer(concreteHero.getMyPlayer());
    }

    @Override
    public void dies() {}


    @Override
    public void generateEffect(Map<String,String> abilityKeyWord) {
        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

            switch (entry.getKey()) {

                case SUMMON_ABILITY:
                    SummonMock abilitySummon = new SummonMock(entry.getValue(), this);
                    this.setMyEffect(abilitySummon);
                    break;
                default:
                    break;
            }
        }
    }
}
