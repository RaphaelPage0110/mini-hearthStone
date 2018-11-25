package impl;

import abstracts.Minion;
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

    public Champion(ArrayList<CardAction> _myActions, int _requiredMana, int _damagePoints, int _healthPoints, CardType _type) {
        super();
        myActions = _myActions;
        requiredMana = _requiredMana;
        damagePoints = _damagePoints;
        healthPoints = _healthPoints;
        type = _type;
    }
}
