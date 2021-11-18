package model;

/**
 * A treasure object that can be defined as a Ruby, Sapphire, or Diamond.
 */
public interface Treasure {

  /**Generates a new piece of treasure.
   *
   * @return A treasure object.
   */
  Treasure getTreasure();

  /**Gets the name of the piece of treasure.
   *
   * @return the name of the piece of treasure as a string.
   */
  String getName();

}
