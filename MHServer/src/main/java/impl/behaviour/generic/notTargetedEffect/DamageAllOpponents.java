package impl.behaviour.generic.notTargetedEffect;

import impl.ConcreteHero;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import impl.Player;
import inter.NotTargetedEffect;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "DamageAllOpponent" used for the "Consecration" spell.
 * Deal 2 damages to all opponents, including hero and minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageAllOpponents extends NotTargetedEffect {

    private ConcreteSpell mySpell;
    private int damage;

    public DamageAllOpponents(ConcreteSpell mySpell, int damage) {
        this.mySpell = mySpell;
        this.damage = damage;
    }

    @Override
    public void effect() {

        Player myPlayer = mySpell.getPlayer();
        Player myOpponent = myPlayer.getOpponent();
        ConcreteHero hisHero = myOpponent.getMyHero();
        ArrayList<ConcreteMinion> hisMinions = myOpponent.getMyMinions();

        ArrayList<Target> myAdversaries = new ArrayList<Target>(hisMinions);
        myAdversaries.add(hisHero);

        for (Target target : myAdversaries) {

            target.takeDamage(damage);

        }
    }
}
