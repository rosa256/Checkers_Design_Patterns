package com.checkers;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

public class Board {

    public Board(){}

    private AffineTransform tr;

    private HashMap<Point, IPiece> board = new HashMap<Point, IPiece>();

    public HashMap<Point, IPiece> getBoard() {
        return board;
    }


    public void loadPieces(){

        board.put(new Point(1, 0), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(3, 0), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(5, 0), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(7, 0), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(1, 2), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(3, 2), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(5, 2), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(7, 2), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(0, 1), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(4, 1), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(6, 1), new TransformDecorator(Piece.getPiece(1), tr));

        board.put(new Point(1, 6), new TransformDecorator(Piece.getPiece(1), tr));
        board.put(new Point(3, 6), new TransformDecorator(Piece.getPiece(2), tr));
        board.put(new Point(5, 6), new TransformDecorator(Piece.getPiece(3), tr));
        board.put(new Point(7, 6), new TransformDecorator(Piece.getPiece(4), tr));
        board.put(new Point(0, 5), new TransformDecorator(Piece.getPiece(5), tr));
        board.put(new Point(2, 5), new TransformDecorator(Piece.getPiece(6), tr));
        board.put(new Point(4, 5), new TransformDecorator(Piece.getPiece(8), tr));
        board.put(new Point(6, 5), new TransformDecorator(Piece.getPiece(8), tr));
        board.put(new Point(0, 7), new TransformDecorator(Piece.getPiece(8), tr));
        board.put(new Point(2, 7), new TransformDecorator(Piece.getPiece(8), tr));
        board.put(new Point(4, 7), new TransformDecorator(Piece.getPiece(8), tr));
        board.put(new Point(6, 7), new TransformDecorator(Piece.getPiece(8), tr));
    }
}
