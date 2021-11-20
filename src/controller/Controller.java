package controller;

import model.Dungeon;
import model.iDungeon;

/**
 * This is the controller of the MVC implementation. this class provides the user the ability to
 * build a dungeon as well as start the game.
 */
public interface Controller {

  /**
   * Collects the information for building the dungeon and attempts to contstruct it based off of
   * user input. In the current implementation, it does not get used because of needing the ability
   * to take in command line arguments but does have the capability to do so as well as create a
   * player.
   */
  void buildDungeon();

  /**Takes in a dungeon and allows the user to act as the player moving through the dungeon, firing
   * arrows, and picking up treasure and arrows.
   *
   * @param d the dungeon that is required for the user to navigate and play.
   */
  void playGame(iDungeon d);


}
