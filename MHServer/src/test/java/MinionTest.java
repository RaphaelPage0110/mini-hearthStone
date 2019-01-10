import abstracts.Minion;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import impl.Player;
import inter.Effect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MinionTest {

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

    private Player player;

    private EntitiesFactory entitiesFactory;

    @BeforeEach
    void setup() {
        entitiesFactory = new EntitiesFactory();

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


        //Poorly managed bidirectional association (Player-Card)
        player = new Player();
        player.addMinion(chefDeRaid); chefDeRaid.setPlayer(player);
        player.addMinion(championFrisselame); championFrisselame.setPlayer(player);
        player.addMinion(sanglierBrocheroc); sanglierBrocheroc.setPlayer(player);
        player.addMinion(soldatDuCompteDeLOr); soldatDuCompteDeLOr.setPlayer(player);
        player.addMinion(yetiNoroit); yetiNoroit.setPlayer(player);
        player.addMinion(chevaucheurDeLoup); chevaucheurDeLoup.setPlayer(player);
        player.addMinion(avocatCommisDOffice); avocatCommisDOffice.setPlayer(player);
        player.addMinion(imageMiroir); imageMiroir.setPlayer(player);
        player.addMinion(mouton); mouton.setPlayer(player);
        player.addMinion(recrueDeLaMainDArgent); recrueDeLaMainDArgent.setPlayer(player);

        for (ConcreteMinion minion : player.getMyMinions()) {
            minion.generateEffect();
        }

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

    @Test
    void diesTest() {
        assertTrue(sanglierBrocheroc.getMyDeathRattles().isEmpty()); //This minion doesn't have a Death Rattle. i.e. an effect when he dies.
        assertTrue(player.getMyMinions().contains(sanglierBrocheroc));
        sanglierBrocheroc.dies();
        assertFalse(player.getMyMinions().contains(sanglierBrocheroc));

        assertTrue(recrueDeLaMainDArgent.getMyDeathRattles().isEmpty());
        assertTrue(player.getMyMinions().contains(recrueDeLaMainDArgent));
        recrueDeLaMainDArgent.takeDamage(recrueDeLaMainDArgent.getMaxHealthPoints());
        assertTrue(recrueDeLaMainDArgent.isDead());
        assertFalse(player.getMyMinions().contains(recrueDeLaMainDArgent));

        /*-----Raid Leader dies without having activated his effect-----*/
        assertFalse(chefDeRaid.getMyDeathRattles().isEmpty()); //This minion have a Death Rattle.
        assertTrue(player.getMyMinions().contains(chefDeRaid));
        //In this case, we don't activate the effect "+1 attack" !
        assertEquals(0, player.getMyDamageAura());
        assertEquals(4, yetiNoroit.getDamagePoints());
        chefDeRaid.dies();
        assertEquals(-1, player.getMyDamageAura()); //The bonus effect wasn't activated ! So there is an attack malus.
        assertFalse(chefDeRaid.isDead()); //Also, this minion is not really dead.


        /*-----Raid Leader dies without after activating the bonus-----*/
        player.setMyDamageAura(0);
        entitiesFactory = new EntitiesFactory();
        chefDeRaid = entitiesFactory.createMinion(MINION_NAME[0]);
        player.addMinion(chefDeRaid); chefDeRaid.setPlayer(player);

        chefDeRaid.generateEffect();

        for (Effect effect : chefDeRaid.getMyEffects()) {
            effect.effect();
        }

        assertFalse(chefDeRaid.getMyDeathRattles().isEmpty());
        assertTrue(player.getMyMinions().contains(chefDeRaid));
        assertEquals(1, player.getMyDamageAura());
        assertEquals(4, yetiNoroit.getDamagePoints()); //We don't actually boost this property, but only the Player's aura
        chefDeRaid.dies();
        assertEquals(0, player.getMyDamageAura()); //Removing the bonus.
        assertFalse(chefDeRaid.isDead());
    }
}
