package abstracts;

import identifiers.IdMinion;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

public abstract class Minion extends Card implements Target {

    private IdMinion id;
    protected int healthPoints;

    public Minion() {
        this.id = new IdMinion(this);
        this.myActions = new ArrayList<CardAction>();
    }

    public int takeDamage(int damagePoints) {
        this.healthPoints = this.healthPoints - damagePoints;
        return damagePoints;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int _healthPoints) {
        this.healthPoints = _healthPoints;
    }

    public IdMinion getId() {
        return id;
    }

}
