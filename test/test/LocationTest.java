package test;

import org.junit.Test;

import model.Cave;
import model.Dungeon;
import model.DungeonImpl;
import model.iDungeon;
import model.Player;
import model.PlayerImpl;

import static org.junit.Assert.assertEquals;

/**
 * Testing for the functionality of locations.
 */
public class LocationTest {

  @Test
  public void getTreasureList() {
    Player player = new PlayerImpl();
    iDungeon test = new DungeonImpl(false, 4,3,0,50, player,
            1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    Cave testCave = testboard[3][1];
    assertEquals(5,testCave.getTreasureList().size());

  }

  @Test
  public void getNeighbors() {
    Player player = new PlayerImpl();
    iDungeon test = new DungeonImpl(false, 4,3,0,50, player,
            1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    Cave testCave = testboard[1][0];
    assertEquals(2, testCave.getNeighbors().size());
  }

  @Test
  public void getArrowList() {
    Player player = new PlayerImpl();
    iDungeon test = new DungeonImpl(false, 4,3,0,50, player,
            1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int listSize = testboard[1][0].getArrowListSize();
    assertEquals(1, listSize);
  }
}