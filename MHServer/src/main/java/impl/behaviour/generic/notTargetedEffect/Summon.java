package impl.behaviour.generic.notTargetedEffect;

import abstracts.NotTargetedEffect;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing the special action "Summon" used for the "Mirror Image" (fr:Image Miroir)
 * spell. Summon two servants 0/2 with Taunt.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Summon extends NotTargetedEffect {

    private String myMinionKeyword;
    private int numberSummoned;

    public Summon() {
    }

    public Summon(@NotNull String minionKeyword) {

        // in the database, the summon action is sored as follow:
        // "summon": "nameOfTheMinion, numberOfMinion"
        // hence the reason why we need to split the received String
        List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
        myMinionKeyword = minionKeywords.get(0);
        numberSummoned = Integer.parseInt(minionKeywords.get(1));
    }

    public void effect() {
    }

    public String getMyMinionKeyword() {
        return myMinionKeyword;
    }

    public int getNumberSummoned() {
        return numberSummoned;
    }
}
