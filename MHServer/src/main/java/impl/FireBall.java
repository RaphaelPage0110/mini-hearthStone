package impl;

import inter.*;

public class FireBall implements CardAction {

    private Spell mySpell;
    private Target myTarget;

    public FireBall(Spell _mySpell, Target _myTarget) {
        mySpell = _mySpell;
        myTarget = _myTarget;
    }

    public void effect(Target myTarget) {
        myTarget.takeDamage(mySpell.getDamagePoints());
    }
}
