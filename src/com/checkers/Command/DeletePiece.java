package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;

import java.awt.*;

public class DeletePiece implements Command{

    private IPiece iPiece;
    private Point point;

    public DeletePiece(IPiece iPiece, Point point){
        this.iPiece = iPiece;
        this.point = point;
    }

    @Override
    public void undo() {
        //take((int)point.getX(), (int)point.getY());           //
        //drop(iPiece, (int)point.getX(), (int)point.getY());
    }

    @Override
    public void redo() {
        //take((int)point.getX(), (int)point.getY());
        //drop(iPiece, (int)point.getX(), (int)point.getY());
    }
}
