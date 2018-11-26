package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

/**
 * Class representing the special action "ModifyDamagePoints" used for the "Raid Leader" (fr:Chef de raid) minion.
 * Your other minions have +1 attack.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyDamagePoints implements CardAction {

    private Spell mySpell;
    private int modifier;

    public ModifyDamagePoints(Spell newSpell, int dmgModifier) {
        this.mySpell = newSpell;
        this.modifier = dmgModifier;
    }

    public void effect(Target myTarget) {
        Minion myMinion;
        myMinion = (Minion)myTarget;
        int damagePoints;

        damagePoints = myMinion.getDamagePoints();
        damagePoints += modifier;
        myMinion.setDamagePoints(damagePoints);
    }
}
