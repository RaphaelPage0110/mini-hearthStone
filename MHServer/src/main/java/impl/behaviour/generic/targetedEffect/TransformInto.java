package impl.behaviour.generic.targetedEffect;

import abstracts.Spell;
import impl.Player;
import inter.Target;
import inter.TargetedEffect;

/**
 * Class representing the special action "TransformInto" used for the "Polymorph" (fr:Métamorphose) spell.
 * Transforms a targeted minion into a 1/1 minion.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class TransformInto extends TargetedEffect {

    private Spell mySpell;
    private String myMinionKeyword;
    private Player myPlayer;

    public TransformInto(Spell newSpell, String minionKeyword) {
        this.mySpell = newSpell;
        this.myMinionKeyword = minionKeyword;
        this.myPlayer = mySpell.getPlayer();
    }

    public void effect(Target target) {

    }

    public String getMyMinionKeyword() {
        return myMinionKeyword;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }
}
