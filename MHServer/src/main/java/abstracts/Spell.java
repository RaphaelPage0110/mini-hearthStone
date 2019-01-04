package abstracts;

import identifiers.IdSpell;

import java.util.ArrayList;
import inter.Effect;
import org.springframework.data.annotation.Id;

public abstract class Spell extends Card {

	 /**
		* Default empty constructor which initializes the ID and the list of actions.
		*/
	 public Spell() {
			 this.myEffects = new ArrayList<>();
	 }

	@Override
	public String toString() {
		return String.format(
				"Minion[id=%s, spellName='%s', damage='%s']",
				id, name, damagePoints);
	}
}
