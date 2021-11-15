package random;

import java.util.Random;

/**
 * A class for generating random numbers and list of random numbers.
 */
public class RandomNumberGenerator {
  private int min;
  private int max;
  private int seed;
  private int listSize;
  private Random generator;


  /**A random number generator constructor that is called to help pick random numbers based on the
   * needs of different methods. A seed can be used to help predict the sequence of numbers but is
   * strictly for testing purposes.
   *
   * @param min An integer which represents the minimum value required for generating random
   *            numbers.
   * @param max An integer which represents the maximum value required for generating random
   *            numbers.
   * @param seed A seed value for helping produce consistent and repeatable numbers for testing.
   * @param listSize A list of integers which may be required for certain operations.
   */
  public RandomNumberGenerator(int seed) {
    if (seed == 0) {
      this.generator = new Random();
    } else {
      this.generator = new Random(0);
    }
    if (min == 0) {
      this.min = min;
    }
    this.max = max + 1;
    this.seed = seed;


    if (min >= max) {
      throw new IllegalArgumentException("The minimum value cannot be equal to or greater than the"
              + " maximum value");
    }
  }

  /**Used to generate a single random number. Takes in a min and max from the constructor above.
   *
   * @return An integer between the minimum and maximum inclusively.
   */
  public int getRandomNumber(int min, int max) {
    int returnInt = generator.nextInt(max - min) + min;
    return returnInt;
  }

}
