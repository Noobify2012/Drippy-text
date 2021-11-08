package test;

import org.junit.Test;
import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerImplTest {


  @Test
  public void getTreasureList() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,20, player);
    assertEquals(0, player.getTreasureList().size());
  }

  @Test
  public void getTreasureListAfterRun() {
    Player player = new PlayerImpl();
    Dungeon test = new DungeonImpl(false, 5,5,0,100, player);
    test.getDungeon();
    assertTrue(player.getTreasureList().size() > 1);
  }
}