package com.checkers.Command;

import com.checkers.data.CheckersBoard2;

import java.util.LinkedList;
import java.util.Stack;

public class CommandManager{

    private Command[] commands = new Command[2];

    public CommandManager(Command deletePiece, Command move) {
        commands[0]= deletePiece;
        commands[1]= move;
    }

    public void undo(CheckersBoard2 checkersBoard2){
        commands[1].undo(checkersBoard2);
        commands[0].undo(checkersBoard2);
    }

    public void redo(CheckersBoard2 checkersBoard2){
        commands[0].redo(checkersBoard2);
        commands[1].redo(checkersBoard2);
    }
}
