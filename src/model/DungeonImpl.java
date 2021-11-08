package model;

import driver.Driver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import random.RandomNumberGenerator;

/**
 * The implementation of the Dungeon interface.
 */
public class DungeonImpl implements Dungeon {
  private boolean wraps;
  private int rows;
  private int columns;
  private int interconnect;
  private int treasure;
  private Cave[][] gameboard;
  private ArrayList<Edge> potEdgeList;
  private ArrayList<Edge> leftOverEdge;
  private ArrayList<Edge> finalEdgeList;
  private int startPoint;
  private int endPoint;
  private Player player;
  private ArrayList<Integer> shortestPath;
  private Graph graph;

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
                     Player player) {

    ArrayList<Edge> potEdgeList = new ArrayList<>();
    ArrayList<Edge> leftOverEdge = new ArrayList<>();
    ArrayList<Edge> finalEdgeList = new ArrayList<>();
    ArrayList<Integer> shortestPath = new ArrayList<>();
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
    this.shortestPath = shortestPath;
    this.graph = graph;





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
      //forumla derived by Madhira Datta
      int maxEdges = 2 * rows * columns;
      if (interconnect > maxEdges) {
        throw new IllegalArgumentException("Interconnectivity too high, beyond number of edges in"
                + " graph.");
      }
    }


    //construct caves
    int index = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        ArrayList<Integer> neighborList = new ArrayList<>();
        ArrayList<Treasure> treasureList = new ArrayList<>();
        Cave cave = new Cave(r, c, neighborList, treasureList, index, index);
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
  public void getDungeon() {
    //runs Kruscals, adds interconnectivity
    runKruscals();
    //generates a start point by index
    this.startPoint = getStartPoint(getCavesByIndex());
    //finds a viable end point
    this.endPoint = findEndPoint(this.startPoint);
    //find caves and adds Treasure
    findCaves(getCavesByIndex());

    setUpPlayer();

    runBfs();

    //runDfs();

    //setUpPlayerDfs();

    runDungeon();

  }



  //This is the code for running a depth first search of the edges which will run a player through
  // all possible nodes
  void runDfs() {
    int nodes = rows * columns;
    Graph g = new Graph(nodes);

    for (int t = 0; t < finalEdgeList.size(); t++) {
      g.addEdge(finalEdgeList.get(t).getLeftIndex(), finalEdgeList.get(t).getRightIndex());
    }

    this.shortestPath = g.dfs();
    String dfsString = "\nThis is the DFS Path: " + this.shortestPath;
    Driver.printHelper(dfsString);
  }

  //This executes the player's path through the dungeon
  private void runDungeon() {
    for (int i = 0; i < this.shortestPath.size(); i++) {
      //check for treasure, if it exists add it to the treasure bag, remove it from cave
      ArrayList<Treasure> caveTreasure = new ArrayList<>();
      if (findCaveByIndex(shortestPath.get(i)).getTreasureList() != null
              || findCaveByIndex(shortestPath.get(i)).getTreasureList().size() != 0) {
        caveTreasure = findCaveByIndex(shortestPath.get(i)).getTreasureFromCave();
      }

      if (i == this.shortestPath.size() - 1) {
        String endString = "\nThe has reached their end point! Their quest is over. "
                + "Lets check on our player.";
        Driver.printHelper(endString);
      }
      player.move(shortestPath.get(i), getPossibleDirection(shortestPath.get(i)), caveTreasure);

      //change player location
      //move to new index announcing direction traveled(get from edge)
      //run get player status
      player.getPlayerStatus();
    }
  }

  /**
   * This puts the player in their starting cave for navigating through dungeon for moving from the
   * start point to the end point.
   */
  private void setUpPlayer() {
    //place the player in the dungeon at the cave index
    player.enterDungeon(this.startPoint, findCaveByIndex(this.startPoint).getTreasureList(),
            getPossibleDirection(this.startPoint));

    String enterString = "\nThe Player enters the dungeon at Cave " + this.startPoint
            + ". They currently have no treasure.";
    Driver.printHelper(enterString);

  }

  /**
   * This puts the player in their starting cave for navigating through dungeon for moving through
   * all caves in the dungeon.
   */
  private void setUpPlayerDfs() {
    //place the player in the dungeon at the cave index
    player.enterDungeon(this.shortestPath.get(0),
            findCaveByIndex(this.shortestPath.get(0)).getTreasureList(),
            getPossibleDirection(this.shortestPath.get(0)));

    String enterString = "\nThe Player enters the dungeon at Cave " + this.shortestPath.get(0)
            + ". They currently have no treasure.";
    Driver.printHelper(enterString);

  }

  /**A helper to get the directions from the next cave to give the player all the options for the
   * next cave for which direction to travel.
   *
   * @param index takes in the index of the next cave
   * @return a list of the possible directions for the player to travel.
   */
  private ArrayList<Direction> getPossibleDirection(int index) {
    ArrayList<Direction> tempArray = new ArrayList<>();
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
  private int getStartPoint(ArrayList<Integer> caves) {

    RandomNumberGenerator rand = new RandomNumberGenerator(0, caves.size() - 1, 0,
            1);
    int startIndex = caves.get(rand.getRandomNumber());
    return startIndex;
  }

  /**This finds the end point by searching through all nodes within 5 moves of the start node by
   * finding the children of the previous nodes. It then performs a difference between the caves
   * found and the total caves. It then randomly selects an end point from the caves remaining.
   *
   * @param startIndex takes in the start index as an integer.
   * @return the end point index as an integer.
   */
  private int findEndPoint(int startIndex) {
    ArrayList<Integer> nonViable = new ArrayList<>();
    ArrayList<Integer> viable = new ArrayList<>();
    ArrayList<Integer> allCaves = getCavesByIndex();
    ArrayList<Integer> listToCheck = new ArrayList<>();
    int endPoint = 0;

    //add start index to list of things to check and those that can't be an end point
    nonViable.add(startIndex);
    listToCheck.add(startIndex);
    //check for incomplete graph
    if (findCaveByIndex(startIndex).getNeighbors().size() != 0) {
      //loop throught the list of neighbors of the start index
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
          ArrayList<Integer> tempList = findCaveByIndex(tempInt).getNeighbors();
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
          //System.out.print("\nViable end points: " + viable.toString());
        }
      }
      if (viable.size() != 1) {
        RandomNumberGenerator rand = new RandomNumberGenerator(0, viable.size() - 1, 0, 1);
        int endRand = rand.getRandomNumber();
        endPoint = viable.get(endRand);
      } else {
        endPoint = viable.get(0);
      }
    }
    return endPoint;
  }

  /**This is used for setting up the caves and adding treasure.
   *
   * @param caves takes the complete list of nodes as an array list of their integer indexes.
   */
  private void findCaves(ArrayList<Integer> caves) {
    int treasureInt = 0;
    //make list of caves, exclude tunnels
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (gameboard[r][c].getNeighbors().size() != 2
                && !caves.contains(gameboard[r][c].getIndex())) {
          caves.add(gameboard[r][c].getIndex());
        }
      }
    }
    //calculate how many caves require treasure
    if (this.treasure != 0) {
      int treasCaveNum = (int) Math.ceil((caves.size() * treasure) / 100);
      if (treasCaveNum == 0) {
        treasCaveNum++;
      }
      //System.out.print("\nNumber of caves that need treasure: " + treasCaveNum);
      RandomNumberGenerator rand = new RandomNumberGenerator(0, caves.size() - 1,
              0, 1);
      RandomNumberGenerator rand2 = new RandomNumberGenerator(1, 3, 0, 1);
      TreasureImpl treasureFactory = new TreasureImpl();
      for (int t = 0; t < treasCaveNum; t++) {
        int treasureRand = rand2.getRandomNumber();
        if (treasureRand == 0) {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.RUBY));
          }
        } else if (treasureRand == 1) {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.DIAMOND));
          }
        } else {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.SAPPHIRE));
          }
        }
      }
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
  private ArrayList<Edge> getPotEdgeList() {
    return this.potEdgeList;
  }

  /**This builds and returns all the cave and tunnel index values in the dungeon.
   *
   * @return an array list of the indexes of all caves and tunnels in the dungeon.
   */
  private ArrayList<Integer> getCavesByIndex() {
    ArrayList<Integer> caves = new ArrayList<>();
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
  private void runKruscals() {
    //start condition - every cave in own set
    RandomNumberGenerator rand = new RandomNumberGenerator(0, this.getPotEdgeList().size(), 0, 1);
    Random randGen = new Random(rand.getRandomNumber());
    boolean exitCond = false;
    ArrayList<Integer> setList = new ArrayList<>();
    for (int s = 0; s < rows * columns; s++) {
      setList.add(s);
    }
    if (setList.size() - 1 != gameboard[rows - 1][columns - 1].getIndex()) {
      throw new IllegalArgumentException("the set list doesn't match the number of elements");
    }
    while (!exitCond) {
      // grab random edge
      int random = randGen.nextInt(this.getPotEdgeList().size());
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
              int randomInt = randGen.nextInt(leftOverEdge.size());
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
    Driver.printHelper(finalEdgeListString);
  }

  /**
   * Runs a breadth first search to generate the shortest path for the player to navigate the
   * dungeon.
   */
  //code adapted from https://www.geeksforgeeks.org/shortest-path-unweighted-graph/?ref=lbp
  private void runBfs() {
    // No of vertices
    int v = rows * columns;

    // Adjacency list for storing which vertices are connected
    ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(v);
    for (int i = 0; i < v; i++) {
      adj.add(new ArrayList<Integer>());
    }

    // Creating graph given in the above diagram.
    // add_edge function takes adjacency list, source
    // and destination vertex as argument and forms
    // an edge between them.
    for (int i = 0; i < finalEdgeList.size(); i++) {
      addEdge(adj, finalEdgeList.get(i).getLeftIndex(), finalEdgeList.get(i).getRightIndex());
    }
    int source = this.startPoint;
    int dest = this.endPoint;
    this.shortestPath = printShortestDistance(adj, source, dest, v);

  }

  // function to form edge between two vertices
  // source and dest

  /**Adds an edge to the adjaceny list with the index of two nodes for determining the shortest
   * path. Helper for the breadth first search.
   *
   * @param adj the adjacency list for doing the breadth first search.
   * @param i the index of one of the two nodes that were selected as edges in the graph.
   * @param j the index of the second of the two nodes that were selected as edges in the graph.
   */
  private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
    adj.get(i).add(j);
    adj.get(j).add(i);
  }

  // function to print the shortest distance and path
  // between source vertex and destination vertex

  /**Builds the shortest path for the player to navigate the dungeon.
   *
   * @param adj the adjacency list with the nodes added to it for determine the shortest path. It
   *            must have already had all the final nodes added to it.
   * @param s the index of the starting node.
   * @param dest the destination index.
   * @param v the number of vertices or nodes in the graph.
   * @return the array list of integers with the shortest path for the player to navigate.
   */
  private static ArrayList<Integer> printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s,
                                                          int dest, int v) {
    // predecessor[i] array stores predecessor of
    // i and distance array stores distance of i
    // from s
    int pred[] = new int[v];
    int dist[] = new int[v];

    if (bfs(adj, s, dest, v, pred, dist) == false) {
      throw new IllegalStateException("Given source and destination are not connected");
    }

    // LinkedList to store path
    LinkedList<Integer> path = new LinkedList<Integer>();
    int crawl = dest;
    path.add(crawl);
    while (pred[crawl] != -1) {
      path.add(pred[crawl]);
      crawl = pred[crawl];
    }

    // Print distance

    ArrayList<Integer> pathList = new ArrayList<>();
    for (int i = path.size() - 1; i >= 0; i--) {
      pathList.add(path.get(i));
    }
    String pathString = "Final path: " + pathList;
    Driver.printHelper(pathString);
    return pathList;
  }

  // a modified version of BFS that stores predecessor
  // of each vertex in array pred
  // and its distance from source in array dist

  /**Performs the breadth first search for finding the shortest path to from the start point to the
   * end point.
   *
   * @param adj the adjacency list of integers with all of the edges added to
   * @param src the start point index
   * @param dest the end point index
   * @param v the total number of nodes in the dungeon
   * @param pred an array that stores the predecessor of the node being processed.
   * @param dist an array that stores the distance of a node from the end.
   * @return a boolean which indicates if the search has reached its end point.
   */
  private static boolean bfs(ArrayList<ArrayList<Integer>> adj, int src,
                             int dest, int v, int pred[], int dist[]) {
    // a queue to maintain queue of vertices whose
    // adjacency list is to be scanned as per normal
    // BFS algorithm using LinkedList of Integer type

    // boolean array visited[] which stores the
    // information whether ith vertex is reached
    // at least once in the Breadth first search
    boolean visited[] = new boolean[v];

    // initially all vertices are unvisited
    // so v[i] for all i is false
    // and as no path is yet constructed
    // dist[i] for all i set to infinity
    for (int i = 0; i < v; i++) {
      visited[i] = false;
      dist[i] = Integer.MAX_VALUE;
      pred[i] = -1;
    }
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // now source is first to be visited and
    // distance from source to itself should be 0
    visited[src] = true;
    dist[src] = 0;
    queue.add(src);

    // bfs Algorithm
    while (!queue.isEmpty()) {
      int u = queue.remove();
      for (int i = 0; i < adj.get(u).size(); i++) {
        if (visited[adj.get(u).get(i)] == false) {
          visited[adj.get(u).get(i)] = true;
          dist[adj.get(u).get(i)] = dist[u] + 1;
          pred[adj.get(u).get(i)] = u;
          queue.add(adj.get(u).get(i));

          // stopping condition (when we find
          // our destination)
          if (adj.get(u).get(i) == dest) {
            return true;
          }
        }
      }
    }
    return false;
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
        copy[c][r] = gameboard[c][r];
      }
    }
    //make deep copy and return
    return copy;
  }

  @Override
  public ArrayList<Edge> getFinalEdgeList() {
    ArrayList<Edge> copy = new ArrayList<>();
    for (int i = 0; i < finalEdgeList.size(); i++) {
      copy.add(finalEdgeList.get(i));
    }
    return copy;
  }

  @Override
  public ArrayList<Integer> getFinalPath() {
    ArrayList<Integer> copyList = new ArrayList<>();
    for (int i = 0; i < shortestPath.size(); i++) {
      copyList.add(shortestPath.get(i));
    }
    return copyList;
  }


}
