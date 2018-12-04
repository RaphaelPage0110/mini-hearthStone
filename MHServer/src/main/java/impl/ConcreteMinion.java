package impl;

import abstracts.CardType;
import abstracts.Minion;
import inter.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.MinionRepository;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class ConcreteMinion extends Minion {

    @Autowired
    private MinionRepository minionRepository ;

    public ConcreteMinion() {

    }

    public ConcreteMinion(String minionName, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<Effect> abilityKeyWord) {
        super();

        this.setName(minionName);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.type = type;
        this.myEffects = abilityKeyWord;
    }

    /**
     * @param minionKeyword
     * @return
     */
    public ConcreteMinion summon(String minionKeyword) {

        ConcreteMinion newMinion = minionRepository.findByName(minionKeyword);

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
