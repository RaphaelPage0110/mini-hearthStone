package impl.behaviour.generic;
import inter.Effect;
import inter.Target;
import abstracts.Minion;
import abstracts.Spell;
import java.util.ArrayList;

/**
 * Class representing the special action "DamageAllMinions" used for the "Whirlwind" (fr:Tourbillon) spell.
 * Deal 1 damage to ALL minions (including yours).
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DamageAllMinions implements Effect {

    private Spell mySpell;
    
    public DamageAllMinions(Spell mySpell) {
        this.mySpell = mySpell;
    }

    public void effect(Target myTarget) {
        ArrayList<Minion> myTargets;
        int damage;

        damage = mySpell.getDamagePoints();
        myTargets = mySpell.getPlayer().getMyGame().getMinionsInPlay();
      

        for (Minion targeted: myTargets) {
            targeted.takeDamage(damage);
        }
      
    }
}
