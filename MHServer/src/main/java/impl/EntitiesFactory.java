package impl;

import abstracts.CardType;

import java.util.HashMap;
import java.util.Map;

public class EntitiesFactory {

    public ConcreteHero createHero(String heroName) {

        ConcreteHero concreteHero = new ConcreteHero();
        Map<String, String> abilityKeyword = new HashMap<>();

        switch(heroName) {
            case "Jaina":
                abilityKeyword.put("damageTarget", "1");
                concreteHero = new ConcreteHero(CardType.MAGE, 30,0, abilityKeyword, heroName,"https://bit.ly/2Vpe5ZK");
                break;
            case "Garrosh":
                abilityKeyword.put("modifyArmor", "2");
                concreteHero = new ConcreteHero(CardType.WARRIOR, 30, 0, abilityKeyword, heroName, "https://bit.ly/2s7lCyU");
                break;
            case "Uther":
                abilityKeyword.put("summon", "Recrue de la main d'argent, 1");
                concreteHero = new ConcreteHero(CardType.PALADIN, 30, 0, abilityKeyword, heroName, "https://bit.ly/2F6AzKh");
                break;
            default:
                break;
        }

        return concreteHero;
    }
}
