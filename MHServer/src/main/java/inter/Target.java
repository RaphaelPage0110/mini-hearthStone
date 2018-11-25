package inter;

/**
 * Interface that allows a target (Hero or Minion) to be attacked.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public interface Target {

    /**
     * Allows a target to be attacked and lose health points or armor points.
     * @param damagePoints the number of damage points inflicted to this target. Must be >= 0.
     * @return the number of health points that the target has lost. Must be >= 0.
     */
    int takeDamage(int damagePoints);
}
