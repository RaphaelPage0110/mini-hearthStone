package abstracts;

import identifiers.IdMinion;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

/**
 * Abstract representation of a Minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Minion extends Card implements Target {

    /**
     * The ID of this Minion.
     */
    private IdMinion id;

    /**
     * Indicates the number of health points that this Minion has.
     */
    protected int healthPoints;

    /**
     * Indicates if the Minion can attack. Set to false by default, Charge behavior set it to true, and is also set to true at the next turn;
     */
    protected boolean canAttack;

    /**
     * Indicates if the Minion has Taunt behavior, which will be attacked as a priority.
     */
    protected boolean hasTaunt;

    /**
     * Default empty constructor which initializes the ID and the list of actions.
     */
    public Minion() {
        this.id = new IdMinion(this);
        this.myActions = new ArrayList<CardAction>();
    }

    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damagePoints) {
        this.healthPoints = this.healthPoints - damagePoints;
        return damagePoints;
    }

    /**
     * Returns the value of healthPoints.
     * @return this.healthPoints.
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * Sets the new value of healthPoints.
     * @param healthPoints the new value.
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Returns the value of id.
     * @return this.id.
     */
    public IdMinion getId() {
        return this.id;
    }

    /**
     * Returns the value of canAttack.
     * @return this.canAttack.
     */
    public boolean isCanAttack() {
        return this.canAttack;
    }

    /**
     * Sets the new value of canAttack.
     * @param canAttack the new value.
     */
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    /**
     * Returns the value of hasTaunt.
     * @return this.hasTaunt.
     */
    public boolean isHasTaunt() {
        return this.hasTaunt;
    }

    /**
     * Sets the new value of hasTaunt.
     * @param hasTaunt the new value.
     */
    public void setHasTaunt(boolean hasTaunt) {
        this.hasTaunt = hasTaunt;
    }

    /**
     * Add a bonus to the damage points.
     * @param bonus the number of points to add.
     */
    public void addDamagePoints(int bonus) {
        this.damagePoints += bonus;
    }

    public void attack(Target target) {
        int totalDamage;
        if(this.canAttack) {
            totalDamage = this.damagePoints + this.getPlayer().getMyAura();
            target.takeDamage(totalDamage);
            if(target instanceof Minion) {

                this.takeDamage(((Minion) target).getDamagePoints());

            }
        }
    }
}
