package com.checkers.Command;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;

import java.awt.*;

public class Move implements Command{

    private IPiece iPiece;
    private Point from;
    private Point to;

    public Move(IPiece iPiece, Point from, Point to) {
        this.iPiece = iPiece;
        this.from = from;
        this.to = to;
    }

    //TODO
    public void undo() {
        //take((int)to.getX(), (int)to.getY());                 //zabranie pionka z pozycji na którą się ruszył
        //drop(iPiece, (int)from.getX(), (int)from.getY());     //położenie pionka na pozycję z której rozpoczynał ruch
    }

    //TODO
    public void redo() {
        //take((int)from.getX(), (int)from.getY());
        //drop(iPiece,(int)to.getX(), (int)to.getY());
    }
}
