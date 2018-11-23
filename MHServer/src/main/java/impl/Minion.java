package impl;

import abstracts.Card;
import identifiers.IdMinion;
import inter.*;

public abstract class Minion extends Card implements Target {

    private IdMinion id;
    protected MinionAction myAction;
    protected int healthPoints;
    protected int damagePoints;
    protected int requiredMana;

    public Minion() {
        this.id = new IdMinion(this);
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getDamagePoints() {
        return this.damagePoints;
    }

    public int getRequiredMana() {
        return this.requiredMana;
    }

    public void setHealthPoints(int _healthPoints) {
        this.healthPoints = _healthPoints;
    }

    public void setDamagePoints(int _damagePoints) {
        this.damagePoints = _damagePoints;
    }

    public void setRequiredMana(int _requiredMana) {
        this.requiredMana = _requiredMana;
    }

    public int takeDamage(int damagePoints) {
        this.healthPoints = this.healthPoints - damagePoints;
        return damagePoints;
    }

    public void effect(Target myTarget) {
        myAction.effect(myTarget);
    }
}