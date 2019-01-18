package com.checkers.Layout;
import com.checkers.Checkers;
import com.checkers.Game;
import com.checkers.Player;
import com.checkers.data.CheckersBoard2;
import javafx.scene.control.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuFrame extends JFrame {

    private static final String ARIAL = "Arial"; /* Font style */

    private GameFrame gameFrame;
    private JButton loadGameButton;
    private ArrayList<Player> playersInGame = new ArrayList<>();
    public CheckersBoard2 board;
    private Checkers checkers;
    private Game game;
    private static MenuFrame instance;
    private JLabel timeLabel_player1 = new JLabel();
    private JLabel timeLabel_player2 = new JLabel();

    public MenuFrame(){
        setResizable(false);
        setLocation(0, 0);
        setTitle("Design Patterns");
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));

        AddButtonsToFrame();
        //Do wyświetlenia okna Menu
        setVisible(true);
        pack();
        checkers=new Checkers();
    }


    private void AddButtonsToFrame() {
        JButton newGameButton = new JButton("Nowa gra");
        newGameButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        newGameButton.setPreferredSize(new Dimension(300, 300));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionsPanel();
                board=new CheckersBoard2();

                if(gameFrame==null)
                    gameFrame = new GameFrame();
                gameFrame.getBar().add(board.getUndo());
                gameFrame.getBar().add(board.getRedo());

                ArrayList<Player> playersInGame = Game.getInstance().getPlayersInGame();
                gameFrame.setNickNames(playersInGame.get(0).getNickName(),
                        playersInGame.get(1).getNickName());

                gameFrame.add(board);
                Game.getInstance().getPieces().clear();
                Game.getInstance().loadPieces();
                gameFrame.setVisible(true);
                gameFrame.repaint();
                gameFrame.revalidate();

                gameFrame.pack();

                Game.getInstance().getPlayersInGame().get(0).getTimer().start();

                gameFrame.getSaveButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("save Button");
                        checkers.addMemento(Game.getInstance().createMemento()); //tworze pamiątke i dodaje na liste w checkers
                    }
                });

            }
        });
        add(newGameButton);

        JButton instructionButton = new JButton("Instrukcja gry");
        instructionButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Złap Pionek lewym przyciskiem myszy i upuść go w " +
                        "wybranym przez siebie miejscu", "Instrukcja gry", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(instructionButton);
        JButton authorsButton = new JButton("Autorzy");
        authorsButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        authorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Sławomir Romanowski\nAdam Polejczuk\nDamian Rosiński",
                        "Autorzy", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(authorsButton);

        loadGameButton = new JButton("Wczytaj grę");
        loadGameButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        loadGameButton.setPreferredSize(new Dimension(300, 300));
        add(loadGameButton);

        JButton exitButton = new JButton("Wyjście");
        exitButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }

    public CheckersBoard2 getBoard() {
        return board;
    }
    public static MenuFrame getInstance(){
        if(instance==null)
            instance=new MenuFrame();
        return instance;
    }

    public JLabel getTimeLabel_player1() {
        return timeLabel_player1;
    }

    public JLabel getTimeLabel_player2() {
        return timeLabel_player2;
    }
}