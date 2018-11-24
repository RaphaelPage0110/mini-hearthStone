package impl;

import abstracts.Card;
import identifiers.IdMinion;
import inter.*;

import java.util.ArrayList;

public abstract class Minion extends Card implements Target {

    private IdMinion id;

    public Minion() {
        this.id = new IdMinion(this);
        this.myActions = new ArrayList<CardAction>();
    }

    public int takeDamage(int damagePoints) {
        this.healthPoints = this.healthPoints - damagePoints;
        return damagePoints;
    }


}
