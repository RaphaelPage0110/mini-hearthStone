package impl;

import abstracts.CardType;
import abstracts.Minion;
import impl.behaviour.generic.notTargetedEffect.BuffAlliedMinions;
import impl.behaviour.generic.notTargetedEffect.RemoveAura;
import impl.behaviour.minion.Charge;
import impl.behaviour.minion.LifeSteal;
import impl.behaviour.minion.Taunt;
import inter.Effect;

import java.util.ArrayList;
import java.util.Map;


public class ConcreteMinion extends Minion implements Cloneable {

    private Map<String,String> deathRattleKeyWords;

    public ConcreteMinion(CardType minionType, int requiredMana, int damagePoints, int healthPoints, Map<String,String> abilityKeyWord, Map<String,String> deathRattle, String minionName, String imgurl, String text) {
        super();

        this.text = text;
        this.imgurl = imgurl;
        this.type = minionType;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
        this.setAbilityKeyWord(abilityKeyWord);
        this.deathRattleKeyWords = deathRattle;
        this.name = minionName;
    }

    public void generateEffect() {
        generateMinionEffect(this.abilityKeyWord);
        generateMinionDeathRattle(this.deathRattleKeyWords);
    }

    /**
     *  allows to generate the effect of a minion
     *  the abilities of the minions are stored using a Map in the database in the form <key:value> where key is the
     *  ability keyword and value is its modifier
     * @param abilityKeyWord
     */
    private void generateMinionEffect(Map<String, String> abilityKeyWord){

        //for an unknow reason, when generating the effect of a new minion, if this effect type had already been generated then it would add it to the new minion, resultating in an additional Effect.
        //to fix it, I set the effect of a new minion to null. But that could be better.
        ArrayList<Effect> nullEffect = new ArrayList<>();
        this.setMyEffects(nullEffect);

        for (Map.Entry<String,String> abilityEntry: abilityKeyWord.entrySet()) {

            switch(abilityEntry.getKey()) {
                case "charge":
                    Charge chargeAbility = new Charge(this);
                    this.addEffect(chargeAbility);
                    break;
                case "lifeSteal" :
                    LifeSteal stealAbility = new LifeSteal(this);
                    this.addEffect(stealAbility);
                    break;
                case "taunt":
                    Taunt tauntAbility = new Taunt(this);
                    this.addEffect(tauntAbility);
                    break;
                case "buffAlliedMinions":
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
     * @param deathRattle
     */
    private void generateMinionDeathRattle(Map<String, String> deathRattle){
        //for an unknow reason, when generating the effect of a new minion, if this effect type had already been generated then it would add it to the new minion, resultating in an additional Effect.
        //to fix it, I set the effect of a new minion to null. But that could be better.
        ArrayList<Effect> nullEffect = new ArrayList<>();
        this.setMyDeathRattles(nullEffect);

        for (Map.Entry<String, String> deathEntry: deathRattle.entrySet()) {
            switch(deathEntry.getKey()) {
                case "removeAura":
                    RemoveAura auraDeath = new RemoveAura(this);
                    this.addDeathRattle(auraDeath);
                    break;
                default:
                    break;
            }
        }
    }

    public Map<String, String> getDeathRattleKeyWords() {
        return deathRattleKeyWords;
    }

    public void setDeathRattleKeyWords(Map<String, String> deathRattle) {
        this.deathRattleKeyWords = deathRattle;
    }

    public ConcreteMinion clone() {
        ConcreteMinion minion = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            minion = (ConcreteMinion) super.clone();
        } catch(CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }

        // on renvoie le clone
        return minion;
    }

    public ConcreteMinion() {

    }

    /**
     * add to the current health the number of health to be restor
     * @param healthPoints
     */
    @Override
    public void heal(int healthPoints) {
        this.setCurrentHealthPoints(Math.min(this.currentHealthPoints+healthPoints,this.maxHealthPoints));
    }

    /**
     * add a bonus to the max health point of the minion
     * @param bonusHealtPoints
     */
    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {
        this.maxHealthPoints += bonusHealtPoints;
    }

    @Override
    public void setMaxHealthPoints(int maxHealthPoints){
        this.maxHealthPoints = maxHealthPoints;
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

    @Override
    public String toString() {
        return String.format(
                "Minion[id=%s, minionName='%s', requiredMana=%s, maxHealthPoints=%s, currentHealthPoints=%s, damage=%s, type=%s]",
                id, name, requiredMana, maxHealthPoints, currentHealthPoints, damagePoints, type);
    }

}
