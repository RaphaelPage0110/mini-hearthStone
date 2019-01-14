import static abstracts.Consts.*;
import impl.*;

import mocks.SpellMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SpellTest {

    private ConcreteSpell benedictionDePuissance,
            imageMiroir,
            metamorphose,
            explosionDesArcanes,
            consecration,
            tourbillon,
            maitriseDuBlocage;

    private ConcreteMinion sanglierBrocheroc,yetiNoroit, chefDeRaid, chevaucheurDeLoup;

    private Player player1, player2;

    @BeforeEach
    void setup() {
        EntitiesFactory entitiesFactory = new EntitiesFactory();

        //Spell creation
        benedictionDePuissance = entitiesFactory.createSpell(BENEDICTION_DE_PUISSANCE);
        imageMiroir = entitiesFactory.createSpell(IMAGE_MIROIR);
        metamorphose = entitiesFactory.createSpell(METAMORPHOSE);
        explosionDesArcanes = entitiesFactory.createSpell(EXPLOSION_DES_ARCANES);
        consecration = entitiesFactory.createSpell(CONSECRATION);
        tourbillon = entitiesFactory.createSpell(TOURBILLON);
        maitriseDuBlocage = entitiesFactory.createSpell(MAITRISE_DU_BLOCAGE);

        //Minion
        sanglierBrocheroc = entitiesFactory.createMinion(SANGLIER_BROCHEROC);
        yetiNoroit = entitiesFactory.createMinion(YETI_NOROIT);
        chefDeRaid = entitiesFactory.createMinion(CHEF_DE_RAID);
        chevaucheurDeLoup = entitiesFactory.createMinion(CHEVAUCHEUR_DE_LOUP);

        player1 = new Player();
        player2 = new Player();

        //Poorly managed bidirectional association (Player-Card)
        player1.addCardToHand(benedictionDePuissance); benedictionDePuissance.setPlayer(player1);
        player1.addCardToHand(imageMiroir); imageMiroir.setPlayer(player1);
        player1.addCardToHand(metamorphose); metamorphose.setPlayer(player1);
        player1.addCardToHand(explosionDesArcanes); explosionDesArcanes.setPlayer(player1);
        player1.addCardToHand(consecration); consecration.setPlayer(player1);
        player1.addCardToHand(tourbillon); tourbillon.setPlayer(player1);
        player1.addCardToHand(maitriseDuBlocage); maitriseDuBlocage.setPlayer(player1);


        //Poorly managed bidirectional association (Player-Card)
        player1.addMinion(sanglierBrocheroc); sanglierBrocheroc.setPlayer(player1);
        player1.addMinion(yetiNoroit); yetiNoroit.setPlayer(player1);

        player2.addMinion(chefDeRaid); chefDeRaid.setPlayer(player2);
        player2.addMinion(chevaucheurDeLoup); chevaucheurDeLoup.setPlayer(player2);

        player1.setOpponent(player2);

    }

    @Test
    void benedictionDePuissanceTest() {
        player1.setMyMana(10);
        assertEquals(1, sanglierBrocheroc.getDamagePoints());
        benedictionDePuissance.activateEffect(sanglierBrocheroc);
        assertEquals(4, sanglierBrocheroc.getDamagePoints());

        assertEquals(3, chevaucheurDeLoup.getDamagePoints());
        benedictionDePuissance.activateEffect(chevaucheurDeLoup);
        assertEquals(6, chevaucheurDeLoup.getDamagePoints());
    }

    @Test
    void imageMiroirTest() {
        imageMiroir = new SpellMock(imageMiroir);

        player1.setMyMana(10);
        assertFalse(imageMiroir.getPlayer().containsMinion(IMAGE_MIROIR));
        imageMiroir.activateEffect(null);
        assertTrue(imageMiroir.getPlayer().containsMinion(IMAGE_MIROIR));

        for (int i = 2; i < 4; i++) {
            assertEquals(IMAGE_MIROIR, player1.getMyMinions().get(i).getName());
        }
    }

    @Test
    void metamorphoseTest() {
        metamorphose = new SpellMock(metamorphose);

        player1.setMyMana(10);
        assertFalse(player2.containsMinion(MOUTON));
        assertTrue(metamorphose.canCastSpell());

        metamorphose.activateEffect(chefDeRaid);
        assertEquals(MOUTON, chefDeRaid.getName());

        assertTrue(player2.containsMinion(MOUTON));
    }

    @Test
    void explosionDesArcanesTest() {
        player1.setMyMana(10);
        assertEquals(chefDeRaid.getMaxHealthPoints(), chefDeRaid.getCurrentHealthPoints());
        assertEquals(chevaucheurDeLoup.getMaxHealthPoints(), chevaucheurDeLoup.getCurrentHealthPoints());

        explosionDesArcanes.activateEffect(null);

        assertEquals(chefDeRaid.getMaxHealthPoints() - 1, chefDeRaid.getCurrentHealthPoints());
        assertEquals(chevaucheurDeLoup.getMaxHealthPoints() -1, chevaucheurDeLoup.getCurrentHealthPoints());
    }

    /*@Test
    void giveDamageTest() {
        assertEquals(sanglierBrocheroc.getMaxHealthPoints(),sanglierBrocheroc.getCurrentHealthPoints());
        sanglierBrocheroc.takeDamage(explosionDesArcanes.getDamagePoints());
        assertEquals(0,sanglierBrocheroc.getCurrentHealthPoints());
        assertTrue(sanglierBrocheroc.isDead());

        yetiNoroit.takeDamage(tourbillon.getDamagePoints());
        assertEquals(4,yetiNoroit.getCurrentHealthPoints());

        assertFalse(yetiNoroit.isDead());

    }

    @Test
    void useManaTest() {

        assertEquals(player1.getMyManaMax(),player1.getMyMana());
        assertEquals(player1.getMyMana()-2,player1.getMyManaMax()-explosionDesArcanes.getRequiredMana());

        assertEquals(player1.getMyManaMax(),player1.getMyMana());
        assertEquals(player1.getMyMana()-1,player1.getMyManaMax()-tourbillon.getRequiredMana());

    }*/
}
