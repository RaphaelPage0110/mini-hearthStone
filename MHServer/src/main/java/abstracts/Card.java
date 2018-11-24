package abstracts;

import impl.Hero;

public abstract class Card {

    public enum CardType {
        COMMON,
        PALADIN,
        MAGE,
        WARRIOR;
    }
    protected CardType type;
    protected Hero hero;
    protected int requiredMana;
    protected int healthPoints;
    protected int damagePoints;
    protected ArrayList<CardAction> myActions;

    public CardType getType() {
        return this.type;
    }

    public void setType(CardType _type) {
        this.type = _type;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero _hero) {
        this.hero = _hero;
    }

    public void setRequiredMana(int _requiredMana) {
        this.requiredMana = _requiredMana;
    }

    public int getRequiredMana() {
        return this.requiredMana;
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

    public void effect(Target myTarget) {
 			 for (CardAction action : myActions) {
 					 action.effect(myTarget);
 			 }
 	 }
}
