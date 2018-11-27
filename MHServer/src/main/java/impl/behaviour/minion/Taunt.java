package impl.behaviour.minion;

import abstracts.Minion;
import inter.Effect;
import inter.Target;

/**
 * Class representing the special action "Taunt" used for the "Public Defender" (fr:Avocat commis d'office) & "Goldshire Footman" (fr:Soldat du comté-de-l'or) minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Taunt implements Effect {

    private Minion myMinion;

    public Taunt(Minion myMinion) {
        this.myMinion = myMinion;
    }

    @Override
    public void effect(Target myTarget) {
        myMinion.setHasTaunt(true);
    }
}
