package com.checkers.Layout;

import com.checkers.Player;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class OptionsPanel extends JPanel {

    JTextField nameField1 = new JTextField();
    JTextField nameField2 = new JTextField();
    JLabel player1Label = new JLabel("Gracz 1");
    JLabel player2Label = new JLabel("Gracz 2");

    public OptionsPanel(List<Player> playersInGame){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createGameOptionsListeners(this, nameField1, nameField2, player1Label, player2Label);

        add(new Label("Nazwa gracza 1:"));
        add(nameField1);
        add(new Label("Kolor gracza 1: BIAŁY"));

        add(new Label("Nazwa gracza 2:"));
        add(nameField2);
        add(new Label("Kolor gracza 2: CZARNY"));

        int result = JOptionPane.showConfirmDialog(null, this, "Opcje gry", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (!nameField1.getText().equals("")) {
                playersInGame.get(0).setNickName(nameField1.getText());
            } else {
                playersInGame.get(0).setNickName("Gracz 1 - Default");
            }
            if (!nameField2.getText().equals("")) {
                playersInGame.get(1).setNickName(nameField1.getText());
            } else {
                playersInGame.get(1).setNickName("Gracz 2 - Default");
            }
        }
    }

    private void createGameOptionsListeners(JPanel popup, JTextField nameField1, JTextField nameField2, JLabel test1, JLabel test2) {
        nameField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if ((nameField2.getText().equals(nameField1.getText()) && !nameField2.getText().equals("")) ||
                        (nameField1.getText().equals(nameField2.getText()) && !nameField1.getText().equals(""))) {
                    JOptionPane.showMessageDialog(popup, "Gracze nie mogą mieć takich samych nazw");
                    nameField1.setText("");
                }
            }
        });

        nameField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if ((nameField2.getText().equals(nameField1.getText()) && !nameField2.getText().equals("")) ||
                        (nameField1.getText().equals(nameField2.getText()) && !nameField1.getText().equals(""))) {
                    JOptionPane.showMessageDialog(popup, "Gracze nie mogą mieć takich samych nazw");
                    nameField2.setText("");
                }
            }
        });

        nameField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                test1.setText(nameField1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                test1.setText(nameField1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException();
            }
        });
        nameField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                test2.setText(nameField2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                test2.setText(nameField2.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException();
            }
        });
    }

}
