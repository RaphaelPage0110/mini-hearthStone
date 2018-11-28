package abstracts;

import identifiers.IdHero;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

/**
 * Abstract representation of a Hero.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Hero implements Target {

    /**
     * The ID of this Hero.
     */
    private IdHero id;

    /**
     *  The name of the hero
     */
    private String heroName;

    /**
     * Indicates the number of health points that this Hero has.
     */
    private int healthPoints;

    /**
     * Indicates the number of armor points that this Hero has.
     */
    private int armorPoints;

    /**
     * Reference the list of actions or behaviors of this card.
     */
    protected Effect myEffect;

    protected Player myPlayer;

    /**
     * Returns value of id
     * @return id the id of the hero
     */
    public IdHero getId() {
        return id;
    }

    /**
     * Returns value of healthPoints
     * @return healthPoints the current health points of the hero
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Sets new value of healthPoints
     * @param hp the health points of the hero
     */
    public void setHealthPoints(int hp) {
        this.healthPoints = hp;
    }

    /**
     * Returns value of healthPoints
     * @return healthPoints the current health points of the hero
     */
    public int getArmorPoints() {
        return armorPoints;
    }

    /**
     * Sets new value of armorpoints
     * @param armor the armor points of the hero
     */
    public void setArmorPoints(int armor) {
        this.armorPoints = armor;
    }

    /**
     * Returns value of heroName
     * @return heroName the name of the hero
     */
    public String getHeroName() {
        return heroName;
    }

    /**
     * Sets new value of heroName
     * @param heroName the name of the hero
     */
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    /**
     * Returns the special ability of a hero
     * @return myEffect the ability of a hero
     */
    public Effect getMyEffect() {
        return myEffect;
    }

    /**
     * Sets the new value of myEffects
     * @param myEffect the special ability of a hero
     */
    public void setMyEffect(Effect myEffect) {
        this.myEffect = myEffect;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }


    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damageTaken) {
        //Add armor behavior
        this.healthPoints = this.healthPoints - damageTaken;
        return damageTaken;
    }

    /**
     * Allows to give a few health points back to a living hero.
     * @param healingPoints the number of health points to be returned.
     */
    public void heal(int healingPoints) {
        this.healthPoints = this.healthPoints + healingPoints;
    }

    public void addArmor(int armor) {
        this.armorPoints += armor;
    }

    /**
     * check if the hero is dead
     * @return true if the hero is dead
     */
    public boolean isDead() {

        return healthPoints <= 0;

    }

    /**
     * what happens when the hero dies
     */
    public void dies() {

        myPlayer.lost();

    }

    
}
