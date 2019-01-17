package com.checkers;

import com.checkers.Command.Command;
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
import java.util.*;
import java.util.List;

import static com.checkers.data.CheckersData.WHITE_KING;

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
    private LinkedList<Command> undoList = new LinkedList<>();
    private LinkedList<Command> redoList = new LinkedList<>();


    private Game(){
    }
    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }

    public LinkedList<Command> getUndoList() {
        return undoList;
    }

    public LinkedList<Command> getRedoList() {
        return redoList;
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
        pieces.put(new Point(1, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(2, 1), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(5, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 2), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(0, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(6, 1), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(3, 2), new TransformDecorator(Piece.getPiece(0), tr));
        //pieces.put(new Point(1, 0), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(4, 1), new TransformDecorator(Piece.getPiece(0), tr));

        pieces.put(new Point(1, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(3, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(5, 6), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(7, 6), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(0, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(2, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(4, 5), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(6, 5), new TransformDecorator(Piece.getPiece(0), tr));
        pieces.put(new Point(0, 7), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(2, 7), new TransformDecorator(Piece.getPiece(6), tr));
        //pieces.put(new Point(4, 7), new TransformDecorator(Piece.getPiece(6), tr));
        pieces.put(new Point(6, 7), new TransformDecorator(Piece.getPiece(6), tr));
    }

    public AffineTransform getTr() {
        return tr;
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
    public boolean canKingMoveJump(int player, int rowFrom, int colFrom, int rowTo, int colTo, int turn) {
        if ((player == 4 && turn == 0) || (player == 10 && turn == 1)) {
            ArrayList<Point> availablePoints = new ArrayList<Point>();
            availablePoints.clear();
            int colPosition;

            ArrayList<Integer> kingPath = new ArrayList<>();

            if (rowFrom > rowTo && colFrom > colTo) { // lewy_gorny
                colPosition = colFrom;
                for (int i = rowFrom; i >= rowTo; i--) {

                    if(pieces.containsKey(new Point(colPosition,i))) {
                        int index = pieces.get(new Point(colPosition,i)).getPiece().getIndex();
                        kingPath.add(index);

                    }else{
                        kingPath.add(-1);
                    }

                    System.out.println();

                    availablePoints.add(new Point(colPosition,i));
                    colPosition--;
                }
                for (Integer k:kingPath) {
                    System.out.print(k+" ");
                }
                System.out.println();
                for(Point p: availablePoints){
                   System.out.println(p.x+" "+p.y);
                }


                System.out.println(colTo+" "+rowTo+" "+availablePoints.get(availablePoints.size()-1).x+ availablePoints.get(availablePoints.size()-1).y);
                if(!(colTo == availablePoints.get(availablePoints.size()-1).getX()
                        && rowTo ==availablePoints.get(availablePoints.size()-1).getY())){
                    return false;
                }

            } else if (rowFrom > rowTo && colFrom < colTo) { // prawy_gorny
                colPosition = colFrom;
                for (int i = rowFrom; i >= rowTo; i--) {
                    if(pieces.containsKey(new Point(colPosition,i))) {
                        int index = pieces.get(new Point(colPosition,i)).getPiece().getIndex();
                        kingPath.add(index);

                    }else{
                        kingPath.add(-1);
                    }

                    availablePoints.add(new Point(colPosition,i));
                    colPosition++;
                }

                if(!(colTo == availablePoints.get(availablePoints.size()-1).getX()
                        && rowTo ==availablePoints.get(availablePoints.size()-1).getY())){
                    return false;
                }

            } else if (rowFrom < rowTo && colFrom < colTo) { // prawy_dolny
                colPosition = colFrom;
                for (int i = rowFrom; i <= rowTo; i++) {
                    if(pieces.containsKey(new Point(colPosition,i))) {
                        int index = pieces.get(new Point(colPosition,i)).getPiece().getIndex();
                        kingPath.add(index);

                    }else{
                        kingPath.add(-1);
                    }

                    for (Integer k:kingPath) {
                        System.out.print(k+" ");
                    }
                    availablePoints.add(new Point(colPosition,i));
                    colPosition++;
                }
                if(!(colTo == availablePoints.get(availablePoints.size()-1).getX()
                        && rowTo ==availablePoints.get(availablePoints.size()-1).getY())){
                    return false;
                }
            } else if (rowFrom < rowTo && colFrom > colTo) { // lewy_dolny
                colPosition = colFrom;
                for (int i = rowFrom; i <= rowTo; i++) {
                    if(pieces.containsKey(new Point(colPosition,i))) {
                        int index = pieces.get(new Point(colPosition,i)).getPiece().getIndex();
                        kingPath.add(index);

                    }else{
                        kingPath.add(-1);
                    }

                    availablePoints.add(new Point(colPosition,i));
                    colPosition--;
                }
                if(!(colTo == availablePoints.get(availablePoints.size()-1).getX()
                        && rowTo ==availablePoints.get(availablePoints.size()-1).getY())){
                    return false;
                }
            } else{
                return false;
            }
            return makeKingMove(player,kingPath,availablePoints);
        }
        return false;
    }

    private boolean makeKingMove(int player, ArrayList<Integer> kingPath, ArrayList<Point> availablePoints) {
        System.out.print("Wszedłem :)))");
        boolean makeMove = true;

        //Ruch dla Czarnego Króla
        if(player == 10 && (!kingPath.contains(6) && !kingPath.contains(10))) {  //czarny Król się rusza. - I na ścieżce nie ma Czarnych pionków i czarnych Króli.
            System.out.println("Głębiej");
            for (int i = 0; i < kingPath.size() - 1; i++) {
                if ((kingPath.get(i) == 4 || kingPath.get(i) == 0) && (kingPath.get(i) == kingPath.get(i + 1))) //Jeżeli dwie figury białego koloru stoją obok siebie
                    makeMove = false;
            }
            if (makeMove) {
                for (int i = 0; i < kingPath.size() - 1; i++)
                    if (kingPath.get(i) == 0 || kingPath.get(i) == 4)
                        pieces.remove(availablePoints.get(i));
                return true;
            }

            //Ruch dla Białego Króla

        } else if(player == 4 && (!kingPath.contains(0) && !kingPath.contains(4))) {  //czarny Król się rusza. - I na ścieżce nie ma Czarnych pionków i czarnych Króli.
            System.out.println("Głębiej");
            for (int i = 0; i < kingPath.size() - 1; i++) {
                if ((kingPath.get(i) == 6 || kingPath.get(i) == 10) && (kingPath.get(i) == kingPath.get(i + 1)))
                    makeMove = false;
            }
            if (makeMove) {
                for (int i = 0; i < kingPath.size() - 1; i++)
                    if (kingPath.get(i) == 6 || kingPath.get(i) == 10)
                        pieces.remove(availablePoints.get(i));
                return true;
            }
        }
        return false;
    }


}
