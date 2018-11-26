package impl;

import inter.Target;
import abstracts.Minion;
import abstracts.Spell;
import java.util.ArrayList;
import inter.CardAction;

public class Tourbillon implements CardAction {

    private Spell mySpell;

    public Tourbillon() {

    }

    public Tourbillon(Spell _mySpell) {
        mySpell = _mySpell;
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
