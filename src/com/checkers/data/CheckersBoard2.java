package com.checkers.data;

import com.checkers.Command.Command;
import com.checkers.Command.CommandMakro;
import com.checkers.Command.DeletePiece;
import com.checkers.Command.Move;
import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;
import com.checkers.Game;
import com.checkers.Layout.MenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CheckersBoard2 extends JPanel{

    private int turn=0;
    private Game game;
    private int selectedColFrom, selectedRowFrom, selectedRowTo, selectedColTo;
    JButton undo,redo;

    private HashMap<Point, IPiece> board;

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


    public CheckersBoard2( ){
        AffineTransform transform = new AffineTransform();
        //transform.translate(ZEROX, ZEROY);
        transform.scale(128, 96);
        this.undo = Game.getInstance().getUndo();
        this.redo = Game.getInstance().getRedo();

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


                    int currentIndex = dragged.getPiece().getIndex();

                    boolean someAction=false;

                    if (Game.getInstance().canMove(currentIndex,  selectedColFrom,selectedRowFrom,selectedColTo , selectedRowTo, turn)) {
                        if ((selectedRowFrom + 1 == selectedRowTo || selectedRowFrom - 1 == selectedRowTo) && (currentIndex == 0 || currentIndex == 6)) {

                            someAction=true;
                            Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX()/Piece.WIDTH, ev.getY()/Piece.HEIGHT)));

                            //Damka Czarna
                            if (selectedRowTo == 0 ) {
                                Game.getInstance().getPieces().put(new Point((ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT), new TransformDecorator(Piece
                                        .getPiece(10), Game.getInstance().getTr()));
                                Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX() / Piece.WIDTH, ev.getY() / Piece.HEIGHT)));
                            }else if(selectedRowTo == 7){
                                Game.getInstance().getPieces().put(new Point((ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT), new TransformDecorator(Piece
                                        .getPiece(4), Game.getInstance().getTr()));
                                Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX() / Piece.WIDTH, ev.getY() / Piece.HEIGHT)));
                            }
                            else
                                drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);

                        }
                        changeTurn();
                    }  else if ((selectedRowFrom + 2 == selectedRowTo || selectedRowFrom - 2 == selectedRowTo) && (currentIndex == 0 || currentIndex == 6)) {
                        if (Game.getInstance().canJump(currentIndex, selectedRowFrom, selectedColFrom, selectedRowTo, selectedColTo, turn)) {
                            if (selectedRowTo == 0 ) {
                                Game.getInstance().getPieces().put(new Point((ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT), new TransformDecorator(Piece
                                        .getPiece(10), Game.getInstance().getTr()));
                                Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX() / Piece.WIDTH, ev.getY() / Piece.HEIGHT)));
                            }else if(selectedRowTo == 7){
                                Game.getInstance().getPieces().put(new Point((ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT), new TransformDecorator(Piece
                                        .getPiece(4), Game.getInstance().getTr()));
                                Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX() / Piece.WIDTH, ev.getY() / Piece.HEIGHT)));
                            }else {
                                drop(dragged.getPiece(), (ev.getX()) / Piece.WIDTH, (ev.getY()) / Piece.HEIGHT);
                            }
                            int jumpRow = (selectedRowFrom + selectedRowTo) / 2;
                            int jumpCol = (selectedColFrom + selectedColTo) / 2;
                            Point zbity = new Point(jumpCol,jumpRow);
                            someAction=true;
                            changeTurn();
                            DeletePiece deletePiece = new DeletePiece(board.get(zbity), zbity);
                            Move move = new Move(dragged.getPiece(), savedPoint, new Point(ev.getX()/Piece.WIDTH, ev.getY()/Piece.HEIGHT));
                            Game.getInstance().getUndoList().push(new CommandMakro(deletePiece, move));
                            Game.getInstance().getPieces().remove(new Point(jumpCol,jumpRow));
                        }
                    }else if((currentIndex == 4 || currentIndex == 10)&&(Game.getInstance().canKingMoveJump(currentIndex, selectedColFrom, selectedRowFrom, selectedColTo, selectedRowTo, turn))){
                            drop(dragged.getPiece(), ev.getX()/Piece.WIDTH, ev.getY()/Piece.HEIGHT);
                            if(Game.getInstance().getColPionkaDoBicia() != 0 && Game.getInstance().getRowPionkaDoBicia() != 0){
                                Point doBicia = new Point(Game.getInstance().getColPionkaDoBicia(), Game.getInstance().getRowPionkaDoBicia());
                                DeletePiece deletePiece = new DeletePiece(board.get(doBicia), doBicia);
                                Move move = new Move(dragged.getPiece(), savedPoint, new Point(ev.getX()/Piece.WIDTH, ev.getY()/Piece.HEIGHT));
                                Game.getInstance().getUndoList().push(new CommandMakro(deletePiece, move));
                                Game.getInstance().getPieces().remove(new Point(Game.getInstance().getColPionkaDoBicia(),Game.getInstance().getRowPionkaDoBicia()));
                                someAction=true;
                            }else{
                                someAction = true;
                                Game.getInstance().getUndoList().push(new Move(dragged.getPiece(), savedPoint, new Point(ev.getX()/Piece.WIDTH, ev.getY()/Piece.HEIGHT)));
                            }
                            changeTurn();
                    }

                    if(!someAction)
                        drop(dragged.getPiece(),savedPoint.x,savedPoint.y);

                    dragged = null;
                    repaint();
                    Game.getInstance().getRedoList().clear();
                    redo.setEnabled(false);
                    undo.setEnabled(true);
                }
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("JEEEJ DZIALAM UNDO");
                Command command = Game.getInstance().getUndoList().pop();
                Game.getInstance().getRedoList().push(command);
                command.undo(CheckersBoard2.this);
                if(Game.getInstance().getUndoList().isEmpty())
                    undo.setEnabled(false);
                redo.setEnabled(true);
                changeTurn();
            }
        });

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("JEEEJ DZIALAM REDO");
                Command command = Game.getInstance().getRedoList().pop();
                Game.getInstance().getUndoList().push(command);
                command.redo(CheckersBoard2.this);
                if(Game.getInstance().getRedoList().isEmpty())
                    redo.setEnabled(false);
                undo.setEnabled(true);
                changeTurn();
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
        return isOver();
    }

    private void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else if (turn == 1) {
            turn = 0;
        }
    }

    public int isOver() {
        int white=0;
        int black=0;
        for (Map.Entry<Point, IPiece> row : board.entrySet()) {
            if(row.getValue().getPiece().getIndex()==0 || row.getValue().getPiece().getIndex()== 4)
                white++;
            else if(row.getValue().getPiece().getIndex()==6 || row.getValue().getPiece().getIndex()== 10)
                black++;
        }
        if(white == 0)
            return 1;
        else if (black == 0)
            return 0;
        else
            return -1;
    }

}
