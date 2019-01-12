package com.checkers;

import com.checkers.Layout.GameFrame;
import com.checkers.Layout.LoadPanel;
import com.checkers.Layout.SavePanel;
import com.checkers.Memento.Memento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Checkers {

    private Game game;
    private Statistics statistics;
    private ArrayList<Memento> mementos = new ArrayList<>();

    public Checkers(){
        Game game = Game.getInstance();
        System.out.println("Wykonalo sie cokowliek?");

        game.getMenuFrame().getGameFrame().getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavePanel savePanel = new SavePanel();
                if(!savePanel.getNapis().isEmpty())
                addMemento(new Memento(game.getMenuFrame().getGameFrame(),savePanel.getNapis()));
                System.out.println();
            }
        });

        game.getMenuFrame().getLoadGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame;
                LoadPanel loadPanel = new LoadPanel();

                try {
                    FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/tmp/"+loadPanel.getNapis()+".ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    gameFrame = (GameFrame) in.readObject();
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

                gameFrame.board.loadImages();
                gameFrame.board.setListeners();

                gameFrame.revalidate();
                gameFrame.setVisible(true);
                gameFrame.pack();
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
