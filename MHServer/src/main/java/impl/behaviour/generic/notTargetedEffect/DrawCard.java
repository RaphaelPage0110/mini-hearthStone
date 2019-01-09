package impl.behaviour.generic.notTargetedEffect;

import abstracts.Spell;
import inter.NotTargetedEffect;

/**
 * Class representing the special action "DrawCard" used for the "Shield Block" (fr:Maîtrise du blocage) spell.
 * Draw a card.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class DrawCard extends NotTargetedEffect {

    private Spell mySpell;
    private int numberCards;

    public DrawCard(Spell newSpell, int numberCards) {
        this.mySpell = newSpell;
        this.numberCards = numberCards;
    }

    @Override
    public void effect() {
    }

    /**
     * the drawing actions are implemented in the Application becaus ethey need to call the database
     * @return nulberCards the number of cards the player has to draw
     */
    public int getNumberDraw() {
        return numberCards;
    }

    public Spell getMySpell() {
        return mySpell;
    }
}
