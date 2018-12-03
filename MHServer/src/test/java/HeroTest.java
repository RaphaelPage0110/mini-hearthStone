import abstracts.Hero;
import com.sun.javaws.exceptions.InvalidArgumentException;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static abstracts.CardType.MAGE;
import static abstracts.CardType.WARRIOR;
import static org.junit.Assert.assertEquals;


class HeroTest {

    private Hero warrior, mage;
    private Player player1 = new Player(), player2 = new Player();


    @BeforeEach
    void setup() throws InvalidArgumentException {

        player1.chooseHero("Garrosh");
        player2.chooseHero("Jaina");

        warrior = player1.getMyHero();
        mage = player2.getMyHero();
    }

    @Test
    void maxHealthPointsTest() {
        assertEquals(30, warrior.getMaxHealthPoints());

        warrior.addMaxHealthPoints(10);
        assertEquals(40, warrior.getMaxHealthPoints());

        warrior.takeDamage(20);
        assertEquals(40, warrior.getMaxHealthPoints());
    }

    @Test
    void currentHealthPoints() {
        assertEquals(warrior.getMaxHealthPoints(), warrior.getCurrentHealthPoints());

        warrior.takeDamage(20);
        assertEquals(10, warrior.getCurrentHealthPoints());

        warrior.heal(10);
        assertEquals(20, warrior.getCurrentHealthPoints());
    }

    @Test
    void armorTest() {
        assertEquals(0,warrior.getArmorPoints());

        warrior.heroPower();
        assertEquals(2,warrior.getArmorPoints());

        mage.heroPower();
        assertEquals(0, mage.getArmorPoints());
    }

    @Test
    void heroTypeTest() {
        assertEquals(WARRIOR, warrior.getHeroType());
        assertEquals(MAGE, mage.getHeroType());
    }
}