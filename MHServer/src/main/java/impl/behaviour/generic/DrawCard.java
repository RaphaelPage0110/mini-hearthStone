package impl.behaviour.generic;

import abstracts.Spell;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;

/**
 * Class representing the special action "DrawCard" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Draw a card.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DrawCard extends NotTargetedEffect {

    private Spell mySpell;

    public DrawCard(Spell newSpell) {
        this.mySpell = newSpell;
    }

    @Override
    /**
     * TODO : write thi method
     */
    public void effect() {
        ;
    }
}
