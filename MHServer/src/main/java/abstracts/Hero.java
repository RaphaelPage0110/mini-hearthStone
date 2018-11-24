package abstracts;

import identifiers.IdHero;
import inter.Target;

public abstract class Hero implements Target {

    private IdHero id;
    private int healthPoints;

    public int takeDamage(int damagePoints) {
        //Add armor behavior
        int damages = damagePoints;
        this.healthPoints = this.healthPoints - damages;
        return damages;
    }

    public void heal(int healingPoints) {
        this.healthPoints = this.healthPoints + healingPoints;
    }
}
