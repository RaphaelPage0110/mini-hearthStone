package identifiers;

import abstracts.Minion;

public class IdMinion extends IdTarget {
    private Minion minion;

    public IdMinion(Minion minion) {
        super();
        this.minion = minion;
    }

    public Minion getMinion() {
        return minion;
    }
}
