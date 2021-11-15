package model;

import driver.Driver;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the player. This player when created has a location and treasure list that
 * will be filled as they move from cave to cave.
 */
public class PlayerImpl implements Player {
  private int playerLocation;
  private List<Treasure> treasureList;
  private List<Direction> directions;
  private List<Treasure> currentTreasure;
  private boolean isAlive;

  /**
   * The constructor of a player.
   */
  public PlayerImpl() {
    this.playerLocation = playerLocation;
    this.treasureList = new ArrayList<>();
    List<Treasure> treasureList = new ArrayList<>();
    List<Direction> directions = new ArrayList<>();
    this.currentTreasure = new ArrayList<>();
    this.isAlive = true;
  }

  /**This helps to update the players location based on the index of the cave the player is now in.
   *
   * @param index the index of the cave that the player is moving to.
   * @param directions the directions that the player can move based on the location they are moving
   *                  to.
   * @param curTreasure the treasure in the cave of associated index.
   */
  private void updatePlayerLocation(int index, List<Direction> directions,
                                    List<Treasure> curTreasure) {
    this.playerLocation = index;
    this.directions = directions;
    updateTreasure();
    if (!curTreasure.isEmpty()) {
      for (int i = 0; i < curTreasure.size(); i++) {
        this.currentTreasure.add(curTreasure.get(i));
      }
    }
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
   * The player moves.
   */
  public void move(int index, List<Direction> directions,
                   List<Treasure> curTreasure) {

    updatePlayerLocation(index, directions, curTreasure);
  }

  /**A helper to get the current treasure list.
   *
   * @return the current contents of the player's treasure list.
   */
  public List<Treasure> getTreasureList() {
    List<Treasure> treasureCopy = new ArrayList<>();
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
  public void getPlayerStatus(int smell) {
    String treasureString = "";
    String directionString = "";
    String curTreasureString = "";
    if (this.treasureList == null || this.treasureList.size() == 0) {
      treasureString = "nothing";
    } else {
      for (int i = 0; i < this.treasureList.size(); i++) {
        treasureString = getTreasureString(this.treasureList);
      //treasureString + " " + this.treasureList.get(i).getName() + ",";
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
        curTreasureString = getTreasureString(currentTreasure);
      //curTreasureString + " " + this.currentTreasure.get(i).getName() + ",";
      }
    }

    String monsterString = "";
    String playerString = "The player is currently in Cave " + playerLocation + " and has "
            + treasureString + " in their treasure bag. \nThey can go " + directionString
            + "and there is " + curTreasureString + " in this cave.";
    if (smell >= 2) {
      monsterString = ("\nThe player smells something awful and strong.\n");
      playerString = playerString + monsterString;
    } else if (smell == 1) {
      monsterString = ("\nThe player smells something faint but awful.\n");
      playerString = playerString + monsterString;
    }
    Driver.printHelper(playerString);
  }

  private String getTreasureString(List<Treasure> treasureList) {
    int rubyInt = 0;
    int diamondInt = 0;
    int sapphireInt = 0;
    for (int t = 0; t < treasureList.size(); t++) {
      if (treasureList.get(t).getName().equalsIgnoreCase("Ruby")) {
        rubyInt++;
      } else if (treasureList.get(t).getName().equalsIgnoreCase("Diamond")) {
        diamondInt++;
      } else if (treasureList.get(t).getName().equalsIgnoreCase("Sapphire")) {
        sapphireInt++;
      }
    }
     String treasureString2 = " " + rubyInt + " rubies, " + diamondInt + " diamonds, "
             + sapphireInt + " sapphires.";
    return treasureString2;
  }


  /**This updates the players location based on the index of the start point, the treasure in the
   * cave that the player enters, and the directions the player can go from that start location.
   *
   * @param caveIndex the index of the cave that the player enters
   * @param treasureInCave the treasure in the cave where the player enters the dungeon.
   * @param directions the directions the player can go from the start point.
   */
  public void enterDungeon(int caveIndex, List<Treasure> treasureInCave,
                           List<Direction> directions) {
    updatePlayerLocation(caveIndex, directions, treasureInCave);
  }

  public boolean isPlayerAlive() {
    if (this.isAlive) {
      return true;
    } else {
      return false;
    }
  }

  public int getPlayerLocation() {
    int temp = playerLocation;
    return temp;
  }

  //  @Override
//  public void moveDirection(Direction direction) {
//    if (!directions.contains(direction)) {
//      throw new IllegalArgumentException("direction not an option");
//    } else {
//      //not useful at the moment
//    }
//  }
  public String monsterEncounter(int monsterHealth, int rand) {
    String encounterString = "";
    if (monsterHealth == 2 || (monsterHealth == 1 && rand == 1)) {
      isAlive = false;
      encounterString = "Chomp! Our player was eaten by a Monster.";
    } else if (monsterHealth == 1 && rand == 0 ) {
        //player survives
      encounterString = "Whew! Our player barely escapes being eaten by a Monster.";
    } else if (monsterHealth == 0) {
      encounterString = "Our player finds the body of a slain Monster.";
    }
    return encounterString;
  }

  @Override
  public void shoot(int distance, Direction direction) {
    if (distance < 0 ) {
      throw new IllegalArgumentException("Cannot shoot less than 0");
    }

    if (direction == null) {
      throw new IllegalArgumentException("Must have a direction to shoot");
    }


  }

  private void updateArrowCount() {

  }
}
