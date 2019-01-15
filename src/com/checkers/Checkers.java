package com.checkers;

import com.checkers.Layout.GameFrame;
import com.checkers.Layout.GamePanel;
import com.checkers.Layout.LoadPanel;
import com.checkers.Layout.SavePanel;
import com.checkers.Memento.Memento;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Checkers extends JPanel implements ActionListener{

    private Game game;
    private Statistics statistics;
    private ArrayList<Memento> mementos = new ArrayList<>();
    private int screenWidth;
    private int screenHeight;
    private JButton newGameButton=new JButton("Nowa gra");
    private JButton loadGameButton=new JButton("Wczytaj gre");
    JButton instructionButton = new JButton("Instrukcja gry");
    JButton authorsButton = new JButton("Autorzy");
    JButton exitButton = new JButton("Wyjście");


    public Checkers(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setLayout(new GridLayout(5, 1));
        setBounds(0, 0, screenWidth, screenHeight);
         game = Game.getInstance();
        System.out.println("Wykonalo sie cokowliek?");
        addButtons();

       /* game.getMenuFrame().getGameFrame().getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavePanel savePanel = new SavePanel();
                if(!savePanel.getNapis().isEmpty())
                addMemento(new Memento(game.getMenuFrame().getGameFrame(),savePanel.getNapis()));
                System.out.println();
            }
        });*/

        /*game.getMenuFrame().getLoadGameButton().addActionListener(new ActionListener() {
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
        });*/

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addButtons(){
        add(newGameButton);
        newGameButton.addActionListener(e -> {
            GamePanel gamePanel=new GamePanel(this);
            gamePanel.startNewGame();
            getParent().add(gamePanel);
            getParent().repaint();
            getParent().revalidate();
            getParent().remove(Checkers.this);
        });
        add(loadGameButton);
        add(instructionButton);
        instructionButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "Złap Pionek lewym przyciskiem myszy i " +
                        "upuść go w " + "wybranym przez siebie miejscu",
                        "Instrukcja gry", JOptionPane.INFORMATION_MESSAGE));
        add(authorsButton);
        authorsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Sławomir Romanowski" +
                        "\nAdam Polejczuk\nDamian Rosiński", "Autorzy", JOptionPane.INFORMATION_MESSAGE));
        add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

    }
}
