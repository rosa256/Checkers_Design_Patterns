package com.checkers;

import com.checkers.Layout.GameFrame;
import com.checkers.Observer.IObserver;
import com.checkers.Observer.ISubject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Player implements java.io.Serializable, ISubject {

    private String nickName;
    private boolean turn;
    private IObserver iObserver;

    private final long TEN_SECOUNDS = 15000;
    private long[] actual_time = {TEN_SECOUNDS - 1000};
    private Timer timer;
    private final SimpleDateFormat sdf = new SimpleDateFormat("mm : ss");


    public String getNickName() {
        return nickName;
    }
    public Player(JLabel time_player){
        add(Game.getInstance());
        runTimer(time_player);
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getActual_time() {
        return actual_time[0];
    }

    public void runTimer(JLabel time_player){

        time_player.setText(sdf.format(new Date(TEN_SECOUNDS)));

        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                time_player.setText(sdf.format(new Date(actual_time[0])));
                actual_time[0] -= 1000;
                if(actual_time[0]<=4000)
                    Player.this.notifyObserver(Player.this);
            }
        });
    }

    @Override
    public void add(IObserver observer) {
        this.iObserver=observer;
    }

    @Override
    public void remove(IObserver observer) {

    }

    @Override
    public void notifyObserver(Player player) {
        this.iObserver.update(player);
    }

    public Timer getTimer(){
        return this.timer;
    }

    public void refreshTimer() {
        actual_time = new long[]{TEN_SECOUNDS - 1000};
    }
}
