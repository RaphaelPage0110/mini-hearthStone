package impl.behaviour.minion;

import impl.ConcreteMinion;
import inter.NotTargetedEffect;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "Taunt" used for the "Public Defender" (fr:Avocat commis d'office) & "Goldshire Footman" (fr:Soldat du comté-de-l'or) minions.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Taunt extends NotTargetedEffect {

    private ConcreteMinion myMinion;

    public Taunt(@NotNull ConcreteMinion myMinion) {
        this.myMinion = myMinion;
        myMinion.setHasTaunt(true);
    }

    @Override
    public void effect() {
    }

    public ConcreteMinion getMyMinion() {
        return myMinion;
    }
}
