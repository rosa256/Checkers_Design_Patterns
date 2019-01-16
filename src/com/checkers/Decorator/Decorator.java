package com.checkers.Decorator;

import java.awt.*;

public class Decorator implements IPiece{
    protected  IPiece block;

    public Decorator(IPiece block){
        this.block=block;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        block.draw(graphics2D,x,y);
    }

    @Override
    public IPiece getPiece() {
        return block;
    }
}
