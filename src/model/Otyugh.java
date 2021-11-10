package model;

public class Otyugh {
  private Location location;
  private int health;


  public Otyugh(Location location, int health) {
    this.location = location;
    this.health = health;
  }

  void setLocation(Location location) {
    this.location = location;
  }

  int takeDamage() {
    return 0;
  }
}
