package abstracts;

import impl.ConcreteMinion;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

/**
 * Abstract representation of a Minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Minion extends Card implements Target {


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
    protected boolean canAttack = false;

    /**
     * Indicates if the Minion has Taunt behavior, which will be attacked as a priority.
     */
    protected boolean hasTaunt = false;

    /**
     * Indicates if the Minion has lifesteal
     */
    protected boolean hasLifesteal = false;

    /**
     * Reference the list of actions or behaviors of this minions when it dies.
     */
    protected ArrayList<Effect> myDeathRattles;

    /**
     * Default empty constructor which initializes the list of actions and death rattle (dying actions).
     */
    public Minion() {
        this.myEffects = new ArrayList<>();
        this.myDeathRattles = new ArrayList<>();
    }

    /**
     * Add a new effect to the list of death rattle.
     * @param deathEffect the new value to add.
     * @return true if the effect has been added to myDeathRattle.
     */
    public boolean addDeathRattle(Effect deathEffect) {
        return this.myDeathRattles.add(deathEffect);
    }

    /**
     * Add a list of effects to the list myDeathRattle.
     * @param deathEffects the new list to add
     * @return true if all values has been added to myDeathRattle.
     */
    public boolean addAllDeathRattle(ArrayList<Effect> deathEffects) {
        return this.myDeathRattles.addAll(deathEffects);
    }
    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damageTaken) {
        this.currentHealthPoints = this.currentHealthPoints - damageTaken;

        if(this.isDead()) {

            this.dies();

        }

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
    public void setCurrentHealthPoints(int healthPoints) {
        this.currentHealthPoints = healthPoints;
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

        if(this.canAttack) {
            int totalDamageDealt = this.damagePoints + this.getPlayer().getMyDamageAura();
            if(target instanceof ConcreteMinion) {
                int totalDamageReceived = ((Minion) target).getDamagePoints() + ((Minion) target).getPlayer().getMyDamageAura();
                target.takeDamage(totalDamageDealt);
                //the enemy retaliates
                this.takeDamage(totalDamageReceived);

                //if the target has lifeSteal, then its hero is healed
                if(((ConcreteMinion) target).isHasLifesteal()){
                    ((ConcreteMinion) target).getPlayer().getMyHero().heal(totalDamageReceived);
                }
            }

            //else the target is a hero
            else {

                target.takeDamage(totalDamageDealt);

            }

            //if the minion has lifesteal, then his player's hero is healed
            if (this.isHasLifesteal()){
                player.getMyHero().heal(totalDamageDealt);
            }
            this.setCanAttack(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {

        return currentHealthPoints <= 0;

    }

    public boolean isHasLifesteal() {
        return hasLifesteal;
    }

    public void setHasLifesteal(boolean hasLifesteal) {
        this.hasLifesteal = hasLifesteal;
    }

}
