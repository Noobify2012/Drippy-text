package test;

import random.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Testing for the random number generator. This uses a seed so that we can know the next value.
 */
public class RandomNumberGeneratorTest {

  @org.junit.Test
  public void getRandomNumber() {
    RandomNumberGenerator rand = new RandomNumberGenerator(1);
    int random = rand.getRandomNumber(0,10);
    assertEquals(0, random);
  }
}