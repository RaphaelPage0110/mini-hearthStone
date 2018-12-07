package impl;

import abstracts.CardType;
import abstracts.Hero;
import impl.behaviour.generic.DamageTarget;
import impl.behaviour.generic.ModifyArmor;
import impl.behaviour.generic.Summon;
import java.util.HashMap;
import java.util.Map;

public class ConcreteHero extends Hero {

    private Map<String,String> abilityKeyWord;


    public ConcreteHero(CardType heroType, int maxHealthPoints, int armorPoints, Map<String,String> abilityKeyWord, String heroName) {
        super();

        this.heroType = heroType;
        this.armorPoints = armorPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.abilityKeyWord = abilityKeyWord;
        this.heroName = heroName;

        //the abilities of the heroes are stored using a Map in the database in the form <key:value> where key is the
        //ability keyword and value is its modifier
        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

            switch(entry.getKey()) {

                case "damageTarget":
                    DamageTarget abilityDamage = new DamageTarget(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityDamage);
                    break;
                case "modifyArmor" :
                    ModifyArmor abilityArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityArmor);
                    break;
                case "summon" :
                    Summon abilitySummon = new Summon(this, entry.getValue());
                    this.setMyEffect(abilitySummon);
                    break;

            }
        }
    }

    public Map<String,String> getAbilityKeyWord() {
        return abilityKeyWord;
    }

    public void setAbilityKeyWord(Map<String,String> abilityKeyWord) {
        this.abilityKeyWord = abilityKeyWord;
    }

    @Override
    public void setMaxHealthPoints(int healthPoints) {

    }

    /**
     * TODO : write this method
     *
     */
    @Override
    public void activateEffect() {

    }

    @Override
    public void addDamagePoints(int bonusDamage) {

    }

    @Override
    public void addMaxHealthPoints(int bonusHealtPoints) {
        this.maxHealthPoints += bonusHealtPoints;
        this.currentHealthPoints += bonusHealtPoints;
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }
}
