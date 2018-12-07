package impl;

import abstracts.CardType;
import abstracts.Minion;
import impl.behaviour.generic.BuffAlliedMinions;
import impl.behaviour.generic.RemoveAura;
import impl.behaviour.minion.Charge;
import impl.behaviour.minion.LifeSteal;
import impl.behaviour.minion.Taunt;
import inter.Effect;

import java.util.Map;


public class ConcreteMinion extends Minion {

    private Map<String,String> abilityKeyWord;
    private Map<String,String> deathRattle;


    public ConcreteMinion(CardType minionType, int requiredMana, int damagePoints, int maxHealthPoints, Map<String,String> abilityKeyWord, Map<String,String> deathRattle, String minionName) {
        super();

        this.type = minionType;
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.abilityKeyWord = abilityKeyWord;
        this.deathRattle = deathRattle;
        this.name = minionName;

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
                    break;
                default:
                    break;
            }
        }

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

    @Override
    public void heal(int healthPoints) {
        this.setCurrentHealthPoints(Math.min(this.currentHealthPoints+healthPoints,this.maxHealthPoints));
    }

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
}
