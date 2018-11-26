package impl;

import abstracts.Minion;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

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
