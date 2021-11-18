package model;

/**
 * The representation of a monster.
 */
public interface Monster {

  /**Gets the health of a monster as an integer. The maximum/starting value of a monster is 2.
   *
   * @return the health as an integer. The value is between 0 and 2, where 2 is the maximum/starting
   *          health value and 0 means the monster is dead.
   */
  int getHealth();

  /**If the monster is alive(health greater than or equal to 1), this reduces the health of the
   * monster by one. If the health is 0 it just returns 0.
   *
   * @return The remaining health of the monster as an integer. Returns a value between 0 and 1.
   */
  int takeDamage();
}
