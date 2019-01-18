package com.checkers.Command;
import com.checkers.data.CheckersBoard;

public interface Command {

    void undo(CheckersBoard checkersBoard);
    void redo(CheckersBoard checkersBoard);
}
