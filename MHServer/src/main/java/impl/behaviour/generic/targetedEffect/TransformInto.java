package impl.behaviour.generic.targetedEffect;

import impl.ConcreteSpell;
import inter.Target;
import inter.TargetedEffect;

/**
 * Class representing the special action "TransformInto" used for the "Polymorph" (fr:Métamorphose) spell.
 * Transforms a targeted minion into a 1/1 minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class TransformInto extends TargetedEffect {

    private ConcreteSpell mySpell;
    private String myMinionKeyword;

    public TransformInto(ConcreteSpell newSpell, String minionKeyword) {
        this.mySpell = newSpell;
        this.myMinionKeyword = minionKeyword;
    }

    public void effect(Target target) {

    }

    public String getMyMinionKeyword() {
        return myMinionKeyword;
    }
}
