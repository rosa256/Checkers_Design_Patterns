package com.checkers.Memento;

import com.checkers.Game;
import com.checkers.Layout.GameFrame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Memento {

    private String state;

    public Memento(){}
    public Memento(GameFrame game,String saveName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/tmp/" +saveName +".ser");
            this.state = System.getProperty("user.dir") + "/tmp/" + saveName +".ser";
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+System.getProperty("user.dir")+"/tmp/"+ saveName +".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
