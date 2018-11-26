package abstracts;

import identifiers.IdSpell;
import inter.Target;
import java.util.ArrayList;
import inter.CardAction;

public abstract class Spell extends Card {

	 private IdSpell id;

	 /**
		* Returns the value of id.
		* @return this.id.
		*/
	 public IdSpell getId() {
			 return this.id;
	 }

	 /**
		* Default empty constructor which initializes the ID and the list of actions.
		*/
	 public Spell() {
			 this.id = new IdSpell(this);
			 this.myActions = new ArrayList<CardAction>();
	 }
}
