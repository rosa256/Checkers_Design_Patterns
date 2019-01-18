package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.data.CheckersBoard2;

import java.awt.*;
import java.io.Serializable;

public class Move implements Command, Serializable {

    private IPiece iPiece;
    private Point from;
    private Point to;

    public Move(IPiece iPiece, Point from, Point to) {
        this.iPiece = iPiece;
        this.from = from;
        this.to = to;
    }

    public void undo(CheckersBoard2 checkersBoard2) {
        checkersBoard2.take((int)to.getX(), (int)to.getY());
        checkersBoard2.drop(iPiece, (int)from.getX(), (int)from.getY());
    }

    public void redo(CheckersBoard2 checkersBoard2) {
        checkersBoard2.take((int)from.getX(), (int)from.getY());
        checkersBoard2.drop(iPiece,(int)to.getX(), (int)to.getY());
    }
}
