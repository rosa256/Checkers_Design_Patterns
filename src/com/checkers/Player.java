package com.checkers;

import java.util.Timer;

public class Player {

    private String nickName;
    private Timer timer;
    private boolean turn;

    public String getNickName() {
        return nickName;
    }

    public Timer getTimer() {
        return timer;
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

    //TODO
    void register() {

    }
    //TODO
    void unregister(){

    }
    //TODO
    //void notify();
}
