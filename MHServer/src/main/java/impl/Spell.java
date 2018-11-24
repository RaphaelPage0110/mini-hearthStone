package impl;

import abstracts.Card;

public abstract class Spell extends Card implements Target{

	 private IdSpell id;

	 public Spell() {
			 this.id = new IdSpell(this);
			 this.myActions = new ArrayList<CardAction>();
	 }

}
