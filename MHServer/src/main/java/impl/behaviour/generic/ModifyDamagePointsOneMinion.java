package impl.behaviour.generic;

public class ModifyDamagePointsOneMinion {
}

/*
package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import impl.Player;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

public class ModifyDamagePointsAllAllies implements CardAction {

    private Spell mySpell;
    private int modifier;

    public ModifyDamagePointsAllAllies(Spell newSpell, int dmgModifier) {
        this.mySpell = newSpell;
        this.modifier = dmgModifier;
    }

    public void effect(Target myTarget) {
        Player myPlayer = mySpell.getPlayer();
        ArrayList<Minion> myAllies = myPlayer.getMyMinions();

        for (Minion friend : myAllies) {
            friend.addDamagePoints(modifier);
        }
    }
}

 */