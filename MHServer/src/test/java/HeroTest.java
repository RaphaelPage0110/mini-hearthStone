import abstracts.Hero;
import impl.ConcreteHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


class HeroTest {

    private Hero warrior;


    @BeforeEach
    void setup() {
        Map<String, String> map =  new HashMap<>();
        map.put("ModifyArmor", "2");
        warrior = new ConcreteHero("Warrior", 30, 0, map);
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
    }
}