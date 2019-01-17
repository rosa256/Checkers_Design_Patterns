package com.checkers.Command;

import com.checkers.Decorator.Piece;
import com.checkers.data.CheckersBoard2;

import java.util.LinkedList;

public interface Command {

    void undo(CheckersBoard2 checkersBoard2);
    void redo(CheckersBoard2 checkersBoard2);
}
