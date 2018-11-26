package impl;

import abstracts.Minion;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

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
