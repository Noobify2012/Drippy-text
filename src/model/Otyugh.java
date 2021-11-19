package model;

/**
 * The otyugh class which contains the two operations of the monster. These are to get the heatlh
 * and for the monster to take damage when they are hit.
 */
public class Otyugh implements Monster {
  private int health;


  /**The constructor for the Otyugh. It only has one field which is the health. An integer value
   * between 0 and 2.
   *
   * @param health The health of the monster which starts at 2 and is reduced depending on the
   *               number of times they are shot.
   */
  public Otyugh(int health) {

    this.health = health;
  }

  /**Gets the health of a monster as an integer. The maximum/starting value of a monster is 2.
   *
   * @return the health as an integer. The value is between 0 and 2, where 2 is the maximum/starting
   *        health value and 0 means the monster is dead.
   */
  public int takeDamage() {
    if (this.health == 0) {
      return 0;
    } else {
      this.health--;
      int tempHealth = this.health;
      return tempHealth;
    }
  }

  /**If the monster is alive(health greater than or equal to 1), this reduces the health of the
   * monster by one. If the health is 0 it just returns 0.
   *
   * @return The remaining health of the monster as an integer. Returns a value between 0 and 1.
   */
  @Override
  public int getHealth() {
    int temp = this.health;
    return temp;
  }
}
