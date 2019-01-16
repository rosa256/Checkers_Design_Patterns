package com.checkers.Layout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.checkers.Game;
import com.checkers.Player;
import com.checkers.data.*;

public class GameFrame extends JFrame{

    private JButton saveButton = new JButton("Save");
    private static final String ARIAL = "Arial";
    public CheckersBoard2 board;

    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();
    public JLabel timeLabel_player1 = new JLabel();
    public JLabel timeLabel_player2 = new JLabel();
    private JLabel turn1 = new JLabel("Twoja tura!");
    private JLabel win1 = new JLabel("Wygrałeś!");
    private JLabel turn2 = new JLabel("Twoja tura!");
    private JLabel win2 = new JLabel("Wygrałeś!");


    public GameFrame(ArrayList<Player> playersInGame){
        //board = new CheckersBoard();
        board = new CheckersBoard2();
        add(board);
        JPanel gameBoard = new JPanel();
        JPanel player1panel = new JPanel();
        JLabel gracz1 = new JLabel("Gracz 1: ");
        JPanel turnpanel = new JPanel();
        JPanel gui = new JPanel();
        JLabel turnTimeLabel = new JLabel("Czas tury:");
        //Date date = new Date(board.getElapsedBoardTime());
        //String formatted = getFormattedTime(date);
        //JLabel time = new JLabel(formatted);



        JPanel player2panel = new JPanel();
        JLabel gracz2 = new JLabel("Gracz 2:");


        gameBoard.setPreferredSize(new Dimension(1024, 768));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.add(board);
        gui.setPreferredSize(new Dimension(256, 128));
        gui.setLayout(new GridLayout(4, 1));

        setupPlayerPanel(player1panel, gracz1, player1, turn1, win1, gui, Color.BLACK, timeLabel_player1);

        //setupTimePanel(turnpanel, gui, turnTimeLabel, time);

        setupPlayerPanel(player2panel, gracz2, player2, turn2, win2, gui, Color.WHITE, timeLabel_player2);
        turn2.setVisible(false);

//        runGameFrameTimers(playersInGame);

        gui.add(saveButton);
        this.add(gui, BorderLayout.EAST);
    }

    public void runGameFrameTimers(ArrayList<Player> playersInGame) {
        board.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (board.getTurn() == 0 && board.isGameOver() == -1) {
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    win1.setVisible(false);
                    win2.setVisible(false);

                    playersInGame.get(0).getTimer().start();
                    playersInGame.get(1).getTimer().stop();
                    timeLabel_player2.setText(new SimpleDateFormat("mm : ss").format(new Date(10000)));
                    playersInGame.get(1).refreshTimer();


                } else if (board.getTurn() == 1 && board.isGameOver() == -1) {
                    turn2.setVisible(true);
                    turn1.setVisible(false);
                    win1.setVisible(false);
                    win2.setVisible(false);
                    timeLabel_player1.setText(new SimpleDateFormat("mm : ss").format(new Date(10000)));
                    playersInGame.get(0).getTimer().stop();
                    playersInGame.get(1).getTimer().start();
                    playersInGame.get(0).refreshTimer();
                }
                if (board.isGameOver() == 0) {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    win1.setVisible(true);

                } else if (board.isGameOver() == 1) {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    win2.setVisible(true);

                }
            }
        });
    }


    private String getFormattedTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    private void setupTimePanel(JPanel turnpanel, JPanel gui, JLabel turnTimeLabel, JLabel time) {
        turnTimeLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        turnpanel.add(turnTimeLabel);
        time.setFont(new Font(ARIAL, Font.PLAIN, 24));
        turnpanel.add(time);
        gui.add(turnpanel);
    }

    private void setupPlayerPanel(JPanel panel, JLabel graczLabel, JLabel playerLabel, JLabel turnLabel, JLabel winLabel, JPanel gui, Color color, JLabel time_player) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        graczLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));

        panel.add(graczLabel);
        playerLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        playerLabel.setOpaque(true);
        setPlayerColor(playerLabel, color);
        time_player.setFont(new Font(ARIAL, Font.PLAIN, 24));
        panel.add(playerLabel);
        panel.add(time_player);
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


    public void setNickNames(String nickName1, String nickName2) {
        player1.setText(nickName1);
        player2.setText(nickName2);
    }

    public ArrayList<JLabel> getTimerPlayerLabels(){
        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(timeLabel_player1); labels.add(timeLabel_player2);
        return labels;
    }
}
