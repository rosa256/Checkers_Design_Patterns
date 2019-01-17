package com.checkers;

import com.checkers.Layout.GameFrame;
import com.checkers.Layout.LoadPanel;
import com.checkers.Layout.MenuFrame;
import com.checkers.Layout.SavePanel;
import com.checkers.Memento.Memento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Checkers {

    private Game game;
    private Statistics statistics;
    private ArrayList<Memento> mementos = new ArrayList<>();
    private MenuFrame menuFrame;

    public Checkers(){
        game = Game.getInstance();

        menuFrame = new MenuFrame();

        menuFrame.getGameFrame().getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavePanel savePanel = new SavePanel();
                if(!savePanel.getNapis().isEmpty())
                addMemento(new Memento(Game.getInstance(),savePanel.getNapis()));
                System.out.println();
            }
        });

        menuFrame.getLoadGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadPanel loadPanel = new LoadPanel();

                try {
                    FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/tmp/"+loadPanel.getNapis()+".ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    game = (Game) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    System.out.println("Problem z Savem. Byc moze taki nie istnieje: "+loadPanel.getNapis());
                    JOptionPane.showMessageDialog(null, "My Goodness, być może taki save nie istnieje:\n"+loadPanel.getNapis());
                    //i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("GameFrame class not found");
                    c.printStackTrace();
                    return;
                }

                System.out.println("Deserialized GameFrame...");


                //Coś tu jeszcze naprawic z Timerami.


                //game.getMenuFrame().getGameFrame().board.loadImages();
               // game.getMenuFrame().getGameFrame().board.setListeners();
                game.getPlayersInGame().get(0).refreshTimer();
                game.getPlayersInGame().get(1).refreshTimer();
                //game.getPlayersInGame().get(0).runTimer(menuFrame.getGameFrame().getTimerPlayerLabels().get(0));
                //game.getPlayersInGame().get(1).runTimer(menuFrame.getGameFrame().getTimerPlayerLabels().get(1));
                menuFrame.getGameFrame().runGameFrameTimers(game.getPlayersInGame());

                System.out.println(game.getPlayersInGame().get(0).getActual_time());


                menuFrame.getGameFrame().setVisible(true);
                menuFrame.getGameFrame().pack();
            }
        });

    }

    //TODO
    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    //TODO
    public Memento getMemento(){
        return null;
    }

    //TODO
    public void mouseReleased(MouseEvent event){

    }
}
