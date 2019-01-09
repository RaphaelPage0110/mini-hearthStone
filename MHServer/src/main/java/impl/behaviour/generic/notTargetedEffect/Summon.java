package impl.behaviour.generic.notTargetedEffect;

import abstracts.Hero;
import abstracts.Spell;
import impl.Player;
import inter.NotTargetedEffect;


import java.util.Arrays;
import java.util.List;

/**
 * Class representing the special action "Summon" used for the "Mirror Image" (fr:Image Miroir) spell.
 * Summon two servants 0/2 with Taunt.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Summon extends NotTargetedEffect {

    private Spell mySpell;
    private Hero myHero;
    private String myMinionKeyword;
    private int numberSummoned;
    private Player myPlayer;

    public Summon(Spell newSpell, String minionKeyword) {

        this.mySpell = newSpell;
        this.myPlayer = mySpell.getPlayer();

        //in the database, the summon action is sotred as follow:
        //"summon": "nameOfTheMinion, numberOfMinion"
        //hence the reason why we need to split the received String
        List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
        myMinionKeyword = minionKeywords.get(0);
        numberSummoned = Integer.parseInt(minionKeywords.get(1));

    }


    public Summon(Hero myHero, String minionKeyword) {

        this.myHero = myHero;
        this.myPlayer = myHero.getMyPlayer();

        //in the database, the summon action is sotred as follow:
        //"summon": "nameOfTheMinion, numberOfMinion"
        //hence the reason why we need to split the received String
        List<String> minionKeywords = Arrays.asList(minionKeyword.split(",[ ]*"));
        myMinionKeyword = minionKeywords.get(0);
        numberSummoned = Integer.parseInt(minionKeywords.get(1));
    }

    public void effect() {

    }

    public String getMyMinionKeyword() {
        return myMinionKeyword;
    }

    public int getNumberSummoned() {return numberSummoned; }

    public Hero getMyHero() {
        return myHero;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }
}
