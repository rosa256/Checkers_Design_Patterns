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

import com.checkers.Player;
import com.checkers.data.*;

public class GameFrame extends JFrame{

    private JButton saveButton = new JButton("Save");
    private static final String ARIAL = "Arial";
    public CheckersBoard board;

    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();
    public GameFrame(ArrayList<Player> playersInGame){
        board = new CheckersBoard();
        JPanel gameBoard = new JPanel();
        JPanel player1panel = new JPanel();
        JLabel gracz1 = new JLabel("Gracz 1: ");
        JLabel turn1 = new JLabel("Twoja tura!");
        JLabel win1 = new JLabel("Wygrałeś!");
        JPanel turnpanel = new JPanel();
        JPanel gui = new JPanel();
        JLabel turnTimeLabel = new JLabel("Czas tury:");
        Date date = new Date(board.getElapsedBoardTime());
        String formatted = getFormattedTime(date);
        JLabel time = new JLabel(formatted);

        String formatted1 = getFormattedTime(date);
        String formatted2 = getFormattedTime(date);
        JLabel time_player1 = new JLabel(formatted1);
        JLabel time_player2 = new JLabel(formatted2);


        JPanel player2panel = new JPanel();
        JLabel gracz2 = new JLabel("Gracz 2:");
        JLabel turn2 = new JLabel("Twoja tura!");
        JLabel win2 = new JLabel("Wygrałeś!");


        gameBoard.setPreferredSize(new Dimension(1024, 768));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(board);
        gui.setPreferredSize(new Dimension(256, 128));
        gui.setLayout(new GridLayout(4, 1));

        setupPlayerPanel(player1panel, gracz1, player1, turn1, win1, gui, Color.BLACK, time_player1);

        setupTimePanel(turnpanel, gui, turnTimeLabel, time);


        //Stworzyc Graczy przed nacieniejeim OK na okienku. :) i bedzie banglać.
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

        setupPlayerTimer(time_player1,playersInGame.get(0).getTimer());
        setupPlayerTimer(time_player2,playersInGame.get(1).getTimer());

        playersInGame.get(0).getTimer().start();

        setupPlayerPanel(player2panel, gracz2, player2, turn2, win2, gui, Color.WHITE, time_player2);
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

    private void setupPlayerTimer(JLabel time_player, Timer timer) {
        final long THIRTY_SECOUNDS = 30000;
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
        time_player.setText(sdf.format(new Date(THIRTY_SECOUNDS)));

        final long[] x = {THIRTY_SECOUNDS - 1000};
        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                time_player.setText(sdf.format(new Date(x[0])));
                x[0] -= 1000;
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
}
