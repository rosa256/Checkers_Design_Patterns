package com.checkers.Decorator;

import java.awt.*;

public interface IPiece {

    int TILESIZE = 32;

    void draw(Graphics2D graphics2D, int x, int y);

    IPiece getDecoratedPiece(int x, int y);
}
