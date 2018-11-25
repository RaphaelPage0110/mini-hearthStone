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
     * Default empty constructor which initializes the ID.
     */
    public Hero() {
      this.id = new IdHero(this);
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
}
