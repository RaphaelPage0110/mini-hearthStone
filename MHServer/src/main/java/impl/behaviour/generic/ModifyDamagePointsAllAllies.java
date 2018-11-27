package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import impl.Player;
import inter.CardAction;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "ModifyDamagePointsAllAllies" used for the "Raid Leader" (fr:Chef de raid) minion.
 * Your other minions have +1 attack.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyDamagePointsAllAllies implements CardAction {

    private Minion myMinion;
    private int modifier;

    public ModifyDamagePointsAllAllies(Minion newMinion, int dmgModifier) {
        this.myMinion = newMinion;
        this.modifier = dmgModifier;
    }

    public void effect(Target myTarget) {
        Player myPlayer = myMinion.getPlayer();
        int aura;

        aura = myPlayer.getMyAura();
        aura += modifier;
        myPlayer.setMyAura(aura);
    }
}
