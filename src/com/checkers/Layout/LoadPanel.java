package com.checkers.Layout;

import javax.swing.*;

public class LoadPanel extends JPanel{

    JTextField saveTextField = new JTextField();
    JLabel saveAsLabel = new JLabel("Wczytaj Gre:");
    int result = 0;
    String napis;

    public LoadPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(saveAsLabel);
        add(saveTextField);
        result = JOptionPane.showConfirmDialog(null, this, "Wczytaj Gre", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            if(!saveTextField.getText().equals("")){
                result = 1;
                napis = saveTextField.getText();
            }
        }
    }

    public int getResult() {
        return result;
    }
    public String getNapis(){
        return napis;
    }


}
