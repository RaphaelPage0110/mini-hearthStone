package impl.behaviour.generic.notTargetedEffect;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import impl.Player;

import java.util.ArrayList;

/**
 * Class representing the special action "DamageEnemyMinions" used for the "Arcane Explosion" spell.
 * Deals damage to all enemy minions.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageEnemyMinions extends NotTargetedEffect {

    private ConcreteSpell mySpell;
    private int damage;

    public DamageEnemyMinions(ConcreteSpell newSpell, int damage) {
        this.mySpell = newSpell;
        this.damage = damage;
    }

    @Override
    public void effect() {
        Player myOpponent = mySpell.getPlayer().getOpponent();
        ArrayList<ConcreteMinion> hisMinions = myOpponent.getMyMinions();
        int i = 0;

        while (i < hisMinions.size()) {
            ConcreteMinion target = hisMinions.get(i);
            if (target.getCurrentHealthPoints() > damage) {
                target.takeDamage(damage);
                i++;
            } else {
                target.takeDamage(damage);
                hisMinions = myOpponent.getMyMinions();
            }
        }
    }
}
