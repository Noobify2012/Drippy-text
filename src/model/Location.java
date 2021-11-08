package model;

import java.util.ArrayList;

/**
 * This represents a location which is made up of a x and y location. Currently, it doesn't
 * have any public methods.
 */
public interface Location {

  ArrayList<Treasure> getTreasureList();

  /**Gets the list of neighbors for the current cave.
   *
   * @return an ArrayList of the neighbors of the current cave.
   */
  ArrayList<Integer> getNeighbors();

}
