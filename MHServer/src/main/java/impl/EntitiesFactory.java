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

        Player myPlayer = new Player();
        concreteMinion.setPlayer(myPlayer);

        return concreteMinion;
    }

    public ConcreteSpell createSpell(String nameSpell) {

        ConcreteSpell concreteSpell = new ConcreteSpell();
        Map<String, String> abilityKeyword = new HashMap<>();

        switch(nameSpell) {
            case "Bénédiction de puissance":
                abilityKeyword.put("modifyDamagePointsOneMinion", "3");
                concreteSpell = new ConcreteSpell(CardType.PALADIN, 1, 0, 0, abilityKeyword, nameSpell, "https://bit.ly/2Qmsgev", "Rajoute 3 points de dégats à un serviteur");
                break;
            case "Image miroir":
                abilityKeyword.put("summon", "Image miroir, 2");
                concreteSpell = new ConcreteSpell(CardType.MAGE, 1, 0, 0, abilityKeyword, nameSpell, "https://bit.ly/2R7eWzF", "Invoque deux serviteurs 'Image Miroir'");
                break;
            case "Métamorphose":
                abilityKeyword.put("transformInto", "Mouton");
                concreteSpell = new ConcreteSpell(CardType.MAGE, 4, 0, 0, abilityKeyword, nameSpell, "https://bit.ly/2SBLqiu", "Transforme un serviteur en mouton");
                break;
            case "Explosion des arcanes":
                abilityKeyword.put("damageEnemyMinions", "1");
                concreteSpell = new ConcreteSpell(CardType.MAGE, 2, 1, 0, abilityKeyword, nameSpell, "https://bit.ly/2TsvhMi", "Inflige 1 points de dégats aux serviteurs ennemis");
                break;
            case "Consécration":
                abilityKeyword.put("damageAllOpponents", "2");
                concreteSpell = new ConcreteSpell(CardType.PALADIN, 4, 0, 0, abilityKeyword, nameSpell, "https://bit.ly/2SAFiqC", "Inflige 2 points de dégats à tous les adversaires");
                break;
            case "Tourbillon":
                abilityKeyword.put("damageAllMinions", "1");
                concreteSpell = new ConcreteSpell(CardType.WARRIOR, 1, 1, 0, abilityKeyword, nameSpell, "https://bit.ly/2LNnXrW", "Inflige 1 point de dégat à tous les serviteurs");
                break;
            case "Maîtrise du blocage":
                abilityKeyword.put("modifyArmor", "5");
                abilityKeyword.put("drawCard", "1");
                concreteSpell = new ConcreteSpell(CardType.WARRIOR, 3, 0, 0, abilityKeyword, nameSpell, "https://bit.ly/2Vq7RZI", "Rajoute 5 points d'armure et pioche 1 carte");
                break;
            default:
                break;
        }

        return concreteSpell;
    }
}
