package abstracts;

import impl.Player;
import inter.Effect;
import inter.Target;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Abstract representation of Minions and Spells.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Card {


    /**
     * The ID of this type of Card.
     */
    @Id
    protected String id;

    private Map<String,String> abilityKeyWord;

    public Map<String, String> getAbilityKeyWord() {
        return abilityKeyWord;
    }

    public void setAbilityKeyWord(Map<String, String> abilityKeyWord) {
        this.abilityKeyWord = abilityKeyWord;
    }

    /**
     * the unique identifier of this card
     */
    protected String uniqueID;

    protected String text;


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
    protected String name;

    /**
     * Reference the list of actions or behaviors of this card.
     */
    protected ArrayList<Effect> myEffects;

    /**
     * Allows a Minion or a Spell to give a target a bonus of damage, armor, health, or mana.
     */
    protected int bonus;

    protected String imgurl;

    /**
     * Returns the value of id.
     * @return this.id.
     */
    public String getId() {
        return this.id;
    }

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
     * Returns the value of myEffects.
     * @return this.myEffects.
     */
    public ArrayList<Effect> getMyEffects() {
        return this.myEffects;
    }

    /**
     * Sets the new value of myEffects.
     * @param newActions the new value.
     */
    public void setMyEffects(ArrayList<Effect> newActions) {
        this.myEffects = newActions;
    }

    /**
     * Add a new effect to the list myEffects.
     * @param newEffect the new Effect to add.
     */
    public void addEffect(Effect newEffect) {
        myEffects.add(newEffect);
    }

    /**
     * Add a list of effects to the list myEffects.
     * @param effectList the new list to add.
     * @return true if all values has been added to myEffects.
     */
    public boolean addAllEffects(ArrayList<Effect> effectList) {
        return myEffects.addAll(effectList);
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

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * Affects the effect that this card wil have, on one or more targets.
     * @param myTarget the affected target.
     */
    public void effect(Target myTarget) {
        for (Effect action : myEffects) {
            action.effect(myTarget);
        }
    }

    public Card(){
        String uniqueID = UUID.randomUUID().toString();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID() {
        uniqueID = UUID.randomUUID().toString();
    }

}
