package com.checkers.Layout;


import javax.swing.*;
import java.awt.*;
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

    private JLabel player1NickName = new JLabel();
    private JLabel player2NickName = new JLabel();
//    public JLabel timeLabel_player1 = new JLabel();
//    public JLabel timeLabel_player2 = new JLabel();
    private JLabel turn1 = new JLabel("Twoja tura!");
    private JLabel win1 = new JLabel("Wygrałeś!");
    private JLabel turn2 = new JLabel("Twoja tura!");
    private JLabel win2 = new JLabel("Wygrałeś!");
    JToolBar bar = new JToolBar();
//    private JButton undo = new JButton(new ImageIcon("undo.png"));
//    private JButton redo = new JButton(new ImageIcon("redo.png"));



    public GameFrame(){
        //board = new CheckersBoard();
        JButton undo = Game.getInstance().getUndo();
        JButton redo = Game.getInstance().getRedo();
        bar.add(undo);
        bar.add(redo);
        undo.setEnabled(false);
        redo.setEnabled(false);
        this.add(bar, BorderLayout.PAGE_START);
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
        gui.setLayout(new GridLayout(3, 1));

        setupPlayerPanel(player1panel, gracz1, player1NickName, turn1, win1, gui, Color.BLACK, Game.getInstance().getTimeLabel_player1(),Game.getInstance().getPlayer1DelayLabel());
        //setupTimePanel(turnpanel, gui, turnTimeLabel, time);

        setupPlayerPanel(player2panel, gracz2, player2NickName, turn2, win2, gui, Color.WHITE, Game.getInstance().getTimeLabel_player2(),Game.getInstance().getPlayer2DelayLabel());
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
                    Game.getInstance().getTimeLabel_player2().setText(new SimpleDateFormat("mm : ss").format(new Date(10000)));
                    playersInGame.get(1).refreshTimer();


                } else if (board.getTurn() == 1 && board.isGameOver() == -1) {
                    turn2.setVisible(true);
                    turn1.setVisible(false);
                    win1.setVisible(false);
                    win2.setVisible(false);
                    Game.getInstance().getTimeLabel_player1().setText(new SimpleDateFormat("mm : ss").format(new Date(10000)));
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

    private void setupPlayerPanel(JPanel panel, JLabel graczLabel, JLabel playerLabel, JLabel turnLabel, JLabel winLabel, JPanel gui, Color color, JLabel time_player, JLabel warnDelay) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        graczLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));

        panel.add(graczLabel);
        playerLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        playerLabel.setOpaque(true);
        setPlayerColor(playerLabel, color);
        time_player.setFont(new Font(ARIAL, Font.PLAIN, 24));
        warnDelay.setFont(new Font(ARIAL, Font.PLAIN,24));
        panel.add(playerLabel);
        panel.add(time_player);
        turnLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        winLabel.setFont(new Font(ARIAL, Font.PLAIN, 24));
        winLabel.setVisible(false);
        panel.add(turnLabel);
        panel.add(warnDelay);
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
        player1NickName.setText(nickName1);
        player2NickName.setText(nickName2);
    }
}
