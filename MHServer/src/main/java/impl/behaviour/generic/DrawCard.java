package impl.behaviour.generic;

import abstracts.Spell;
import inter.Effect;
import inter.Target;

/**
 * Class representing the special action "DrawCard" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Draw a card.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DrawCard implements Effect {

    private Spell mySpell;

    public DrawCard(Spell newSpell) {
        this.mySpell = newSpell;
    }

    public void effect(Target myTarget) {
        mySpell.getPlayer().draw();
    }
}
