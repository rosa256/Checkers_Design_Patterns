package com.checkers;

import com.checkers.Decorator.IPiece;

import java.awt.*;
import java.util.HashMap;

public class Board {

    private HashMap<Point, IPiece> board = new HashMap<Point, IPiece>();

    public HashMap<Point, IPiece> getBoard() {
        return board;
    }
}
