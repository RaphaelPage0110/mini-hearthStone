package impl;

import abstracts.Minion;
import inter.CardAction;

import java.util.ArrayList;

public class WolfRider extends Minion {

    public WolfRider() {
        super();
        myActions.add(new Charge(this));
        requiredMana = 3;
        damagePoints = 3;
        healthPoints = 1;
        type = CardType.COMMON;
    }

    public WolfRider(int _requiredMana, int _damagePoints, int _healthPoints, CardType _type) {
        super();
        myActions.add(new Charge(this));
        requiredMana = _requiredMana;
        damagePoints = _damagePoints;
        healthPoints = _healthPoints;
        type = _type;
    }

    public WolfRider(ArrayList<CardAction> _myActions, int _requiredMana, int _damagePoints, int _healthPoints, CardType _type) {
        super();
        myActions = _myActions;
        requiredMana = _requiredMana;
        damagePoints = _damagePoints;
        healthPoints = _healthPoints;
        type = _type;
    }

}
