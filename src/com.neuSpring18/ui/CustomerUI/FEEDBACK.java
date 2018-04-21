package com.neuSpring18.ui.CustomerUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FEEDBACK extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new FEEDBACK();

    }

    /**
     * Create the frame.
     */
    public FEEDBACK() {
//        Image TopImage = new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/topDown.jpeg")).getImage();
//        contentPane=new BackgroundPanel(TopImage);
        contentPane = new JPanel();
        setBounds(100, 100, 450, 300);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblYourSuggestionIs = new JLabel("Your suggestion is important for us");
        lblYourSuggestionIs.setBounds(6, 6, 241, 16);
        contentPane.add(lblYourSuggestionIs);

        JLabel lblSubject = new JLabel("subject");
        lblSubject.setBounds(6, 41, 61, 16);
        contentPane.add(lblSubject);

        textField = new JTextField();
        textField.setBounds(84, 36, 130, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(94, 74, 307, 164);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class BackgroundPanel extends JPanel {

        private Image image = null;
        public BackgroundPanel(Image image) {
            this.image = image;
        }
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),this);
        }
    }

}

