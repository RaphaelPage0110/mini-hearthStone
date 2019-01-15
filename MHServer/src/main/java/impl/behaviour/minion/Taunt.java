package impl.behaviour.minion;

import abstracts.NotTargetedEffect;
import impl.ConcreteMinion;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the special action "Taunt" used for the "Public Defender" (fr:Avocat commis
 * d'office) & "Goldshire Footman" (fr:Soldat du comté-de-l'or) minions.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Taunt extends NotTargetedEffect {

    public Taunt(@NotNull ConcreteMinion myMinion) {
        myMinion.setHasTaunt(true);
    }

    @Override
    public void effect() {
    }
}
