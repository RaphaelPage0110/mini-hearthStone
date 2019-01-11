package impl.behaviour.generic.targetedEffect;

import abstracts.Spell;
import impl.ConcreteMinion;
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
    private int myDamage;

    public ModifyDamagePointsOneMinion(Spell newSpell, int damage) {
        this.mySpell = newSpell;
        this.myDamage = damage;
    }

    @Override
    public void effect(Target myTarget) {
        ConcreteMinion targetedMinion = (ConcreteMinion) myTarget;
        targetedMinion.addDamagePoints(myDamage);
    }

    public Spell getMySpell() {
        return mySpell;
    }
}
