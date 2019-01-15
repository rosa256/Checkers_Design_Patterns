package com.checkers;

import com.checkers.Observer.IObserver;
import com.checkers.Observer.ISubject;

import java.util.Timer;

public class Player implements java.io.Serializable, ISubject {

    private String nickName;
    private Timer timer;
    private boolean turn;
    private IObserver iObserver;

    public String getNickName() {
        return nickName;
    }
    public Player(){};
    public Player(IObserver iObserver){
        this.iObserver = iObserver;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public void add(IObserver observer) {
        this.iObserver = observer;
    }

    @Override
    public void remove(IObserver observer) {
        this.iObserver = null;   // Czy trzeba usuwac referencje do Game, czyli obiektu obserwatora jeśli on jest Singletonem?
    }

    @Override
    public void notifyObserver(Player player) {
        this.iObserver.update(player);
    }

    public Timer getTimer() {
        return timer;
    }

}
