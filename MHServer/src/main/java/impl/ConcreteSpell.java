package impl;

import abstracts.Card;
import abstracts.CardType;
import impl.behaviour.generic.notTargetedEffect.*;
import impl.behaviour.generic.targetedEffect.DamageTarget;
import impl.behaviour.generic.targetedEffect.ModifyDamagePointsOneMinion;
import impl.behaviour.generic.targetedEffect.TransformInto;

import java.util.Map;

import static abstracts.Consts.*;

public class ConcreteSpell extends Card implements Cloneable {

    /**
     * Default empty constructor of ConcreteSpell.
     */
    public ConcreteSpell() {}

    /**
     * Constructor of ConcreteSpell.Initializes most of the properties of this class. Activates the creation of Effects from abilityKeyword.
     * @param type the type of the Card.
     * @param requiredMana the mana cost of using the Card.
     * @param bonus the bonus of damage, armor, health, or mana.
     * @param abilityKeyWord the list of actions or behaviors of a the Card.
     * @param name the name of the ConcreteSpell.
     * @param imgurl the URL of the Card image.
     * @param text the description of the Card.
     */
    public ConcreteSpell(CardType type, int requiredMana, int bonus, Map<String, String> abilityKeyWord, String name, String imgurl, String text) {
        super(type, requiredMana, abilityKeyWord, name, imgurl, text);
        this.bonus = bonus;

        generateEffect();
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


    public void generateEffect() {
        generateSpellEffect(this.abilityKeyWord);
    }



    /**
     * allows to generate the effect of a card
     * The abilities of the card are stored using a Map in the database in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @param abilityKeyWords a map of the keywords of the abilities
     */
    public void generateSpellEffect(Map<String,String> abilityKeyWords) {
        for (Map.Entry<String, String> entry : abilityKeyWords.entrySet()) {

            switch(entry.getKey()) {

                case DAMAGE_TARGET_ABILITY:
                    DamageTarget abilityDamage = new DamageTarget(Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamage);
                    break;

                case SUMMON_ABILITY:
                    Summon abilitySummon = new Summon(entry.getValue());
                    this.myEffects.add(abilitySummon);
                    break;

                case TRANSFORM_INTO_ABILITY:
                    TransformInto abilityTransform = new TransformInto(entry.getValue());
                    this.myEffects.add(abilityTransform);
                    break;

                case DAMAGE_ALL_OPPONENTS_ABILITY:
                    DamageAllOpponents abilityDamageAllOpponents = new DamageAllOpponents(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamageAllOpponents);
                    break;

                case DAMAGE_ENEMY_MINIONS_ABILITY:
                    DamageEnemyMinions abilityDamageEnemyMinions = new DamageEnemyMinions(this, Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDamageEnemyMinions);
                    break;

                case MODIFY_DAMAGE_POINTS_ONE_MINION_ABILITY:
                    ModifyDamagePointsOneMinion abilityBuffOneMinion = new ModifyDamagePointsOneMinion(Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityBuffOneMinion);
                    break;

                case DAMAGE_ALL_MINIONS_ABILITY:
                    DamageAllMinions abilityDamageAllMinions = new DamageAllMinions(this, Integer.parseInt(entry.getValue()) );
                    this.myEffects.add(abilityDamageAllMinions);
                    break;

                case MODIFY_ARMOR_ABILITY:
                    ModifyArmor abilityModifyArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()) );
                    this.myEffects.add(abilityModifyArmor);
                    break;

                case DRAW_CARD_ABILITY:
                    DrawCard abilityDraw = new DrawCard(Integer.parseInt(entry.getValue()));
                    this.myEffects.add(abilityDraw);
                    break;

                default:
                    break;

            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Spell[id=%s, spellName='%s', requiredMana=%s, type=%s, text='%s']",
                 id, name, requiredMana, type, text);
    }
}
