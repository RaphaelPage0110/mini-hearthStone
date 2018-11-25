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
     * @param _healthPoints the new value.
     */
    public void setHealthPoints(int _healthPoints) {
        this.healthPoints = _healthPoints;
    }

    /**
     * Returns the valu of id.
     * @return this.id.
     */
    public IdMinion getId() {
        return this.id;
    }

}
