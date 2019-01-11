package impl;

import abstracts.Card;
import abstracts.CardType;
import impl.behaviour.generic.notTargetedEffect.*;
import impl.behaviour.generic.targetedEffect.DamageTarget;
import impl.behaviour.generic.targetedEffect.ModifyDamagePointsOneMinion;
import impl.behaviour.generic.targetedEffect.TransformInto;

import java.util.ArrayList;
import java.util.Map;

public class ConcreteSpell extends Card implements Cloneable {

    public ConcreteSpell() {
    }

    public ConcreteSpell(CardType type, int requiredMana, int damagePoints, int bonus, Map<String, String> abilityKeyWords, String name, String imgurl, String text) {

        this.myEffects = new ArrayList<>();
        this.text = text;
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.setBonus(bonus);
        this.type = type;
        this.setAbilityKeyWord(abilityKeyWords);
        this.imgurl = imgurl;

        generateSpellEffect(abilityKeyWords);
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

    public ConcreteSpell(CardType type, int requiredMana, int damagePoints, int bonus, Map<String, String> abilityKeyWords, String name, String imgurl, String text, Player player) {
        super();
        this.player = player;

        this.text = text;
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.setBonus(bonus);
        this.type = type;
        this.setAbilityKeyWord(abilityKeyWords);
        this.imgurl = imgurl;

        generateSpellEffect(abilityKeyWords);
    }

    public void generateEffect() {
        generateSpellEffect(this.abilityKeyWord);
    }



    /**
     * allows to generate the effect of a card
     * The abilities of the card are stored using a Map in the database in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @param abilityKeyWords
     */
    public void generateSpellEffect(Map<String,String> abilityKeyWords) {
        for (Map.Entry<String, String> entry : abilityKeyWords.entrySet()) {

            switch(entry.getKey()) {

                case "damageTarget":
                    DamageTarget abilityDamage = new DamageTarget(Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamage);
                    break;

                case "summon":
                    Summon abilitySummon = new Summon(entry.getValue());
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

                case "damageEnemyMinions":
                    DamageEnemyMinions abilityDamageEnemyMinions = new DamageEnemyMinions(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamageEnemyMinions);
                    break;

                case "modifyDamagePointsOneMinion":
                    ModifyDamagePointsOneMinion abilityBuffOneMinion = new ModifyDamagePointsOneMinion(Integer.parseInt(entry.getValue()));
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

                    DrawCard abilityDraw = new DrawCard(Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDraw);
                    break;

            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Minion[id=%s, spellName='%s', damage='%s']",
                id, name, damagePoints);
    }
}
