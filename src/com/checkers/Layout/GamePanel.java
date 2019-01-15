package com.checkers.Layout;

import com.checkers.Decorator.IPiece;
import com.checkers.Decorator.Piece;
import com.checkers.Game;
import com.checkers.data.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {

    private Game game;
    private JPanel backPanel;
    private JLabel player1 = new JLabel("Player1");
    private JLabel player2 = new JLabel("Player2");
    private ImageIcon boardIcon;
    private int screenWidth;
    private int screenHeight;
    HashMap<Point, IPiece> pieces = new HashMap<>();

    public Game getGame() {
        return game;
    }
    public GamePanel(JPanel backPanel){
        this.backPanel=backPanel;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
    }

    public void setNewGame(){
        game=Game.getInstance();
        game.getBoard().loadPieces();
        pieces=game.getBoard().getBoard();
    }
    public void setLoadgame(Game game){
        this.game=game;
    }
    public void setBoardIcon(){
        boardIcon=game.getBoard().getBoard_image_ico();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        System.out.println("paint");
        boardIcon.paintIcon(this,graphics2D,0,0);
        System.out.println("Pieces size "+pieces.size());
        for (Map.Entry<Point, IPiece> e : pieces.entrySet()){
            Point point = e.getKey();
            IPiece pc = e.getValue();
            //pc.draw(graphics2D,(int)point.getX(),(int)point.getY(),this); to nie działa
            pc.getImageIcon().paintIcon(this, graphics2D, (int) point.getX()*128, (int) point.getY()*96); //a to działa
            System.out.println("piece painted");



        }
    }
    public void startNewGame(){
        setNewGame();
        setBoardIcon();
        add(player1);
        player1.setBounds(1070,50,150,30);

        add(player2);
        player2.setBounds(1070,700,150,30);
        repaint();

    }
}
