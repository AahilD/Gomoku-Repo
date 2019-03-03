package controller;

import broker.Game;

public abstract class GameController
{

    // TODO @Aahil make private variable private
    // TODO @Aahil remove roundCount (controllers will keep track of this)
    static Game game;
    static int roundCount;
    static int turnCount;
    private int roundCount;
}
