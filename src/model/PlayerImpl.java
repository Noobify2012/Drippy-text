package model;

import driver.Driver;
import java.util.ArrayList;

/**
 * The implementation of the player. This player when created has a location and treasure list that
 * will be filled as they move from cave to cave.
 */
public class PlayerImpl implements Player {
  private int playerLocation;
  private ArrayList<Treasure> treasureList;
  private ArrayList<Direction> directions;
  private ArrayList<Treasure> currentTreasure;

  /**
   * The constructor of a player.
   */
  public PlayerImpl() {
    this.playerLocation = playerLocation;
    this.treasureList = new ArrayList<>();
    ArrayList<Treasure> treasureList = new ArrayList<>();
    ArrayList<Direction> directions = new ArrayList<>();
    this.currentTreasure = new ArrayList<>();
  }

  /**This helps to update the players location based on the index of the cave the player is now in.
   *
   * @param index the index of the cave that the player is moving to.
   * @param directions the directions that the player can move based on the location they are moving
   *                  to.
   * @param curTreasure the treasure in the cave of associated index.
   */
  private void updatePlayerLocation(int index, ArrayList<Direction> directions,
                                    ArrayList<Treasure> curTreasure) {
    this.playerLocation = index;
    this.directions = directions;
    updateTreasure();
    if (!curTreasure.isEmpty()) {
      for (int i = 0; i < curTreasure.size(); i++) {
        this.currentTreasure.add(curTreasure.get(i));
      }
    }
    //update the location after a move
    //this.playerLocation =
  }

  private void updateTreasure() {
    //treasure list is empty and the player picked up treasure in the last cave
    if (this.currentTreasure != null) {
      for (int i = 0; i < this.currentTreasure.size(); i++) {
        this.treasureList.add(this.currentTreasure.get(i));
      }
    }
  }


  /**
   * The player moves east.
   */
  @Override
  public void move(int index, ArrayList<Direction> directions,
                   ArrayList<Treasure> curTreasure) {

    updatePlayerLocation(index, directions, curTreasure);

  }

  /**A helper to get the current treasure list.
   *
   * @return the current contents of the player's treasure list.
   */
  public ArrayList<Treasure> getTreasureList() {
    ArrayList<Treasure> treasureCopy = new ArrayList<>();
    for (int i = 0; i < this.treasureList.size(); i++) {
      treasureCopy.add(this.treasureList.get(i));
    }
    return treasureCopy;
  }

  /**This builds and returns the player's status which includes, the index of the cave they are
   * currently in, the treasure the player has collected so far, the moves the player can make based
   * on their current location, and the treasure in the cave they are currently in.
   *
   */
  @Override
  public void getPlayerStatus() {
    String treasureString = "";
    String directionString = "";
    String curTreasureString = "";
    if (this.treasureList == null || this.treasureList.size() == 0) {
      treasureString = "nothing";
    } else {
      for (int i = 0; i < this.treasureList.size(); i++) {
        treasureString = treasureString + " " + this.treasureList.get(i).getName() + ",";
      }
    }

    if (directions.size() == 1) {
      directionString = directions.toString() + " ";
    } else {
      for (int i = 0; i < directions.size(); i++) {
        directionString = directionString.concat(directions.get(i) + " ");
      }
    }

    if (this.currentTreasure == null || this.currentTreasure.size() == 0) {
      curTreasureString = "no treasure in this cave";
    } else if (currentTreasure.size() == 1) {
      curTreasureString = "a " + currentTreasure.get(0).getName();
    } else {
      for (int i = 0; i < currentTreasure.size(); i++) {
        curTreasureString = curTreasureString + " " + this.currentTreasure.get(i).getName() + ",";
      }
    }

    String moveString = "";
    String playerString = "The player is currently in Cave " + playerLocation + " and has "
            + treasureString + " in their treasure bag. \nThey can go " + directionString
            + "and there is " + curTreasureString + " in this cave.";
    Driver.printHelper(playerString);
  }


  /**This updates the players location based on the index of the start point, the treasure in the
   * cave that the player enters, and the directions the player can go from that start location.
   *
   * @param caveIndex the index of the cave that the player enters
   * @param treasureInCave the treasure in the cave where the player enters the dungeon.
   * @param directions the directions the player can go from the start point.
   */
  @Override
  public void enterDungeon(int caveIndex, ArrayList<Treasure> treasureInCave,
                           ArrayList<Direction> directions) {
    updatePlayerLocation(caveIndex, directions, treasureInCave);
  }
}
