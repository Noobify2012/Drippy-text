package test;

import random.RandomNumberGenerator;

import static org.junit.Assert.assertTrue;

public class RandomNumberGeneratorTest {

  @org.junit.Test
  public void getRandomNumber() {
    RandomNumberGenerator rand = new RandomNumberGenerator(0,10,0,1);
    assertTrue(rand.getRandomNumber() >= 0);
    assertTrue(rand.getRandomNumber() <= 10);
  }

}