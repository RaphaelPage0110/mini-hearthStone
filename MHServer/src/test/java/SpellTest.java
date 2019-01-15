import impl.*;
import mocks.SpellMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static abstracts.ConstsUtils.*;
import static org.junit.Assert.*;

public class SpellTest {

  private ConcreteSpell benedictionDePuissance,
          imageMiroir,
          metamorphose,
          explosionDesArcanes,
          consecration,
          tourbillon,
          maitriseDuBlocage;
  private ConcreteMinion sanglierBrocheroc, yetiNoroit, chefDeRaid, chevaucheurDeLoup;
  private ConcreteHero jaina, uther;
  private Player player1, player2;

  @BeforeEach
  void setup() {
    EntitiesFactory entitiesFactory = new EntitiesFactory();

    // Spell creation
    benedictionDePuissance = entitiesFactory.createSpell(BENEDICTION_DE_PUISSANCE);
    imageMiroir = entitiesFactory.createSpell(IMAGE_MIROIR);
    metamorphose = entitiesFactory.createSpell(METAMORPHOSE);
    explosionDesArcanes = entitiesFactory.createSpell(EXPLOSION_DES_ARCANES);
    consecration = entitiesFactory.createSpell(CONSECRATION);
    tourbillon = entitiesFactory.createSpell(TOURBILLON);
    maitriseDuBlocage = entitiesFactory.createSpell(MAITRISE_DU_BLOCAGE);

    // Minion
    sanglierBrocheroc = entitiesFactory.createMinion(SANGLIER_BROCHEROC);
    yetiNoroit = entitiesFactory.createMinion(YETI_NOROIT);
    chefDeRaid = entitiesFactory.createMinion(CHEF_DE_RAID);
    chevaucheurDeLoup = entitiesFactory.createMinion(CHEVAUCHEUR_DE_LOUP);

    // Hero
    jaina = entitiesFactory.createHero(JAINA);
    uther = entitiesFactory.createHero(UTHER);

    player1 = new Player();
    player2 = new Player();

    // Poorly managed bidirectional association (Player-Spell)
    player1.addCardToHand(benedictionDePuissance);
    benedictionDePuissance.setPlayer(player1);
    player1.addCardToHand(imageMiroir);
    imageMiroir.setPlayer(player1);
    player1.addCardToHand(metamorphose);
    metamorphose.setPlayer(player1);
    player1.addCardToHand(explosionDesArcanes);
    explosionDesArcanes.setPlayer(player1);
    player1.addCardToHand(consecration);
    consecration.setPlayer(player1);
    player1.addCardToHand(tourbillon);
    tourbillon.setPlayer(player1);
    player1.addCardToHand(maitriseDuBlocage);
    maitriseDuBlocage.setPlayer(player1);

    // Poorly managed bidirectional association (Player-Minion)
    player1.addMinion(sanglierBrocheroc);
    sanglierBrocheroc.setPlayer(player1);
    player1.addMinion(yetiNoroit);
    yetiNoroit.setPlayer(player1);

    player2.addMinion(chefDeRaid);
    chefDeRaid.setPlayer(player2);
    player2.addMinion(chevaucheurDeLoup);
    chevaucheurDeLoup.setPlayer(player2);

    // Poorly managed bidirectional association (Player-Hero)
    player1.setMyHero(jaina);
    jaina.setMyPlayer(player1);
    player2.setMyHero(uther);
    uther.setMyPlayer(player2);

    player1.setOpponent(player2);
  }

  @Test
  void benedictionDePuissanceTest() {
    player1.setMyMana(10);
    assertEquals(1, sanglierBrocheroc.getDamagePoints());
    assertTrue(benedictionDePuissance.canCastSpell());

    benedictionDePuissance.activateEffect(sanglierBrocheroc);

    assertEquals(4, sanglierBrocheroc.getDamagePoints());
    assertFalse(benedictionDePuissance.canCastSpell());

    player1.addCardToHand(benedictionDePuissance);
    assertEquals(3, chevaucheurDeLoup.getDamagePoints());
    assertTrue(benedictionDePuissance.canCastSpell());

    benedictionDePuissance.activateEffect(chevaucheurDeLoup);

    assertEquals(6, chevaucheurDeLoup.getDamagePoints());
    assertFalse(benedictionDePuissance.canCastSpell());
  }

  @Test
  void imageMiroirTest() {
    imageMiroir = new SpellMock(imageMiroir);

    player1.setMyMana(10);
    assertFalse(imageMiroir.getPlayer().containsMinion(IMAGE_MIROIR));
    assertTrue(imageMiroir.canCastSpell());
    player1.setMyMinions(new ArrayList<ConcreteMinion>());
    assertTrue(player1.getMyMinions().isEmpty());

    imageMiroir.activateEffect(null);

    assertTrue(imageMiroir.getPlayer().containsMinion(IMAGE_MIROIR));
    for (int i = 0; i < 2; i++) {
      assertEquals(IMAGE_MIROIR, player1.getMyMinions().get(i).getName());
    }
    assertFalse(imageMiroir.canCastSpell());
  }

  @Test
  void metamorphoseTest() {
    metamorphose = new SpellMock(metamorphose);

    player1.setMyMana(10);
    assertFalse(player2.containsMinion(MOUTON));
    assertEquals(CHEF_DE_RAID, chefDeRaid.getName());
    assertTrue(metamorphose.canCastSpell());

    metamorphose.activateEffect(chefDeRaid);

    assertEquals(MOUTON, chefDeRaid.getName());
    assertTrue(player2.containsMinion(MOUTON));
    assertFalse(metamorphose.canCastSpell());
  }

  @Test
  void explosionDesArcanesTest() {
    player1.setMyMana(10);
    assertEquals(chefDeRaid.getMaxHealthPoints(), chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints(), chevaucheurDeLoup.getCurrentHealthPoints());
    assertTrue(explosionDesArcanes.canCastSpell());

    explosionDesArcanes.activateEffect(null);

    assertEquals(chefDeRaid.getMaxHealthPoints() - 1, chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints() - 1, chevaucheurDeLoup.getCurrentHealthPoints());
    assertFalse(explosionDesArcanes.canCastSpell());
  }

  @Test
  void consecrationTest() {
    player1.setMyMana(10);
    assertEquals(uther.getMaxHealthPoints(), uther.getCurrentHealthPoints());
    assertEquals(chefDeRaid.getMaxHealthPoints(), chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints(), chevaucheurDeLoup.getCurrentHealthPoints());
    assertTrue(consecration.canCastSpell());

    consecration.activateEffect(null);

    assertEquals(uther.getMaxHealthPoints() - 2, uther.getCurrentHealthPoints());
    assertEquals(chefDeRaid.getMaxHealthPoints() - 2, chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints() - 2, chevaucheurDeLoup.getCurrentHealthPoints());
    assertFalse(consecration.canCastSpell());
  }

  @Test
  void tourbillonTest() {
    player1.setMyMana(10);
    assertEquals(
            sanglierBrocheroc.getMaxHealthPoints(), sanglierBrocheroc.getCurrentHealthPoints());
    assertEquals(yetiNoroit.getMaxHealthPoints(), yetiNoroit.getCurrentHealthPoints());
    assertEquals(chefDeRaid.getMaxHealthPoints(), chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints(), chevaucheurDeLoup.getCurrentHealthPoints());
    assertTrue(tourbillon.canCastSpell());

    tourbillon.activateEffect(null);

    assertEquals(
            sanglierBrocheroc.getMaxHealthPoints() - 1, sanglierBrocheroc.getCurrentHealthPoints());
    assertEquals(yetiNoroit.getMaxHealthPoints() - 1, yetiNoroit.getCurrentHealthPoints());
    assertEquals(chefDeRaid.getMaxHealthPoints() - 1, chefDeRaid.getCurrentHealthPoints());
    assertEquals(
            chevaucheurDeLoup.getMaxHealthPoints() - 1, chevaucheurDeLoup.getCurrentHealthPoints());
    assertFalse(tourbillon.canCastSpell());
  }

  @Test
  void maitriseDuBlocageTest() {
    maitriseDuBlocage = new SpellMock(maitriseDuBlocage);

    player1.setMyMana(10);
    assertEquals(8, player1.getMyHand().size());
    assertEquals(0, jaina.getArmorPoints());
    assertTrue(maitriseDuBlocage.canCastSpell());

    maitriseDuBlocage.activateEffect(null);

    assertEquals(5, jaina.getArmorPoints());
    assertEquals(
            8,
            player1
                    .getMyHand()
                    .size()); // We add a new card, then spell "maitriseDuBlocage" is remove from hand
    assertFalse(maitriseDuBlocage.canCastSpell());
  }
}
