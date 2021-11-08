package model;

import java.util.ArrayList;

/**This represents a dungeon which is where the player navigates from a start point to an end point.
 *
 */
public interface Dungeon {

  /**A helper to get the number of rows in the game board.
   *
   * @return the number of rows in the game board as an integer.
   */
  int getGameBoardRows();

  /**A helper to get the number of columns in the game board.
   *
   * @return the number of columns in the game board as an integer.
   */
  int getGameBoardCols();

  /** A helper to set up the dungeon and run the player through their predesignated path.
   *
   */
  void getDungeon();

  /**A getter method that returns the list of final edges after Kruskal's has been run.
   *
   * @return an arraylist of the edges which were selected to act as the paths in the dungeon.
   */
  ArrayList<Edge> getFinalEdgeList();

  /**A getter that returns the final path the player will take from the start point to the end.
   *
   * @return an ArrayList of the indexes of the path from the start point to the end point.
   */
  ArrayList<Integer> getFinalPath();

  /**A getter that returns a copy of the game board data.
   *
   * @return a deep copy of the game board.
   */
  Cave[][] getGameBoard();

}
