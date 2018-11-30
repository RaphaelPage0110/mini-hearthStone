import abstracts.Hero;
import impl.ConcreteHero;
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


    @BeforeEach
    void setup() {
        Map<String, String> map =  new HashMap<>();
        map.put("ModifyArmor", "2");
        warrior = new ConcreteHero(WARRIOR, 30, 0, map);
        map = new HashMap<>();
        map.put("Summon", "Recrue de la main d'argent");
        paladin = new ConcreteHero(PALADIN, 30, 0, map);
        map = new HashMap<>();
        map.put("DamageTarget", "1");
        mage = new ConcreteHero(MAGE, 30, 0, map);
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
        assertEquals(PALADIN, paladin.getHeroType());
        assertEquals(MAGE, mage.getHeroType());
    }
}