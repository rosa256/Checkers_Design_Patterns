package com.checkers.Command;

import com.checkers.Decorator.Piece;

import java.util.LinkedList;

public interface Command {

    void undo();
    void redo();
}
