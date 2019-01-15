package com.checkers;

import javax.swing.*;

public class MainFrame extends JFrame{

    public MainFrame() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        add(new Checkers());
        setLayout(null);
        setVisible(true);
    }
}
