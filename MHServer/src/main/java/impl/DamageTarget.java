package impl;

import inter.CardAction;
import inter.Target;
import abstracts.Spell;
import inter.CardAction;

public class DamageTarget implements CardAction {

    private Spell mySpell;
    private Target myTarget;

    public DamageTarget(Spell mySpell, Target myTarget) {
        this.mySpell = mySpell;
        this.myTarget = myTarget;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(mySpell.getDamagePoints());
    }
}