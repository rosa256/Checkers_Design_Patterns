package com.checkers.Decorator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Piece implements IPiece{

    private static final Image image = new ImageIcon("pieces4.png").getImage();
    private int index;
    private static Map<Integer,Piece> pool = new HashMap<>();

    public void draw(Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, x + 1, y + 1, index * TILESIZE, 0, (index + 1) * TILESIZE, TILESIZE, null);
    }

    private Piece(int index) {
        this.index = index;
    }

    @Override
    public IPiece getDecoratedPiece(int x, int y) {
        return null;
    }

    public static Piece getPiece(Integer index){
        if(!pool.containsKey(index))
            pool.put(index, new Piece(index));
        return pool.get(index);
    }
}
