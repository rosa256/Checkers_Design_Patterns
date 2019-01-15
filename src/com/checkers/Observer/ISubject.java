package com.checkers.Observer;

import com.checkers.Player;

public interface ISubject {

    //TODO
    void add(IObserver observer);

    //TODO
    void remove(IObserver observer);

    //TODO
    void notifyObserver(Player player);
}
