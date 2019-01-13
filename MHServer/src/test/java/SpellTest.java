import static abstracts.Consts.*;
import abstracts.Card;
import impl.*;

import org.junit.jupiter.api.BeforeEach;

public class SpellTest {

    private ConcreteSpell benedictionDePuissance,
            imageMiroir,
            metamorphose,
            explosionDesArcanes,
            consecration,
            tourbillon,
            maitriseDuBlocage;

    private ConcreteMinion sanglierBrocheroc,yetiNoroit;

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

        player1 = new Player();

        //Poorly managed bidirectional association (Player-Card)
        player1.addCardToStock(benedictionDePuissance); benedictionDePuissance.setPlayer(player1);
        player1.addCardToStock(imageMiroir); imageMiroir.setPlayer(player1);
        player1.addCardToStock(metamorphose); metamorphose.setPlayer(player1);
        player1.addCardToStock(explosionDesArcanes); explosionDesArcanes.setPlayer(player1);
        player1.addCardToStock(consecration); consecration.setPlayer(player1);
        player1.addCardToStock(tourbillon); tourbillon.setPlayer(player1);
        player1.addCardToStock(maitriseDuBlocage); maitriseDuBlocage.setPlayer(player1);


        //Poorly managed bidirectional association (Player-Card)
        player1.addMinion(sanglierBrocheroc); sanglierBrocheroc.setPlayer(player1);
        player1.addMinion(yetiNoroit); yetiNoroit.setPlayer(player1);

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
