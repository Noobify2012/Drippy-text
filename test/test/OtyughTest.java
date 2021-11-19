package test;

import org.junit.Test;

import model.Monster;
import model.Otyugh;

import static org.junit.Assert.assertEquals;

/**
 * Testing for the monster functionality.
 */
public class OtyughTest {

  @Test
  public void takeDamage() {
    Monster testMonster = new Otyugh(2);
    int damageInt = testMonster.takeDamage();
    assertEquals(1, damageInt);
    int damageInt2 = testMonster.takeDamage();
    assertEquals(0, damageInt2);
  }

  @Test
  public void getHealth() {
    Monster testMonster = new Otyugh(2);
    int testInt = testMonster.getHealth();
    assertEquals(2, testInt);
  }
}