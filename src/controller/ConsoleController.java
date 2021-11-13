package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

public class ConsoleController implements Controller {

  private final Appendable out;
  private final Scanner scan;

  public ConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void buildDungeon() {

    String welcomeString = "Welcome to the Dungeon. "
            + "\nPlease enter true or false if you would like the dungeon to wrap."
            + " \nthe number of rows you would like as an integer. \nThe "
            + "number of columns as an integer.\nThe level of interconnectedness you would like"
            + " as an integer, \nthe percentage of caves that you would like to have"
            + " treasure between 0 and 100, and \nthe difficulty level you would like(IE how many"
            + " Otyugh you would like. \nHere is an example: true 10 10 0 10 2";

    try {
      //String element = scan.next();
      out.append(welcomeString + "\n");
      //+ element);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

    String next = "";
    String next2 = "";
    String next3 = "";
    String next4 = "";
    String next5 = "";
    String next6 = "";


    boolean getBuildArgs = false;
    while (!getBuildArgs) {
      boolean quitFlag = false;
//      next = scan.next();

      //while game is not over keep looping
      //check if game is over, conditions one player wins, stalemate or player quits, is game over
//      while (!m.isGameOver() && !quitFlag) {

//        getGameState(m);

//        announceMove(m.getTurn());

        boolean valid = false;
//        while (!validInput) {
      boolean firstBool = false;

      while (!firstBool && !quitFlag) {
        //check for next integer or character in the next token
        next = scan.next();
        // is first thing either an int or char, request new input
        if (next.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          //TODO Build quit game method
          quitGame();
          quitFlag = true;
          firstBool = true;
          //getBuildArgs = true;
          break;
        } else {
          firstBool = validBool(next);
          if (firstBool == true) {
            try {
              //String element = scan.next();
              out.append("got a valid wrap\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          } else {
            try {
              //String element = scan.next();
              out.append("invalid wrap value please enter true or false\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }

      boolean firstInt = false;
      while (!firstInt && !quitFlag) {
        //check for next integer or character in the next token
        next2 = scan.next();
        // is first thing either an int or char, request new input
        if (next2.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame();
          quitFlag = true;
          firstInt = true;
          //validInput = true;
          break;
        } else {
          firstInt = validateInput(next2);
          if (firstInt == true) {
            try {
              //String element = scan.next();
              out.append("got a valid row\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }

      boolean secondInt = false;
      while (!secondInt && !quitFlag) {
        //check for next integer or character in the next token
        next3 = scan.next();
        // is first thing either an int or char, request new input
        if (next3.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame();
          quitFlag = true;
          secondInt = true;
          //validInput = true;
          break;
        } else {
          secondInt = validateInput(next3);
          if (secondInt == true) {
            try {
              //String element = scan.next();
              out.append("got a valid column\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }

      boolean thirdInt = false;
      while (!thirdInt && !quitFlag) {
        //check for next integer or character in the next token
        next4 = scan.next();
        // is first thing either an int or char, request new input
        if (next4.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame();
          quitFlag = true;
          thirdInt = true;
          //validInput = true;
          break;
        } else {
          thirdInt = validateInput(next4);
          if (thirdInt == true) {
            try {
              //String element = scan.next();
              out.append("got a valid interconnect\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }

      boolean forthInt = false;
      while (!forthInt && !quitFlag) {
        //check for next integer or character in the next token
        next5 = scan.next();
        // is first thing either an int or char, request new input
        if (next5.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame();
          quitFlag = true;
          forthInt = true;
          //validInput = true;
          break;
        } else {
          forthInt = validateInput(next5);
          if (forthInt == true) {
            try {
              //String element = scan.next();
              out.append("got a valid treas\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }

      boolean fifthInt = false;
      while (!fifthInt && !quitFlag) {
        //check for next integer or character in the next token
        next6 = scan.next();
        // is first thing either an int or char, request new input
        if (next6.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame();
          quitFlag = true;
          fifthInt = true;
          //validInput = true;
          break;
        } else {
          fifthInt = validateInput(next6);
          if (fifthInt == true) {
            try {
              //String element = scan.next();
              out.append("got a valid difficulty\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
        }
      }
      boolean wraps = Boolean.parseBoolean(next);
      int rows = Integer.parseInt(next2);
      int columns = Integer.parseInt(next3);
      int interconnect = Integer.parseInt(next4);
      int treasPer = Integer.parseInt(next5);
      int diff = Integer.parseInt(next6);

      if (firstBool && firstInt && secondInt && thirdInt && forthInt && fifthInt && !quitFlag) {
        //build dungeon and try to catch errors
        Player player = new PlayerImpl();
        try {
          Dungeon test = new DungeonImpl(wraps, rows, columns, interconnect, treasPer, player, diff);
          playGame(test);
//          test.getDungeon();
        } catch (IllegalArgumentException iae) {
          try {
            //String element = scan.next();
            out.append(iae.getMessage() + "\n");
            //+ element);
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        } catch (IllegalStateException ise) {
          try {
            //String element = scan.next();
            out.append(ise.getMessage() + "\n");
            firstInt = false;
            secondInt = false;
            //+ element);
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        }
        //getBuildArgs = processMove(next, next2, m);
      }
      if (firstBool && firstInt && secondInt && thirdInt && forthInt && fifthInt && !quitFlag) {
        getBuildArgs = true;
      }
    }

    // if int look for second int
    // if char check for q,
    // if q append game over message, and return game state with new line character appended
    // if char not q or token not int then throw illegal arg error

    //if valid int pass to model, check valid integer model method

    //prepare for model throwing error if invalid input

    //check model for turn

    //scan.close();
//    if (m.getWinner() != null || m.isGameOver()) {
//      declareWinner(m.getWinner(), m);
//    }


    //build parameter loops here


    //while(!d.isGameOver())

    {
      //implement how to get the game
    }
  }

  private boolean validBool(String next) {
    return next.equalsIgnoreCase("false") || next.equalsIgnoreCase("true");
  }

  private boolean validateInput(String next){
    try {
      //String element = scan.next();
      Integer.parseInt(next);
      return true;
      //+ element);
    } catch (NumberFormatException nfe) {
      try {
        //String element = scan.next();
        out.append("Not a valid number: " + next + "\n");
        return false;
        //+ element);
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
    }
  }


  private void quitGame() {
    try {
      //String element = scan.next();
      out.append("Game quit! Ending game state:" + "\n");
      //+ element);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }


  @Override
  public void playGame(Dungeon d) {
    if (d == null) {
      throw new IllegalStateException("the dungeon model cannot be null");
    }
    while (!d.isGameOver()) {
      try {
        //String element = scan.next();
        out.append("Would you like to move or shoot?" + "\n");
        //+ element);
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
//      String testMove = "Please enter which direction you would like to move.";
//      Driver.printHelper(testMove);
//      Scanner in2 = new Scanner(System.in);
//      String input2 = in2.nextLine();
//
//      String inputChunks2[] = new String[1];
//      inputChunks2 = input2.split(" ");
//      Driver.printHelper(inputChunks2[0]);
//      Direction playerDirection = null;
//      if (inputChunks2[0].equalsIgnoreCase("North")) {
//        playerDirection = Direction.NORTH;
//      } else if (inputChunks2[0].equalsIgnoreCase("South")) {
//        playerDirection = Direction.SOUTH;
//      } else if (inputChunks2[0].equalsIgnoreCase("East")) {
//        playerDirection = Direction.EAST;
//      } else if (inputChunks2[0].equalsIgnoreCase("West")) {
//        playerDirection = Direction.WEST;
//      }
//      printHelper("the direction the player is going to move: " + playerDirection);
//      test.movePlayer(playerDirection);
//      String hope = "Hopefully the player moved";
//      printHelper(hope);
//      player.getPlayerStatus();
    }

  }



}
