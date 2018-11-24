package impl;

import abstracts.Card;
import identifiers.IdMinion;
import inter.*;

import java.util.ArrayList;

public abstract class Minion extends Card implements Target {

    private IdMinion id;
    protected int healthPoints;
    protected int damagePoints;

    public Minion() {
        this.id = new IdMinion(this);
        this.myActions = new ArrayList<CardAction>();
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getDamagePoints() {
        return this.damagePoints;
    }

    public void setHealthPoints(int _healthPoints) {
        this.healthPoints = _healthPoints;
    }

    public void setDamagePoints(int _damagePoints) {
        this.damagePoints = _damagePoints;
    }

    public int takeDamage(int damagePoints) {
        this.healthPoints = this.healthPoints - damagePoints;
        return damagePoints;
    }


}
