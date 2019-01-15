package inter;

/**
 * Interface that allows a target (Hero or Minion) to be attacked.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public interface Target {

  /**
   * Allows a target to be attacked and lose health points or armor points.
   *
   * @param damageTaken the number of damage points inflicted to this target. Must be >= 0.
   * @return the number of health points that the target has lost. Must be >= 0.
   */
  int takeDamage(int damageTaken);

  /**
   * Check if the Target points are below 0.
   *
   * @return true if the target is dead, false otherwise
   */
  boolean isDead();

  /**
   * what happens when a target dies
   */
  void dies();

  /**
   * Add a bonus to the damage points.
   *
   * @param bonusDamage the number of points to add.
   */
  void addDamagePoints(int bonusDamage);

  /** */
  void heal(int healthPoints);

  /** */
  void addMaxHealthPoints(int bonusHealthPoints);

  int getMaxHealthPoints();

  void setMaxHealthPoints(int maxHealthPoints);
}
