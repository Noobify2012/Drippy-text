package model;

import java.util.ArrayList;
import java.util.List;
import random.RandomNumberGenerator;

/**
 * The implementation of the Dungeon interface.
 */
public class DungeonImpl implements IDungeon {
  private boolean wraps;
  private int rows;
  private int columns;
  private int interconnect;
  private int treasure;
  private Cave[][] gameboard;
  private List<Edge> potEdgeList;
  private List<Edge> leftOverEdge;
  private List<Edge> finalEdgeList;
  private int startPoint;
  private int endPoint;
  private Player player;
  private int difficulty;
  private RandomNumberGenerator randomNumberGenerator;
  private int genSeed;
  private boolean quitFlag;

  /**This creates a dungeon that requires the specification of whether the dungeon should wrap or
   * not. How many rows and columns there should be specified as integers. The degree of
   * interconnectivity(default is 0) or how many paths between nodes should there be. An
   * interconnectivity of 0 means that there is exactly 1 path between all nodes. Each degree above
   * that is an additional edge/connection added to the map. Finally, what percentage of caves
   * should have treasure in it. The default is 20%. Caves are defined as having 1, 3, or 4
   * entrances. Tunnels only have 2 entrances and do not have treasure.
   Params:
   wraps – A boolean which determines if a dungeon wraps its edges around to the other side.
   rows – The number of rows in the dungeon as an integer.
   columns – The number of columns in the dungeon as an integer.
   interconnect – The level of interconnectivity expressed as an integer. Default is 0.
   treasure – The percentage of caves with treasure expressed as an integer. Default is 20.
   Returns:
   The dungeon built to specification represented as a 2 dimensional array.**/
  public DungeonImpl(boolean wraps, int rows, int columns, int interconnect, int treasure,
                     Player player, int difficulty, int genSeed) {

    List<Edge> potEdgeList = new ArrayList<>();
    List<Edge> leftOverEdge = new ArrayList<>();
    List<Edge> finalEdgeList = new ArrayList<>();
    List<Integer> shortestPath = new ArrayList<>();
    Cave[][] gameboard = new Cave[rows][columns];

    this.wraps = wraps;
    this.rows = rows;
    this.columns = columns;
    this.interconnect = interconnect;
    this.treasure = treasure;
    this.gameboard = gameboard;
    this.potEdgeList = potEdgeList;
    this.leftOverEdge = leftOverEdge;
    this.finalEdgeList = finalEdgeList;
    this.startPoint = 0;
    this.endPoint = 0;
    this.player = player;
    this.difficulty = difficulty;
    this.randomNumberGenerator = new RandomNumberGenerator(genSeed);
    this.quitFlag = false;



    if (difficulty < 1) {
      throw new IllegalArgumentException("There must be at least one monster in the dungeon");
    }

    if (rows < 1 || columns < 1) {
      throw new IllegalArgumentException("Rows or Columns cannot be less than 1.");
    } else if (rows == 1 && columns < 6 || rows < 6 && columns == 1) {
      throw new IllegalArgumentException("You must have at least 6 rows or columns if the other "
              + "is 1.");
    } else if (rows == 2 && columns < 3 || rows < 3 && columns == 2) {
      throw new IllegalArgumentException("You must have at least 6 nodes in the graph.");
    }

    if (treasure < 0) {
      throw new IllegalArgumentException("You cannot have negative treasure");
    } else if (treasure > 100) {
      throw new IllegalArgumentException("You cannot have more than 100% treasure");
    }

    if (interconnect < 0) {
      throw new IllegalArgumentException("The interconnectivity cannot be less than 0");
    }

    if (interconnect > 0 && !wraps) {
      //forumla derived by Madhira Datta
      int maxEdges = 2 * rows * columns - rows - columns;
      if (interconnect > maxEdges -  (rows  * columns - 1)) {
        throw new IllegalArgumentException("Interconnectivity too high, beyond number of edges in"
                + " graph.");
      }
    } else if (interconnect > 0 && wraps) {
      //formula derived by Madhira Datta
      int maxEdges = 2 * rows * columns;
      if (interconnect > maxEdges) {
        throw new IllegalArgumentException("Interconnectivity too high, beyond number of edges in"
                + " graph.");
      }
    }

    if (difficulty <= 0) {
      throw new IllegalArgumentException("Must have at least 1 monster");
    }


    //construct caves
    int index = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        List<Integer> neighborList = new ArrayList<>();
        List<Treasure> treasureList = new ArrayList<>();
        List<CrookedArrow> arrowList = new ArrayList<>();
        List<Monster> monsterList = new ArrayList<>();
        Cave cave = new Cave(r, c, neighborList, treasureList, index, index, arrowList,
                monsterList);
        gameboard[r][c] = cave;
        index++;
      }
    }
    // build edges
    if (!wraps) {
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < columns; c++) {
          //case for nodes that aren't on far edge
          if (c < columns - 1 && r < rows - 1) {
            Edge edge = new Edge(gameboard[r][c], gameboard[r + 1][c]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(gameboard[r][c], gameboard[r][c + 1]);
            potEdgeList.add(edge2);
            //bottom right hand corner, opposite origin
          } else if (c == columns - 1 && r == rows - 1) {
            //do nothing
            //max column, co
          } else if (c == columns - 1 && r <= rows - 1) {
            Edge edge = new Edge(gameboard[r][c], gameboard[r + 1][c]);
            potEdgeList.add(edge);
          } else {
            Edge edge = new Edge(gameboard[r][c], gameboard[r][c + 1]);
            potEdgeList.add(edge);
          }
        }
      }
    } else {
      //figure out wrapping logic for finding edges
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < columns; c++) {
          //case: not an edge node, add edge right, add edge down
          if (c < columns - 1 && r < rows - 1) {
            Edge edge = new Edge(gameboard[r][c], gameboard[r + 1][c]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(gameboard[r][c], gameboard[r][c + 1]);
            potEdgeList.add(edge2);
            //case: bottom right edge, wrap right, wrap down
          } else if (c == columns - 1 && r == rows - 1) {
            Edge edge = new Edge(gameboard[r][c], gameboard[0][c]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(gameboard[r][c], gameboard[r][0]);
            potEdgeList.add(edge2);

            //case: right edge, not bottom
          } else if (c == columns - 1 && r <= rows - 1) {
            Edge edge = new Edge(gameboard[r][c], gameboard[r + 1][c]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(gameboard[r][c], gameboard[r][0]);
            potEdgeList.add(edge2);
          } else {
            Edge edge = new Edge(gameboard[r][c], gameboard[r][c + 1]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(gameboard[r][c], gameboard[0][c]);
            potEdgeList.add(edge2);
          }
        }
      }
    }
  }


  /**
   * This builds all aspects of the dungeon including finding the start and end point. Adding
   * treasure and placing the player in the dungeon and running them through the path.
   */
  public String getDungeon() {
    //runs Kruscals, adds interconnectivity
    String kruskalsString = runKruscals();
    //generates a start point by index
    String startString = getStartPoint(getCavesByIndex());
    //finds a viable end point
    String endPointString = findEndPoint(this.startPoint);
    //find caves and adds Treasure
    findCaves(getCavesByIndex());

    addMonstersToDungeon(getCavesByIndex());

    addArrows();

    String setUpString = setUpPlayer();

    String initPlayerStat = player.getPlayerStatus(checkSmell(),
            findCaveByIndex(player.getPlayerLocation()));

    String dungeonString = "\n" + initPlayerStat;
    return dungeonString;

  }

  /**
   * This puts the player in their starting cave for navigating through dungeon for moving from the
   * start point to the end point.
   */
  private String setUpPlayer() {
    //place the player in the dungeon at the cave index
    player.enterDungeon(this.startPoint, findCaveByIndex(this.startPoint).getTreasureList(),
            getPossibleDirection(this.startPoint));
    String enterString = "";
    if (getPossibleDirection(this.startPoint).size() > 1) {
      enterString = "\nThe Player enters the dungeon at Cave " + this.startPoint + ". They can"
              + " go " + getPossibleDirection(this.startPoint).toString() + ". They currently have"
              + " no treasure.";
    } else {
      String dirString = "";
      for (int s = 0; s < getPossibleDirection(this.startPoint).size(); s++) {
        if (s != getPossibleDirection(this.startPoint).size() - 1) {
          dirString = dirString + getPossibleDirection(this.startPoint).get(s).toString() + " or ";
        } else {
          dirString = dirString + getPossibleDirection(this.startPoint).get(s).toString();
        }
      }
      enterString = "\nThe Player enters the dungeon at Cave " + this.startPoint + ". They can"
              + " go " + dirString + ". They currently have no treasure.";
    }
    return enterString;
  }

  /**A helper to get the directions from the next cave to give the player all the options for the
   * next cave for which direction to travel.
   *
   * @param index takes in the index of the next cave
   * @return a list of the possible directions for the player to travel.
   */
  private List<Direction> getPossibleDirection(int index) {
    List<Direction> tempArray = new ArrayList<>();
    for (int i = 0; i < finalEdgeList.size(); i++) {
      if (finalEdgeList.get(i).getLeftIndex() == index) {
        if (!tempArray.contains(finalEdgeList.get(i).getDirectionToCave2())) {
          tempArray.add(finalEdgeList.get(i).getDirectionToCave2());
        }
      } else if (finalEdgeList.get(i).getRightIndex() == index) {
        if (!tempArray.contains(finalEdgeList.get(i).getDirectionToCave1())) {
          tempArray.add(finalEdgeList.get(i).getDirectionToCave1());
        }
      }
    }
    return tempArray;
  }

  /**Gets the players start point by first finding all possible caves(1,3,4 entrances) and randomly
   * selecting one from a list of eligible caves.
   *
   * @param caves takes an array list of caves to select from
   * @return the index of the starting cave as an integer.
   */
  private String getStartPoint(List<Integer> caves) {
    int startIndex = randomNumberGenerator.getRandomNumber(0, caves.size());
    this.startPoint = startIndex;
    return "The Player StartPoint is " + this.startPoint + ".";
  }

  /**This finds the end point by searching through all nodes within 5 moves of the start node by
   * finding the children of the previous nodes. It then performs a difference between the caves
   * found and the total caves. It then randomly selects an end point from the caves remaining.
   *
   * @param startIndex takes in the start index as an integer.
   * @return the end point index as an integer.
   */
  private String findEndPoint(int startIndex) {
    List<Integer> nonViable = new ArrayList<>();
    List<Integer> viable = new ArrayList<>();
    List<Integer> allCaves = getCavesByIndex();
    List<Integer> listToCheck = new ArrayList<>();
    int endPoint = 0;

    //add start index to list of things to check and those that can't be an end point
    nonViable.add(startIndex);
    listToCheck.add(startIndex);
    //check for incomplete graph
    if (findCaveByIndex(startIndex).getNeighbors().size() != 0) {
      //loop through the list of neighbors of the start index
      for (int i = 0; i < findCaveByIndex(startIndex).getNeighbors().size(); i++) {
        //check that we already haven't seen the current node
        if (!(nonViable.contains(findCaveByIndex(startIndex).getNeighbors().get(i)))) {
          //add current neighbor node to list of indexes to check and nonviable
          nonViable.add((int) findCaveByIndex(startIndex).getNeighbors().get(i));
          listToCheck.add((int) findCaveByIndex(startIndex).getNeighbors().get(i));
        }
      }
      //remove node we just checked from list of nodes to check
      listToCheck.remove(0);
      //loop 3 times
      for (int y = 0; y < 3; y++) {
        //list size at time of iteration, not to keep looping through more stuff
        int temp = listToCheck.size();
        for (int c = 0; c < temp; c++) {
          //grab next value in list to check
          int tempInt = listToCheck.get(c);
          List<Integer> tempList = findCaveByIndex(tempInt).getNeighbors();
          //checks to see if next element down has children
          if (tempList.size() > 1) {
            //if it does have children add them to the lists
            for (int a = 0; a < tempList.size(); a++) {
              if (!(nonViable.contains((int) findCaveByIndex(tempInt).getNeighbors().get(a)))) {
                nonViable.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
                listToCheck.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
              }
            }
          }
          //add current neighbor node to list of indexes to check and nonviable
        }
        for (int r = 0; r < temp; r++) {
          listToCheck.remove(0);
        }
      }
    } else {
      throw new IllegalStateException("Start Node has no neighbors.");
    }
    //compare list of non-viable vs total list
    if (nonViable.size() == (rows * columns)) {
      throw new IllegalStateException("There are no viable end points, try increasing the size of "
              + "your dungeon");
    } else {
      for (int t = 0; t < allCaves.size(); t++) {
        if (!nonViable.contains(allCaves.get(t))) {
          viable.add(allCaves.get(t));
        }
      }
      if (viable.size() != 1) {
        int endRand = randomNumberGenerator.getRandomNumber(0, viable.size());
        endPoint = viable.get(endRand);
      } else {
        endPoint = viable.get(0);
      }
    }
    String endPointString = "The end point is cave : " + endPoint;
    this.endPoint = endPoint;
    return endPointString;
  }

  /**This is used for setting up the caves and adding treasure.
   *
   * @param caves takes the complete list of nodes as an array list of their integer indexes.
   */
  private void findCaves(List<Integer> caves) {
    int treasureInt = 0;

    //calculate how many caves require treasure
    if (this.treasure != 0) {
      int treasCaveNum = (int) Math.ceil((caves.size() * treasure) / 100);
      if (treasCaveNum == 0) {
        treasCaveNum++;
      }
      TreasureImpl treasureFactory = new TreasureImpl();
      for (int t = 0; t < treasCaveNum; t++) {
        int treasureRand = randomNumberGenerator.getRandomNumber(1, 3);
        if (treasureRand == 0) {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(randomNumberGenerator.getRandomNumber(0, caves.size() - 1)))
                    .addTreasure(TreasureImpl
                            .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.RUBY));
          }
        } else if (treasureRand == 1) {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(randomNumberGenerator.getRandomNumber(0, caves.size() - 1)))
                    .addTreasure(TreasureImpl.TreasureFactory.getTreasureFromEnum(TreasureImpl
                            .TreasureType.DIAMOND));
          }
        } else {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(randomNumberGenerator.getRandomNumber(0, caves.size() - 1)))
                    .addTreasure(TreasureImpl.TreasureFactory
                            .getTreasureFromEnum(TreasureImpl.TreasureType.SAPPHIRE));
          }
        }
      }
    }
  }

  private void addMonstersToDungeon(List<Integer> caves) {

    // add monster to end cave
    Monster endPointMonster = new Otyugh(2);
    findCaveByIndex(endPoint).addMonster(endPointMonster);

    if (difficulty > 1 && difficulty < caves.size()) {
      int monsterCount = difficulty - 1;
      while (monsterCount > 0) {

        int rand = randomNumberGenerator.getRandomNumber(0, caves.size());
        if (findCaveByIndex(caves.get(rand)).getMonsterListSize() == 0) {
          Monster caveMonster = new Otyugh(2);
          findCaveByIndex(caves.get(rand)).addMonster(caveMonster);
          monsterCount--;
        }
      }
      //get new random cave from list

      //check if it has a monster

      //if it does get new random cave

      //if not add monster and change count.

    } else if (difficulty > caves.size()) {
      throw new IllegalArgumentException("Not enough caves for monsters reduce difficulty");
    }
  }

  /**This does a lookup specific caves and returns the cave of the associated index that is passed
   * to it.
   *
   * @param index the integer index of the cave being searched.
   * @return the cave of the index that was searched for.
   */
  private Cave findCaveByIndex(int index) {
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (gameboard[r][c].getIndex() == index) {
          return gameboard[r][c];
        }
      }
    }
    throw new IllegalArgumentException("couldn't find cave index of " + index);
  }

  /**This returns the potentential edge list for performing Kruskal's.
   *
   * @return the array list of edges.
   */
  private List<Edge> getPotEdgeList() {
    return this.potEdgeList;
  }

  /**This builds and returns all the cave and tunnel index values in the dungeon.
   *
   * @return an array list of the indexes of all caves and tunnels in the dungeon.
   */
  private List<Integer> getCavesByIndex() {
    List<Integer> caves = new ArrayList<>();
    //make list of caves, exclude tunnels
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (gameboard[r][c].getNeighbors().size() != 2) {
          caves.add(gameboard[r][c].getIndex());
        }
      }
    }
    return caves;
  }

  /**
   * This runs Kruscal's algorithm on the edges which have been generated and produces a complete
   * dungeon.
   */
  private String runKruscals() {
    //start condition - every cave in own set
    boolean exitCond = false;
    List<Integer> setList = new ArrayList<>();
    for (int s = 0; s < rows * columns; s++) {
      setList.add(s);
    }
    if (setList.size() - 1 != gameboard[rows - 1][columns - 1].getIndex()) {
      throw new IllegalArgumentException("the set list doesn't match the number of elements");
    }
    while (!exitCond) {
      // grab random edge
      int random = randomNumberGenerator.getRandomNumber(0, this.getPotEdgeList().size());
      //if they are in the same set check to see if this edge has already been called,
      // if not add to left over list
      if (this.potEdgeList.get(random).compareSets()) {
        if (!this.leftOverEdge.contains(this.potEdgeList.get(random))) {
          this.leftOverEdge.add(this.potEdgeList.get(random));
        }
      } else {
        //if not in the same set
        //add edge to final set
        this.potEdgeList.get(random).addNeighbors();
        finalEdgeList.add(this.potEdgeList.get(random));

        // save set number of right cave
        int tempint = this.potEdgeList.get(random).getRightSet();
        int newSetNum = this.potEdgeList.get(random).getLeftSet();
        //remove from potential edge list
        this.potEdgeList.remove(random);
        //loop through all members of that set and set to left set value
        for (int r = 0; r < rows; r++) {
          for (int c = 0; c < columns; c++) {
            if (gameboard[r][c].getSet() == tempint) {
              gameboard[r][c].adjSet(newSetNum);
            }
          }
        }
        //remove setnum from setList
        if (setList.contains(tempint)) {
          int removeInt = setList.indexOf(tempint);
          setList.remove(setList.indexOf(tempint));
        }

        //check for single set
        if (setList.size() == 1 && interconnect == 0) {
          exitCond = true;
        } else if (setList.size() == 1 && interconnect > 0) {
          //dump edges into single list
          for (int l = 0; l < this.potEdgeList.size(); l++) {
            if (!this.leftOverEdge.contains(this.potEdgeList.get(l))) {
              this.leftOverEdge.add(this.potEdgeList.get(l));
            }
          }
          for (int j = 0; j < interconnect; j++) {
            if (leftOverEdge.size() <= 0) {
              throw new IllegalStateException("Left over edge list is already empty");
            } else {
              int randomInt = randomNumberGenerator.getRandomNumber(0, leftOverEdge.size());
              finalEdgeList.add(leftOverEdge.get(randomInt));
              leftOverEdge.get(randomInt).addNeighbors();
              leftOverEdge.remove(randomInt);
            }

          }
          exitCond = true;
        }
      }
    }
    String finalEdgeListString = "status of final edge list: " + finalEdgeList.toString();
    return finalEdgeListString;
  }


  @Override
  public int getGameBoardRows() {
    //make deep copy and return
    return rows;
  }

  @Override
  public int getGameBoardCols() {
    //make deep copy and return
    return columns;
  }

  @Override
  public Cave[][] getGameBoard() {
    Cave[][] copy = new Cave[rows][columns];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        copy[r][c] = gameboard[r][c];
      }
    }
    //make deep copy and return
    return copy;
  }

  @Override
  public boolean isGameOver() {
    return !player.isPlayerAlive() || checkForEnd() || quitFlag;
  }

  private boolean checkForEnd() {
    return player.getPlayerLocation() == this.endPoint;
  }


  @Override
  public String movePlayer(Direction direction) {
    String moveString = "";
    if (!getPossibleDirection(player.getPlayerLocation()).contains(direction)) {
      throw new IllegalArgumentException("Can't move that way");
    } else {
      //move is valid move the player to the new cave
      for (int i = 0; i < finalEdgeList.size(); i++) {
        if (finalEdgeList.get(i).getLeftIndex() == player.getPlayerLocation()
                && direction == finalEdgeList.get(i).getDirectionToCave2()) {
          //set the new player location to the right index
          player.move(findCaveByIndex(finalEdgeList.get(i).getRightIndex()),
                  getPossibleDirection(finalEdgeList.get(i).getRightIndex()));
          break;
        } else if (finalEdgeList.get(i).getRightIndex() == player.getPlayerLocation()
                && direction == finalEdgeList.get(i).getDirectionToCave1()) {
          player.move(findCaveByIndex(finalEdgeList.get(i).getLeftIndex()),
                  getPossibleDirection(finalEdgeList.get(i).getLeftIndex()));
          break;
        }
      }
      //check if the cave is the end point
      if (player.getPlayerLocation() == this.endPoint) {
        moveString = "\nPlayer has reached final cave\n";
      }

      String encounterString = "";
      //check if it has a monster
      if (findCaveByIndex(player.getPlayerLocation()).getMonsterListSize() == 1
              && findCaveByIndex(player.getPlayerLocation()).getMonsterHealth() == 2) {
        encounterString = player.monsterEncounter(findCaveByIndex(player.getPlayerLocation())
                .getMonsterHealth(), 0);
        //the player dies
      } else if (findCaveByIndex(player.getPlayerLocation()).getMonsterListSize() == 1
              && findCaveByIndex(player.getPlayerLocation()).getMonsterHealth() == 1) {
        //player has 50/50 shot of escaping
        int returnInt = randomNumberGenerator.getRandomNumber(0, 2);
        if (returnInt == 0) {
          //player escapes
          encounterString = player.monsterEncounter(findCaveByIndex(player.getPlayerLocation())
                  .getMonsterHealth(), returnInt);
        } else {
          encounterString = player.monsterEncounter(findCaveByIndex(player.getPlayerLocation())
                  .getMonsterHealth(), returnInt);
        }
      } else {
        if (findCaveByIndex(player.getPlayerLocation()).getMonsterListSize() == 0) {
          encounterString = "";
        } else {
          encounterString = player.monsterEncounter(findCaveByIndex(player.getPlayerLocation())
                  .getMonsterHealth(), 0);
        }
        //monster is dead do nothing
      }

      //check for smell;
      String statusString = "";
      if (player.isPlayerAlive()) {
        statusString = player.getPlayerStatus(checkSmell(),
                findCaveByIndex(player.getPlayerLocation()));
      }
      //update player location and check around them for stuff.

      //update player status
      moveString = moveString + "\n" + encounterString + "\n" + statusString;
    }
    return moveString;
  }

  @Override
  public List<Edge> getFinalEdgeList() {
    List<Edge> copy = new ArrayList<>();
    for (int i = 0; i < finalEdgeList.size(); i++) {
      copy.add(finalEdgeList.get(i));
    }
    return copy;
  }

  private void addArrows() {
    int arrowNum = (int) Math.ceil((treasure  * rows * columns) / 100);
    while (arrowNum > 0) {
      //generate random number for index
      int rand = randomNumberGenerator.getRandomNumber(0, (rows * columns) - 1);
      if (findCaveByIndex(rand).getArrowListSize() == 0) {
        CrookedArrow arrow = new CrookedArrow();
        findCaveByIndex(rand).addArrow(arrow);
        arrowNum--;
      }
    }
  }


  private int checkSmell() {
    List<Integer> checked = new ArrayList<>();
    List<Integer> listToCheck = new ArrayList<>();
    List<Integer> listToLoop = new ArrayList<>();
    //find the neighbors of the current location
    checked.add(player.getPlayerLocation());
    for (int i = 0; i < findCaveByIndex(player.getPlayerLocation()).getNeighbors().size(); i++) {
      if (!(checked.contains(findCaveByIndex(player.getPlayerLocation()).getNeighbors().get(i)))) {
        listToCheck.add(findCaveByIndex(player.getPlayerLocation()).getNeighbors().get(i));
        listToLoop.add(findCaveByIndex(player.getPlayerLocation()).getNeighbors().get(i));
      }
    }
    int smell = 0;

    //check neighbors for monsters
    for (int n = 0; n < listToCheck.size(); n++) {
      if (findCaveByIndex(listToCheck.get(n)).getMonsterListSize() == 1
              && findCaveByIndex(listToCheck.get(n)).getMonsterHealth() > 0) {
        smell++;
        smell++;
      }
    }

    for (int y = 0; y < 1; y++) {
      //list size at time of iteration, not to keep looping through more stuff
      int temp = listToCheck.size();
      for (int c = 0; c < temp; c++) {
        //grab next value in list to check
        int tempInt = listToCheck.get(c);
        List<Integer> tempList = findCaveByIndex(tempInt).getNeighbors();
        //checks to see if next element down has children
        if (tempList.size() > 1) {
          //if it does have children add them to the lists
          for (int a = 0; a < tempList.size(); a++) {
            if (!(checked.contains((int) findCaveByIndex(tempInt).getNeighbors().get(a)))) {
              checked.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
              listToCheck.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
            }
          }
        }
      }
    }

    for (int l = 0; l < listToCheck.size(); l++) {
      if (findCaveByIndex(listToCheck.get(l)).getMonsterListSize() == 1
              && findCaveByIndex(listToCheck.get(l)).getMonsterHealth() > 0) {
        smell++;
      }
    }
    return smell;
  }

  public boolean getWrapping() {
    return this.wraps;
  }

  @Override
  public String shootArrow(int distance, Direction direction) {
    String finalShotString = "";
    if (distance < 0) {
      throw new IllegalArgumentException("Cannot shoot less than 0");
    }

    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }

    if (!getPossibleDirection(player.getPlayerLocation()).contains(direction)) {
      throw new IllegalArgumentException("Can't shoot that way");
    } else {
      String shootString = "Fired an arrow " + distance + " spaces " + direction;
      String updateString = player.shoot(distance, direction);
      finalShotString = finalShotString + "\n" + shootString + "\n" + updateString + "\n";

    }

    //decriment player arrow count by 1
    int nextCaveIndex = 0;
    int monsterHealth = -1;
    int currentIndex = this.player.getPlayerLocation();
    Direction currentDirection = direction;
    Direction nextDirection = null;
    String shotString = "";
    if (distance == 0
            && findCaveByIndex(this.player.getPlayerLocation()).getMonsterListSize() == 1
            && findCaveByIndex(this.player.getPlayerLocation()).getMonster().getHealth() != 0) {
      monsterHealth = findCaveByIndex(this.player.getPlayerLocation()).getMonster().takeDamage();
    } else if (distance == 0
            && (findCaveByIndex(this.player.getPlayerLocation()).getMonsterListSize() != 1
            || findCaveByIndex(this.player.getPlayerLocation()).getMonster().getHealth() == 0)) {
      shotString = "The shot missed!";

    } else {
      while (distance > 0) {
        if (findCaveByIndex(currentIndex).getNeighbors().size() == 1) {
          break;
        }
        for (int i = 0; i < finalEdgeList.size(); i++) {
          //find the next cave based on current direction and current index;
          if (finalEdgeList.get(i).getLeftIndex() == currentIndex
                  && currentDirection == finalEdgeList.get(i).getDirectionToCave2()) {
            nextCaveIndex = finalEdgeList.get(i).getRightIndex();
          } else if (finalEdgeList.get(i).getRightIndex() == currentIndex
                  && currentDirection == finalEdgeList.get(i).getDirectionToCave1()) {
            nextCaveIndex = finalEdgeList.get(i).getLeftIndex();
          }
        }

        List<Direction> tempList = getPossibleDirection(nextCaveIndex);
        if (tempList.contains(currentDirection) && tempList.size() != 2) {
          nextDirection = currentDirection;
          distance--;
        } else if (tempList.size() == 2) {
          for (int i = 0; i < 2; i++) {
            if (tempList.get(i) != getOppositeDirection(currentDirection)) {
              nextDirection = tempList.get(i);
            }
          }
        } else if (distance != 1) {
          shotString = "Zing! The arrow bounced off a wall.";
          break;
        } else {
          currentIndex = nextCaveIndex;
          break;
        }
        //next index becomes current
        //if the distance is zero quit, else set up for next round;

        //handle tunnels
        //if direction equals current direction then don't change it,
        //if not direction !the opposite direction
        //find next cave/tunnel that the arrow is entering

        //check if going through a cave or tunnel
        //if cave find out if there is an adjacent exit,
        //if so decriment and move on
        //if not arrow hits the wall break out and message

        //if tunnel check directions and adjust if its a bender, do not decriment
        currentIndex = nextCaveIndex;
        currentDirection = nextDirection;
      }
      if (findCaveByIndex(currentIndex).getMonsterListSize() == 1
              && findCaveByIndex(currentIndex).getMonster().getHealth() != 0) {
        monsterHealth = findCaveByIndex(currentIndex).getMonster().takeDamage();
      }

    }
    if (monsterHealth == -1) {
      finalShotString = finalShotString + shotString + "\n";
    } else if (monsterHealth == 0) {
      finalShotString = finalShotString + "A great howl echos through the dungeon and a loud "
              + "crash as the monster falls over dead.\n";
    } else {
      finalShotString = finalShotString + "A great howl echos through the dungeon.\n";
    }

    return finalShotString;
    //check final location for monster, if monster present take damage and send message
    //if not tell user they missed
  }

  @Override
  public String pickUpItem(int option) {
    String pickupString;
    if (option < 0 || option >= 3) {
      throw new IllegalArgumentException("that is not an option for pickup");
    } else {
      pickupString = player.pickUp(findCaveByIndex(player.getPlayerLocation()), option) + "\n"
              + player.getPlayerStatus(checkSmell(), findCaveByIndex(player.getPlayerLocation()));
    }
    return pickupString;
  }

  @Override
  public String quitGame() {
    String quitString = "Game quit! Thank You for Playing Dungeon Adventure.\n";
    String finalPlayerStatus = player.getPlayerStatus(checkSmell(),
            findCaveByIndex(player.getPlayerLocation()));
    quitFlag = true;
    return quitString + finalPlayerStatus;
  }

  private Direction getOppositeDirection(Direction direction) {
    Direction returnDirection = null;
    if (direction == Direction.NORTH) {
      returnDirection = Direction.SOUTH;
    } else if (direction == Direction.SOUTH) {
      returnDirection = Direction.NORTH;
    } else if (direction == Direction.EAST) {
      returnDirection = Direction.WEST;
    } else if (direction == Direction.WEST) {
      returnDirection = Direction.EAST;
    }
    return returnDirection;
  }
}
