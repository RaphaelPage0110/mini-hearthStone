import abstracts.Minion;
import impl.ConcreteMinion;
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

    private Minion sanglier,soldat,chevaucheur,chef,yetiRenoi;

    @BeforeEach
    void setup() {
        Map<String, String> map;

        map = new HashMap<>();
        map.put("taunt", "");
        soldat = new ConcreteMinion(COMMON,1,1,2,map, null, "soldat du comté de l'or", "img", "");
    }
    @Test
    void test() {

    }
}
