package com.checkers.Decorator;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TransformDecorator extends Decorator{

    private AffineTransform transform;

    public TransformDecorator(IPiece iPiece, AffineTransform affineTransform) {
        super(iPiece);
        transform = affineTransform;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.transform(transform);
        iPiece.draw(graphics2D, x, y);
        graphics2D.setTransform(affineTransform);
    }
}
