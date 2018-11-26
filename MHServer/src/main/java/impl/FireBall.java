package impl;

import inter.CardAction;
import inter.Target;
import abstracts.Spell;

public class FireBall implements CardAction {

    private Spell mySpell;
    private Target myTarget;

    public FireBall(Spell mySpell, Target myTarget) {
        this.mySpell = mySpell;
        this.myTarget = myTarget;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(mySpell.getDamagePoints());
    }
}
