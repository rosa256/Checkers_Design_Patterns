package com.checkers.Decorator;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class TransformDecorator extends Decorator implements Serializable {

    private AffineTransform transform;

    public TransformDecorator(IPiece block,AffineTransform affineTransform) {
        super(block);
        this.transform = affineTransform;
    }

    @Override
    public void draw(Graphics2D g, int X, int Y) {
        AffineTransform tr = g.getTransform();
        g.transform(transform);
        block.draw(g,X,Y);
        g.setTransform(tr);
    }
}
