package com.neuSpring18.ui.DealerUI;

import javax.swing.*;
import java.awt.*;

public class DealerCommonFrame extends JFrame {


    protected void makeItVisible(){

        setSize(1330,965);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("WELCOME");

    }

    Font lableFontArial = new Font("Arial",Font.PLAIN,20);
    Font lableFontTNM = new Font("Times New Roman",Font.BOLD,18);
    Font lableHelvetica = new Font("Helvetica Neue",Font.BOLD,20);
    Font lablesansserif = new Font("sans-serif",Font.BOLD,20);
    Font stratumBoldFont = new Font("StratumBold",Font.BOLD,20);
    Font stratumBoldFontSmaller = new Font("StratumBold",Font.PLAIN,18);
    Font LucidaGrande = new Font("Lucida Grande",Font.BOLD,17);
    Font LucidaGrandeSmall = new Font("Lucida Grande",Font.PLAIN,16);
    Font LucidaGrandeForPage = new Font("Lucida Grande",Font.PLAIN,16);
    Font stratumBoldFontPlain = new Font("StratumBold",Font.PLAIN,18);
    Font StratumBoldFontForButton = new Font("StratumBold",Font.BOLD,22);
    Font getStratumBoldForCarTitle = new Font("stratumBoldFont",Font.PLAIN,20);
    Color fiterPanelColor = new Color(220,220,220);
    Color buttonColor = new Color(15,130,200);
}
