package impl;

import abstracts.Minion;
import impl.behaviour.minion.Charge;
import inter.Effect;

import java.util.ArrayList;

public class WolfRider extends Minion {

    public WolfRider() {
        super();
        myEffects.add(new Charge(this));
        requiredMana = 3;
        damagePoints = 3;
        maxHealthPoints = 1;
        type = CardType.COMMON;
    }

    public WolfRider(int requiredMana, int damagePoints, int maxHealthPoints, CardType type) {
        super();
        myEffects.add(new Charge(this));
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = maxHealthPoints;
        this.type = type;
    }

    public WolfRider(ArrayList<Effect> myActions, int requiredMana, int damagePoints, int maxHealthPoints, CardType type) {
        super();
        this.myEffects = myActions;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = maxHealthPoints;
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
