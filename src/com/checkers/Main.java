package com.checkers;

import com.checkers.Layout.MenuFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->
                MenuFrame.getInstance());
    }
}
