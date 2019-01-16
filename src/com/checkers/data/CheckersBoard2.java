package com.checkers.data;

import com.checkers.Command.Command;
import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;
import com.checkers.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CheckersBoard2 extends JPanel{

    private static final int ZEROX = 23;
    private static final int ZEROY = 7;
    private int turn;
    private Game game;
    private int selectedColFrom, selectedRowFrom, selectedRowTo, selectedColTo;



    private HashMap<Point, IPiece> board = new HashMap<Point, IPiece>();

    public void drop(IPiece dragged2, int x, int y)	{
        repaint();
        board.put(new Point(x, y), dragged2);
    }
    public IPiece take(int x, int y)	{
        repaint();
        orginalPoint.x=x;
        orginalPoint.y=y;
        return board.remove(new Point(x, y));
    }

    int x,y;
    private Image image;
    private IPiece dragged = null;
    private Point savedPoint= null;
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
                selectedRowFrom =ev.getY()/Piece.HEIGHT;
                selectedColFrom =ev.getX()/Piece.WIDTH;
                savedPoint = new Point((ev.getX())/Piece.WIDTH, (ev.getY())/Piece.HEIGHT);
                draggedAffineTransform = new AffineTransform();
                dragged = new TransformDecorator(dragged,draggedAffineTransform);
                mouse = ev.getPoint();
            }
        }
        public void mouseReleased(MouseEvent ev) {
            if (dragged != null) {
                selectedColTo =ev.getX()/Piece.WIDTH;
                selectedRowTo =ev.getY()/Piece.HEIGHT;

                System.out.println();
                System.out.println("xFrom"+ selectedRowFrom +" selectedColFrom"+ selectedColFrom +" xT"+ selectedRowTo +" yT"+ selectedColTo);

                int currentIndex = dragged.getPiece().getIndex();
                System.out.println("INDEX: "+currentIndex);

                boolean someAction=false;

                if (Game.getInstance().canMove(currentIndex,  selectedColFrom,selectedRowFrom,selectedColTo , selectedRowTo, turn, currentIndex)) {
                if ((selectedRowFrom + 1 == selectedRowTo || selectedRowFrom - 1 == selectedRowTo) && (currentIndex == 0 || currentIndex == 6)) {
                        drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);
                        someAction=true;

                        //changeTurn();
                    }
                }  else if ((selectedRowFrom + 2 == selectedRowTo || selectedRowFrom - 2 == selectedRowTo) && (currentIndex == 0 || currentIndex == 6)) {
                    if (Game.getInstance().canJump(currentIndex, selectedRowFrom, selectedColFrom, selectedRowTo, selectedColTo, turn)) {
                        drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);
                        int jumpRow = (selectedRowFrom + selectedRowTo) / 2;
                        int jumpCol = (selectedColFrom + selectedColTo) / 2;
                        Game.getInstance().getPieces().remove(new Point(jumpCol,jumpRow));
                        someAction=true;
                        //changeTurn();
                    }
                }

                if(!someAction)
                    drop(dragged.getPiece(),savedPoint.x,savedPoint.y);
                dragged = null;
                System.out.println("Size:"+Game.getInstance().getPieces().size());
                repaint();
            }
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
