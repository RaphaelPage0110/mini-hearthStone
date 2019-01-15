package impl;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Effect;
import impl.behaviour.generic.notTargetedEffect.BuffAlliedMinions;
import impl.behaviour.generic.notTargetedEffect.RemoveAura;
import impl.behaviour.minion.Charge;
import impl.behaviour.minion.LifeSteal;
import impl.behaviour.minion.Taunt;
import inter.Target;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import static abstracts.Consts.*;

public class ConcreteMinion extends Card implements Cloneable, Target {

    private Map<String, String> deathRattleKeyWords;

    /**
     * Indicates the maximum number of health points that this Minion has.
     */
    private int maxHealthPoints;

    /**
     * Indicates the current number of healt points this minion has.
     */
    private int currentHealthPoints;

    /**
     * Indicates if the Minion can attack. Set to false by default, Charge behavior set it to true,
     * and is also set to true at the next turn;
     */
    private boolean canAttack = false;

    /**
     * Indicates if the Minion has Taunt behavior, which will be attacked as a priority.
     */
    private boolean hasTaunt = false;

    /**
     * Indicates if the Minion has lifesteal
     */
    private boolean hasLifesteal = false;

    /**
     * Reference the list of actions or behaviors of this minions when it dies.
     */
    private ArrayList<Effect> myDeathRattles;

    /**
     * Indicates the points of damage that this card can inflict.
     */
    private int damagePoints;

    public ConcreteMinion(
            CardType type,
            int requiredMana,
            int damagePoints,
            int healthPoints,
            Map<String, String> abilityKeyWord,
            Map<String, String> deathRattle,
            String name,
            String imgurl,
            String text) {
        super(type, requiredMana, abilityKeyWord, name, imgurl, text);
        this.myDeathRattles = new ArrayList<>();
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
        this.deathRattleKeyWords = deathRattle;

        generateEffect();
    }

    /**
     * Default empty constructor of ConcreteMinion.
     */
    public ConcreteMinion() {
    }

    public Map<String, String> getDeathRattleKeyWords() {
        return deathRattleKeyWords;
    }

    public void setDeathRattleKeyWords(Map<String, String> deathRattle) {
        this.deathRattleKeyWords = deathRattle;
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

    @Override
    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getCurrentHealthPoints() {
        return this.currentHealthPoints;
    }

    public void setCurrentHealthPoints(int healthPoints) {
        this.currentHealthPoints = healthPoints;
    }

    public boolean isCanAttack() {
        return this.canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean isHasTaunt() {
        return this.hasTaunt;
    }

    public void setHasTaunt(boolean hasTaunt) {
        this.hasTaunt = hasTaunt;
    }

    public ArrayList<Effect> getMyDeathRattles() {
        return myDeathRattles;
    }

    public void setMyDeathRattles(ArrayList<Effect> myDeathRattles) {
        this.myDeathRattles = myDeathRattles;
    }

    public boolean isHasLifesteal() {
        return hasLifesteal;
    }

    public void setHasLifesteal(boolean hasLifesteal) {
        this.hasLifesteal = hasLifesteal;
    }

    public int getDamagePoints() {
        return this.damagePoints;
    }

    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public ConcreteMinion clone() {
        ConcreteMinion minion = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            minion = (ConcreteMinion) super.clone();
        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }

        // on renvoie le clone
        return minion;
    }

    public void generateEffect() {
        generateMinionEffect(this.abilityKeyWord);
        generateMinionDeathRattle(this.deathRattleKeyWords);
    }

    /**
     * allows to generate the effect of a minion the abilities of the minions are stored using a Map
     * in the database in the form <key:value> where key is the ability keyword and value is its
     * modifier
     *
     * @param abilityKeyWord a map of the ability keywords
     */
    private void generateMinionEffect(@NotNull Map<String, String> abilityKeyWord) {

        // for an unknow reason, when generating the effect of a new minion, if this effect type had
        // already been generated then it would add it to the new minion, resultating in an additional
        // Effect.
        // to fix it, I set the effect of a new minion to null. But that could be better.
        ArrayList<Effect> nullEffect = new ArrayList<>();
        this.setMyEffects(nullEffect);

        for (Map.Entry<String, String> abilityEntry : abilityKeyWord.entrySet()) {

            switch (abilityEntry.getKey()) {
                case CHARGE_ABILITY:
                    Charge chargeAbility = new Charge(this);
                    this.addEffect(chargeAbility);
                    break;
                case LIFE_STEAL_ABILITY:
                    LifeSteal stealAbility = new LifeSteal(this);
                    this.addEffect(stealAbility);
                    break;
                case TAUNT_ABILITY:
                    Taunt tauntAbility = new Taunt(this);
                    this.addEffect(tauntAbility);
                    break;
                case BUFF_ALLIED_MINIONS_ABILITY:
                    BuffAlliedMinions buffAbility = new BuffAlliedMinions(this);
                    this.addEffect(buffAbility);
                    this.setBonus(Integer.parseInt(abilityEntry.getValue()));
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * set the minions deathRattle
     *
     * @param deathRattle a map of the deathrattle keywords
     */
    private void generateMinionDeathRattle(@NotNull Map<String, String> deathRattle) {
        // for an unknow reason, when generating the effect of a new minion, if this effect type had
        // already been generated then it would add it to the new minion, resultating in an additional
        // Effect.
        // to fix it, I set the effect of a new minion to null. But that could be better.
        ArrayList<Effect> nullEffect = new ArrayList<>();
        this.setMyDeathRattles(nullEffect);

        for (Map.Entry<String, String> deathEntry : deathRattle.entrySet()) {
            if (REMOVE_AURA_DEATH_RATTLE.equals(deathEntry.getKey())) {
                RemoveAura auraDeath = new RemoveAura(this);
                this.addDeathRattle(auraDeath);
            }
        }
    }

    /**
     * add to the current health the number of health to be restored
     *
     * @param healthPoints the number of healthPoints
     */
    @Override
    public void heal(int healthPoints) {
        if (healthPoints >= 0) {
            long sum = (long) this.currentHealthPoints + (long) healthPoints;
            int hp;
            if (sum >= Integer.MAX_VALUE) {
                hp = Math.min(Integer.MAX_VALUE, this.maxHealthPoints);
            } else {
                hp = (int) Math.min(sum, (long) this.maxHealthPoints);
            }
            this.currentHealthPoints = hp;
        }
    }

    /**
     * add a bonus to the max health point of the minion
     *
     * @param bonusHealtPoints bonus health points that should be added the the minion
     */
    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {
        this.maxHealthPoints += bonusHealtPoints;
        this.currentHealthPoints += bonusHealtPoints;
    }

    /**
     * Add a new effect to the list of death rattle.
     *
     * @param deathEffect the new value to add.
     */
    private void addDeathRattle(Effect deathEffect) {
        this.myDeathRattles.add(deathEffect);
    }

    /**
     * Add a list of effects to the list myDeathRattle.
     *
     * @param deathEffects the new list to add
     * @return true if all values has been added to myDeathRattle.
     */
    public boolean addAllDeathRattle(ArrayList<Effect> deathEffects) {
        return this.myDeathRattles.addAll(deathEffects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int takeDamage(int damageTaken) {
        if (damageTaken >= 0) {

            this.currentHealthPoints = this.currentHealthPoints - damageTaken;

            if (this.isDead()) this.dies();

            return damageTaken;

        } else {

            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDamagePoints(int bonusDamage) {
        this.damagePoints += bonusDamage;
    }

    /**
     * what happens when the minion dies
     */
    @Override
    public void dies() {

        // we go through all the minion's death rattles
        for (Effect effect : myDeathRattles) {
            effect.effect();
        }

        // we then remove it from the game
        Player player = this.getPlayer();
        player.removeMinionFromPlay(this);
    }

    /**
     * allows a minion to attack a target
     *
     * @param target the target of the minion's attack
     */
    public void attack(Target target) {

        if (this.canAttack) {
            int totalDamageDealt = this.damagePoints + this.getPlayer().getMyDamageAura();
            if (target instanceof ConcreteMinion) {
                int totalDamageReceived =
                        ((ConcreteMinion) target).getDamagePoints()
                                + ((ConcreteMinion) target).getPlayer().getMyDamageAura();
                target.takeDamage(totalDamageDealt);
                // the enemy retaliates
                this.takeDamage(totalDamageReceived);

                // if the target has lifeSteal, then its hero is healed
                if (((ConcreteMinion) target).isHasLifesteal()) {
                    ((ConcreteMinion) target).getPlayer().getMyHero().heal(totalDamageReceived);
                }
            }

            // else the target is a hero
            else {

                target.takeDamage(totalDamageDealt);
            }

            // if the minion has lifesteal, then his player's hero is healed
            if (this.isHasLifesteal()) {
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
}
