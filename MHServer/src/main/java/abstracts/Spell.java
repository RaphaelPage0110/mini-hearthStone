package abstracts;

import identifiers.IdSpell;

import java.util.ArrayList;
import inter.Effect;

public abstract class Spell extends Card {

	 private Integer id;

	 /**
		* Returns the value of id.
		* @return this.id.
		*/
	 public Integer getId() {
			 return this.id;
	 }

	 /**
		* Default empty constructor which initializes the ID and the list of actions.
		*/
	 public Spell() {
			 this.id = new IdSpell(this).getId();
			 this.myEffects = new ArrayList<>();
	 }
}
