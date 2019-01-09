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

    public ConcreteMinion createMinion(String minionName) {

        ConcreteMinion concreteMinion = new ConcreteMinion();
        Map<String, String> abilityKeyword = new HashMap<>();
        Map<String, String> deathRattle = new HashMap<>();

        switch(minionName) {
            case "Chef de raid":
                abilityKeyword.put("buffAlliedMinions", "1");
                deathRattle.put("removeAura", "");
                concreteMinion = new ConcreteMinion(CardType.COMMON, 3, 2, 2, abilityKeyword, deathRattle, minionName, "https://bit.ly/2SA51zw", "+1 d'attaque pour tous les serviteurs alliés du plateau");
                break;
            case "Champion frisselame":
                abilityKeyword.put("charge", "");
                abilityKeyword.put("lifeSteal", "");
                concreteMinion = new ConcreteMinion(CardType.PALADIN, 4, 3, 2, abilityKeyword, deathRattle, minionName, "https://bit.ly/2C0mH0d", "Charge, Vol de vie");
                break;
            case "Sanglier Brocheroc":
                concreteMinion = new ConcreteMinion(CardType.COMMON, 1, 1, 1, abilityKeyword, deathRattle, minionName, "https://bit.ly/2R8Y4Zy", "");
                break;
            case "Soldat du comté de l'or":
                abilityKeyword.put("taunt", "");
                concreteMinion = new ConcreteMinion(CardType.COMMON, 1, 1, 2, abilityKeyword, deathRattle, minionName, "https://bit.ly/2shdhch", "Provocation");
                break;
            case "Yéti noroit":
                concreteMinion = new ConcreteMinion(CardType.COMMON, 4, 4, 5, abilityKeyword, deathRattle, minionName, "https://bit.ly/2LO6Sy3", "Provocation");
                break;
            case "Chevaucheur de loup":
                abilityKeyword.put("charge", "");
                concreteMinion = new ConcreteMinion(CardType.COMMON, 3, 3, 1, abilityKeyword, deathRattle, minionName, "https://bit.ly/2F4e5td", "Charge");
                break;
            case "Image miroir":
                abilityKeyword.put("taunt", "");
                concreteMinion = new ConcreteMinion(CardType.SPECIAL, 1, 0, 2, abilityKeyword, deathRattle, minionName, "https://bit.ly/2Sz8Ftr", "Provocation");
                break;
            case "Mouton":
                concreteMinion = new ConcreteMinion(CardType.SPECIAL, 1, 1, 1, abilityKeyword, deathRattle, minionName, "https://bit.ly/2SBLqiu", "");
                break;
            case "Recrue de la main d'argent":
                concreteMinion = new ConcreteMinion(CardType.SPECIAL, 1, 1, 1, abilityKeyword, deathRattle, minionName, "https://bit.ly/2C01Ny3", "");
                break;
            case "Avocat commis d'office":
                abilityKeyword.put("taunt", "");
                concreteMinion = new ConcreteMinion(CardType.WARRIOR, 2, 0, 7, abilityKeyword, deathRattle, minionName, "https://bit.ly/2TpGxsu", "Provocation");
                break;
            default:
                break;
        }

        return concreteMinion;
    }
}
