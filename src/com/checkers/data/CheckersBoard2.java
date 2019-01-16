package com.checkers.data;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;
import com.checkers.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

public class CheckersBoard2 extends JPanel{

    private static final int ZEROX = 23;
    private static final int ZEROY = 7;
    private int turn;
    private Game game;
    private int yfrom,xfrom, xto, yto;



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
    //transform.translate(ZEROX, ZEROY);
    transform.scale(128, 96);

    //Game.getInstance().loadPieces();
    board = Game.getInstance().getPieces();

    image = new ImageIcon("src/com/checkers/pictures/board.png").getImage()
            .getScaledInstance(1024,768,Image.SCALE_SMOOTH);
    setPreferredSize(new Dimension(1024, 768));

    this.addMouseListener(new MouseAdapter(){

        public void mousePressed(MouseEvent ev) {
            dragged = take((ev.getX())/Piece.WIDTH, (ev.getY())/Piece.HEIGHT);

            if(dragged!=null){
                xfrom=ev.getX()/Piece.WIDTH;
                yfrom=ev.getY()/Piece.HEIGHT;
                draggedAffineTransform = new AffineTransform();
                dragged = new TransformDecorator(dragged,draggedAffineTransform);
                mouse = ev.getPoint();
                System.out.println("Zlaaaapalem");
            }
        }
        public void mouseReleased(MouseEvent ev) {
            if (dragged != null) {
                xto=ev.getX()/Piece.WIDTH;
                yto=ev.getY()/Piece.HEIGHT;

                System.out.println();
                System.out.println("xFrom"+xfrom+" yfrom"+yfrom+" xT"+xto+" yT"+yto);

                int currentIndex = dragged.getPiece().getIndex();
                System.out.println("INDEX: "+currentIndex);


                if ((xfrom + 1 == xto || xfrom - 1 == xto) && (currentIndex == 0 || currentIndex == 6)) {
                    if (Game.getInstance().canMove(currentIndex, xfrom, yfrom, xto, yto, turn, currentIndex)) {
                        drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);
                        System.out.println("Ruch");
                        System.out.println("Ruch");
                        //changeTurn();
                    }
                }  else if ((xfrom + 2 == xto || xfrom - 2 == xto) && (currentIndex == 0 || currentIndex == 6)) {
                    if (Game.getInstance().canJump(currentIndex, xfrom, yfrom, xto, yto, turn)) {
                        drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);
                        int jumpRow = (xfrom + xto) / 2;
                        int jumpCol = (xfrom + yto) / 2;
                        Game.getInstance().getPieces().remove(new Point(jumpRow,jumpCol));
                        changeTurn();
                    }
                    repaint();
                }
            }
                dragged = null;
        }
    });
    this.addMouseMotionListener(new MouseMotionAdapter(){
        public void mouseDragged(MouseEvent ev)	{
            draggedAffineTransform.setToTranslation((int)(ev.getX() - CheckersBoard2.this.mouse.getX()), (int)(ev.getY() - CheckersBoard2.this.mouse.getY()));
            repaint();
        }
    });

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
