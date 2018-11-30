import abstracts.Hero;
import impl.ConcreteHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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
    }
}