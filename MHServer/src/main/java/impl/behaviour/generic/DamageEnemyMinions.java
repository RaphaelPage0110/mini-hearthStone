package impl.behaviour.generic;

import abstracts.Minion;
import abstracts.Spell;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "DamageEnemyMinions" used for the "Arcane Explosion" spell.
 * Deal 1 damage to all enemy minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageEnemyMinions implements Effect {

    private Spell mySpell;

    public DamageEnemyMinions(Spell newSpell) {
        this.mySpell = newSpell;
    }

    @Override
    public void effect(Target myTarget) {
        Player myOpponent = mySpell.getPlayer().getOpponent();
        ArrayList<Minion> hisMinions = myOpponent.getMyMinions();
        int damages = mySpell.getDamagePoints();

        for (Minion target : hisMinions) {
            target.takeDamage(damages);
        }
    }
}
