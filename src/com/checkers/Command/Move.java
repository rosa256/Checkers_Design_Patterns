package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.data.CheckersBoard;

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

    public void undo(CheckersBoard checkersBoard) {
        checkersBoard.take((int)to.getX(), (int)to.getY());
        checkersBoard.drop(iPiece, (int)from.getX(), (int)from.getY());
    }

    public void redo(CheckersBoard checkersBoard) {
        checkersBoard.take((int)from.getX(), (int)from.getY());
        checkersBoard.drop(iPiece,(int)to.getX(), (int)to.getY());
    }
}
