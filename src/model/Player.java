package model;

import java.util.List;

/**
 * Represents a player and all of the associated actions.
 */
public interface Player {

  /**
   * The player moves.
   */
  void move(Cave cave, List<Direction> directions);

  /**This builds and returns the player's status which includes, the index of the cave they are
   * currently in, the treasure the player has collected so far, the moves the player can make based
   * on their current location, and the treasure in the cave they are currently in.
   *
   */
  String getPlayerStatus(int smell, Cave cave);

  /**This updates the players location based on the index of the start point, the treasure in the
   * cave that the player enters, and the directions the player can go from that start location.
   *
   * @param caveIndex the index of the cave that the player enters
   * @param treasureInCave the treasure in the cave where the player enters the dungeon.
   * @param possibleDirection the directions the player can go from the start point.
   */
  void enterDungeon(int caveIndex, List<Treasure> treasureInCave,
                    List<Direction> possibleDirection);

  /**A helper to get the current treasure list.
   *
   * @return the current contents of the player's treasure list.
   */
  List<Treasure> getTreasureList();

  /**This checks to see if the player is still alive.
   *
   * @return a boolean value of whether the player is alive or not. False is dead, true is alive.
   */
  boolean isPlayerAlive();

  /**This just returns the index of the cave which the player is located in.
   *
   * @return the integer value of the cave which the player is in.
   */
  int getPlayerLocation();

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
  String monsterEncounter(int monsterHealth, int rand);

  /**Executes the player shooting an arrow and returns the result as a string. If the player hears a
   * howl then the monster was hit and has half health. If the player hears a howl and a loud thud,
   * the monster they shot has died. Otherwise, they will either hear a zing as the arrow bounces
   * off a wall, or they won't hear everything if they arrow runs out of distance.
   *
   * @param distance the number of caves the arrow will traverse as an integer.
   * @param direction the direction the player shoots the arrow.
   * @return A string describing the result of the shot of the arrow.
   */
  String shoot(int distance, Direction direction);

  /**Executes the action of a player picking up arrows or treasure. It allows the player to pickup
   * arrows or treasure or both at the same time. It will pick up all treasure or an arrow. There
   * are only 1 arrow per location if they are present.
   *
   * @param cave the cave that the player is currently in.
   * @param option the option of either treasure, arrows, or both for picking up items in a cave or
   *               tunnel.
   * @return The string result of attempting to pickup items.
   */
  String pickUp(Cave cave, int option);

}
