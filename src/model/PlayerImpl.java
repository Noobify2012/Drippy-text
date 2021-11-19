package model;

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
  private List<CrookedArrow> quiver;
  private boolean isAlive;

  /**
   * The constructor of a player.
   */
  public PlayerImpl() {
    this.playerLocation = playerLocation;
    this.treasureList = new ArrayList<>();
    List<Treasure> treasureList = new ArrayList<>();
    List<Direction> directions = new ArrayList<>();
    this.isAlive = true;
    this.quiver = new ArrayList<>();

    for (int a = 0; a < 3; a++) {
      CrookedArrow arrow = new CrookedArrow();
      this.quiver.add(arrow);
    }
  }

  /**This helps to update the players location based on the index of the cave the player is now in.
   *
   * @param index the index of the cave that the player is moving to.
   * @param directions the directions that the player can move based on the location they are moving
   *                  to.
   */
  private void updatePlayerLocation(int index, List<Direction> directions) {
    this.playerLocation = index;
    this.directions = directions;
  }

  /**
   * The player moves.
   */
  public void move(Cave cave, List<Direction> directions) {
    updatePlayerLocation(cave.getIndex(), directions);
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
  public String getPlayerStatus(int smell, Cave cave) {
    String treasureString = "";
    if (this.treasureList == null || this.treasureList.size() == 0) {
      treasureString = "nothing";
    } else {
      treasureString = getTreasureString(this.treasureList);
    }

    String directionString = "";
    if (directions.size() == 1) {
      directionString = directions.toString() + " ";
    } else {
      for (int i = 0; i < directions.size(); i++) {
        directionString = directionString.concat(directions.get(i) + " ");
      }
    }

    String curTreasureString = "";
    if (cave.getTreasureList().size() == 0) {
      curTreasureString = "no treasure in this cave";
    } else if (cave.getTreasureList().size() == 1) {
      curTreasureString = "a " + cave.getTreasureList().get(0).getName();
    } else {
      for (int i = 0; i < cave.getTreasureList().size(); i++) {
        curTreasureString = getTreasureString(cave.getTreasureList());
      }
    }

    String arrowString = "";
    if (cave.getArrowListSize() == 0) {
      //arrow string says it has nothing
      arrowString = "no arrows";
    } else {
      //arrowstring says it has something.
      arrowString = "an arrow";
    }

    String monsterString = "";
    String playerString = "The player is currently in Cave " + playerLocation + " and has "
            + treasureString + " in their treasure bag. \nThey can go " + directionString
            + ", there are " + quiver.size() + " arrows remaining in their quiver, and there is "
            + curTreasureString + " and " + arrowString + " in this cave.\n";
    if (smell >= 2) {
      monsterString = ("\nThe player smells something awful and strong.\n");
      playerString = playerString + monsterString;
    } else if (smell == 1) {
      monsterString = ("\nThe player smells something faint but awful.\n");
      playerString = playerString + monsterString;
    }
    return playerString;
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
    updatePlayerLocation(caveIndex, directions);
  }

  /**This checks to see if the player is still alive.
   *
   * @return a boolean value of whether the player is alive or not. False is dead, true is alive.
   */
  public boolean isPlayerAlive() {
      return this.isAlive;
  }

  /**This just returns the index of the cave which the player is located in.
   *
   * @return the integer value of the cave which the player is in.
   */
  public int getPlayerLocation() {
    int temp = playerLocation;
    return temp;
  }

  /**Handles how a player interacts with a monster when they run into one. If the Monster has full
   * health the player dies, else if monster has half health the player has a 50/50 chance of
   * surviving, else the player just sees the monsters dead body.
   *
   * @param monsterHealth the integer value of the monster's health that is in the same cave as the
   *                     player.
   * @param rand the random integer value used to determine if the player escapes if the monster has
   *            half health. If the value is 0 the player dies, if its 1 the player escapes.
   * @return the string describing the interaction between the player and monster.
   */
  public String monsterEncounter(int monsterHealth, int rand) {
    String encounterString = "";
    if (monsterHealth == 2 || (monsterHealth == 1 && rand == 1)) {
      isAlive = false;
      encounterString = "Chomp! Our player was eaten by a Monster.";
    } else if (monsterHealth == 1 && rand == 0) {
      //player survives
      encounterString = "Whew! Our player barely escapes being eaten by a Monster.";
    } else if (monsterHealth == 0) {
      encounterString = "Our player finds the body of a slain Monster.";
    }
    return encounterString;
  }

  /**Executes the player shooting an arrow and returns the result as a string. If the player hears a
   * howl then the monster was hit and has half health. If the player hears a howl and a loud thud,
   * the monster they shot has died. Otherwise, they will either hear a zing as the arrow bounces
   * off a wall, or they won't hear everything if they arrow runs out of distance.
   *
   * @param distance the number of caves the arrow will traverse as an integer.
   * @param direction the direction the player shoots the arrow.
   * @return A string describing the result of the shot of the arrow.
   */
  @Override
  public String shoot(int distance, Direction direction) {
    if (distance < 0) {
      throw new IllegalArgumentException("Cannot shoot less than 0");
    }

    if (direction == null) {
      throw new IllegalArgumentException("Must have a direction to shoot");
    }
    //make sure player has arrows and if so deduct 1, else throw error.
    String quiverString = updateArrowCount();
    return quiverString;
  }

  /**Executes the action of a player picking up arrows or treasure. It allows the player to pickup
   * arrows or treasure or both at the same time. It will pick up all treasure or an arrow. There
   * are only 1 arrow per location if they are present.
   *
   * @param cave the cave that the player is currently in.
   * @param option the option of either treasure, arrows, or both for picking up items in a cave or
   *               tunnel.
   * @return The string result of attempting to pickup items.
   */
  @Override
  public String pickUp(Cave cave, int option) {
    List<Treasure> treasList = new ArrayList<>();
    List<CrookedArrow> arrowList = new ArrayList<>();
    String pickupString = "";
    String arrowString = "";
    String treasureString = "";
    if (option == 0) {
      treasureString = pickupTreasure(cave);
      pickupString = pickupString + treasureString;
    } else if (option == 1) {
      if (cave.getArrowListSize() == 0) {
        pickupString = "\nThere are no arrows for the player to pick up.";
      } else {
        arrowString = pickupArrows(cave);
        pickupString = pickupString + arrowString;
      }
      //pick up arrows
    } else if (option == 2) {
      if (cave.getTreasureList().size() == 0 && cave.getArrowListSize() == 0) {
        pickupString = "\nThere is no treasure or arrows for the player to pick up.";
      } else {
        treasureString = pickupTreasure(cave);
        arrowString = pickupArrows(cave);
        pickupString = pickupString + treasureString + arrowString;

      }
      //pick up both
    }
    return pickupString;
  }

  private String pickupTreasure(Cave cave) {
    String treasureString = "";
    List<Treasure> treasList = new ArrayList<>();
    if (cave.getTreasureList().size() == 0) {
      treasureString = "\nThere is no treasure for the player to pick up.";
    } else {
      treasureString = "The player picked up " + getTreasureString(cave.getTreasureList());
      treasList = cave.getTreasureFromCave();
    }
    for (int i = 0; i < treasList.size(); i++) {
      this.treasureList.add(treasList.get(i));
    }
    return treasureString;
  }

  private String pickupArrows(Cave cave) {
    String arrowString = "";
    List<CrookedArrow> tempList = new ArrayList<>();
    if (cave.getArrowListSize() == 0) {
      arrowString = "\nThere are no arrows for the player to pick up.";
    } else {
      arrowString = "\nThe player picked up an arrow.";
      tempList = cave.getArrowsFromCave();
      this.quiver.add(tempList.get(0));
    }
    return arrowString;
  }

  private String updateArrowCount() {
    if (quiver.size() == 0) {
      throw new IllegalArgumentException("Player doesn't have any arrows to shoot.");
    } else {
      quiver.remove(0);
      String quiverString = "\nThe player has " + quiver.size() + " arrows remaining.";
      return quiverString;
    }

  }
}
