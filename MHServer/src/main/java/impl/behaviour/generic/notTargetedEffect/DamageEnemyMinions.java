package impl.behaviour.generic.notTargetedEffect;

import abstracts.Spell;
import impl.ConcreteMinion;
import impl.Player;
import inter.NotTargetedEffect;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing the special action "DamageEnemyMinions" used for the "Arcane Explosion" spell.
 * Deal 1 damage to all enemy minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageEnemyMinions extends NotTargetedEffect {

    private Spell mySpell;
    private int damage;

    public DamageEnemyMinions(Spell newSpell, int damage) {
        this.mySpell = newSpell;
        this.damage = damage;
    }

    @Override
    public void effect() {
        Player myOpponent = mySpell.getPlayer().getOpponent();
        ArrayList<ConcreteMinion> hisMinions = myOpponent.getMyMinions();

        for(Iterator<ConcreteMinion> target = hisMinions.iterator(); target.hasNext();){
            ConcreteMinion targeted = target.next();
            targeted.takeDamage(damage);
        }
    }
}
