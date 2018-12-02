package impl;

import abstracts.CardType;
import abstracts.Minion;
import inter.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.MinionRepository;

import java.util.ArrayList;

public class ConcreteMinion extends Minion {

    @Autowired
    private MinionRepository minionRepository;

    public ConcreteMinion() {

    }

    public ConcreteMinion(String name, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<Effect> myActions) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.type = type;
        this.myEffects = myActions;
    }

    /**
     * TODO construct a minion using a keyword
     * @param minionKeyword
     * @return
     */
    public ConcreteMinion summon(String minionKeyword) {

        ConcreteMinion newMinion = minionRepository.findByName("Recrue de la main d'argent");

        CardType type = CardType.PALADIN;



        return newMinion;

    }

    @Override
    public void heal(int healthPoints) {
        this.setHealthPoints(Math.max(this.currentHealthPoints+healthPoints,this.maxHealthPoints));
    }

    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {

    }

    @Override
    public int getMaxHealthPoints() {
        return 0;
    }
}
