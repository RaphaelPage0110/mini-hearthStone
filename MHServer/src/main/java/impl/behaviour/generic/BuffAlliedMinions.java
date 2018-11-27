package impl.behaviour.generic;

import abstracts.Minion;
import impl.Player;
import inter.Effect;
import inter.Target;


/**
 * Class representing the special action "BuffAlliedMinions" used for the "Raid Leader" (fr:Chef de raid) minion.
 * Your other minions have +1 attack.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class BuffAlliedMinions implements Effect {

    private Minion myMinion;

    public BuffAlliedMinions(Minion newMinion) {
        this.myMinion = newMinion;
    }

    public void effect(Target myTarget) {
        Player myPlayer = myMinion.getPlayer();
        int aura = myMinion.getBonus();
        int damagePoints = myMinion.getDamagePoints();

        //the minion buffs all allies
        myPlayer.addAura(aura);
        //but not himself!
        myMinion.addDamagePoints(-aura);
    }
}