package driver;

import controller.ConsoleController;
import java.io.InputStreamReader;

/**
 * Driver that acts as the controller for the Dungeon project. This just needs to be run.
 */
public class Driver {

  /**This is the main for the dungeon model.
   *
   * @param args this takes in string arguments.
   */
  public static void main(String[] args) {
    Readable inputs = new InputStreamReader(System.in);
    Appendable output = System.out;
    new ConsoleController(inputs, output).buildDungeon();
  }
}
