package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a cave object which has a unique index, what set it is in for completing
 * Kruskal's, a list of its neighbors, and a list of the treasure in that cave.
 */
public class Cave extends AbstractLocation {
  private int index;
  private int set;
  private List<Integer> neighborList;
  private List<Treasure> caveTreasureList;
  private List<CrookedArrow> arrowList;
  private List<Monster> monsterList;

  protected Cave(int row, int column, List<Integer> neighborList,
                 List<Treasure> treasureList, int index, int set, List<CrookedArrow> arrowList,
                 List<Monster> monsterList) {
    super(new Point2D(row, column), neighborList, treasureList, arrowList);
    this.index = index;
    this.set = set;
    this.neighborList = neighborList;
    this.caveTreasureList = treasureList;
    this.arrowList = arrowList;
    this.monsterList = monsterList;
    if (neighborList.size() == 2 && !treasureList.isEmpty()) {
      throw new IllegalStateException("Tunnels can not have treasure");
    }
    if (neighborList.size() == 2 && !monsterList.isEmpty()) {
      throw new IllegalStateException("Tunnels can not have monsters");
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
  public List<Integer> getNeighbors() {
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
   * @return a deep copy array list of all the treasure that the cave has.
   */
  public List<Treasure> getTreasureList() {
    List<Treasure> treasureForPlayer = new ArrayList<>();
    if (!this.caveTreasureList.isEmpty()) {
      for (int i = 0; i < caveTreasureList.size(); i++) {
        treasureForPlayer.add(i, this.caveTreasureList.get(i));
      }
    }
    return treasureForPlayer;
  }

  List<Treasure> getTreasureFromCave() {
    List<Treasure> treasureForPlayer = new ArrayList<>();
    if (!this.caveTreasureList.isEmpty()) {
      for (int i = 0; i < caveTreasureList.size(); i++) {
        treasureForPlayer.add(i, this.caveTreasureList.get(i));
      }
    }
    int temp = this.caveTreasureList.size();
    for (int t = 0; t < temp; t++) {
      this.caveTreasureList.remove(0);
    }
    return treasureForPlayer;
  }

  List<CrookedArrow> getArrowsFromCave() {
    List<CrookedArrow> arrowsForPlayer = new ArrayList<>();
    if (!this.arrowList.isEmpty()) {
      for (int i = 0; i < arrowList.size(); i++) {
        arrowsForPlayer.add(i, this.arrowList.get(i));
      }
    }
    this.arrowList.remove(0);
    return arrowsForPlayer;
  }

  void addMonster(Monster monster) {
    if (this.monsterList.size() == 0) {
      this.monsterList.add(monster);
    } else {
      throw new IllegalArgumentException("Can't add more than one monster to a cave.");
    }
  }

  int getMonsterListSize() {
    return this.monsterList.size();
  }

  int getMonsterHealth() {
    if (this.monsterList.isEmpty()) {
      return 0;
    } else {
      return this.monsterList.get(0).getHealth();
    }
  }

  int getArrowListSize() {
    return this.arrowList.size();
  }

  void addArrow(CrookedArrow arrow) {
    this.arrowList.add(arrow);
  }

  Monster getMonster() {
    return this.monsterList.get(0);
  }


}
