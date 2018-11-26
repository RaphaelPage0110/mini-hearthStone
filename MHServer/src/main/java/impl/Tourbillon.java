package impl;

import inter.CardAction;
import inter.Target;
import abstracts.Minion;
import abstracts.Spell;
import java.util.ArrayList;

public class Tourbillon implements CardAction {

    private Spell mySpell;

    public Tourbillon() {

    }
    
    public Tourbillon(Spell mySpell) {
        this.mySpell = mySpell;
    }

    public void effect(Target myTarget) {
/*
      myTargets = new ArrayList<Minion>;
      alliedMinions = new ArrayList<Minion>;
      opponentsMinions = new ArrayList<Minion>;

      alliedMinions = mySpell.getPlayer().getMyMinions();
      opponentsMinions = mySpell.getPlayer().getOpponentsMinions;
      myTargets = alliedMinions.addAll(0, opponentsMinions);

      for (Minion object: list) {
        System.out.println(object);
      }*/
    }
}
