package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.data.CheckersBoard;

import java.awt.*;

public class DeletePiece implements Command{

    private IPiece iPiece;
    private Point point;

    public DeletePiece(IPiece iPiece, Point point){
        this.iPiece = iPiece;
        this.point = point;
    }

    @Override
    public void undo(CheckersBoard checkersBoard) {
        checkersBoard.take((int)point.getX(), (int)point.getY());
        checkersBoard.drop(iPiece, (int)point.getX(), (int)point.getY());
    }

    @Override
    public void redo(CheckersBoard checkersBoard) {
        checkersBoard.take((int)point.getX(), (int)point.getY());
        //checkersBoard.drop(iPiece, (int)point.getX(), (int)point.getY());
    }
}
