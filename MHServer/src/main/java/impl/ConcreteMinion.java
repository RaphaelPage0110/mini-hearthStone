package impl;

import abstracts.Minion;
import inter.Effect;

import java.util.ArrayList;

public class ConcreteMinion extends Minion {

    public ConcreteMinion(String name, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<Effect> myActions) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
        this.myEffects = myActions;
    }

}
