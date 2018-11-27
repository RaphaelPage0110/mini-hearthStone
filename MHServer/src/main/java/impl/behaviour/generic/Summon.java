package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import impl.Player;
import inter.Effect;
import inter.Target;

/**
 * Class representing the special action "Summon" used for the "Mirror Image" (fr:Image Miroir) spell.
 * Summon two servants 0/2 with Taunt.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Summon implements Effect {

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
