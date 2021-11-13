package driver;

import java.io.InputStreamReader;
import java.util.Scanner;

import model.Direction;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

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
    boolean startCond = false;
    boolean wraps = false;
    int rows = 0;
    int columns = 0;
    int interconnect = 0;
    int treasPer = 0;
    int diff = 0;
    while (!startCond) {

      String welcomeString = "Welcome to the Dungeon. "
              + "\nPlease enter true or false if you would like the dungeon to wrap."
              + " \nthe number of rows you would like as an integer. \nThe "
              + "number of columns as an integer.\nThe level of interconnectedness you would like"
              + " as an integer, \nthe percentage of caves that you would like to have"
              + " treasure between 0 and 100, and \nthe difficulty level you would like(IE how many"
              + " Otyugh you would like. \nHere is an example: true 10 10 0 10 2";

      Driver.printHelper(welcomeString);

      Scanner in = new Scanner(System.in);
      String input = in.nextLine();


      String inputChunks[] = new String[6];
      inputChunks = input.split(" ");

      boolean wrapsBool = false;
      if (inputChunks[0].equalsIgnoreCase("false")
              || inputChunks[0].equalsIgnoreCase("true")) {
        if (inputChunks[0].equalsIgnoreCase("false")) {
          wraps = false;
        } else {
          wraps = true;
        }
        wrapsBool = true;
      }

      boolean rowBool = false;
      if (Integer.parseInt(inputChunks[1]) > 0) {
        rowBool = true;
        rows = Integer.parseInt(inputChunks[1]);
        String rowPrint = "Value of Rows: " + rows;
      }

      boolean colBool = false;
      if (Integer.parseInt(inputChunks[2]) > 0) {
        colBool = true;
        columns = Integer.parseInt(inputChunks[2]);
        String colPrint = "Value of Rows: " + columns;
      }

      boolean intBool = false;
      if (Integer.parseInt(inputChunks[3]) >= 0) {
        intBool = true;
        interconnect = Integer.parseInt(inputChunks[3]);
        String intPrint = "Value of Rows: " + interconnect;
      }

      boolean treasBool = false;
      if (Integer.parseInt(inputChunks[4]) >= 0 && Integer.parseInt(inputChunks[4]) <= 100) {
        treasBool = true;
        treasPer = Integer.parseInt(inputChunks[4]);
        String trePrint = "Value of Rows: " + treasPer;
      }

      boolean diffBool = false;
      if (Integer.parseInt(inputChunks[5]) >= 1 && Integer.parseInt(inputChunks[5]) <= rows *
              columns) {
        diffBool = true;
        diff = Integer.parseInt(inputChunks[5]);
        String trePrint = "Level of Difficulty: " + diff;
      }

      if (wrapsBool && rowBool && colBool && intBool && treasBool && diffBool) {
        startCond = true;
      }

    }

    Player player = new PlayerImpl();
    DungeonImpl test = new DungeonImpl(wraps, rows, columns, interconnect, treasPer, player, diff);
    test.getDungeon();

    while (!test.isGameOver()) {
      String testMove = "Please enter which direction you would like to move.";
      Driver.printHelper(testMove);
      Scanner in2 = new Scanner(System.in);
      String input2 = in2.nextLine();

      String inputChunks2[] = new String[1];
      inputChunks2 = input2.split(" ");
      Driver.printHelper(inputChunks2[0]);
      Direction playerDirection = null;
      if (inputChunks2[0].equalsIgnoreCase("North")) {
        playerDirection = Direction.NORTH;
      } else if (inputChunks2[0].equalsIgnoreCase("South")) {
        playerDirection = Direction.SOUTH;
      } else if (inputChunks2[0].equalsIgnoreCase("East")) {
        playerDirection = Direction.EAST;
      } else if (inputChunks2[0].equalsIgnoreCase("West")) {
        playerDirection = Direction.WEST;
      }
      printHelper("the direction the player is going to move: " + playerDirection);
      test.movePlayer(playerDirection);
      String hope = "Hopefully the player moved";
      printHelper(hope);
      player.getPlayerStatus();
    }

  }

  /**This is a helper which takes in a string and prints it.
   *
   * @param printString The string being passed that is supposed to be printed.
   */
  public static void printHelper(String printString) {
    System.out.println(printString);
  }
}
