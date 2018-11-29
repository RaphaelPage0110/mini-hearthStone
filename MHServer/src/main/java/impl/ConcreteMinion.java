package impl;

import abstracts.Minion;
import inter.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.MinionRepository;

import java.util.ArrayList;

public class ConcreteMinion extends Minion {

    @Autowired
    private MinionRepository minionRepository ;

    public ConcreteMinion() {

    }

    public ConcreteMinion(String name, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<Effect> myActions) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
        this.myEffects = myActions;
    }

    /**
     * TODO construct a minion using a keyword
     * @param minionKeyword
     * @return
     */
    public ConcreteMinion summon(String minionKeyword) {

        ConcreteMinion newMinion = minionRepository.findByMinionName("Recrue de la main d'argent");

        CardType type = CardType.PALADIN;
        ConcreteMinion minion = new ConcreteMinion("serviteur main argent", 2, 1,1, type, null ) ;

        return minion;

    }

}
