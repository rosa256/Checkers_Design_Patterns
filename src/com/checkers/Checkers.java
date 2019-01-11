package com.checkers;

import com.checkers.Memento.Memento;

import java.awt.event.MouseEvent;
import java.util.List;

public class Checkers {

    private Game game;
    private Statistics statistics;
    private List<Memento> mementos;

    public Checkers(){
        Game game = Game.getInstance();
    }

    //TODO
    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    //TODO
    public Memento getMemento(){
        return null;
    }

    //TODO
    public void mouseReleased(MouseEvent event){

    }
}
