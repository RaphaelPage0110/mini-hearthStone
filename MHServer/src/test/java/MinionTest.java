import abstracts.Effect;
import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static abstracts.Consts.*;
import static org.junit.Assert.*;

class MinionTest {

  private ConcreteMinion chefDeRaid,
          championFrisselame,
          sanglierBrocheroc,
          soldatDuCompteDeLOr,
          yetiNoroit,
          chevaucheurDeLoup,
          avocatCommisDOffice,
          imageMiroir,
          mouton,
          recrueDeLaMainDArgent;

  private Player player;

  private ConcreteHero jaina;

  @BeforeEach
  void setup() {
    EntitiesFactory entitiesFactory = new EntitiesFactory();

    chefDeRaid = entitiesFactory.createMinion(CHEF_DE_RAID);
    championFrisselame = entitiesFactory.createMinion(CHAMPION_FRISSELAME);
    sanglierBrocheroc = entitiesFactory.createMinion(SANGLIER_BROCHEROC);
    soldatDuCompteDeLOr = entitiesFactory.createMinion(SOLDAT_DU_COMTE_DE_L_OR);
    yetiNoroit = entitiesFactory.createMinion(YETI_NOROIT);
    chevaucheurDeLoup = entitiesFactory.createMinion(CHEVAUCHEUR_DE_LOUP);
    avocatCommisDOffice = entitiesFactory.createMinion(AVOCAT_COMMIS_D_OFFICE);
    imageMiroir = entitiesFactory.createMinion(IMAGE_MIROIR);
    mouton = entitiesFactory.createMinion(MOUTON);
    recrueDeLaMainDArgent = entitiesFactory.createMinion(RECRUE_DE_LA_MAIN_D_ARGENT);

    // Poorly managed bidirectional association (Player-Card)
    player = new Player();
    player.addMinion(chefDeRaid);
    chefDeRaid.setPlayer(player);
    player.addMinion(championFrisselame);
    championFrisselame.setPlayer(player);
    player.addMinion(sanglierBrocheroc);
    sanglierBrocheroc.setPlayer(player);
    player.addMinion(soldatDuCompteDeLOr);
    soldatDuCompteDeLOr.setPlayer(player);
    player.addMinion(yetiNoroit);
    yetiNoroit.setPlayer(player);
    player.addMinion(chevaucheurDeLoup);
    chevaucheurDeLoup.setPlayer(player);
    player.addMinion(avocatCommisDOffice);
    avocatCommisDOffice.setPlayer(player);
    player.addMinion(imageMiroir);
    imageMiroir.setPlayer(player);
    player.addMinion(mouton);
    mouton.setPlayer(player);
    player.addMinion(recrueDeLaMainDArgent);
    recrueDeLaMainDArgent.setPlayer(player);

    jaina = entitiesFactory.createHero("Jaina");
    player.setMyHero(jaina);
  }

  @Test
  void takeDamageTest() {
    assertEquals(yetiNoroit.getMaxHealthPoints(), yetiNoroit.getCurrentHealthPoints());
    assertEquals(5, yetiNoroit.getCurrentHealthPoints());
    assertFalse(yetiNoroit.isDead());

    assertEquals(0, yetiNoroit.takeDamage(0)); // takeDamage returns the damage taken
    assertEquals(5, yetiNoroit.getCurrentHealthPoints()); // This minion lost 0 HP
    assertFalse(yetiNoroit.isDead());

    assertEquals(1, yetiNoroit.takeDamage(1));
    assertEquals(4, yetiNoroit.getCurrentHealthPoints()); // This minion lost 1 HP
    assertFalse(yetiNoroit.isDead());

    assertEquals(0, yetiNoroit.takeDamage(-10));
    assertEquals(4, yetiNoroit.getCurrentHealthPoints());
    assertFalse(yetiNoroit.isDead());

    assertEquals(4, yetiNoroit.takeDamage(4)); // This minion is dead
    assertEquals(0, yetiNoroit.getCurrentHealthPoints());
    assertTrue(yetiNoroit.isDead());

    assertEquals(10, yetiNoroit.takeDamage(10)); // Even more dead !
    assertEquals(-10, yetiNoroit.getCurrentHealthPoints());
    assertTrue(yetiNoroit.isDead());

    assertEquals(0, yetiNoroit.takeDamage(-1));
    assertEquals(-10, yetiNoroit.getCurrentHealthPoints());
    assertTrue(yetiNoroit.isDead());

    /*-----Another Minion-----*/

    assertEquals(7, avocatCommisDOffice.getCurrentHealthPoints());
    assertFalse(avocatCommisDOffice.isDead());

    assertEquals(0, avocatCommisDOffice.takeDamage(Integer.MIN_VALUE));
    assertEquals(7, avocatCommisDOffice.getCurrentHealthPoints());
    assertFalse(avocatCommisDOffice.isDead());

    assertEquals(
            Integer.MAX_VALUE, avocatCommisDOffice.takeDamage(Integer.MAX_VALUE)); // Well, it hurts.
    assertEquals(Integer.MIN_VALUE + 7 + 1, avocatCommisDOffice.getCurrentHealthPoints());
  }

  @Test
  void diesTest() {
    assertTrue(
            sanglierBrocheroc
                    .getMyDeathRattles()
                    .isEmpty()); // This minion doesn't have a Death Rattle. i.e. an effect when he dies.
    assertTrue(player.getMyMinions().contains(sanglierBrocheroc));
    sanglierBrocheroc.dies();
    assertFalse(player.getMyMinions().contains(sanglierBrocheroc));

    assertTrue(recrueDeLaMainDArgent.getMyDeathRattles().isEmpty());
    assertTrue(player.getMyMinions().contains(recrueDeLaMainDArgent));
    recrueDeLaMainDArgent.takeDamage(recrueDeLaMainDArgent.getMaxHealthPoints());
    assertTrue(recrueDeLaMainDArgent.isDead());
    assertFalse(player.getMyMinions().contains(recrueDeLaMainDArgent));
  }

  @Test
  void raidLeaderDiesWithoutEffectTest() {
    /*-----Raid Leader dies without having activated his effect-----*/
    assertFalse(chefDeRaid.getMyDeathRattles().isEmpty()); // This minion has a Death Rattle.
    assertTrue(player.getMyMinions().contains(chefDeRaid));
    // In this case, we don't activate the effect "+1 attack" !
    assertEquals(0, player.getMyDamageAura());
    assertEquals(4, yetiNoroit.getDamagePoints());
    chefDeRaid.dies();
    assertEquals(
            -1,
            player
                    .getMyDamageAura()); // The bonus effect wasn't activated ! So there is an attack malus.
    assertFalse(chefDeRaid.isDead()); // Also, this minion is not really dead.
  }

  @Test
  void raidLeaderDiesWithEffectTest() {
    /*-----Raid Leader dies without after activating the bonus-----*/

    /*--Begin setup--*/
    for (Effect effect : chefDeRaid.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    assertFalse(chefDeRaid.getMyDeathRattles().isEmpty());
    assertTrue(player.getMyMinions().contains(chefDeRaid));
    assertEquals(1, player.getMyDamageAura());
    assertEquals(
            4,
            yetiNoroit
                    .getDamagePoints()); // We don't actually boost this property, but only the Player's
    // aura
    chefDeRaid.dies();
    assertEquals(0, player.getMyDamageAura()); // Removing the bonus.
    assertFalse(chefDeRaid.isDead());
  }

  @Test
  void healTest() {
    soldatDuCompteDeLOr.setCurrentHealthPoints(1);
    soldatDuCompteDeLOr.heal(10);
    assertEquals(2, soldatDuCompteDeLOr.getCurrentHealthPoints());

    mouton.setCurrentHealthPoints(0);
    mouton.heal(2);
    assertEquals(1, mouton.getCurrentHealthPoints());
    mouton.setMaxHealthPoints(10);
    assertEquals(1, mouton.getCurrentHealthPoints());
    mouton.heal(4);
    assertEquals(5, mouton.getCurrentHealthPoints());
    mouton.heal(-5);
    assertEquals(5, mouton.getCurrentHealthPoints());
    mouton.heal(6);
    assertEquals(10, mouton.getCurrentHealthPoints());

    mouton.heal(Integer.MAX_VALUE);
    assertEquals(10, mouton.getCurrentHealthPoints());
    mouton.heal(Integer.MIN_VALUE);
    assertEquals(10, mouton.getCurrentHealthPoints());

    mouton.setCurrentHealthPoints(Integer.MAX_VALUE);
    mouton.setMaxHealthPoints(Integer.MAX_VALUE);
    mouton.heal(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, mouton.getCurrentHealthPoints());

    mouton.setCurrentHealthPoints(Integer.MIN_VALUE);
    mouton.heal(2);
    assertEquals(Integer.MIN_VALUE + 2, mouton.getCurrentHealthPoints());
    mouton.setCurrentHealthPoints(Integer.MIN_VALUE);
    mouton.heal(Integer.MAX_VALUE);
    assertEquals(-1, mouton.getCurrentHealthPoints());
  }

  @Test
  void isDeadTest() {
    assertFalse(championFrisselame.isDead());
    championFrisselame.setCurrentHealthPoints(0);
    assertTrue(championFrisselame.isDead());
    championFrisselame.heal(1);
    assertFalse(championFrisselame.isDead());

    championFrisselame.setCurrentHealthPoints(Integer.MIN_VALUE);
    assertTrue(championFrisselame.isDead());
    championFrisselame.setCurrentHealthPoints(Integer.MAX_VALUE);
    assertFalse(championFrisselame.isDead());
  }

  @Test
  void attackingMinionTest() {

    /*-----Attacking a Minion-----*/

    // By default, Minions can't attack if we don't inform them it's their turn.
    assertFalse(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(recrueDeLaMainDArgent);
    assertEquals(
            recrueDeLaMainDArgent.getMaxHealthPoints(),
            recrueDeLaMainDArgent.getCurrentHealthPoints()); // This minion hasn't lost any HP

    sanglierBrocheroc.setCanAttack(true); // It's his turn ! He can attack.
    assertTrue(sanglierBrocheroc.isCanAttack());
    assertEquals(1, sanglierBrocheroc.getDamagePoints());
    sanglierBrocheroc.attack(recrueDeLaMainDArgent);
    assertEquals(
            recrueDeLaMainDArgent.getMaxHealthPoints() - 1,
            recrueDeLaMainDArgent.getCurrentHealthPoints());
    assertTrue(recrueDeLaMainDArgent.isDead()); // In this case, he is also dead.

    // We can't attack twice in a row with the same Minion !
    assertFalse(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(imageMiroir);
    assertEquals(imageMiroir.getMaxHealthPoints(), imageMiroir.getCurrentHealthPoints());
    assertFalse(sanglierBrocheroc.isCanAttack());
  }

  @Test
  void attackingHeroTest() {

    /*-----Attacking a Hero-----*/

    assertFalse(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(jaina);
    assertEquals(jaina.getMaxHealthPoints(), jaina.getCurrentHealthPoints());

    sanglierBrocheroc.setCanAttack(true);
    assertTrue(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(jaina);
    assertEquals(jaina.getMaxHealthPoints() - 1, jaina.getCurrentHealthPoints());
    assertFalse(sanglierBrocheroc.isCanAttack());
  }

  @Test
  void attackingHeroArmorTest() {

    /*-----Attacking a Hero with armor-----*/

    assertEquals(0, jaina.getArmorPoints());
    jaina.setArmorPoints(1);
    assertEquals(1, jaina.getArmorPoints());
    assertEquals(1, sanglierBrocheroc.getDamagePoints());
    sanglierBrocheroc.setCanAttack(true);
    assertTrue(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(jaina);
    assertEquals(0, jaina.getArmorPoints());
    assertEquals(jaina.getMaxHealthPoints(), jaina.getCurrentHealthPoints());
    assertFalse(sanglierBrocheroc.isCanAttack());

    jaina.setArmorPoints(5);
    sanglierBrocheroc.setDamagePoints(10);
    sanglierBrocheroc.setCanAttack(true);
    assertEquals(5, jaina.getArmorPoints());
    assertEquals(10, sanglierBrocheroc.getDamagePoints());
    assertEquals(30, jaina.getCurrentHealthPoints()); // Note that a hero has a maximum of 30 HP
    assertTrue(sanglierBrocheroc.isCanAttack());
    sanglierBrocheroc.attack(jaina);
    assertEquals(0, jaina.getArmorPoints());
    assertEquals(25, jaina.getCurrentHealthPoints());
    assertFalse(sanglierBrocheroc.isCanAttack());
  }

  @Test
  void attackingWithChargeBehaviourTest() {

    /*-----The "Wolf Rider" minion has the "Charge" behaviour,
    which allows him to attack without waiting for the next turn-----*/

    /*--Begin setup--*/
    for (Effect effect : chevaucheurDeLoup.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    assertTrue(chevaucheurDeLoup.isCanAttack());
    chevaucheurDeLoup.attack(mouton);
    assertTrue(mouton.isDead());
    assertEquals(
            mouton.getMaxHealthPoints() - chevaucheurDeLoup.getDamagePoints(),
            mouton.getCurrentHealthPoints());
    assertFalse(chevaucheurDeLoup.isCanAttack());
  }

  @Test
  void attackingWithLifeStealBehaviourTest() {

    /*-----The "Chillblade Champion" minion has the "Life Steal" behaviour,
    which allows him to heal his hero by attacking someone.
    He also has the "Charge" behaviour-----*/

    /*--Begin setup--*/
    for (Effect effect : championFrisselame.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    assertTrue(championFrisselame.isHasLifesteal());
    assertTrue(championFrisselame.isCanAttack());
    jaina.setCurrentHealthPoints(20);
    assertEquals(20, jaina.getCurrentHealthPoints());
    championFrisselame.attack(yetiNoroit);
    assertEquals(3, championFrisselame.getDamagePoints());
    assertEquals(23, jaina.getCurrentHealthPoints()); // Normal case
    assertEquals(2, yetiNoroit.getCurrentHealthPoints());
    assertFalse(championFrisselame.isCanAttack());
    assertTrue(championFrisselame.isHasLifesteal());
  }

  @Test
  void attackingTargetLowHPWithLifeStealTest() {

    /*--Begin setup--*/
    for (Effect effect : championFrisselame.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    /*-----Attacking a target with low HP-----*/
    assertTrue(championFrisselame.isHasLifesteal());
    jaina.setCurrentHealthPoints(20);
    championFrisselame.setCanAttack(true);
    championFrisselame.setDamagePoints(9);
    assertTrue(championFrisselame.isCanAttack());
    assertEquals(20, jaina.getCurrentHealthPoints());
    assertEquals(9, championFrisselame.getDamagePoints());
    assertEquals(1, mouton.getCurrentHealthPoints());
    championFrisselame.attack(mouton);
    assertEquals(
            29,
            jaina
                    .getCurrentHealthPoints()); // Hero is healed depending on the number of Damage Point of
    // his ally
    assertEquals(-8, mouton.getCurrentHealthPoints());
    assertTrue(mouton.isDead());
    assertFalse(championFrisselame.isCanAttack());
    assertTrue(championFrisselame.isHasLifesteal());
  }

  @Test
  void attackingWithLifeStealAndHeroFullHPTest() {

    /*--Begin setup--*/
    for (Effect effect : championFrisselame.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    /*-----Attacking with hero full HP-----*/
    assertTrue(championFrisselame.isHasLifesteal());
    jaina.setCurrentHealthPoints(30);
    assertEquals(jaina.getMaxHealthPoints(), jaina.getCurrentHealthPoints());
    championFrisselame.setCanAttack(true);
    championFrisselame.setDamagePoints(5);
    mouton.setCurrentHealthPoints(1);
    assertTrue(championFrisselame.isCanAttack());
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(5, championFrisselame.getDamagePoints());
    assertEquals(1, mouton.getCurrentHealthPoints());
    championFrisselame.attack(mouton);
    assertEquals(30, jaina.getCurrentHealthPoints()); // Hero don't get more HP
    assertEquals(-4, mouton.getCurrentHealthPoints());
    assertTrue(mouton.isDead());
    assertFalse(championFrisselame.isCanAttack());
    assertTrue(championFrisselame.isHasLifesteal());
  }

  @Test
  void attackingHisOwnHeroWithLifeStealTest() {

    /*--Begin setup--*/
    for (Effect effect : championFrisselame.getMyEffects()) {
      effect.effect();
    }
    /*--End setup--*/

    /*-----Attacking his own hero-----*/
    assertTrue(championFrisselame.isHasLifesteal());
    jaina.setCurrentHealthPoints(30);
    assertEquals(jaina.getMaxHealthPoints(), jaina.getCurrentHealthPoints());
    championFrisselame.setCanAttack(true);
    championFrisselame.setDamagePoints(5);
    assertTrue(championFrisselame.isCanAttack());
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(5, championFrisselame.getDamagePoints());
    championFrisselame.attack(jaina);
    assertEquals(
            30,
            jaina.getCurrentHealthPoints()); // Hero is injured and then healed with the same number of
    // HP
    assertFalse(championFrisselame.isCanAttack());
    assertTrue(championFrisselame.isHasLifesteal());
  }
}
