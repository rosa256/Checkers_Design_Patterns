package com.checkers.data;

public class TimeCounter implements java.io.Serializable {

    public long getElapsedTime() {
        return System.currentTimeMillis() - this.startingTime;
    }

    private long startingTime = System.currentTimeMillis();
}
