package com.checkers;

import com.checkers.Command.CommandManager;
import com.checkers.Layout.MenuFrame;
import com.checkers.Strategy.Strategy;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> playersInGame;
    private Strategy strategy;
    private static Game instance;
    private CommandManager commandManager;
    private MenuFrame menuFrame;

    private Game(){
        playersInGame = Arrays.asList(new Player[]{new Player(), new Player()});
        board=new Board();
    }
    public static Game getInstance(){
        if(instance == null)
            instance=new Game();
        return instance;
    }


    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayersInGame() {
        return playersInGame;
    }

    public MenuFrame getMenuFrame(){
        return this.menuFrame;
    }


}
