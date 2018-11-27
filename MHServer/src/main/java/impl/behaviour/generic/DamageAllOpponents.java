package impl.behaviour.generic;


import abstracts.Hero;
import abstracts.Minion;
import abstracts.Spell;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "DamageAllOpponent" used for the "Consecration" spell.
 * Deal 2 damages to all opponents, including hero and minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageAllOpponents implements Effect {

    private Spell mySpell;

    public DamageAllOpponents(Spell mySpell) {
        this.mySpell = mySpell;
    }

    @Override
    public void effect(Target myTarget) {
        ArrayList<Target> myAdversaries = new ArrayList<>();

        Player myPlayer = mySpell.getPlayer();
        Player myOpponent = myPlayer.getOpponent();
        Hero hisHero = myOpponent.getMyHero();
        ArrayList<Minion> hisMinions = myOpponent.getMyMinions();

        myAdversaries.addAll(hisMinions);
        myAdversaries.add(hisHero);

        for (Target target : myAdversaries) {
            target.takeDamage(mySpell.getDamagePoints());
        }
    }
}
