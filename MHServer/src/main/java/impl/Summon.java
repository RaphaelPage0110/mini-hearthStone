package impl;

import abstracts.Minion;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

public class Summon implements CardAction {

    private Spell mySpell;
    private Minion myMinion;

    public Summon(Spell newSpell, Minion newMinion) {
        this.mySpell = newSpell;
        this.myMinion = newMinion;
    }

    public void effect(Target myTarget) {
        Player player = mySpell.getPlayer();

        player.addMinion(myMinion);
    }
}
