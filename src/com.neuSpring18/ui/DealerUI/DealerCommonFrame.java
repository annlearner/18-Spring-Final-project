package com.neuSpring18.ui.DealerUI;

import javax.swing.*;

public class DealerCommonFrame extends JFrame {

    protected JButton addButton, goButton;

    protected JTextField searchTextArea;
    protected JTextField priceStart, priceEnd;
    protected JTextField yearStart, yearEnd;

    protected JLabel searchLabel;
    protected JLabel filterLabel;
    protected JLabel priceLabel, yearLabel, colorLabel, categoryLabel, typeLabel;

    protected void makeItVisible(){

        setSize(800,600);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

}
