package model;

public class CrookedArrow {
  private int distance;
  private Direction direction;

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
