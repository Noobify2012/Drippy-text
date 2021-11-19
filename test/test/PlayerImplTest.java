package test;

import org.junit.Before;
import org.junit.Test;
import model.Cave;
import model.Direction;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;
import static org.junit.Assert.assertEquals;


/**
 * Testing the functionality of the player. 
 */
public class PlayerImplTest {

  @Before
  public void setup() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4, 3, 0 , 50, player,
            1, 1 );
    String dungeonString = test.getDungeon();
  }


  @Test
  public void getTreasureList() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player, 1, 0);
    assertEquals(0, player.getTreasureList().size());
  }

  @Test
  public void getPlayerStatus() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4, 3, 0, 50, player,
            1, 1);
    test.getDungeon();
    Cave[][] testdungeon = test.getGameBoard();
    String testString = "The player is currently in Cave 3 and has nothing in their treasure " +
            "bag. \n" +
            "They can go SOUTH NORTH , there are 3 arrows remaining in their quiver, and there is" +
            " no treasure in this cave and an arrow in this cave.\n";
    assertEquals(testString, player.getPlayerStatus(0, testdungeon[1][0]));
  }

  @Test
  public void isPlayerAlive() {
    Player testPlayer = new PlayerImpl();
    assertEquals(true, testPlayer.isPlayerAlive());
  }

  @Test
  public void getPlayerLocation() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4, 3, 0, 50, player,
            1, 1);
    test.getDungeon();
    assertEquals(3, player.getPlayerLocation());
  }

  @Test
  public void monsterEncounter() {
    String monsterHealthTwoString = "Chomp! Our player was eaten by a monster.";
    String monsterHealthOneZeroString = "Whew! Our player barely escapes being eaten by a monster.";
    String monsterHealthZeroString = "Our player finds the body of a slain monster.";
    Player player = new PlayerImpl();
    String testString1 = player.monsterEncounter(2,0);
    String monsterHealthOneRandOneString = player.monsterEncounter(1, 1);
    String monsterHealthOneRandZeroString = player.monsterEncounter(1, 0);
    String monsterHealthZero = player.monsterEncounter(0, 1);
    assertEquals(monsterHealthTwoString, testString1);
    assertEquals(monsterHealthTwoString, monsterHealthOneRandOneString);
    assertEquals(monsterHealthOneZeroString, monsterHealthOneRandZeroString);
    assertEquals(monsterHealthZeroString, monsterHealthZero);
  }

  @Test
  public void shoot() {
    String testString = "\nThe player has 2 arrows remaining.";
    String testStringTwo = "\nThe player has 1 arrows remaining.";
    String testStringThree = "\nThe player has 0 arrows remaining.";
    Player player = new PlayerImpl();
    String returnString = player.shoot(1,Direction.EAST);
    String returnStringTwo = player.shoot(1,Direction.EAST);
    String returnStringThree = player.shoot(1,Direction.EAST);
    assertEquals(testString, returnString);
    assertEquals(testStringTwo, returnStringTwo);
    assertEquals(testStringThree, returnStringThree);
  }

  @Test
  public void pickUp() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 4, 3, 0, 50, player,
            1, 1);
    test.getDungeon();
    Cave[][] testdungeon = test.getGameBoard();
    String testArrow = player.pickUp(testdungeon[1][0], 1);
    String answerString = "\nThe player picked up an arrow.";
    assertEquals(answerString, testArrow);
    String testBoth = player.pickUp(testdungeon[3][1],2);
    String answerStringBoth = "The player picked up  0 rubies, 3 diamonds, 2 sapphires.\n" +
            "The player picked up an arrow.";
    assertEquals(answerStringBoth, testBoth);
    String testTreas = player.pickUp(testdungeon[2][0],0);
    String pickUpTreas = "The player picked up  0 rubies, 0 diamonds, 1 sapphires.";
    assertEquals(pickUpTreas, testTreas);
  }
}