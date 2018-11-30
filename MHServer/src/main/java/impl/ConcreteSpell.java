package impl;

import abstracts.CardType;
import abstracts.Spell;
import inter.Effect;
import java.util.ArrayList;

public class ConcreteSpell extends Spell {
    public ConcreteSpell(String name, int requiredMana, int damagePoints, int bonus, CardType type, ArrayList<Effect> myActions) {
        super();
        this.setName(name);
        this.requiredMana = requiredMana;
        this.damagePoints = damagePoints;
        this.setBonus(bonus);
        this.type = type;
        this.myEffects = myActions;
    }
}