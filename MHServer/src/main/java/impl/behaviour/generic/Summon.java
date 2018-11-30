package impl.behaviour.generic;

import abstracts.Card;
import abstracts.Hero;
import abstracts.Minion;
import abstracts.Spell;
import impl.ConcreteMinion;
import impl.Player;
import impl.behaviour.minion.Taunt;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;

import java.util.ArrayList;

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
    private Player myPlayer;

    public Summon(Spell newSpell, String minionKeyword) {
        this.mySpell = newSpell;
        this.myMinionKeyword = minionKeyword;
        this.myPlayer = mySpell.getPlayer();
    }


    public Summon(Hero myHero, String minionKeyword) {
        this.myHero = myHero;
        this.myMinionKeyword = minionKeyword;
        this.myPlayer = myHero.getMyPlayer();
    }

    public void effect() {

        ConcreteMinion minion = new ConcreteMinion();
        ConcreteMinion minionToSummon;

        minionToSummon = minion.summon(myMinionKeyword);

        myPlayer.addMinion(minionToSummon);

    }
}
