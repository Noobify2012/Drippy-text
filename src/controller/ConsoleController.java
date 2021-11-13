package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Dungeon;

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
  public void playGame(Dungeon d) {
    String next = "";
    String next2 = "";
    String next3 = "";
    String next4 = "";
    String next5 = "";
    String next6 = "";

    if (d == null) {
      throw new IllegalStateException("the dungeon model cannot be null");
    }
    boolean getBuildArgs = false;
    while (!getBuildArgs) {
      boolean quitFlag = false;
      next = scan.next();

      //while game is not over keep looping
      //check if game is over, conditions one player wins, stalemate or player quits, is game over
//      while (!m.isGameOver() && !quitFlag) {

//        getGameState(m);

//        announceMove(m.getTurn());

//        boolean validInput = false;
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
          getBuildArgs = true;
          break;
        } else {
          firstBool = validBool(next);
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
        }
      }

      if (firstBool && firstInt && secondInt && thirdInt && forthInt && fifthInt && !quitFlag) {
        //build dungeon and try to catch errors
        //validInput = processMove(next, next2, m);
      }

    }

    // if int look for second int
    // if char check for q,
    // if q append game over message, and return game state with new line character appended
    // if char not q or token not int then throw illegal arg error

    //if valid int pass to model, check valid integer model method

    //prepare for model throwing error if invalid input

    //check model for turn

    scan.close();
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



}
