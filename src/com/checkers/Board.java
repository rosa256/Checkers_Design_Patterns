package com.checkers;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

public class Board {

    private HashMap<Point, IPiece> board = new HashMap<Point, IPiece>();
    private Image boardImage;
    private ImageIcon board_image_icon;
    private AffineTransform tr;

    public Board() {
        boardImage=new ImageIcon("src/com/checkers/pictures/board.png").getImage()
                .getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        board_image_icon = new ImageIcon(boardImage);
        tr=new AffineTransform();
        tr.scale(Piece.WIDTH,Piece.HEIGHT);
    }

    public ImageIcon getBoard_image_ico() {
        return board_image_icon;
    }

    public void loadPieces(){

        board.put(new Point(1, 0), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(3, 0), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(5, 0), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(7, 0), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(1, 2), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(3, 2), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(5, 2), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(7, 2), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(0, 1), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(4, 1), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(6, 1), new TransformDecorator(Piece.getPiece(2), tr));

        board.put(new Point(1, 6), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(3, 6), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(5, 6), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(7, 6), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(0, 5), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(2, 5), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(4, 5), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(6, 5), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(0, 7), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(2, 7), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(4, 7), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(6, 7), new TransformDecorator(Piece.getPiece(1), tr));
    }

    public HashMap<Point, IPiece> getBoard() {
        return board;
    }

    /*public void setPiece(int x,int y){
        board.put(new Point(x,y),new TransformDecorator(Piece.getPiece(index),tr));
    }*/
}
