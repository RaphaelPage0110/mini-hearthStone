package impl.behaviour.generic.notTargetedEffect;

import abstracts.Minion;
import abstracts.Spell;
import impl.ConcreteMinion;
import impl.Player;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "DamageEnemyMinions" used for the "Arcane Explosion" spell.
 * Deal 1 damage to all enemy minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageEnemyMinions extends NotTargetedEffect {

    private Spell mySpell;

    public DamageEnemyMinions(Spell newSpell) {
        this.mySpell = newSpell;
    }

    @Override
    public void effect() {
        Player myOpponent = mySpell.getPlayer().getOpponent();
        ArrayList<ConcreteMinion> hisMinions = myOpponent.getMyMinions();
        int damages = mySpell.getDamagePoints();

        for (Minion target : hisMinions) {
            target.takeDamage(damages);
        }
    }
}
