package com.checkers.Decorator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Piece implements IPiece{

    private Image image;
    private ImageIcon imageIcon;
    private int index;
    private static Map<Integer,Piece> pool = new HashMap<>();
    private Point point;

    public void draw(Graphics2D g, int x, int y,Component component) { //moze Graphics ?
        imageIcon.paintIcon(component, g, x,  y);
        System.out.println("W piece draw "+x+" "+y);
    }

    @Override
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public Piece(int index) { //zmienic na private
        this.index = index;
        switch (index){
            case 1:
                image=new ImageIcon("src/com/checkers/pictures/white_pawn.png").getImage().
                        getScaledInstance(128,96,Image.SCALE_SMOOTH);;
                imageIcon=new ImageIcon(image);
                break;
            case 2:
                image=new ImageIcon("src/com/checkers/pictures/black_pawn.png").getImage()
                        .getScaledInstance(128,96,Image.SCALE_SMOOTH);;
                imageIcon=new ImageIcon(image);
                break;
            case 3:
                image=new ImageIcon("src/com/checkers/pictures/white_king.png").getImage()
                        .getScaledInstance(128,96,Image.SCALE_SMOOTH);;
                imageIcon=new ImageIcon(image);
                break;
            case 4:
                image=new ImageIcon("src/com/checkers/pictures/black_king.png").getImage()
                        .getScaledInstance(128,96,Image.SCALE_SMOOTH);;
                imageIcon=new ImageIcon(image);
                break;
        }
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
