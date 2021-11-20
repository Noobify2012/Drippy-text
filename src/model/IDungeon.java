package model;

import java.util.List;

public interface IDungeon {


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
  String getDungeon();

  /**A getter method that returns the list of final edges after Kruskal's has been run.
   *
   * @return an arraylist of the edges which were selected to act as the paths in the dungeon.
   */
  List<Edge> getFinalEdgeList();

  /**A getter that returns a copy of the game board data.
   *
   * @return a deep copy of the game board.
   */
  Cave[][] getGameBoard();

  boolean isGameOver();


  String movePlayer(Direction direction);

  boolean getWrapping();

  String shootArrow(int distance, Direction direction);

  String pickUpItem(int option);

  String quitGame();
}
