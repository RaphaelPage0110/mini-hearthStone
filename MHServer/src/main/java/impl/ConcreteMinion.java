package impl;

import abstracts.CardType;
import abstracts.Minion;
import inter.Effect;

import java.util.ArrayList;


public class ConcreteMinion extends Minion {


    public ConcreteMinion() {

    }

    public ConcreteMinion(String minionName, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<Effect> abilityKeyWord) {
        super();

        this.setName(minionName);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
        this.type = type;
        this.myEffects = abilityKeyWord;
    }

    @Override
    public void heal(int healthPoints) {
        this.setCurrentHealthPoints(Math.max(this.currentHealthPoints+healthPoints,this.maxHealthPoints));
    }

    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {
        this.maxHealthPoints += bonusHealtPoints;
    }

    @Override
    public void setMaxHealthPoints(int maxHealthPoints){
        this.maxHealthPoints = maxHealthPoints;
    }

    @Override
    public int getMaxHealthPoints() {
        return 0;
    }
}
