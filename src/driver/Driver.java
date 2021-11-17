package driver;

import controller.ConsoleController;
import controller.Controller;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Driver that acts as the controller for the Dungeon project. This just needs to be run.
 */
public class Driver {

  /**This is the main for the dungeon model.
   *
   * @param args this takes in string arguments.
   */
  public static void main(String[] args) {

    if (args.length < 6 || args.length > 6) {
      throw new IllegalArgumentException("must have only 6 arguments in the format");
    } else if (!validBool(args[0])) {
      throw new IllegalArgumentException("the first argument must be a boolean");
    } else if (!validateInput(args[1])) {
      throw new IllegalArgumentException("the second argument must be a positive integer");
    } else if (!(validateInput(args[2]))) {
    throw new IllegalArgumentException("the third argument must be a positive integer");
    } else if (!(validateInput(args[3]))) {
      throw new IllegalArgumentException("the fourth argument must be a positive integer");
    } else if (!(validateInput(args[4]))) {
      throw new IllegalArgumentException("the fifth argument must be a positive integer");
    } else if (!(validateInput(args[5]))) {
      throw new IllegalArgumentException("the sixth argument must be a positive integer");
    }

    boolean wraps = Boolean.parseBoolean(args[0]);
    int row = Integer.parseInt(args[1]);
    int col = Integer.parseInt(args[2]);
    int inter = Integer.parseInt(args[3]);
    int treas = Integer.parseInt(args[4]);
    int dif = Integer.parseInt(args[5]);

    Scanner in = new Scanner((System.in));

    Player player = new PlayerImpl();
    try {
      Dungeon test = new DungeonImpl(wraps, row, col, inter, treas, player,
              dif, 1);
      String dungeonBuilder = test.getDungeon();
      System.out.println(dungeonBuilder + "\n");
      Readable inputs = new InputStreamReader(System.in);
      Appendable output = System.out;
      new ConsoleController(inputs, output).playGame(test);
//      playGame(test);
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage() + "\n");
    } catch (IllegalStateException ise) {
      System.out.println(ise.getMessage() + "\n");
    }
  }

  private static boolean validBool(String next) {
    return next.equalsIgnoreCase("false") || next.equalsIgnoreCase("true");
  }

  private static boolean validateInput(String next) {
    try {
      Integer.parseInt(next);
      return true;
    } catch (NumberFormatException nfe) {
        System.out.println("Not a valid number: " + next + "\n");
        return false;
    }
  }

  private void quitGame(Dungeon d) {
      String quitString = d.quitGame();
      System.out.println(quitString + "\n");

  }
}


