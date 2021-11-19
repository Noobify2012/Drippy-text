package test;

import org.junit.Test;
import java.util.ArrayList;
import model.Cave;
import model.Direction;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the tests for all Dungeon functionality and construction.
 */
public class DungeonImplTest {
  private Dungeon test;
  private Player player;
  private ArrayList edgeList;



  @Test
  public void getGameBoardRows() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player, 1, 1);
    test.getDungeon();
    assertEquals(5, test.getGameBoardRows());
  }

  @Test
  public void getGameBoardCols() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,8,0,20, player, 1, 1);
    test.getDungeon();
    assertEquals(8, test.getGameBoardCols());
  }

  @Test
  public void getFinalEdgeList() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player, 1, 1);
    test.getDungeon();
    assertTrue(test.getFinalEdgeList().size() >= 24);
  }

  @Test
  public void getFinalEdgeListMax() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,16,20, player, 1, 1);
    test.getDungeon();
    assertEquals(40,test.getFinalEdgeList().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotNodesTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 2,2,16,20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotEnoughRowsTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 0,5,16,20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNotEnoughColsTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,0,16,20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNegTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,1,-20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorTooMuchTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,1,200, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNegInterconTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,-1,20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorNonwrapTooMuchIntercon() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,40,20, player, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void dungeonConstructorWrapTooMuchIntercon() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,50,20, player, 1, 1);
  }

  @Test
  public void getGameBoard() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player, 1, 1);
    test.getDungeon();
    assertEquals(5,test.getGameBoard().length);
  }

  @Test
  public void getTeasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player, 1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int treasureInt = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 3; c++) {
        if (!testboard[r][c].getTreasureList().isEmpty()) {
          treasureInt++;
        }
      }
    }
    assertEquals(3, treasureInt);
  }

  @Test
  public void getZeroTunnelTreasureTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player, 1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int treasureInt = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        if (testboard[r][c].getNeighbors().size() == 2
                && testboard[r][c].getTreasureList().size() > 0) {
          treasureInt++;
        }
      }
    }
    assertEquals(0, treasureInt);
  }

  @Test
  public void getDungeon() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    String createString = test.getDungeon();
    String testString =
            "\n" +
            "The player is currently in Cave 3 and has nothing in their treasure bag. \n" +
            "They can go SOUTH NORTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and no arrows in this cave.\n";
    assertEquals(testString, createString);
  }

  @Test
  public void isGameOver() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    boolean testBool = test.isGameOver();
    assertEquals(false, testBool);
  }

  @Test
  public void movePlayer() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    String moveString = test.movePlayer(Direction.NORTH);
    String result = "\n\nThe player is currently in Cave 0 and has nothing in their treasure bag."
            + " \n" +
            "They can go EAST SOUTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and no arrows in this cave.\n";
    assertEquals(result, moveString);
  }

  @Test
  public void getWrapping() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    boolean wraps = test.getWrapping();
    assertEquals(false, wraps);

  }

  @Test
  public void shootArrow() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    String firstShot = test.shootArrow(1,Direction.NORTH);
    String secondShot = test.shootArrow(1,Direction.NORTH);
    String howlString = "\n" +
            "Fired an arrow 1 spaces NORTH\n" +
            "\n" +
            "The player has 2 arrows remaining.\n" +
            "A great howl echos through the dungeon.\n";

    String deathString = "\n" +
            "Fired an arrow 1 spaces NORTH\n" +
            "\n" +
            "The player has 1 arrows remaining.\n" +
            "A great howl echos through the dungeon and a loud crash as the monster falls over "
            + "dead.\n";
    assertEquals(howlString,firstShot);
    assertEquals(deathString, secondShot);
  }

  @Test
  public void missShot() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    String firstShot = test.shootArrow(2,Direction.NORTH);
    String missString = "\n" +
            "Fired an arrow 2 spaces NORTH\n" +
            "\n" +
            "The player has 2 arrows remaining.\n" +
            "Zing! The arrow bounced off a wall.\n";
    assertEquals(missString,firstShot);
  }

  @Test
  public void pickUpItem() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player,
            1, 1);
    test.getDungeon();
    String pickUp = test.pickUpItem(1);
    String pickUpString = "\n" +
            "The player picked up an arrow.\n" +
            "The player is currently in Cave 3 and has nothing in their treasure bag. \n" +
            "They can go SOUTH NORTH , there are 4 arrows remaining in their quiver, and there is"
            + " no treasure in this cave and no arrows in this cave.\n";
    assertEquals(pickUpString, pickUp);
  }

  @Test
  public void quitGame() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player,
            1, 1);
    test.getDungeon();
    String quitString = test.quitGame();
    String finalString = "Game quit! Thank You for Playing Dungeon Adventure.\n" +
            "The player is currently in Cave 3 and has nothing in their treasure bag. \n" +
            "They can go SOUTH NORTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and an arrow in this cave.\n";
    assertEquals(finalString, quitString);
  }

  @Test
  public void getArrowTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player, 1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int arrowInt = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 3; c++) {
        if (testboard[r][c].getArrowListSize() != 0) {
          arrowInt++;
        }
      }
    }
    assertEquals(6, arrowInt);
  }

  @Test
  public void getMonsterTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player, 1, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int monsterInt = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 3; c++) {
        if (testboard[r][c].getMonsterListSize() != 0) {
          monsterInt++;
        }
      }
    }
    assertEquals(1, monsterInt);
  }

  @Test
  public void getMultiMonsterTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,50, player, 2, 1);
    test.getDungeon();
    Cave[][] testboard = test.getGameBoard();
    int monsterInt = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 3; c++) {
        if (testboard[r][c].getMonsterListSize() != 0) {
          monsterInt++;
        }
      }
    }
    assertEquals(2, monsterInt);
  }

  @Test (expected = IllegalArgumentException.class)
  public void notEnoughMonstersTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player, 0, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void tooManyMonstersTest() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player, 5, 1);
    test.getDungeon();
  }

  @Test
  public void smellCheck() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    String moveString = test.movePlayer(Direction.NORTH);
    String result = "\n\nThe player is currently in Cave 0 and has nothing in their treasure bag."
            + " \n" +
            "They can go EAST SOUTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and no arrows in this cave.\n";
    assertEquals(result, moveString);
    test.movePlayer(Direction.EAST);
    String smellStringOne = test.movePlayer(Direction.EAST);
    String smellResultOne = "\n" +
            "\n" +
            "The player is currently in Cave 2 and has nothing in their treasure bag. \n" +
            "They can go SOUTH WEST , there are 3 arrows remaining in their quiver, and there is " +
            "no treasure in this cave and no arrows in this cave.\n" +
            "\n" +
            "The player smells something faint but awful.\n";
    assertEquals(smellResultOne, smellStringOne);
    String smellStringTwo = test.movePlayer(Direction.SOUTH);
    String smellResultTwo = "\n" +
            "\n" +
            "The player is currently in Cave 5 and has nothing in their treasure bag. \n" +
            "They can go WEST NORTH , there are 3 arrows remaining in their quiver, and there is " +
            "no treasure in this cave and no arrows in this cave.\n" +
            "\n" +
            "The player smells something awful and strong.\n";
    assertEquals(smellResultTwo, smellStringTwo);
  }

  //TODO - check smell with dead monster

  @Test
  public void deadMonsterSmellCheck() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4,3,0,20, player,
            1, 1);
    test.getDungeon();
    String moveString = test.movePlayer(Direction.NORTH);
    String result = "\n\nThe player is currently in Cave 0 and has nothing in their treasure bag."
            + " \n" +
            "They can go EAST SOUTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and no arrows in this cave.\n";
    assertEquals(result, moveString);
    test.movePlayer(Direction.EAST);
    String smellStringOne = test.movePlayer(Direction.EAST);
    String smellResultOne = "\n" +
            "\n" +
            "The player is currently in Cave 2 and has nothing in their treasure bag. \n" +
            "They can go SOUTH WEST , there are 3 arrows remaining in their quiver, and there is " +
            "no treasure in this cave and no arrows in this cave.\n" +
            "\n" +
            "The player smells something faint but awful.\n";
    assertEquals(smellResultOne, smellStringOne);
    test.shootArrow(1, Direction.SOUTH);
    test.shootArrow(1, Direction.SOUTH);
    String smellStringTwo = test.movePlayer(Direction.SOUTH);
    String smellResultTwo = "\n" +
            "\n" +
            "The player is currently in Cave 5 and has nothing in their treasure bag. \n" +
            "They can go WEST NORTH , there are 1 arrows remaining in their quiver, and there" +
            " is no treasure in this cave and no arrows in this cave.\n";
    assertEquals(smellResultTwo, smellStringTwo);
  }



}