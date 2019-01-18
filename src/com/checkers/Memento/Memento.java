package com.checkers.Memento;

import com.checkers.Decorator.IPiece;
import com.checkers.Game;
import com.checkers.Layout.GameFrame;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Memento {

    private Game game;

    public Memento(){}
    public Memento(Game game) {

        this.game=game;
    }

   /* public String getState() {
        return state;
    }*/

    public Game getGame() {
        return game;
    }
/* public void setState(String state) {
        this.state = state;
    }*/
}
