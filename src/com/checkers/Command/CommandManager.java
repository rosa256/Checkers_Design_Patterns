package com.checkers.Command;

import java.util.LinkedList;
import java.util.Stack;

public class CommandManager{

    private Command[] commands = new Command[2];

    public CommandManager(Command deletePiece, Command move) {
        commands[0]= deletePiece;
        commands[1]= move;
    }

    public void undo(){
        commands[1].undo();
        commands[0].undo();
    }

    public void redo(){
        commands[0].redo();
        commands[1].redo();
    }
}
