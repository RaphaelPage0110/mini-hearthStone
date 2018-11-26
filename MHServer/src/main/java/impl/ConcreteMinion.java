package impl;

import abstracts.Minion;
import inter.CardAction;

import java.util.ArrayList;

public class ConcreteMinion extends Minion {

    public ConcreteMinion(String name, int requiredMana, int damagePoints, int healthPoints, CardType type, ArrayList<CardAction> myActions) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
        this.myActions = myActions;
    }

}
