package impl.behaviour.generic.notTargetedEffect;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import impl.Player;

/**
 * Class representing the special action "BuffAlliedMinions" used for the "Raid Leader" (fr:Chef de
 * raid) minion. Your other minions have +1 attack.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class BuffAlliedMinions extends NotTargetedEffect {

    private ConcreteMinion myMinion;

    public BuffAlliedMinions(ConcreteMinion newMinion) {
        this.myMinion = newMinion;
    }

    @Override
    public void effect() {
        Player myPlayer = myMinion.getPlayer();
        int aura = myMinion.getBonus();

        // the minion buffs all allies
        myPlayer.addAura(aura);
        // but not himself!
        myMinion.addDamagePoints(-aura);
    }
}
