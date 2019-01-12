package com.checkers.Layout;

import javax.swing.*;
import java.awt.*;

public class SavePanel extends JPanel {

    JTextField saveTextField = new JTextField();
    JLabel saveAsLabel = new JLabel("Zapisz jako:");
    int result = 0;
    String napis;

    public SavePanel(){
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(saveAsLabel);
    add(saveTextField);
    result = JOptionPane.showConfirmDialog(null, this, "Panel Zapisu", JOptionPane.OK_CANCEL_OPTION);
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
