import impl.ConcreteHero;

public class HeroMock extends ConcreteHero {

    public HeroMock(ConcreteHero concreteHero) {
        super(concreteHero.getHeroType(),
                concreteHero.getMaxHealthPoints(),
                concreteHero.getArmorPoints(),
                concreteHero.getAbilityKeyWord(),
                concreteHero.getHeroName(),
                concreteHero.getImgurl());
    }

    @Override
    public void dies() {}
}
