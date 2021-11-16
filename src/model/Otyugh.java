package model;

public class Otyugh implements Monster {
  private Location location;
  private int health;


  public Otyugh(int health) {

    this.health = health;
  }

  public int takeDamage() {
    if (this.health == 0) {
      return 0;
    } else {
      this.health--;
      int tempHealth = this.health;
      return tempHealth;
    }
  }

  public int getHealth() {
    int temp = this.health;
    return temp;
  }
}
