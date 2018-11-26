package impl;

import abstracts.Minion;
import impl.behaviour.minion.Charge;
import impl.behaviour.minion.LifeSteal;
import inter.CardAction;

import java.util.ArrayList;

public class Champion extends Minion {

    public Champion() {
        super();
        myActions.add(new Charge(this));
        myActions.add(new LifeSteal(this));
        requiredMana = 4;
        damagePoints = 3;
        healthPoints = 2;
        type = CardType.PALADIN;
    }

    public Champion(int _requiredMana, int _damagePoints, int _healthPoints, CardType _type) {
        super();
        myActions.add(new Charge(this));
        myActions.add(new LifeSteal(this));
        requiredMana = _requiredMana;
        damagePoints = _damagePoints;
        healthPoints = _healthPoints;
        type = _type;
    }

    public Champion(ArrayList<CardAction> myActions, int requiredMana, int damagePoints, int healthPoints, CardType type) {
        super();
        this.myActions = myActions;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.type = type;
    }
}
