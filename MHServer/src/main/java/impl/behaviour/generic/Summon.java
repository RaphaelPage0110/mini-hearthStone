package impl.behaviour.generic;

import abstracts.Card;
import abstracts.Hero;
import abstracts.Minion;
import abstracts.Spell;
import impl.ConcreteMinion;
import impl.Player;
import impl.behaviour.minion.Taunt;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

/**
 * Class representing the special action "Summon" used for the "Mirror Image" (fr:Image Miroir) spell.
 * Summon two servants 0/2 with Taunt.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public class Summon implements Effect {

    private Spell mySpell;
    private Hero myHero;
    private String myMinionKeyword;

    public Summon(Spell newSpell, String minionKeyword) {
        this.mySpell = newSpell;
        this.myMinionKeyword = minionKeyword;
    }

    public Summon(Hero myHero, String minionKeyword) {
        this.myHero = myHero;
        this.myMinionKeyword = minionKeyword;

    }

    public void effect(Target myTarget) {
        Player player = mySpell.getPlayer();

        ConcreteMinion minion = new ConcreteMinion();
        Minion minionToSummon;

        minionToSummon = minion.summon(myMinionKeyword);

        player.addMinion(minionToSummon);

        /*ArrayList<Effect> uselessMinionAction = new ArrayList<>();
        Minion uselessMinion = new ConcreteMinion("Useless Minion", 0, 0, 2, Card.CardType.MAGE, uselessMinionAction);
        uselessMinionAction.add(new Taunt(uselessMinion));

        Minion uselessMinion2 = uselessMinion.copy();

        Player player = mySpell.getPlayer();

        player.addMinion(uselessMinion);
        player.addMinion(uselessMinion2);*/
    }
}
