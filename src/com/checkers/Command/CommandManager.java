package com.checkers.Command;

import com.checkers.data.CheckersBoard2;

import java.util.LinkedList;
import java.util.Stack;

public class CommandManager{

    private LinkedList<Command> undoList = new LinkedList<>();
    private LinkedList<Command> redoList = new LinkedList<>();

    public LinkedList<Command> getUndoList() {
        return undoList;
    }

    public LinkedList<Command> getRedoList() {
        return redoList;
    }
}
