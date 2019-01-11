import abstracts.Card;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import impl.EntitiesFactory;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
public class SpellTest {

    private ConcreteSpell benedictionDePuissance,
            imageMiroir,
            metamorphose,
            explosionDesArcanes,
            consecration,
            tourbillon,
            maitriseDuBlocage;

    private ConcreteMinion sanglierBrocheroc;

    private final String SPELL_NAME[] = {
            "Bénédiction de puissance",
            "Image miroir",
            "Métamorphose",
            "Explosion des arcanes",
            "Consécration",
            "Tourbillon",
            "Maîtrise du blocage"
    };

    private final String MINION_NAME[] = {
            "Sanglier Brocheroc"
    };

    private Player player;

    EntitiesFactory entitiesFactory;

    @BeforeEach
    void setup() {
        entitiesFactory = new EntitiesFactory();

        //Spell
        benedictionDePuissance = entitiesFactory.createSpell(SPELL_NAME[0]);
        imageMiroir = entitiesFactory.createSpell(SPELL_NAME[1]);
        metamorphose = entitiesFactory.createSpell(SPELL_NAME[2]);
        explosionDesArcanes = entitiesFactory.createSpell(SPELL_NAME[3]);
        consecration = entitiesFactory.createSpell(SPELL_NAME[4]);
        tourbillon = entitiesFactory.createSpell(SPELL_NAME[5]);
        maitriseDuBlocage = entitiesFactory.createSpell(SPELL_NAME[6]);

        //Minion
        sanglierBrocheroc = entitiesFactory.createMinion(MINION_NAME[0]);



        player = new Player();
        //Spell
        player.addCardToStock(benedictionDePuissance); benedictionDePuissance.setPlayer(player);
        player.addCardToStock(imageMiroir); imageMiroir.setPlayer(player);
        player.addCardToStock(metamorphose); metamorphose.setPlayer(player);
        player.addCardToStock(explosionDesArcanes); explosionDesArcanes.setPlayer(player);
        player.addCardToStock(consecration); consecration.setPlayer(player);
        player.addCardToStock(tourbillon); tourbillon.setPlayer(player);
        player.addCardToStock(maitriseDuBlocage); maitriseDuBlocage.setPlayer(player);

        for (Card  spell : player.getMyStock()) {
            ((ConcreteSpell)spell).generateEffect();
        }
        //Minion
        player.addMinion(sanglierBrocheroc); sanglierBrocheroc.setPlayer(player);

    }

    @Test
    void giveDamageTest() {
        assertEquals(sanglierBrocheroc.getMaxHealthPoints(),sanglierBrocheroc.getCurrentHealthPoints());


    }
}
