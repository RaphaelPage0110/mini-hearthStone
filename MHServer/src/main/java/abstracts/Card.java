package abstracts;

import impl.Player;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

public abstract class Card {

    public enum CardType {
        COMMON,
        PALADIN,
        MAGE,
        WARRIOR;
    }

    protected CardType type;
    protected Player player;
    protected int requiredMana;
    protected int damagePoints;
    protected ArrayList<CardAction> myActions;


    public CardType getType() {
        return this.type;
    }

    public void setType(CardType _type) {
        this.type = _type;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player _player) {
        this.player = _player;
    }

    public int getRequiredMana() {
        return this.requiredMana;
    }

    public void setRequiredMana(int _requiredMana) {
        this.requiredMana = _requiredMana;
    }

    public int getDamagePoints() {
        return this.damagePoints;
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
