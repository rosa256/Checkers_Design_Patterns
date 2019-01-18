package com.checkers;

import com.checkers.Command.Command;
import com.checkers.Command.CommandManager;
import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Decorator.TransformDecorator;
import com.checkers.Layout.MenuFrame;
import com.checkers.Memento.Memento;
import com.checkers.Observer.IObserver;
import com.checkers.Observer.ISubject;
import com.checkers.Strategy.Strategy;
import com.checkers.data.CheckersBoard2;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.checkers.data.CheckersData.WHITE_KING;

public class Game implements Serializable, IObserver {

    //Zapytac sie czy wszystkie obiekty Player zamienią się na IObserver?
    //Zapytac czy Game zostanie zamieniony na ISubject

    private HashMap<Point, IPiece> pieces = new HashMap<>();
    private ArrayList<Player> playersInGame = new ArrayList<>();
    private Strategy strategy;
    private static Game instance = new Game();
    private CommandManager commandManager;
    private AffineTransform tr;
    private MenuFrame menuFrame;
    private LinkedList<Command> undoList = new LinkedList<>();
    private LinkedList<Command> redoList = new LinkedList<>();

    public CheckersBoard2 getBoard() {
        return board;
    }

    private CheckersBoard2 board;
    private int colPionkaDoBicia = 0;
    private int rowPionkaDoBicia = 0;

    private JLabel timeLabel_player1 = new JLabel();

    public Memento createMemento(){   //tworzenie pamiątki -  sava
        return new Memento(instance);
    }
    public Game setMemento(Memento memento){ //odczytanie stanu z pamiątki - z sava
        instance=memento.getGame();
        return instance;
    }

    public void setPieces(HashMap<Point, IPiece> pieces) {
        this.pieces = pieces;
    }

    private Game(){ }
    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }


    @Override
    public void update(Player player) {
        int index = playersInGame.indexOf(player);
        JLabel warnLabel = playersInGame.get(index).getWarnLabel();
            warnLabel.setVisible(true);
            if(playersInGame.get(index).getActual_time()<0) {
                playersInGame.get(index).getTimer().stop();
                MenuFrame.getInstance().getBoard().changeTurn();

                if (MenuFrame.getInstance().getBoard().getTurn() == 0 && MenuFrame.getInstance().getBoard().isGameOver() == -1) {
                    getPlayersInGame().get(0).getTurnLabel().setVisible(true);
                    getPlayersInGame().get(1).getTurnLabel().setVisible(false);

                    MenuFrame.getInstance().getTimeLabel_player2().setText(new SimpleDateFormat("mm : ss").format(new Date(15000)));
                    playersInGame.get(0).getTimer().start();
                    playersInGame.get(1).getTimer().stop();
                    playersInGame.get(1).refreshTimer();

                } else if (MenuFrame.getInstance().getBoard().getTurn() == 1 && MenuFrame.getInstance().getBoard().isGameOver() == -1) {
                    getPlayersInGame().get(1).getTurnLabel().setVisible(true);
                    getPlayersInGame().get(0).getTurnLabel().setVisible(false);
                    MenuFrame.getInstance().getTimeLabel_player1().setText(new SimpleDateFormat("mm : ss").format(new Date(15000)));
                    playersInGame.get(0).getTimer().stop();
                    playersInGame.get(1).getTimer().start();
                    playersInGame.get(0).refreshTimer();

                }
                    warnLabel.setVisible(false);
            }
    }

    public void loadPieces(){
        tr=new AffineTransform();
        tr.scale(Piece.WIDTH,Piece.HEIGHT);
//        startowe ustawienie
        pieces.put(new Point(3, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(5, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(1, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(5, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(0, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(6, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(3, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(1, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(4, 1), new TransformDecorator(Piece.getPiece(0), tr));

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



//        pieces.put(new Point(3, 2), new TransformDecorator(Piece.getPiece(6), tr));
//        pieces.put(new Point(1, 6), new TransformDecorator(Piece.getPiece(6), tr));
//        pieces.put(new Point(7, 6), new TransformDecorator(Piece.getPiece(6), tr));
//        pieces.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(0), tr));
//        pieces.put(new Point(2, 5), new TransformDecorator(Piece.getPiece(0), tr));
//        pieces.put(new Point(7, 0), new TransformDecorator(Piece.getPiece(0), tr));

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

        int jumpedChecker=-1;
        try {
            jumpedChecker = Game.getInstance().getPieces().get(new Point((fromCol + toCol) / 2, (fromRow + toRow) / 2)).getPiece().getIndex();
        }catch (NullPointerException e)
        {
            return false;
        }

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
    public boolean canKingMoveJump(int player, int colFrom, int rowFrom, int colTo, int rowTo, int turn) {
        int numberOfPoints;
        int colFromPom = colFrom;
        int rowFromPom = rowFrom;
        setColPionkaDoBicia(0);
        setRowPionkaDoBicia(0);
        if(pieces.containsKey(new Point(colTo, rowTo))){
            return false;
        }
        if(colFrom == colTo){
            return false;
        }
        if(rowFrom == rowTo){
            return false;
        }
        //damka czarna
        if (player == 10 && turn == 1 ) {
            ArrayList<Point> availablePoints = new ArrayList<Point>();
            availablePoints.clear();
            int pointsCounter = 0;

            //kierunek prawy dół i czarna damka
            if(colFrom < colTo && rowFrom < rowTo){
                numberOfPoints = colTo - colFrom;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom++;
                    rowFromPom++;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom++;
                    rowFrom++;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 6 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 10){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek lewy dół i czarna damka
            if(colFrom > colTo && rowFrom < rowTo){
                numberOfPoints = colFrom - colTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom--;
                    rowFromPom++;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom--;
                    rowFrom++;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 6 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 10){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek prawa góra i czarna damka
            if(colFrom < colTo && rowFrom > rowTo){
                numberOfPoints = rowFrom - rowTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom++;
                    rowFromPom--;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom++;
                    rowFrom--;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 6 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 10){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek lewa góra i czarna damka
            if(colFrom > colTo && rowFrom > rowTo){
                numberOfPoints = colFrom - colTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom--;
                    rowFromPom--;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom--;
                    rowFrom--;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 6 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 10){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            System.out.println("zwracam true ziom");
            return true;
        }


        //tu biale damki
        if (player == 4 && turn == 0 ) {
            ArrayList<Point> availablePoints = new ArrayList<Point>();
            availablePoints.clear();
            int pointsCounter = 0;

            //kierunek prawy dół i biała damka
            if(colFrom < colTo && rowFrom < rowTo){
                numberOfPoints = colTo - colFrom;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom++;
                    rowFromPom++;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom++;
                    rowFrom++;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 0 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 4){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek lewy dół i biała damka
            if(colFrom > colTo && rowFrom < rowTo){
                numberOfPoints = colFrom - colTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom--;
                    rowFromPom++;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom--;
                    rowFrom++;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 0 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 4){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek prawa góra i biała damka
            if(colFrom < colTo && rowFrom > rowTo){
                numberOfPoints = rowFrom - rowTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom++;
                    rowFromPom--;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom++;
                    rowFrom--;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 0 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 4){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom+", "+rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            //kierunek lewa góra i biała damka
            if(colFrom > colTo && rowFrom > rowTo){
                numberOfPoints = colFrom - colTo;
                for(int i = 0; i < numberOfPoints; i++){
                    colFromPom--;
                    rowFromPom--;
                    availablePoints.add(new Point(colFromPom, rowFromPom));
                }
                if(!availablePoints.contains((new Point(colTo, rowTo)))){
                    return false;
                }
                for(int i = 0; i < numberOfPoints; i++){
                    if(pointsCounter>1){
                        return false;
                    }
                    colFrom--;
                    rowFrom--;
                    if(pieces.containsKey(new Point(colFrom, rowFrom))){
                        if(pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 0 || pieces.get(new Point(colFrom, rowFrom)).getPiece().getIndex() == 4){
                            return false;
                        }else{
                            setColPionkaDoBicia(colFrom);
                            setRowPionkaDoBicia(rowFrom);
                            System.out.println("Pionek do bicia: "+ colFrom +", " + rowFrom);
                            pointsCounter++;
                        }
                    }
                }
            }

            System.out.println("zwracam true ziom");
            return true;
        }
        return false;
    }


    private JLabel timeLabel_player2 = new JLabel();

    public LinkedList<Command> getUndoList() {
        return undoList;
    }

    public LinkedList<Command> getRedoList() {
        return redoList;
    }

    public int getColPionkaDoBicia() {
        return colPionkaDoBicia;
    }

    public void setColPionkaDoBicia(int colPionkaDoBicia) {
        this.colPionkaDoBicia = colPionkaDoBicia;
    }

    public int getRowPionkaDoBicia() {
        return rowPionkaDoBicia;
    }

    public void setRowPionkaDoBicia(int rowPionkaDoBicia) {
        this.rowPionkaDoBicia = rowPionkaDoBicia;
    }

    public ArrayList<Player> getPlayersInGame(){
        return playersInGame;
    }

    public HashMap<Point, IPiece> getPieces() {
        return pieces;
    }

    public AffineTransform getTr() {
        return tr;
    }
}
