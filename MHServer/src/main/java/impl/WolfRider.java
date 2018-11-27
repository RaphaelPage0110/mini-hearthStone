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
        healthPoints = 1;
        type = CardType.COMMON;
    }

    public WolfRider(int requiredMana, int damagePoints, int healthPoints, CardType type) {
        super();
        myEffects.add(new Charge(this));
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
    }

    public WolfRider(ArrayList<Effect> myActions, int requiredMana, int damagePoints, int healthPoints, CardType type) {
        super();
        this.myEffects = myActions;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
    }

}
