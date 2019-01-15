package com.checkers;

import com.checkers.Command.Command;
import com.checkers.Command.CommandManager;
import com.checkers.Layout.MenuFrame;
import com.checkers.Strategy.Strategy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game implements java.io.Serializable{

    private LinkedList<Command> undoList = new LinkedList<>();
    private LinkedList<Command> redoList = new LinkedList<>();
    // to bedzie potrzebne do commanda chyba, o ile poruszanie sie bedzie sie odbywalo w tej klasie
    // przy ruchu trzeba bedzie wrzucac na ta liste wlasnie nasze ruchy
    private Board board;
    private List<Player> playersInGame;
    private Strategy strategy;
    private static Game instance = null;
    private CommandManager commandManager;
    private MenuFrame menuFrame;

    private Game(){
        playersInGame = Arrays.asList(new Player[]{new Player(), new Player()});
        menuFrame = new MenuFrame(playersInGame);
    }
    public static Game getInstance(){
        if(instance == null)
            return new Game();
        return instance;
    }

    public MenuFrame getMenuFrame(){
        return this.menuFrame;
    }

}
