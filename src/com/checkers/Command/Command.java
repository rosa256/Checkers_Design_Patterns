package com.checkers.Command;
import com.checkers.data.CheckersBoard2;

public interface Command {

    void undo(CheckersBoard2 checkersBoard2);
    void redo(CheckersBoard2 checkersBoard2);
}
