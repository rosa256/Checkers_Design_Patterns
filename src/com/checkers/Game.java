package com.checkers;

import com.checkers.Command.CommandManager;
import com.checkers.Layout.MenuFrame;
import com.checkers.Observer.IObserver;
import com.checkers.Observer.ISubject;
import com.checkers.Strategy.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements java.io.Serializable, IObserver {

    //Zapytac sie czy wszystkie obiekty Player zamienią się na IObserver?
    //Zapytac czy Game zostanie zamieniony na ISubject

    private Board board;
    private List<Player> playersInGame = new ArrayList<>();
    private Strategy strategy;
    private static Game instance = null;
    private CommandManager commandManager;
    private MenuFrame menuFrame;

    private Game(){
        if(menuFrame==null) {
            menuFrame = new MenuFrame(playersInGame);
        }
    }
    private Game(Player player){
        playersInGame.add(player);
    }
    public static Game getInstance(){
        if(instance == null)
            return new Game();
        return instance;
    }

    public MenuFrame getMenuFrame(){
        return this.menuFrame;
    }

    @Override
    public void update(Player player) {
        int index = playersInGame.indexOf(player);
        playersInGame.get(index).getTimer();
    }
}
