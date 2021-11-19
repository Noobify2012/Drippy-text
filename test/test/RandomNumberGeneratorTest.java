package test;

import random.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;

public class RandomNumberGeneratorTest {

  @org.junit.Test
  public void getRandomNumber() {
    RandomNumberGenerator rand = new RandomNumberGenerator(1);
    int random = rand.getRandomNumber(0,10);
    assertEquals(0, random);
  }
}