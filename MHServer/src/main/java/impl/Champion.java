package impl;

import abstracts.CardType;
import abstracts.Minion;
import impl.behaviour.minion.Charge;
import impl.behaviour.minion.LifeSteal;
import inter.Effect;

import java.util.ArrayList;

public class Champion extends Minion {

    public Champion() {
        super();
        myEffects.add(new Charge(this));
        myEffects.add(new LifeSteal(this));
        requiredMana = 4;
        damagePoints = 3;
        maxHealthPoints = 2;
        type = abstracts.CardType.PALADIN;
    }

    public Champion(int _requiredMana, int _damagePoints, int _healthPoints, CardType _type) {
        super();
        myEffects.add(new Charge(this));
        myEffects.add(new LifeSteal(this));
        requiredMana = _requiredMana;
        damagePoints = _damagePoints;
        maxHealthPoints = _healthPoints;
        type = _type;
    }

    public Champion(ArrayList<Effect> myActions, int requiredMana, int damagePoints, int healthPoints, CardType type) {
        super();
        this.myEffects = myActions;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.type = type;
    }

    @Override
    public void heal(int healthPoints) {

    }

    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {

    }

    @Override
    public int getMaxHealthPoints() {
        return 0;
    }
}
