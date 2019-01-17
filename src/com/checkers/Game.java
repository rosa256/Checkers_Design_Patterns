package com.checkers;

import com.checkers.Command.CommandManager;
import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;
import com.checkers.Layout.MenuFrame;
import com.checkers.Observer.IObserver;
import com.checkers.Observer.ISubject;
import com.checkers.Strategy.Strategy;
import com.checkers.data.CheckersBoard2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Game implements java.io.Serializable, IObserver {

    //Zapytac sie czy wszystkie obiekty Player zamienią się na IObserver?
    //Zapytac czy Game zostanie zamieniony na ISubject

    private HashMap<Point, IPiece> pieces = new HashMap<>();
    private ArrayList<Player> playersInGame = new ArrayList<>();
    private Strategy strategy;
    private static Game instance = new Game();
    private CommandManager commandManager;
    private AffineTransform tr;
    private MenuFrame menuFrame;


    private Game(){
    }
    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public MenuFrame getMenuFrame(){
        return this.menuFrame;
    }

    @Override
    public void update(Player player) {
        int index = playersInGame.indexOf(player);
        System.out.println(playersInGame.get(index).getNickName()+" pozostalo 5 sekund! Pospiesz sie!");
        //playersInGame.get(index).getTimer();
    }

    public ArrayList<Player> getPlayersInGame(){
        return playersInGame;
    }

    public HashMap<Point, IPiece> getPieces() {
        return pieces;
    }

    public void loadPieces(){
        tr=new AffineTransform();
        tr.scale(Piece.WIDTH,Piece.HEIGHT);

        pieces.put(new Point(3, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(5, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(1, 2), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(5, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(0, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(4, 1), new TransformDecorator(Piece.getPiece(0), tr));
        //pieces.put(new Point(6, 1), new TransformDecorator(Piece.getPiece(0), tr));

        pieces.put(new Point(1, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(3, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(5, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(7, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(0, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(2, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(4, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(6, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(0, 7), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(2, 7), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(4, 7), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(6, 7), new TransformDecorator(Piece.getPiece(6), tr));
    }

        public boolean canMove(int player, int fromCol, int fromRow, int toCol, int toRow, int turn) {
            if (Game.getInstance().getPieces().containsKey(new Point(toCol,toRow)) == true) { //!Uwaga zamian toCol, na toRow miejscami!!! tylko tu
                return false;
            }
            boolean nextColumn = toCol == (fromCol + 1) || toCol == (fromCol - 1);



            return (player == 0 && turn== 0) && (player == 0 && toRow == fromRow + 1 && nextColumn)
                    || (player == 6 && turn == 1 ) && (player== 6 && toRow == fromRow - 1 && nextColumn);
        }

    public boolean canJump(int player, int fromRow, int fromCol, int toRow, int toCol, int turn) {
        System.out.println("fromX"+fromRow);
        System.out.println("fromY"+fromCol);
        System.out.println("ToX"+toRow);
        System.out.println("ToY"+toCol);
        System.out.println("Bity-pozycja:"+(fromCol + toCol) / 2+(fromRow + toRow) / 2);
        int jumpedChecker=-1;
        try {
        jumpedChecker = Game.getInstance().getPieces().get(new Point((fromCol + toCol) / 2, (fromRow + toRow) / 2)).getPiece().getIndex();
        }catch (NullPointerException e)
        {
            return false;
        }
        System.out.println("Bity-pozycja:"+(fromCol + toCol) / 2+(fromRow + toRow) / 2);

        boolean correctColumn = toCol == (fromCol + 2) || toCol == (fromCol - 2);
        boolean correctRowWhite = toRow == fromRow + 2;
        boolean correctRowBlack = toRow == fromRow - 2;
        if (Game.getInstance().getPieces().containsKey(new Point(toCol,toRow)) == true) {
            return false;
        }
        if (player == 0 && turn == 0) {
            return correctRowWhite && correctColumn && ((jumpedChecker == 6) || (jumpedChecker == 10));
        } else if (player == 6 && turn == 1) {
            return correctRowBlack && correctColumn && ((jumpedChecker == 0) || (jumpedChecker == 4));
        }
        return false;
    }

}
