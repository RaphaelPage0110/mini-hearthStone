package impl;

import abstracts.Spell;
import inter.CardAction;
import inter.Target;

public class DrawCard implements CardAction{

    private Spell mySpell;

    public DrawCard(Spell newSpell) {
        this.mySpell = newSpell;
    }

    public void effect(Target myTarget) {
        mySpell.getPlayer().draw();
    }
}
