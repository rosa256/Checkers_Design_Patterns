package com.checkers.Decorator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Piece implements IPiece{

    public static final int TILESIZE = 32;
    private static Image image = new ImageIcon("pieces4.png").getImage();

    private Integer index;
    private Piece(Integer idx){
        this.index = idx;
    }

    private static final HashMap<Integer, Piece> piecesByIdx = new HashMap<Integer, Piece>();

    public static Piece getPiece(Integer index){
        Piece piece = piecesByIdx.get(index);
        if(piece == null) {
            piece = new Piece(index);
            piecesByIdx.put(index, new Piece(index));
        }
        return piece;
    }

    public void draw(Graphics2D g, int X, int Y) {
        g.drawImage(image, X, Y, X+1, Y+1,
                index*TILESIZE, 0, (index+1)*TILESIZE, TILESIZE, null);   // tu bedzie przekazywany stan zewnetrzny
    }

    @Override
    public IPiece getPiece() {
        return null;
    }
}
