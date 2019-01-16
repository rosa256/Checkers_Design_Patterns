package com.checkers.data;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

public class CheckersBoard2 extends JPanel{

    private static final int ZEROX = 23;
    private static final int ZEROY = 7;
    private int turn;

    private HashMap<Point, IPiece> board = new HashMap<Point, IPiece>();
    public void drop(IPiece dragged2, int x, int y)	{
        repaint();
        board.put(new Point(x, y), dragged2);
    }
    private IPiece take(int x, int y)	{
        repaint();
        orginalPoint.x=x;
        orginalPoint.y=y;
        return board.remove(new Point(x, y));
    }

    int x,y;
    private Image image;
    private IPiece dragged = null;
    private AffineTransform draggedAffineTransform = null;
    private Point orginalPoint = new Point(0,0);
    private Point mouse = null;

    public void paint(Graphics g)	{

        g.drawImage(image, 0, 0, null);
        for(Map.Entry<Point, IPiece> e : board.entrySet()) {
            Point pt = e.getKey();
            x=(int)pt.getX();
            y=(int)pt.getY();
            IPiece pc = e.getValue();
            pc.draw((Graphics2D)g,x,y);
        }
        if(mouse != null && dragged != null) {
            dragged.draw((Graphics2D)g, orginalPoint.x, orginalPoint.y);
        }
    }


public CheckersBoard2(){
    AffineTransform transform = new AffineTransform();
    transform.translate(ZEROX, ZEROY);
    transform.scale(Piece.TILESIZE, Piece.TILESIZE);

    board.put(new Point(1,1), new TransformDecorator(Piece.getPiece(8),transform));
    board.put(new Point(2,2), new TransformDecorator(Piece.getPiece(3),transform));


    image = new ImageIcon("src/com/checkers/pictures/board.png").getImage();
    setPreferredSize(new Dimension(1000, 1000));
    }

    public int getTurn() {
        return turn;
    }

    public int isGameOver() {
        return 0/*board.isOver()*/;
    }

    private void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else if (turn == 1) {
            turn = 0;
        }
    }
}
