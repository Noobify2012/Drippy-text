package model;

import java.util.ArrayList;

/**
 * This represents a cave object which has a unique index, what set it is in for completing
 * Kruskal's, a list of its neighbors, and a list of the treasure in that cave.
 */
public class Cave extends AbstractLocation {
  private int index;
  private int set;
  private ArrayList<Integer> neighborList;
  private ArrayList<Treasure> caveTreasureList;

  protected Cave(int row, int column, ArrayList<Integer> neighborList,
                 ArrayList<Treasure> treasureList, int index, int set) {
    super(new Point2D(row, column), neighborList, treasureList);
    this.index = index;
    this.set = set;
    this.neighborList = neighborList;
    this.caveTreasureList = treasureList;
    if (neighborList.size() == 2 && !treasureList.isEmpty()) {
      throw new IllegalStateException("Tunnels can not have treasure");
    }
  }

  /**Gets the row of the cave.
   *
   * @return the cave's row value as an integer.
   */
  protected int getRow() {
    return location.getRow();
  }

  /**Gets the column of the cave.
   *
   * @return the cave's column as an integer.
   */
  protected int getColumn() {
    return location.getColumn();
  }

  /**Gets the cave's index.
   *
   * @return the cave's unique id or index as an integer.
   */
  protected int getIndex() {
    return this.index;
  }

  /**Gets the cave's set, used for running Kruskal's.
   *
   * @return the cave's set as an integer.
   */
  protected int getSet() {
    return this.set;
  }

  /**Changes the cave's set, used for Kruskal's.
   *
   * @param set the integer value of the new set that the cave is being added to.
   */
  protected void adjSet(int set) {
    this.set = set;
  }

  /**The index of a neighbor being added to the list of neighbors.
   *
   * @param index the integer value of the cave being added as a neighbor.
   */
  void addNeighbor(int index) {
    this.neighborList.add(index);
  }

  /**Gets the list of neighbors for the current cave.
   *
   * @return an ArrayList of the neighbors of the current cave.
   */
  public ArrayList<Integer> getNeighbors() {
    return this.neighborList;
  }

  /**Adds treasure to the treasure list based on the treasure that a player picks up.
   *
   * @param treasure takes in treasure and adds it to the player's treasure list.
   */
  void addTreasure(Treasure treasure) {
    this.caveTreasureList.add(treasure);
  }

  /**Gets the players treasure list.
   *
   * @return an array list of all the treasure that has been added to the player.
   */
  public ArrayList<Treasure> getTreasureList() {
    return this.caveTreasureList;
  }

  ArrayList<Treasure> getTreasureFromCave() {
    ArrayList<Treasure> treasureForPlayer = new ArrayList<>();
    if (!this.caveTreasureList.isEmpty()) {
      for (int i = 0; i < caveTreasureList.size(); i++) {
        treasureForPlayer.add(i, this.caveTreasureList.get(i));
      }
    }
    return treasureForPlayer;
  }
}
