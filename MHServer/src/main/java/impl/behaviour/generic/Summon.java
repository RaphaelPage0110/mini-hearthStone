package impl.behaviour.generic;

import abstracts.Card;
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
    private Minion myMinion;

    public Summon(Spell newSpell, Minion newMinion) {
        this.mySpell = newSpell;
        this.myMinion = newMinion;
    }

    @Override
    public void effect() {
        Player player = mySpell.getPlayer();

        player.addMinion(myMinion);






        /*ArrayList<Effect> uselessMinionAction = new ArrayList<>();
        Minion uselessMinion = new ConcreteMinion("Useless Minion", 0, 0, 2, Card.CardType.MAGE, uselessMinionAction);
        uselessMinionAction.add(new Taunt(uselessMinion));

        Minion uselessMinion2 = uselessMinion.copy();

        Player player = mySpell.getPlayer();

        player.addMinion(uselessMinion);
        player.addMinion(uselessMinion2);*/
    }
}
