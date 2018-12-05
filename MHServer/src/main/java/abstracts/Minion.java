package abstracts;

import identifiers.IdMinion;
import impl.Player;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Abstract representation of a Minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Minion extends Card implements Target {

    /**
     * The ID of this Hero.
     */
    @Id
    protected String id;

    /**
     * Indicates the maximum number of health points that this Minion has.
     */
    protected int maxHealthPoints;

    /**
     * Indicates the current number of healt points this minion has.
     */
    protected int currentHealthPoints;

    /**
     * Indicates if the Minion can attack. Set to false by default, Charge behavior set it to true, and is also set to true at the next turn;
     */
    protected boolean canAttack;

    /**
     * Indicates if the Minion has Taunt behavior, which will be attacked as a priority.
     */
    protected boolean hasTaunt;

    /**
     * Reference the list of actions or behaviors of this minions when it dies.
     */
    protected ArrayList<Effect> myDeathRattles;

    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damageTaken) {
        this.currentHealthPoints = this.currentHealthPoints - damageTaken;
        return damageTaken;
    }

    /**
     * Returns the value of healthPoints.
     * @return this.healthPoints.
     */
    public int getCurrentHealthPoints() {
        return this.currentHealthPoints;
    }

    /**
     * Sets the new value of healthPoints.
     * @param healthPoints the new value.
     */
    public void setHealthPoints(int healthPoints) {
        this.currentHealthPoints = healthPoints;
    }

    /**
     * Returns the value of id.
     * @return this.id.
     */
    public String getId() {
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
     * @{@inheritDoc}
     */
    @Override
    public void addDamagePoints(int bonusDamage) {
        this.damagePoints += bonusDamage;
    }


    public ArrayList<Effect> getMyDeathRattles() {
        return myDeathRattles;
    }


    public void setMyDeathRattles(ArrayList<Effect> myDeathRattles) {
        this.myDeathRattles = myDeathRattles;
    }


    /**
     * what happens when the minion dies
     */
    @Override
    public void dies() {

        //we go through all the minion's death rattles
        for(Effect effect : myDeathRattles){
            effect.effect();
        }

        //we then remove it from the game
        Player player = this.getPlayer();
        player.removeMinionFromPlay(this);

    }


    /**
     * allows a minion to attack a target
     * @param target the target of the minion's attack
     */
    public void attack(Target target) {
        int totalDamage;
        if(this.canAttack) {

            totalDamage = this.damagePoints + this.getPlayer().getMyDamageAura();
            target.takeDamage(totalDamage);

            if(target instanceof Minion) {

                //the enemy retaliates
                int enemyDamagePoints = ((Minion)target).getDamagePoints();
                this.takeDamage(enemyDamagePoints);

                if(target.isDead()) {
                    target.dies();
                }

                if(this.isDead()) {

                    this.dies();

                }

            }

            //else the target is a hero
            else {

                if (target.isDead()) {

                    target.dies();

                }

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {

        return currentHealthPoints <= 0;

    }

    @Override
    public String toString() {
        return String.format(
                "Minion[id=%s, minionName='%s', maxHealthPoints='%s', damage='%s']",
                id, name, maxHealthPoints, damagePoints);
    }

}
