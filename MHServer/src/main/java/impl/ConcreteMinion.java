package impl;

import abstracts.Minion;
import inter.CardAction;

import java.util.ArrayList;

public class ConcreteMinion extends Minion {

    private String name;

    public ConcreteMinion(String _name, int _requiredMana, int _damagePoints, int _healthPoints, CardType _type, ArrayList<CardAction> _myActions) {
        super();
        this.name = _name;
        this.requiredMana = _requiredMana;
        this.damagePoints = _damagePoints;
        this.healthPoints = _healthPoints;
        this.type = _type;
        this.myActions = _myActions;
    }

}
