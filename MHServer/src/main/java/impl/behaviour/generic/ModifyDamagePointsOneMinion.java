package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import inter.Effect;
import inter.Target;
import inter.TargetedEffect;

/**
 * Class representing the special action "ModifyDamagePointsOneMinion" used for the "Blessing of might" (fr:Bénédiction de puissance) spell.
 * Give a minion +3 attack.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyDamagePointsOneMinion extends TargetedEffect {
    private Spell mySpell;

    public ModifyDamagePointsOneMinion(Spell newSpell) {
        this.mySpell = newSpell;
    }

    @Override
    public void effect(Target myTarget) {
        Minion targetedMinion = (Minion) myTarget;
        int bonus = mySpell.getBonus();
        targetedMinion.addDamagePoints(bonus);
    }
}