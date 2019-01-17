package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.data.CheckersBoard2;

import java.awt.*;

public class DeletePiece implements Command{

    private IPiece iPiece;
    private Point point;

    public DeletePiece(IPiece iPiece, Point point){
        this.iPiece = iPiece;
        this.point = point;
    }

    @Override
    public void undo(CheckersBoard2 checkersBoard2) {
        checkersBoard2.take((int)point.getX(), (int)point.getY());
        checkersBoard2.drop(iPiece, (int)point.getX(), (int)point.getY());
    }

    @Override
    public void redo(CheckersBoard2 checkersBoard2) {
        checkersBoard2.take((int)point.getX(), (int)point.getY());
        checkersBoard2.drop(iPiece, (int)point.getX(), (int)point.getY());
    }
}
