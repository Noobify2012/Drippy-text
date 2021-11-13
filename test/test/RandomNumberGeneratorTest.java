package test;

import random.RandomNumberGenerator;

import static org.junit.Assert.assertTrue;

public class RandomNumberGeneratorTest {

  @org.junit.Test
  public void getRandomNumber() {
    RandomNumberGenerator rand = new RandomNumberGenerator(0);
    int random = rand.getRandomNumber(0,10);
    assertTrue(random >= 0);
    assertTrue(random <= 10);
  }

}