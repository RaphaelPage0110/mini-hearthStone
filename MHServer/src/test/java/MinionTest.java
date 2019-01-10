import abstracts.Minion;
import impl.ConcreteMinion;
import impl.EntitiesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static abstracts.CardType.COMMON;
import static abstracts.CardType.MAGE;
import static abstracts.CardType.PALADIN;
import static abstracts.CardType.WARRIOR;
import static org.junit.Assert.assertEquals;

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
    void maxHealthPointsTest() {

    }
}
