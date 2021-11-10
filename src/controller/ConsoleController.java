package controller;

import java.util.Scanner;

import model.Dungeon;

public class ConsoleController implements Controller {

  private final Appendable out;
  private final Scanner scan;

  public ConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(Dungeon d) {
    //build parameter loops here



    while (!d.isGameOver()) {
      //implement how to get the game
    }


  }
}
