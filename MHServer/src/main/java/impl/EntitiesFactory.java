package impl;

import repositories.Application;

public class EntitiesFactory {

    public ConcreteMinion createMinion(String name) {
        return Application.findMinionInDatabase(name);
    }

    public ConcreteSpell createSpell(String name) {
        return Application.findSpellInDatabase(name);
    }

    public ConcreteHero createHero(String name) {
        return Application.findHeroInDatabase(name);
    }
}
