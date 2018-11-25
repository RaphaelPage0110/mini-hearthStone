package identifiers;

import abstracts.Spell;

public class IdSpell extends Id {
    private Spell spell;

    public IdSpell(Spell spell) {
        super();
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }
}
