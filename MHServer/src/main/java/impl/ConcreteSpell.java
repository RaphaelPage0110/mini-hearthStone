package impl;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Spell;
import impl.behaviour.generic.DamageTarget;
import impl.behaviour.generic.Summon;
import impl.behaviour.generic.TransformInto;
import impl.behaviour.minion.Taunt;

import java.util.Map;

public class ConcreteSpell extends Spell {

    private Map<String,String> abilityKeyWords;

    public ConcreteSpell() {

    }

    public ConcreteSpell(String name, int requiredMana, int bonus, CardType type, Map<String,String> abilityKeyWords) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.setBonus(bonus);
        this.type = type;
        this.abilityKeyWords = abilityKeyWords;

        generateEffect(abilityKeyWords);
    }

    /**
     * TODO : A COMPLETER
     * allows to generate the effect of a card
     * The abilities of the card are stored using a Map in the database in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @param abilityKeyWords
     */
    public void generateEffect (Map<String,String> abilityKeyWords) {
        for (Map.Entry<String, String> entry : abilityKeyWords.entrySet()) {

            switch(entry.getKey()) {

                case "damageTarget":
                    DamageTarget abilityDamage = new DamageTarget(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamage);
                    break;

                case "summon":
                    Summon abilitySummon = new Summon(this, entry.getValue());
                    this.myEffects.add(abilitySummon);
                    break;

                case "transformInto":
                    TransformInto abilityTransform = new TransformInto(this, entry.getValue());
                    this.myEffects.add(abilityTransform);
                    break;


            }
        }
    }

    public Map<String, String> getAbilityKeyWords() {
        return abilityKeyWords;
    }

    public void setAbilityKeyWords(Map<String, String> abilityKeyWords) {
        this.abilityKeyWords = abilityKeyWords;
    }
}