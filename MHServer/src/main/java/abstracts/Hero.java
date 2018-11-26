package abstracts;

import identifiers.IdHero;
import inter.Target;

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
     * Indicates the number of health points that this Hero has.
     */
    private int healthPoints;

    /**
     * Indicates the number of armor points that this Hero has.
     */
    private int armorPoints;

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
     * {@inheritDoc}
     */
    public int takeDamage(int damagePoints) {
        //Add armor behavior
        int damages = damagePoints;
        this.healthPoints = this.healthPoints - damages;
        return damages;
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

    
}
