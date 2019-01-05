package impl;

import abstracts.Card;
import abstracts.CardType;
import abstracts.Spell;
import impl.behaviour.generic.*;
import impl.behaviour.minion.Taunt;

import java.util.Map;

public class ConcreteSpell extends Spell  implements Cloneable {

    private Map<String,String> abilityKeyWords;

    public ConcreteSpell() {

    }

    public ConcreteSpell clone() {
        ConcreteSpell spell = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            spell = (ConcreteSpell) super.clone();
        } catch(CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }

        // on renvoie le clone
        return spell;
    }

    public ConcreteSpell(String name, int requiredMana, int bonus, CardType type, Map<String,String> abilityKeyWords, String imgurl, String text) {
        super();

        this.text = text;
        this.setName(name);
        this.requiredMana = requiredMana;
        this.setBonus(bonus);
        this.type = type;
        this.abilityKeyWords = abilityKeyWords;
        this.imgurl = imgurl;

        generateEffect(abilityKeyWords);
    }

    /**
     * TODO : A COMPLETER
     * allows to generate the effect of a card
     * The abilities of the card are stored using a Map in the database in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @param abilityKeyWords
     */
    private void generateEffect (Map<String,String> abilityKeyWords) {
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

                case "damageAllOpponents":
                    DamageAllOpponents abilityDamageAllOpponents = new DamageAllOpponents(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamageAllOpponents);
                    break;

                case "modifyDamagePointsOneMinion":
                    ModifyDamagePointsOneMinion abilityBuffOneMinion = new ModifyDamagePointsOneMinion(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityBuffOneMinion);
                    break;

                case "damageAllMinions":
                    DamageAllMinions abilityDamageAllMinions = new DamageAllMinions(this, Integer.parseInt(entry.getValue()) );
                    this.myEffects.add(abilityDamageAllMinions);
                    break;

                case "modifyArmor":
                    ModifyArmor abilityModifyArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()) );
                    this.myEffects.add(abilityModifyArmor);
                    break;

                case "drawCard":

                    DrawCard abilityDraw = new DrawCard(this, Integer.parseInt(entry.getValue()) );

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
