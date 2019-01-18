package com.checkers;


import com.checkers.Memento.Memento;
import java.util.ArrayList;

public class Checkers {

    private Game game;
    private ArrayList<Memento> mementos = new ArrayList<>(); //hmm może trzymać memetos pod HashMapą? klucz-nazwa save ?
    //private MenuFrame menuFrame;



    public Checkers(){
        System.out.println("Wykonalo sie cokowliek?");



    }

    //TODO
    public void addMemento(Memento memento){
        mementos.add(memento);
        System.out.println("mementos size "+mementos.size());
        for (int i=0;i<mementos.size();i++){
            System.out.println("hash nr "+i+" size "+mementos.get(i).getGame().getPieces().size());
        }
    }

    //TODO
    public Memento getMemento(int index){
        System.out.println("rozmiar mementos "+ mementos.size());
        return mementos.get(index);
    }

}
