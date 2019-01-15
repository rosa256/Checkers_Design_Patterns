package com.checkers.Decorator;

import javax.swing.*;
import java.awt.*;

public interface IPiece {

    int WIDTH=128;
    int HEIGHT=96;

    void draw(Graphics2D graphics2D, int x, int y,Component component);

    IPiece getDecoratedPiece(int x, int y);
     ImageIcon getImageIcon();
}
