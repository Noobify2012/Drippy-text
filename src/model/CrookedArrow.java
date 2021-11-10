package model;

public class CrookedArrow {
  private int distance;
  private Direction direction;

  public CrookedArrow(int distance, Direction direction) {
    this.distance = distance;
    this.direction = direction;

    if (distance <= 0) {
      throw new IllegalArgumentException("Cannot shoot an arrow less than 1.");
    }
  }
}
