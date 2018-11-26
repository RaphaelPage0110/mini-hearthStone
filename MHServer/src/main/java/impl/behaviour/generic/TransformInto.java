package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

/**
 * Class representing the special action "TransformInto" used for the "Polymorph" (fr:Métamorphose) spell.
 * Transforms a targeted minion into a 1/1 minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class TransformInto implements CardAction {

    private Spell mySpell;
    private Minion myMinion;

    public TransformInto(Spell newSpell, Minion newMinion) {
        this.mySpell = newSpell;
        this.myMinion = newMinion;
    }

    public void effect(Target myTarget) {
        Minion oldMinion = (Minion)myTarget;
        oldMinion.setName(myMinion.getName());
        oldMinion.setRequiredMana(myMinion.getRequiredMana());
        oldMinion.setDamagePoints(myMinion.getDamagePoints());
        oldMinion.setHealthPoints(myMinion.getHealthPoints());
        oldMinion.setType(myMinion.getType());
        oldMinion.setMyActions(myMinion.getMyActions());
    }
}
