import abstracts.Minion;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MinionTest {

    private Minion chefDeRaid,
            championFrisselame,
            sanglierBrocheroc,
            soldatDuCompteDeLOr,
            yetiNoroit,
            chevaucheurDeLoup,
            avocatCommisDOffice,
            imageMiroir,
            mouton,
            recrueDeLaMainDArgent;

    private final String MINION_NAME[] = {
            "Chef de raid",
            "Champion frisselame",
            "Sanglier Brocheroc",
            "Soldat du comté de l'or",
            "Yéti noroit",
            "Chevaucheur de loup",
            "Avocat commis d'office",
            "Image miroir",
            "Mouton",
            "Recrue de la main d'argent"
    };

    @BeforeEach
    void setup() {
        EntitiesFactory entitiesFactory = new EntitiesFactory();

        chefDeRaid = entitiesFactory.createMinion(MINION_NAME[0]);
        championFrisselame = entitiesFactory.createMinion(MINION_NAME[1]);
        sanglierBrocheroc = entitiesFactory.createMinion(MINION_NAME[2]);
        soldatDuCompteDeLOr = entitiesFactory.createMinion(MINION_NAME[3]);
        yetiNoroit = entitiesFactory.createMinion(MINION_NAME[4]);
        chevaucheurDeLoup = entitiesFactory.createMinion(MINION_NAME[5]);
        avocatCommisDOffice = entitiesFactory.createMinion(MINION_NAME[6]);
        imageMiroir = entitiesFactory.createMinion(MINION_NAME[7]);
        mouton = entitiesFactory.createMinion(MINION_NAME[8]);
        recrueDeLaMainDArgent = entitiesFactory.createMinion(MINION_NAME[9]);
    }

    @Test
    void takeDamageTest() {
        assertEquals(yetiNoroit.getMaxHealthPoints(), yetiNoroit.getCurrentHealthPoints());
        assertEquals(5, yetiNoroit.getCurrentHealthPoints());
        assertFalse(yetiNoroit.isDead());

        assertEquals(0, yetiNoroit.takeDamage(0)); //takeDamage returns the damage taken
        assertEquals(5, yetiNoroit.getCurrentHealthPoints()); //This minion lost 0 HP
        assertFalse(yetiNoroit.isDead());

        assertEquals(1, yetiNoroit.takeDamage(1));
        assertEquals(4, yetiNoroit.getCurrentHealthPoints()); //This minion lost 1 HP
        assertFalse(yetiNoroit.isDead());

        assertEquals(0, yetiNoroit.takeDamage(-10));
        assertEquals(4, yetiNoroit.getCurrentHealthPoints());
        assertFalse(yetiNoroit.isDead());

        assertEquals(4, yetiNoroit.takeDamage(4)); //This minion is dead
        assertEquals(0, yetiNoroit.getCurrentHealthPoints());
        assertTrue(yetiNoroit.isDead());


        assertEquals(10, yetiNoroit.takeDamage(10)); //Even more dead !
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

        assertEquals(Integer.MAX_VALUE, avocatCommisDOffice.takeDamage(Integer.MAX_VALUE)); //Well, it hurts.
        assertEquals(Integer.MIN_VALUE + 7+1, avocatCommisDOffice.getCurrentHealthPoints());
    }
}
