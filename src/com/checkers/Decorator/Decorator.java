package com.checkers.Decorator;

import java.awt.*;

public class Decorator implements IPiece{

    protected IPiece iPiece;

    @Override
    public void draw(Graphics2D graphics2D, int x, int y){
        iPiece.draw(graphics2D, x, y);
    }

    public Decorator(IPiece iPiece){
        this.iPiece = iPiece;
    }

    public IPiece getDecoratedPiece(int x, int y){
        return  iPiece;
    }
}
