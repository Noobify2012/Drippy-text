package model;

// Java program to print DFS
// traversal from a given given
// graph

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**This represents the graph used for performing a depth first search.
 *  This code was adapted from the code at
 *  https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
 */
// This class represents a
// directed graph using adjacency
// list representation
class Graph {
  private int verticies; // No. of vertices

  // Array of lists for
  // Adjacency List Representation
  private LinkedList<Integer> adj[];

  // Constructor
  @SuppressWarnings("unchecked")
  Graph(int v) {
    verticies = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; ++i) {
      adj[i] = new LinkedList<Integer>();
    }
  }

  /**Adds an edge to the graph.
   *
   * @param v the index of the first cave or tunnel in the edge.
   * @param w the index of the second cave or tunnel in the edge.
   */
  // Function to add an edge into the graph
  void addEdge(int v, int w) {
    adj[v].add(w); // Add w to v's list.
  }

  /**The utility method used by the depth first search for determining of all nodes have been
   * visited.
   *
   * @param v the number of nodes in the graph as an integer
   * @param visited a boolean array tracking whether nodes have been visited
   * @param dfsList the list of nods in the depth first search used to guide the player list
   * @return an array list of the node indexes used to guide the player to all nodes in the dungeon.
   */
  // A function used by DFS
  ArrayList<Integer> dfsUtil(int v, boolean visited[], ArrayList<Integer> dfsList) {
    // Mark the current node as visited and print it
    visited[v] = true;
    dfsList.add(v);
    //System.out.print(v + " ");

    // Recur for all the vertices adjacent to this
    // vertex
    Iterator<Integer> i = adj[v].listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited[n]) {
        dfsUtil(n, visited, dfsList);
      }
    }
    return dfsList;
  }

  /**This is a helper to actually perform the depth first search.
   *
   * @return the list of nodes for navigating the entire dungeon.
   */
  // The function to do DFS traversal. It uses recursive
  // dfsUtil()
  ArrayList<Integer> dfs() {
    ArrayList<Integer> dfsList = new ArrayList<>();
    // Mark all the vertices as not visited(set as
    // false by default in java)
    boolean visited[] = new boolean[verticies];

    // Call the recursive helper function to print DFS
    // traversal starting from all vertices one by one
    for (int i = 0; i < verticies; ++i) {
      if (visited[i] == false) {
        dfsUtil(i, visited, dfsList);
      }
    }
    return dfsList;
  }
}
