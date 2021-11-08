package model;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract location object which is the basis for our tunnels and caves. It has a
 * two-dimensional point representation for the row and column location. The list of entrances into
 * the location, the neighbors to the location, and the treasure which is in the location.
 */
public abstract class AbstractLocation implements Location {
  protected Point2D location;
  private List<Integer> neighborList;
  private List<Treasure> treasureList;

  protected AbstractLocation(Point2D location, List<Integer> neighborList,
                             List<Treasure> treasureList) {
    this.location = location;
    this.neighborList = neighborList;
    this.treasureList = treasureList;
  }

  private void setLocation() {
  }
}
