package identifiers;

import abstracts.Hero;

public class IdHero extends IdTarget {
    private Hero hero;

    public IdHero(Hero hero) {
        super();
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }
}
