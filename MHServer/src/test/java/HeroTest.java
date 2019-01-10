import abstracts.Hero;
import impl.ConcreteHero;
import impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static abstracts.CardType.MAGE;
import static abstracts.CardType.PALADIN;
import static abstracts.CardType.WARRIOR;
import static org.junit.Assert.assertEquals;


class HeroTest {

    private Hero warrior, paladin, mage;
    private Player player1 = new Player(), player2 = new Player(), player3 = new Player();


    @BeforeEach
    void setup() {

        Map<String, String> map;

        map = new HashMap<>();
        map.put("modifyArmor","2");
        warrior = new ConcreteHero(WARRIOR,30,0,map,"Garrosh", "img");
        map.clear();
        map.put("damageTarget","1");
        mage = new ConcreteHero(MAGE,30,0,map,"Jaina","img");
        map.clear();
        map.put("summon","Recrue de la main d'argent");
        paladin = new ConcreteHero(PALADIN,30,0,map,"Uther","img");
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

    }

    @Test
    void heroTypeTest() {
        assertEquals(WARRIOR, warrior.getHeroType());
        assertEquals(MAGE, mage.getHeroType());
    }
}
