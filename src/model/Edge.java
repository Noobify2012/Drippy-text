package model;

class Edge {
  private Cave cave1;
  private Cave cave2;

  private Direction directionToCave1;
  private Direction directionToCave2;

  Edge(Cave cave1, Cave cave2) {
    this.cave1 = cave1;
    this.cave2 = cave2;
    this.directionToCave1 = directionToCave1;
    this.directionToCave2 = directionToCave2;

    if (cave1.getRow() - cave2.getRow() == 0 && cave1.getColumn() - cave2.getColumn() == -1) {
      directionToCave2 = Direction.EAST;
      directionToCave1 = Direction.WEST;
    } else if (cave1.getRow() - cave2.getRow() == 0 && cave1.getColumn() - cave2.getColumn() == 1) {
      directionToCave2 = Direction.WEST;
      directionToCave1 = Direction.EAST;
    } else if (cave1.getRow() - cave2.getRow() == -1 && cave1.getColumn()
            - cave2.getColumn() == 0) {
      directionToCave2 = Direction.SOUTH;
      directionToCave1 = Direction.NORTH;
    } else {
      directionToCave2 = Direction.NORTH;
      directionToCave1 = Direction.SOUTH;
    }
  }

  /** This is used to get the direction from one cave to another on an edge.
   *
   * @param cave1 The first cave associated with an edge.
   * @param cave2 The second cave associated with an edge.
   * @return the direction from one cave to another along an edge.
   */
  public Direction getDirection(Cave cave1, Cave cave2) {
    if (cave1 == this.cave1 && cave2 == this.cave2) {
      return directionToCave2;
    } else if (cave1 == this.cave2 && cave1 == this.cave1) {
      return directionToCave1;
    } else {
      throw new IllegalArgumentException(cave1 + " or " + cave2
              + "is not associated with this edge");
    }
  }

  /**Helper that compares the sets of two nodes for performing Kruskal's.
   *
   * @return a boolean to indicate if they are in the same set or not.
   */
  boolean compareSets() {
    if (this.cave1.getSet() == this.cave2.getSet()) {
      return true;
    } else {
      return false;
    }
  }

  /**Returns the set of the first cave in an edge.
   *
   * @return the integer set value of the first cave(cave1) in an edge.
   */
  int getLeftSet() {
    return this.cave1.getSet();
  }

  /**Returns the set of the second cave in an edge.
   *
   * @return the integer set value of the second cave(cave2) in an edge.
   */
  int getRightSet() {
    return this.cave2.getSet();
  }

  /**Returns the index value of the first cave of an edge.
   *
   * @return the integer index value of the first node(cave1).
   */
  int getLeftIndex() {
    return this.cave1.getIndex();
  }

  /**Returns the index value of the second cave of an edge.
   *
   * @return the integer index value of the second node(cave2).
   */
  int getRightIndex() {
    return this.cave2.getIndex();
  }

  /**This adds the index of the opposite cave on an edge to the others neighbor list.
   *
   */
  void addNeighbors() {
    this.cave1.addNeighbor(this.getRightIndex());
    this.cave2.addNeighbor(this.getLeftIndex());
  }

  /**Helper for returning the direction from cave 2 to cave 1.
   *
   * @return the direction as an enum from cave 2 to cave 1.
   */
  Direction getDirectionToCave1() {
    return this.directionToCave1;
  }

  /**Helper for returning the direction from cave 1 to cave 2.
   *
   * @return the direction as an enum from cave 1 to cave 2.
   */
  Direction getDirectionToCave2() {
    return this.directionToCave2;
  }


  /**Produces a string representation of an edge.
   *
   * @return the string representation of an edge with an arrow separating the two indexes.
   */
  @Override
  public String toString() {
    String returnString = cave1.getIndex() + "<========>" + cave2.getIndex();
    return returnString;
  }
}
