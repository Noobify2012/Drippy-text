package model;

/** The crooked arrow class which has a distance and direction that the arrow is followed. This
 * class is really just used for placement in the map for the player to pick up and to ensure that
 * the player doesn't try to shoot an arrow a negative direction or not give a direction.
 *
 */
public class CrookedArrow {
  private int distance;
  private Direction direction;

  /**Constructor for the arrow which will be assigned a distance and direction when the arrow is
   * fired. This doesn't do anything but verify that the distance is greater than or equal to 0
   * and the direction is not null.
   */
  public CrookedArrow() {
    this.distance = 0;
    this.direction = null;

  }

  void shootArrow(int distance, Direction direction) {
    if (distance < 0) {
      throw new IllegalArgumentException("Cannot shoot less than 0 distance");
    }

    if (direction == null) {
      throw new IllegalArgumentException("must provide a direction to shoot.");
    }
    this.distance = distance;
    this.direction = direction;
  }

}
