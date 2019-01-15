package com.checkers.Layout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.checkers.Board;
import com.checkers.Player;
import com.checkers.data.*;

public class GameFrame extends JFrame{

    private JButton saveButton = new JButton("Save");
    private static final String ARIAL = "Arial";
    public CheckersBoard board;

    public GameFrame(java.util.List<Player> playersInGame){
        //board = new CheckersBoard(Color.WHITE, Color.BLACK);
        JPanel gameBoard = new JPanel();
        JPanel player1panel = new JPanel();
        JLabel gracz1 = new JLabel(playersInGame.get(0).getNickName());
        JLabel player1 = new JLabel(playersInGame.get(1).getNickName());
        JLabel turn1 = new JLabel("Twoja tura!");
        JLabel win1 = new JLabel("Wygrałeś!");
        JPanel turnpanel = new JPanel();
        JPanel gui = new JPanel();
        JLabel turnTimeLabel = new JLabel("Czas tury:");
        Date date = new Date(board.getElapsedBoardTime());
        String formatted = getFormattedTime(date);
        JLabel time = new JLabel(formatted);
        JPanel player2panel = new JPanel();
        JLabel gracz2 = new JLabel("Gracz 2:");
        JLabel player2 = new JLabel("Grzegorz-HARDCODE");
        JLabel turn2 = new JLabel("Twoja tura!");
        JLabel win2 = new JLabel("Wygrałeś!");


        gameBoard.setPreferredSize(new Dimension(1024, 768));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(board);
        gui.setPreferredSize(new Dimension(256, 128));
        gui.setLayout(new GridLayout(4, 1));

        setupPlayerPanel(player1panel, gracz1, player1, turn1, win1, gui, Color.WHITE);

        setupTimePanel(turnpanel, gui, turnTimeLabel, time, saveButton);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentTime = new Date(board.getElapsedBoardTime());
                String format = getFormattedTime(currentTime);
                time.setText(format);
            }
        });
        timer.setInitialDelay(0);
        timer.start();

        setupPlayerPanel(player2panel, gracz2, player2, turn2, win2, gui, Color.BLACK);
        turn2.setVisible(false);

        board.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent  e) {
                if (board.getTurn() == 0 && board.isGameOver() == -1) {
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    win1.setVisible(false);
                    win2.setVisible(false);
                } else if (board.getTurn() == 1 && board.isGameOver() == -1) {
                    turn2.setVisible(true);
                    turn1.setVisible(false);
                    win1.setVisible(false);
                    win2.setVisible(false);
                }
                if (board.isGameOver() == 0) {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    win1.setVisible(true);
                    timer.stop();
                } else if (board.isGameOver() == 1) {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    win2.setVisible(true);
                    timer.stop();
                }
            }
        });
        gui.add(saveButton);
        this.add(gui, BorderLayout.EAST);
    }

    private String getFormattedTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    private void setupTimePanel(JPanel turnpanel, JPanel gui, JLabel turnTimeLabel, JLabel time, JButton saveButton) {
        turnTimeLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        turnpanel.add(turnTimeLabel);
        time.setFont(new Font(ARIAL, Font.PLAIN, 24));
        turnpanel.add(time);
        gui.add(turnpanel);
    }

    private void setupPlayerPanel(JPanel panel, JLabel graczLabel, JLabel playerLabel, JLabel turnLabel, JLabel winLabel, JPanel gui, Color color) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        graczLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        panel.add(graczLabel);
        playerLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        playerLabel.setOpaque(true);
        setPlayerColor(playerLabel, color);
        panel.add(playerLabel);
        turnLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        winLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        winLabel.setVisible(false);
        panel.add(turnLabel);
        panel.add(winLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        playerLabel.setForeground(color);
        gui.add(panel);
    }

    private void setPlayerColor(JLabel player, Color color) {
        if (color.getRGB() > -8355712) {
            player.setBackground(Color.BLACK);
            player.setBorder(BorderFactory.createLineBorder(color));
        } else {
            player.setBackground(Color.WHITE);
            player.setBorder(BorderFactory.createLineBorder(color));
        }
    }

    public JButton getSaveButton() {
        return saveButton;
    }


}
