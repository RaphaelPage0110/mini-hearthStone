package abstracts;

import impl.Hero;

public abstract class Card {

    public enum CardType {
        COMMON,
        PALADIN,
        MAGE,
        WARRIOR
    }
    protected CardType type;
    protected Hero hero;
    protected int requiredMana;
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

    public void getHero(Hero _hero) {
        return this.hero;
    }

    public void setRequiredMana(int _requiredMana) {
        this.requiredMana = _requiredMana;
    }

    public int getRequiredMana() {
        return this.requiredMana;
    }
    
    public void effect(Target myTarget) {
 			 for (CardAction action : myActions) {
 					 action.effect(myTarget);
 			 }
 	 }
}
