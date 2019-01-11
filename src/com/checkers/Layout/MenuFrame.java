package com.checkers.Layout;
import com.checkers.Player;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class MenuFrame extends JFrame {

    private static final String ARIAL = "Arial"; /* Font style */
    List<Player> playersInGame;

    public MenuFrame(List playersInGame){
        this.playersInGame = playersInGame;
        setResizable(false);
        setLocation(0, 0);
        setTitle("Design Patterns");
        setLayout(new GridLayout(2, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));


        AddButtonsToFrame();

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
                OptionsPanel optionsPanel = new OptionsPanel(playersInGame);
                //createGameWindow();
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
}
