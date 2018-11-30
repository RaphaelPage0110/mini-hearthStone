package impl;

import abstracts.Hero;
import impl.behaviour.generic.DamageTarget;
import impl.behaviour.generic.ModifyArmor;
import impl.behaviour.generic.Summon;
import java.util.HashMap;
import java.util.Map;

public class ConcreteHero extends Hero {

    private Map<String,String> abilityKeyWord = new HashMap<String, String>();


    public ConcreteHero(String heroName, int healthPoints, int armorPoints, Map<String,String> abilityKeyWord) {
        super();

        this.setHeroName(heroName);
        this.setArmorPoints(armorPoints);
        this.setHealthPoints(healthPoints);
        this.setAbilityKeyWord(abilityKeyWord);

    //the abilities of the heroes are stored using a Map in the database
        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet())
    {
        switch(entry.getKey()) {
            case "DamageTarget":
                DamageTarget abilityDamage = new DamageTarget(this, Integer.parseInt(entry.getValue()));
                this.setMyEffect(abilityDamage);
                break;
            case "ModifyArmor" :
                ModifyArmor abilityArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()));
                this.setMyEffect(abilityArmor);
                break;
            case "Summon" :
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
}
