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
}
