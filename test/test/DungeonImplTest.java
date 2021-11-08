package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.Cave;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DungeonImplTest {
  private Dungeon test;
  private Player player;
  private ArrayList edgeList;

  @Before
  public void setUp() {

    Cave[][] boardCopy = new Cave[5][5];
    ArrayList edgeList = new ArrayList();

  }


  @Test
  public void getGameBoardRows() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    assertEquals(5, test.getGameBoardRows());
  }

  @Test
  public void getGameBoardCols() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,8,0,20, player);
    test.getDungeon();
    assertEquals(8, test.getGameBoardCols());
  }

  @Test
  public void getFinalEdgeList() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    assertTrue(test.getFinalEdgeList().size() >= 24);
  }

  @Test
  public void getFinalEdgeListMax() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,16,20, player);
    test.getDungeon();
    assertEquals(40,test.getFinalEdgeList().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotNodesTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 2,2,16,20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotEnoughRowsTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 0,5,16,20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotEnoughColsTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,0,16,20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNegTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,1,-20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorTooMuchTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,1,200, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNegInterconTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,-1,20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNonwrapTooMuchIntercon() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,40,20, player);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorWrapTooMuchIntercon() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,50,20, player);
  }

  @Test
  public void getFinalPath() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    assertTrue(test.getFinalPath().size() >= 5);
  }

  @Test
  public void getGameBoard() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    assertEquals(5,test.getGameBoard().length);
  }

  @Test
  public void getTeasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int treasureInt = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        if (!testboard[r][c].getTreasureList().isEmpty()) {
          treasureInt++;
        }
      }
    }
    assertTrue(treasureInt >= 5);
  }

  @Test
  public void getZeroTunnelTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int treasureInt = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        if (testboard[r][c].getNeighbors().size() == 2 && testboard[r][c].getTreasureList().size() > 0) {
          treasureInt++;
        }
      }
    }
    assertEquals(0, treasureInt);
  }
}