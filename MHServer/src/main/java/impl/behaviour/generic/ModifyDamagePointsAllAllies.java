package impl.behaviour.generic;

import abstracts.Minion;
import impl.Player;
import inter.CardAction;
import inter.Target;


/**
 * Class representing the special action "ModifyDamagePointsAllAllies" used for the "Raid Leader" (fr:Chef de raid) minion.
 * Your other minions have +1 attack.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class ModifyDamagePointsAllAllies implements CardAction {

    private Minion myMinion;

    public ModifyDamagePointsAllAllies(Minion newMinion) {
        this.myMinion = newMinion;
    }

    public void effect(Target myTarget) {
        Player myPlayer = myMinion.getPlayer();
        int aura = myMinion.getBonus();
        myPlayer.addAura(aura);
    }
}