package com.checkers.Decorator;

import javax.swing.*;
import java.awt.*;

public class Decorator implements IPiece{

    protected IPiece iPiece;

    @Override
    public void draw(Graphics2D graphics2D, int x, int y,Component component){
        iPiece.draw(graphics2D, x, y,component);
    }

    public Decorator(IPiece iPiece){
        this.iPiece = iPiece;
    }

    public IPiece getDecoratedPiece(int x, int y){
        return  iPiece;
    }

    @Override
    public ImageIcon getImageIcon() {
        return iPiece.getImageIcon();
    }
}
