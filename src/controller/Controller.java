package controller;

import model.Dungeon;

public interface Controller {

  void buildDungeon();


  void playGame(Dungeon d);

  void outHelper(String printString);

  //give the model to the controller with playGame method

  //run the game

}
