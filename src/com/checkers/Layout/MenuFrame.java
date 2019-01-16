package com.checkers.Layout;
import com.checkers.Game;
import com.checkers.Player;
import com.checkers.data.CheckersBoard2;

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

    public MenuFrame(){
        setResizable(false);
        setLocation(0, 0);
        setTitle("Design Patterns");
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));

        gameFrame = new GameFrame();
        AddButtonsToFrame();

        ArrayList<JLabel> labels = gameFrame.getTimerPlayerLabels();
        Player player_1 = new Player(labels.get(0));
        Player player_2 = new Player(labels.get(1));



        playersInGame.add(player_1);
        playersInGame.add(player_2);

        //Do wyświetlenia okna Menu
        setVisible(true);
        pack();
    }


    private void AddButtonsToFrame() {
        JButton newGameButton = new JButton("Nowa gra");
        newGameButton.setFont(new Font(ARIAL, Font.PLAIN, 30));
        newGameButton.setPreferredSize(new Dimension(300, 300));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionsPanel(playersInGame, gameFrame);
                gameFrame.setNickNames(playersInGame.get(0).getNickName(),
                        playersInGame.get(1).getNickName());

                board = new CheckersBoard2();
                gameFrame.add(board);
                Game.getInstance().loadPieces();
                gameFrame.setVisible(true);
                gameFrame.repaint();
                gameFrame.revalidate();

                gameFrame.pack();

                playersInGame.get(0).getTimer().start();

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

    public GameFrame getGameFrame(){
        return this.gameFrame;
    }
    public JButton getLoadGameButton(){
        return loadGameButton;
    }


}
