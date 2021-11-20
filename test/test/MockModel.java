package test;

import java.util.List;

import model.Cave;
import model.Direction;
import model.Edge;
import model.Dungeon;

/**
 * This is the mock model for mocking the controller.
 */
public class MockModel implements Dungeon {


  private StringBuilder log;

  /** The constructor for the mockmodel which uses the log and uCode or unique code for testing the
   *  controller.
   *
   * @param log For recording which methods were called to test the controller.
   * @param uCode the unique code returned at the end of the method to compare with the out at the
   *              end.
   */
  public MockModel(StringBuilder log, int uCode) {
    this.log = log;
  }

  /**
   * A helper to get the number of rows in the game board.
   *
   * @return the number of rows in the game board as an integer.
   */
  @Override
  public int getGameBoardRows() {
    return 0;
  }

  /**
   * A helper to get the number of columns in the game board.
   *
   * @return the number of columns in the game board as an integer.
   */
  @Override
  public int getGameBoardCols() {
    return 0;
  }

  /**
   * A helper to set up the dungeon and run the player through their predesignated path.
   */
  @Override
  public String getDungeon() {
    log.append("Got the dungeon\n");
    return "123";
  }

  /**
   * A getter method that returns the list of final edges after Kruskal's has been run.
   *
   * @return an arraylist of the edges which were selected to act as the paths in the dungeon.
   */
  @Override
  public List<Edge> getFinalEdgeList() {
    return null;
  }

  /**
   * A getter that returns a copy of the game board data.
   *
   * @return a deep copy of the game board.
   */
  @Override
  public Cave[][] getGameBoard() {
    return new Cave[0][];
  }

  int iter = 0;

  @Override
  public boolean isGameOver() {
    boolean retBool = false;
    iter++;
    if (iter > 7) {
      retBool = true;
    }
    return retBool;
  }

  @Override
  public String movePlayer(Direction direction) {
    log.append("Input: move " + direction + "\n");
    return "movePlayer";
  }

  @Override
  public boolean getWrapping() {
    return false;
  }

  @Override
  public String shootArrow(int distance, Direction direction) {
    log.append("Input: shoot " + distance + " " + direction + "\n");
    return "shootArrow";
  }

  @Override
  public String pickUpItem(int option) {
    log.append("Input: pickup " + option + "\n");
    return "pickUpItem";
  }

  @Override
  public String quitGame() {
    return "quitGame";
  }


}
