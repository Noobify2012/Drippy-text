package test;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ConsoleController;
import controller.Controller;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

import static org.junit.Assert.assertEquals;

/**
 * The testing for the controller. In this class we not only test the controller's functionality
 * but also mock the model.
 */
public class ControllerTest {


  @Test
  public void playGame() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("m n m e m e m s m w");
    Player player = new PlayerImpl();
    Dungeon dungeon = new DungeonImpl(false, 4, 3, 0 , 50,
            player, 1, 1);
    dungeon.getDungeon();
    Controller control = new ConsoleController(in, out);
    control.playGame((Dungeon) dungeon);
    String shortestPath = "Would you like to move, shoot, or pickup?\n"
            + "which direction?\n" +
            "\n" +
            "\n" +
            "The player is currently in a Tunnel and has nothing in their treasure bag. \n"
            + "They can go EAST SOUTH , there are 3 arrows remaining in their quiver, and there is "
            + "no treasure in this cave and an arrow in this cave.\n"
            + "\n" + "Would you like to move, shoot, or pickup?\n" + "which direction?\n" + "\n"
            + "\n" +
            "The player is currently in a Tunnel and has nothing in their treasure bag. \n"
            + "They can go WEST EAST , there are 3 arrows remaining in their quiver, and there " +
            "is no treasure in this cave and an arrow in this cave.\n" +
            "\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "\n" +
            "\n" +
            "The player is currently in a Tunnel and has nothing in their treasure bag. \n" +
            "They can go SOUTH WEST , there are 3 arrows remaining in their quiver, and there " +
            "is no treasure in this cave and no arrows in this cave.\n" +
            "\n" +
            "The player smells something faint but awful.\n" +
            "\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "\n" +
            "\n" +
            "The player is currently in a Tunnel and has nothing in their treasure bag. \n" +
            "They can go WEST NORTH , there are 3 arrows remaining in their quiver, and there " +
            "is no treasure in this cave and no arrows in this cave.\n" +
            "\n" +
            "The player smells something awful and strong.\n" +
            "\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "\n" +
            "Player has reached final cave\n" +
            "\n" +
            "Chomp! Our player was eaten by a Monster.\n\n";
    assertEquals(shortestPath , out.toString());
  }

  @Test
  public void playMock() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("p a s n 1 m n m e m e m s m w");
    Player player = new PlayerImpl();
    StringBuilder log = new StringBuilder();
    Dungeon dungeon = new MockModel(log, 123);
    dungeon.getDungeon();
    assertEquals("Got the dungeon\n", log.toString());
    Controller control = new ConsoleController(in, out);
    control.playGame((Dungeon) dungeon);
    String shortestPath = "Got the dungeon\n" +
            "Input: pickup 1\n" +
            "Input: shoot 1 NORTH\n" +
            "Input: move NORTH\n" +
            "Input: move EAST\n" +
            "Input: move EAST\n" +
            "Input: move SOUTH\n" +
            "Input: move WEST\n";
    assertEquals(shortestPath , log.toString());
    String outString = "Would you like to move, shoot, or pickup?\n" +
            "treasure, arrows, or both?\n" +
            "pickUpItem\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "how far?\n" +
            "shootArrow\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "movePlayer\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "movePlayer\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "movePlayer\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "movePlayer\n" +
            "Would you like to move, shoot, or pickup?\n" +
            "which direction?\n" +
            "movePlayer\n";
    assertEquals(outString, out.toString());
  }
}