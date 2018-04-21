package com.neuSpring18.ui.CustomerUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LOG extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public LOG() {

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("User Name");
        lblNewLabel.setBounds(60, 70, 78, 16);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(178, 65, 130, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblPassword = new JLabel("PassWord");
        lblPassword.setBounds(60, 118, 61, 16);
        contentPane.add(lblPassword);

        textField_1 = new JTextField();
        textField_1.setBounds(178, 113, 130, 26);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.setBounds(160, 185, 117, 29);
        contentPane.add(btnSignIn);
        setTitle("log in ");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    LOG frame = new LOG();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        new LOG();
    }
}

