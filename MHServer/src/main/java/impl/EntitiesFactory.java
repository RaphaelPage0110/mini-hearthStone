package impl;

import abstracts.CardType;

import java.util.HashMap;
import java.util.Map;

import static abstracts.Consts.*;

public class EntitiesFactory {

  public ConcreteHero createHero(String heroName) {

    ConcreteHero concreteHero = new ConcreteHero();
    Map<String, String> abilityKeyword = new HashMap<>();

    switch (heroName) {
      case JAINA:
        abilityKeyword.put(DAMAGE_TARGET_ABILITY, "1");
        concreteHero =
                new ConcreteHero(
                        CardType.MAGE, 30, 0, abilityKeyword, heroName, "https://bit.ly/2Vpe5ZK");
        break;
      case GARROSH:
        abilityKeyword.put(MODIFY_ARMOR_ABILITY, "2");
        concreteHero =
                new ConcreteHero(
                        CardType.WARRIOR, 30, 0, abilityKeyword, heroName, "https://bit.ly/2s7lCyU");
        break;
      case UTHER:
        abilityKeyword.put(SUMMON_ABILITY, RECRUE_DE_LA_MAIN_D_ARGENT + ", 1");
        concreteHero =
                new ConcreteHero(
                        CardType.PALADIN, 30, 0, abilityKeyword, heroName, "https://bit.ly/2F6AzKh");
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

    switch (minionName) {
      case CHEF_DE_RAID:
        abilityKeyword.put(BUFF_ALLIED_MINIONS_ABILITY, "1");
        deathRattle.put(REMOVE_AURA_DEATH_RATTLE, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.COMMON,
                        3,
                        2,
                        2,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2SA51zw",
                        "+1 d'attaque pour tous les serviteurs alliés du plateau");
        break;
      case CHAMPION_FRISSELAME:
        abilityKeyword.put(CHARGE_ABILITY, "");
        abilityKeyword.put(LIFE_STEAL_ABILITY, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.PALADIN,
                        4,
                        3,
                        2,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2C0mH0d",
                        "Charge, Vol de vie");
        break;
      case SANGLIER_BROCHEROC:
        concreteMinion =
                new ConcreteMinion(
                        CardType.COMMON,
                        1,
                        1,
                        1,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2R8Y4Zy",
                        "");
        break;
      case SOLDAT_DU_COMTE_DE_L_OR:
        abilityKeyword.put(TAUNT_ABILITY, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.COMMON,
                        1,
                        1,
                        2,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2shdhch",
                        "Provocation");
        break;
      case YETI_NOROIT:
        concreteMinion =
                new ConcreteMinion(
                        CardType.COMMON,
                        4,
                        4,
                        5,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2LO6Sy3",
                        "Provocation");
        break;
      case CHEVAUCHEUR_DE_LOUP:
        abilityKeyword.put(CHARGE_ABILITY, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.COMMON,
                        3,
                        3,
                        1,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2F4e5td",
                        "Charge");
        break;
      case IMAGE_MIROIR:
        abilityKeyword.put(TAUNT_ABILITY, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.SPECIAL,
                        1,
                        0,
                        2,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2Sz8Ftr",
                        "Provocation");
        break;
      case MOUTON:
        concreteMinion =
                new ConcreteMinion(
                        CardType.SPECIAL,
                        1,
                        1,
                        1,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2SBLqiu",
                        "");
        break;
      case RECRUE_DE_LA_MAIN_D_ARGENT:
        concreteMinion =
                new ConcreteMinion(
                        CardType.SPECIAL,
                        1,
                        1,
                        1,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2C01Ny3",
                        "");
        break;
      case AVOCAT_COMMIS_D_OFFICE:
        abilityKeyword.put(TAUNT_ABILITY, "");
        concreteMinion =
                new ConcreteMinion(
                        CardType.WARRIOR,
                        2,
                        0,
                        7,
                        abilityKeyword,
                        deathRattle,
                        minionName,
                        "https://bit.ly/2TpGxsu",
                        "Provocation");
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

    switch (nameSpell) {
      case BENEDICTION_DE_PUISSANCE:
        abilityKeyword.put(MODIFY_DAMAGE_POINTS_ONE_MINION_ABILITY, "3");
        concreteSpell =
                new ConcreteSpell(
                        CardType.PALADIN,
                        1,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2Qmsgev",
                        "Rajoute 3 points de dégats à un serviteur");
        break;
      case IMAGE_MIROIR:
        abilityKeyword.put(SUMMON_ABILITY, IMAGE_MIROIR + ", 2");
        concreteSpell =
                new ConcreteSpell(
                        CardType.MAGE,
                        1,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2R7eWzF",
                        "Invoque deux serviteurs 'Image Miroir'");
        break;
      case METAMORPHOSE:
        abilityKeyword.put(TRANSFORM_INTO_ABILITY, MOUTON);
        concreteSpell =
                new ConcreteSpell(
                        CardType.MAGE,
                        4,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2SBLqiu",
                        "Transforme un serviteur en mouton");
        break;
      case EXPLOSION_DES_ARCANES:
        abilityKeyword.put(DAMAGE_ENEMY_MINIONS_ABILITY, "1");
        concreteSpell =
                new ConcreteSpell(
                        CardType.MAGE,
                        2,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2TsvhMi",
                        "Inflige 1 points de dégats aux serviteurs ennemis");
        break;
      case CONSECRATION:
        abilityKeyword.put(DAMAGE_ALL_OPPONENTS_ABILITY, "2");
        concreteSpell =
                new ConcreteSpell(
                        CardType.PALADIN,
                        4,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2SAFiqC",
                        "Inflige 2 points de dégats à tous les adversaires");
        break;
      case TOURBILLON:
        abilityKeyword.put(DAMAGE_ALL_MINIONS_ABILITY, "1");
        concreteSpell =
                new ConcreteSpell(
                        CardType.WARRIOR,
                        1,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2LNnXrW",
                        "Inflige 1 point de dégat à tous les serviteurs");
        break;
      case MAITRISE_DU_BLOCAGE:
        abilityKeyword.put(MODIFY_ARMOR_ABILITY, "5");
        abilityKeyword.put(DRAW_CARD_ABILITY, "1");
        concreteSpell =
                new ConcreteSpell(
                        CardType.WARRIOR,
                        3,
                        0,
                        abilityKeyword,
                        nameSpell,
                        "https://bit.ly/2Vq7RZI",
                        "Rajoute 5 points d'armure et pioche 1 carte");
        break;
      default:
        break;
    }

    return concreteSpell;
  }
}
