package impl;
import inter.CardAction;
import inter.Target;
import abstracts.Minion;
import abstracts.Spell;
import java.util.ArrayList;

public class DamageAllMinions implements CardAction {

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
