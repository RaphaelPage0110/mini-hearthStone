package impl;

import abstracts.Hero;
import abstracts.Spell;
import inter.CardAction;
import inter.Target;

public class modifyArmor implements CardAction {


    private Spell mySpell;
    private int armorModifier;

    public modifyArmor(Spell mySpell, int armorPoints) {
        this.mySpell = mySpell;
        this.armorModifier = armorPoints;
    }

    public void effect(Target myTarget) {

        Hero hero;
        hero = (Hero)myTarget;
        hero.addArmor(armorModifier);

    }
}
