import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static abstracts.CardType.*;
import static abstracts.Consts.*;
import static org.junit.Assert.*;

class HeroTest {

  private ConcreteHero jaina, garrosh, uther;
  private Player player1, player2, player3;

  private ConcreteMinion yetiNoroit;

  @BeforeEach
  void setup() {
    EntitiesFactory entitiesFactory = new EntitiesFactory();

    jaina = entitiesFactory.createHero(JAINA);
    garrosh = entitiesFactory.createHero(GARROSH);
    uther = entitiesFactory.createHero(UTHER);

    player1 = new Player();
    player2 = new Player();
    player3 = new Player();

    // Poorly managed bidirectional association (Player-Hero)
    player1.setMyHero(jaina);
    jaina.setMyPlayer(player1);
    player2.setMyHero(garrosh);
    garrosh.setMyPlayer(player2);
    player3.setMyHero(uther);
    uther.setMyPlayer(player3);

    yetiNoroit = entitiesFactory.createMinion(YETI_NOROIT);
  }

  @Test
  void maxHealthPointsTest() {
    assertEquals(30, garrosh.getMaxHealthPoints());

    garrosh.addMaxHealthPoints(10);
    assertEquals(40, garrosh.getMaxHealthPoints());

    garrosh.takeDamage(20);
    assertEquals(40, garrosh.getMaxHealthPoints());
  }

  @Test
  void currentHealthPoints() {
    assertEquals(garrosh.getMaxHealthPoints(), garrosh.getCurrentHealthPoints());

    garrosh.takeDamage(20);
    assertEquals(10, garrosh.getCurrentHealthPoints());

    garrosh.heal(10);
    assertEquals(20, garrosh.getCurrentHealthPoints());
  }

  @Test
  void jainaHeroPowerTest() {
    assertFalse(jaina.canUseHeroAbility()); // Player don't have enough mana
    jaina.activateEffect(yetiNoroit); // Jaina has the "Fireball" power
    assertEquals(yetiNoroit.getMaxHealthPoints(), yetiNoroit.getCurrentHealthPoints());

    player1.setMyMana(4);
    assertTrue(jaina.canUseHeroAbility());
    jaina.activateEffect(yetiNoroit);
    assertEquals(yetiNoroit.getMaxHealthPoints() - 1, yetiNoroit.getCurrentHealthPoints());
    assertFalse(jaina.canUseHeroAbility()); // We can't use hero power twice in a row !
    jaina.activateEffect(yetiNoroit);
    assertEquals(
            yetiNoroit.getMaxHealthPoints() - 1, yetiNoroit.getCurrentHealthPoints()); // Hasn't changed

    player1.setMyMana(0);
    jaina.setCanUseHeroAbility(true);
    assertFalse(jaina.canUseHeroAbility()); // Not enough mana
    jaina.activateEffect(yetiNoroit);
    assertEquals(
            yetiNoroit.getMaxHealthPoints() - 1, yetiNoroit.getCurrentHealthPoints()); // Still OK

    player1.setMyMana(2);
    jaina.setCanUseHeroAbility(true);
    assertTrue(jaina.canUseHeroAbility());
    jaina.activateEffect(garrosh);
    assertEquals(garrosh.getMaxHealthPoints() - 1, garrosh.getCurrentHealthPoints());
    assertFalse(jaina.canUseHeroAbility());
    jaina.activateEffect(garrosh);
    assertEquals(
            garrosh.getMaxHealthPoints() - 1, garrosh.getCurrentHealthPoints()); // Checking no changes
  }

  @Test
  void garroshHeroPowerTest() {
    assertFalse(garrosh.canUseHeroAbility());
    assertEquals(0, garrosh.getArmorPoints());
    garrosh.activateEffect(null);
    assertEquals(0, garrosh.getArmorPoints());

    player2.setMyMana(4);
    assertTrue(garrosh.canUseHeroAbility());
    assertEquals(0, garrosh.getArmorPoints());
    garrosh.activateEffect(null);
    assertEquals(2, garrosh.getArmorPoints());
    assertFalse(garrosh.canUseHeroAbility());
    garrosh.activateEffect(null);
    assertEquals(2, garrosh.getArmorPoints());

    player2.setMyMana(2);
    garrosh.setCanUseHeroAbility(true);
    assertTrue(garrosh.canUseHeroAbility());
    garrosh.activateEffect(null);
    assertEquals(4, garrosh.getArmorPoints()); // Cumulation of armor points

    player2.setMyMana(0);
    garrosh.setCanUseHeroAbility(true);
    assertFalse(garrosh.canUseHeroAbility());
    garrosh.activateEffect(null);
    assertEquals(4, garrosh.getArmorPoints()); // Hasn't changed

    player2.setMyMana(2);
    garrosh.setCanUseHeroAbility(true);
    assertTrue(garrosh.canUseHeroAbility());
    garrosh.activateEffect(uther); // Target is ignored for ths effect.
    assertEquals(6, garrosh.getArmorPoints()); // Cumulation of armor points
    assertEquals(uther.getMaxHealthPoints(), uther.getCurrentHealthPoints()); // Hasn't changed
  }

  @Test
  void utherHeroPowerTest() {
    uther = new HeroMock(uther);

    assertFalse(uther.canUseHeroAbility());
    assertFalse(uther.getMyPlayer().containsMinion("Recrue de la main d'argent"));
    uther.activateEffect(null);
    assertFalse(uther.getMyPlayer().containsMinion("Recrue de la main d'argent"));

    player3.setMyMana(4);
    assertTrue(uther.canUseHeroAbility());
    assertFalse(uther.getMyPlayer().containsMinion("Recrue de la main d'argent"));
    uther.activateEffect(null);
    assertTrue(uther.getMyPlayer().containsMinion("Recrue de la main d'argent"));
    player3.setMyMinions(new ArrayList<ConcreteMinion>()); // Removing all minions
    assertFalse(uther.getMyPlayer().containsMinion("Recrue de la main d'argent"));
    assertFalse(uther.canUseHeroAbility()); // We can't use hero power twice in a row !
    uther.activateEffect(null);
    assertFalse(uther.getMyPlayer().containsMinion("Recrue de la main d'argent")); // Hasn't changed
  }

  @Test
  void iterateUtherHeroPowerTest() {
    uther = new HeroMock(uther);

    int nb = 5;

    for (int i = 0; i < nb; i++) {
      player3.setMyMana(2);
      uther.setCanUseHeroAbility(true);
      assertTrue(uther.canUseHeroAbility());
      uther.activateEffect(null);
    }

    for (int i = 0; i < nb; i++) {
      assertEquals("Recrue de la main d'argent", player3.getMyMinions().get(i).getName());
    } // Cumulation of invocations
  }

  @Test
  void heroTypeTest() {
    assertEquals(WARRIOR, garrosh.getHeroType());
    assertEquals(MAGE, jaina.getHeroType());
    assertEquals(PALADIN, uther.getHeroType());
  }

  @Test
  void takeDamageTest() {
    jaina = new HeroMock(jaina); // Mock for dies() method
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(10, jaina.takeDamage(10));
    assertEquals(20, jaina.getCurrentHealthPoints());

    assertEquals(Integer.MAX_VALUE, jaina.takeDamage(Integer.MAX_VALUE));
    assertEquals(Integer.MIN_VALUE + 20 + 1, jaina.getCurrentHealthPoints());
    assertTrue(jaina.isDead());

    jaina.setCurrentHealthPoints(30);
    assertEquals(30, jaina.takeDamage(30));
    assertEquals(0, jaina.getCurrentHealthPoints());
    assertTrue(jaina.isDead());

    jaina.setCurrentHealthPoints(30);
    assertEquals(0, jaina.takeDamage(-5));
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertFalse(jaina.isDead());

    assertEquals(0, jaina.takeDamage(Integer.MIN_VALUE));
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertFalse(jaina.isDead());
  }

  @Test
  void takeDamageWithArmorTest() {
    jaina = new HeroMock(jaina); // Mock for dies() method
    jaina.setArmorPoints(10);
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(10, jaina.getArmorPoints());
    assertEquals(0, jaina.takeDamage(10));
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(0, jaina.getArmorPoints());
    assertFalse(jaina.isDead());

    jaina.setArmorPoints(10);
    jaina.setCurrentHealthPoints(30);
    assertEquals(5, jaina.takeDamage(15));
    assertEquals(25, jaina.getCurrentHealthPoints());
    assertEquals(0, jaina.getArmorPoints());
    assertFalse(jaina.isDead());

    jaina.setArmorPoints(10);
    jaina.setCurrentHealthPoints(30);
    assertEquals(30, jaina.takeDamage(40));
    assertEquals(0, jaina.getCurrentHealthPoints());
    assertEquals(0, jaina.getArmorPoints());
    assertTrue(jaina.isDead());

    jaina.setArmorPoints(10);
    jaina.setCurrentHealthPoints(30);
    assertEquals(Integer.MAX_VALUE - 10, jaina.takeDamage(Integer.MAX_VALUE));
    assertEquals(Integer.MIN_VALUE + 30 + 10 + 1, jaina.getCurrentHealthPoints());
    assertEquals(0, jaina.getArmorPoints());
    assertTrue(jaina.isDead());

    jaina.setArmorPoints(10);
    jaina.setCurrentHealthPoints(30);
    assertEquals(0, jaina.takeDamage(Integer.MIN_VALUE));
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(10, jaina.getArmorPoints());
    assertFalse(jaina.isDead());

    jaina.setArmorPoints(10);
    jaina.setCurrentHealthPoints(30);
    assertEquals(0, jaina.takeDamage(-7));
    assertEquals(30, jaina.getCurrentHealthPoints());
    assertEquals(10, jaina.getArmorPoints());
    assertFalse(jaina.isDead());
  }

  @Test
  void healTest() {
    uther.setCurrentHealthPoints(1);
    uther.heal(10);
    assertEquals(11, uther.getCurrentHealthPoints());

    uther.heal(2500);
    assertEquals(30, uther.getCurrentHealthPoints());

    uther.setCurrentHealthPoints(0);
    uther.heal(1);
    assertEquals(1, uther.getCurrentHealthPoints());
    uther.heal(-12);
    assertEquals(1, uther.getCurrentHealthPoints());

    uther.setCurrentHealthPoints(-10);
    uther.heal(1);
    assertEquals(-9, uther.getCurrentHealthPoints());
    assertTrue(uther.isDead());

    uther.setCurrentHealthPoints(30);
    uther.heal(Integer.MAX_VALUE);
    assertEquals(30, uther.getCurrentHealthPoints());
    uther.heal(Integer.MIN_VALUE);
    assertEquals(30, uther.getCurrentHealthPoints());

    uther.setCurrentHealthPoints(Integer.MAX_VALUE);
    uther.setMaxHealthPoints(Integer.MAX_VALUE);
    uther.heal(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, uther.getCurrentHealthPoints());

    uther.setCurrentHealthPoints(Integer.MIN_VALUE);
    uther.heal(42);
    assertEquals(Integer.MIN_VALUE + 42, uther.getCurrentHealthPoints());
    uther.setCurrentHealthPoints(Integer.MIN_VALUE);
    uther.heal(Integer.MAX_VALUE);
    assertEquals(-1, uther.getCurrentHealthPoints());
  }
}
