package controller;

import java.io.IOException;
import java.util.Scanner;
import model.Direction;
import model.Dungeon;
import model.DungeonImpl;
import model.IDungeon;
import model.Player;
import model.PlayerImpl;

/**
 * The controller class for the dungeon adventure game. this class parses and processes all
 * information passed in. This class can take in and validate all command line input for building
 * the dungeon but based on project requirements, that is being handled in the driver.
 */
public class ConsoleController implements Controller {

  private final Appendable out;
  private final Scanner scan;

  /**The constructor for the controller which takes in any data being fed in and appends to any
   * data stream out.
   *
   * @param in the data stream coming into the controller. The current implementation passes in
   *           system.in.
   * @param out the data stream coming out of the controller. The current implementation uses
   *            system.out.
   */
  public ConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  /**
   * Collects the information for building the dungeon and attempts to contstruct it based off of
   * user input. In the current implementation, it does not get used because of needing the ability
   * to take in command line arguments but does have the capability to do so as well as create a
   * player.
   */
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
      boolean valid = false;
      boolean firstBool = false;
      Dungeon q = null;

      while (!firstBool && !quitFlag) {
        //check for next integer or character in the next token
        next = scan.next();
        // is first thing either an int or char, request new input
        if (next.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame(q);
          quitFlag = true;
          firstBool = true;
          //getBuildArgs = true;
          break;
        } else {
          firstBool = validBool(next);
          if (firstBool) {
            try {
              out.append("got a valid wrap\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          } else {
            try {
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
          quitGame(q);
          quitFlag = true;
          firstInt = true;
          break;
        } else {
          firstInt = validateInput(next2);
          if (firstInt) {
            try {
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
          quitGame(q);
          quitFlag = true;
          secondInt = true;
          //validInput = true;
          break;
        } else {
          secondInt = validateInput(next3);
          if (secondInt) {
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
          quitGame(q);
          quitFlag = true;
          thirdInt = true;
          //validInput = true;
          break;
        } else {
          thirdInt = validateInput(next4);
          if (thirdInt) {
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
          quitGame(q);
          quitFlag = true;
          forthInt = true;
          //validInput = true;
          break;
        } else {
          forthInt = validateInput(next5);
          if (forthInt) {
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
          quitGame(q);
          quitFlag = true;
          fifthInt = true;
          //validInput = true;
          break;
        } else {
          fifthInt = validateInput(next6);
          if (fifthInt) {
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
          IDungeon test = new DungeonImpl(wraps, rows, columns, interconnect, treasPer, player,
                  diff, 1);
          String dungeonBuilder = test.getDungeon();
          try {
            out.append(dungeonBuilder + "\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
          playGame((IDungeon) test);
        } catch (IllegalArgumentException iae) {
          try {
            out.append(iae.getMessage() + "\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        } catch (IllegalStateException ise) {
          try {
            out.append(ise.getMessage() + "\n");
            firstInt = false;
            secondInt = false;
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        }
      }
      if (firstBool && firstInt && secondInt && thirdInt && forthInt && fifthInt && !quitFlag) {
        getBuildArgs = true;
      }
    }
  }

  private boolean validBool(String next) {
    return next.equalsIgnoreCase("false") || next.equalsIgnoreCase("true");
  }

  private boolean validateInput(String next) {
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


  private void quitGame(Dungeon d) {
    try {
      String quitString = d.quitGame();
      out.append(quitString + "\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  /**Takes in a dungeon and allows the user to act as the player moving through the dungeon, firing
   * arrows, and picking up treasure and arrows.
   *
   * @param d the dungeon that is required for the user to navigate and play.
   */
  @Override
  public void playGame(IDungeon d) {
    boolean quitFlag = false;
    if (d == null) {
      throw new IllegalStateException("the dungeon model cannot be null");
    }
    while (!d.isGameOver()) {
      try {
        //String element = scan.next();
        out.append("Would you like to move, shoot, or pickup?" + "\n");
        //+ element);
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
      String next = "";
      String next2 = "";
      boolean gameAction = false;
      while (!gameAction && !quitFlag) {
        //check for next integer or character in the next token
        next = scan.next();
        // is first thing either an int or char, request new input
        if (next.equalsIgnoreCase("q")) {
          //get game state, append, and quit method.
          quitGame((Dungeon) d);
          quitFlag = true;
          gameAction = true;
          //validInput = true;
          break;
        } else {
          gameAction = validateAction(next);
          if (gameAction) {
            Direction playerDirection = null;
            if (next.equalsIgnoreCase("move") || next.equalsIgnoreCase("m")
                    || next.equalsIgnoreCase("s")
                    || next.equalsIgnoreCase("shoot")) {
              try {
                //String element = scan.next();
                out.append("which direction?\n");
              } catch (IOException ioe) {
                throw new IllegalStateException("Append failed", ioe);
              }
            } else if (next.equalsIgnoreCase("pickup")
                    || next.equalsIgnoreCase("p")) {
              try {
                //String element = scan.next();
                out.append("treasure, arrows, or both?\n");
              } catch (IOException ioe) {
                throw new IllegalStateException("Append failed", ioe);
              }
            }
            next2 = scan.next();

            if (next.equalsIgnoreCase("move") || next.equalsIgnoreCase("m")) {
              //get move

              if (next2.equalsIgnoreCase("North")
                      || next2.equalsIgnoreCase("n")) {
                playerDirection = Direction.NORTH;
              } else if (next2.equalsIgnoreCase("South")
                      || next2.equalsIgnoreCase("s")) {
                playerDirection = Direction.SOUTH;
              } else if (next2.equalsIgnoreCase("East")
                      || next2.equalsIgnoreCase("e")) {
                playerDirection = Direction.EAST;
              } else if (next2.equalsIgnoreCase("West")
                      || next2.equalsIgnoreCase("w")) {
                playerDirection = Direction.WEST;
              }
              if (playerDirection == Direction.NORTH || playerDirection == Direction.SOUTH
                      || playerDirection == Direction.EAST || playerDirection == Direction.WEST) {
                try {
                  //String element = scan.next();
                  String moveString = d.movePlayer(playerDirection);
                  try {
                    out.append(moveString + "\n");
                  } catch (IOException ioe) {
                    throw new IllegalStateException("Append failed", ioe);
                  }
                } catch (IllegalArgumentException iae) {
                  try {
                    out.append(iae.getMessage() + "\n");
                  } catch (IOException ioe) {
                    throw new IllegalStateException("Append failed", ioe);
                  }
                }
              }

              //player.getPlayerStatus();
            } else if (next.equalsIgnoreCase("s")
                    || next.equalsIgnoreCase("shoot")) {
              // get distance and direction
              Direction arrowDirection = null;

              if (next2.equalsIgnoreCase("North")
                      || next2.equalsIgnoreCase("n")) {
                arrowDirection = Direction.NORTH;
              } else if (next2.equalsIgnoreCase("South")
                      || next2.equalsIgnoreCase("s")) {
                arrowDirection = Direction.SOUTH;
              } else if (next2.equalsIgnoreCase("East")
                      || next2.equalsIgnoreCase("e")) {
                arrowDirection = Direction.EAST;
              } else if (next2.equalsIgnoreCase("West")
                      || next2.equalsIgnoreCase("w")) {
                arrowDirection = Direction.WEST;
              }

              int distance = 0;
              try {
                //String element = scan.next();
                out.append("how far?\n");
              } catch (IOException ioe) {
                throw new IllegalStateException("Append failed", ioe);
              }
              String next3 = scan.next();
              //check for integer
              try {
                //String element = scan.next();
                distance = Integer.parseInt(next3);
              } catch (NumberFormatException nfe) {
                try {
                  out.append("Not a valid distance, please enter a distance as an integer\n");
                } catch (IOException ioe) {
                  throw new IllegalStateException("Append failed", ioe);
                }
              }
              if ((arrowDirection == Direction.NORTH || arrowDirection == Direction.SOUTH
                      || arrowDirection == Direction.EAST || arrowDirection == Direction.WEST)
                      && distance >= 0) {
                try {
                  String shotString = d.shootArrow(distance, arrowDirection);
                  try {
                    out.append(shotString + "\n");
                  } catch (IOException ioe) {
                    throw new IllegalStateException("Append failed", ioe);
                  }
                } catch (IllegalArgumentException iae) {
                  try {
                    out.append(iae.getMessage() + "\n");
                  } catch (IOException ioe) {
                    throw new IllegalStateException("Append failed", ioe);
                  }
                }
              }
            } else if (next.equalsIgnoreCase("pickup")
                    || next.equalsIgnoreCase("p")) {
              if (next2.equalsIgnoreCase("t")
                      || next2.equalsIgnoreCase("treasure")) {
                try {
                  out.append(d.pickUpItem(0) + "\n");
                } catch (IOException ioe) {
                  throw new IllegalStateException("Append failed", ioe);
                }
              } else if (next2.equalsIgnoreCase("a")
                      || next2.equalsIgnoreCase("arrows")) {
                try {
                  out.append(d.pickUpItem(1) + "\n");
                } catch (IOException ioe) {
                  throw new IllegalStateException("Append failed", ioe);
                }
              } else if (next2.equalsIgnoreCase("b")
                      || next2.equalsIgnoreCase("both")) {
                try {
                  out.append(d.pickUpItem(2) + "\n");
                } catch (IOException ioe) {
                  throw new IllegalStateException("Append failed", ioe);
                }
              }
            } else {
              throw new IllegalStateException("didn't get a valid command");
            }
          }
        }

      }

    }
  }

  private boolean validateAction(String next) {
    return next.equalsIgnoreCase("m") || next.equalsIgnoreCase("s")
            || next.equalsIgnoreCase("q") || next.equalsIgnoreCase("move")
            || next.equalsIgnoreCase("shoot")
            || next.equalsIgnoreCase("quit") || next.equalsIgnoreCase("p")
            || next.equalsIgnoreCase("pickup");
  }



}
