package abstracts;



import java.util.ArrayList;


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
