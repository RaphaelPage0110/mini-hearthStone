import abstracts.Hero;
import com.sun.javaws.exceptions.InvalidArgumentException;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static abstracts.CardType.MAGE;
import static abstracts.CardType.PALADIN;
import static abstracts.CardType.WARRIOR;
import static org.junit.Assert.assertEquals;


class HeroTest {

    private Hero warrior, paladin, mage;
    private Player player1 = new Player(), player2 = new Player(), player3 = new Player();


    @BeforeEach
    void setup() throws InvalidArgumentException {

        player1.chooseHero(WARRIOR);
        player2.chooseHero(MAGE);
        player3.chooseHero(PALADIN);

        warrior = player1.getMyHero();
        mage = player2.getMyHero();
        paladin = player3.getMyHero();
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
    void heroPowerTest() {
        assertEquals(0,warrior.getArmorPoints());
        warrior.heroPower();
        assertEquals(2,warrior.getArmorPoints());

        assertEquals(mage.getMaxHealthPoints(), mage.getCurrentHealthPoints());
        mage.heroPower(mage);
        assertEquals(mage.getMaxHealthPoints()-1, mage.getCurrentHealthPoints());

        assertEquals(0, player3.getMyMinions().size());
        paladin.heroPower();
        //assertEquals(1, player3.getMyMinions().size());
    }

    @Test
    void heroTypeTest() {
        assertEquals(WARRIOR, warrior.getHeroType());
        assertEquals(MAGE, mage.getHeroType());
    }
}