package com.checkers.Command;

import com.checkers.data.CheckersBoard2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

public class CommandManager implements Serializable {

    private LinkedList<Command> undoList = new LinkedList<>();
    private LinkedList<Command> redoList = new LinkedList<>();

    public LinkedList<Command> getUndoList() {
        return undoList;
    }

    public LinkedList<Command> getRedoList() {
        return redoList;
    }
}
