package abstracts;

import impl.Player;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

/**
 * Abstract representation of Minions and Spells.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Card {

    /**
     * A basic enumeration that represents the type of a card.
     */
    public enum CardType {
        COMMON,
        PALADIN,
        MAGE,
        WARRIOR;
    }

    /**
     * Indicates the type of this card that can be COMMON, PALADIN, MAGE, or WARRIOR.
     */
    protected CardType type;

    /**
     * Indicates the player who have this card.
     */
    protected Player player;

    /**
     * Indicates the mana cost of this card.
     */
    protected int requiredMana;

    /**
     * Indicates the points of damage that this card can inflict.
     */
    protected int damagePoints;

    /**
     * The name of the card
     */
    private String name;

    /**
     * Reference the list of actions or behaviors of this card.
     */
    protected ArrayList<CardAction> myActions;

    /**
     * Allows a Minion or a Spell to give a target a bonus of damage, armor, health, or mana.
     */
    protected int bonus;


    /**
     * Returns the value of name.
     * @return this.name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the new value of name.
     * @param name the new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the value of type.
     * @return this.type.
     */
    public CardType getType() {
        return this.type;
    }

    /**
     * Sets the new value of type.
     * @param type the new value.
     */
    public void setType(CardType type) {
        this.type = type;
    }

    /**
     * Returns the value of player.
     * @return this.player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the new value of player.
     * @param player the new value.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the value of requiredMana.
     * @return this.requiredMana.
     */
    public int getRequiredMana() {
        return this.requiredMana;
    }

    /**
     * Sets the new value of requiredMana.
     * @param requiredMana the new value.
     */
    public void setRequiredMana(int requiredMana) {
        this.requiredMana = requiredMana;
    }

    /**
     * Returns the value of damagePoints.
     * @return this.damagePoints.
     */
    public int getDamagePoints() {
        return this.damagePoints;
    }

    /**
     * Sets the new value of damagePoints.
     * @param damagePoints the new value.
     */
    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    /**
     * Returns the value of myActions.
     * @return this.myActions.
     */
    public ArrayList<CardAction> getMyActions() {
        return this.myActions;
    }

    /**
     * Sets the new value of myActions.
     * @param newActions the new value.
     */
    public void setMyActions(ArrayList<CardAction> newActions) {
        this.myActions = newActions;
    }

    /**
     * Returns the value of bonus.
     * @return this.bonus.
     */
    public int getBonus() {
        return this.bonus;
    }

    /**
     * Sets the new value of bonus.
     * @param bonus the new value.
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * Affects the effect that this card wil have, on one or more targets.
     * @param myTarget the affected target.
     */
    public void effect(Target myTarget) {
        for (CardAction action : myActions) {
            action.effect(myTarget);
        }
    }
}
