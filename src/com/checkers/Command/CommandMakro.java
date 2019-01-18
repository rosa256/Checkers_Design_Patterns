package com.checkers.Command;

import com.checkers.data.CheckersBoard;

import java.io.Serializable;

public class CommandMakro implements Command, Serializable {

    private Command[] commands = new Command[2];

    public CommandMakro(Command deletePiece, Command move) {
        commands[0]= deletePiece;
        commands[1]= move;
    }

    public void undo(CheckersBoard checkersBoard){
        commands[1].undo(checkersBoard);
        commands[0].undo(checkersBoard);
    }

    public void redo(CheckersBoard checkersBoard){
        commands[0].redo(checkersBoard);
        commands[1].redo(checkersBoard);
    }
}
