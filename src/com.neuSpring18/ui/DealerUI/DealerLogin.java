package com.neuSpring18.ui.DealerUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DealerLogin extends DealerCommonFrame {

    public BackgroundPanel backgroundPanel;


    public DealerLogin() {

        addBackgroundPanel();
        makeItVisible();
    }

    public void addBackgroundPanel() {

        try {
            BufferedImage backgroundImage = ImageIO.read(new URL("https://www.ilovegermanstyle.net/uploads/files/car-ferrari-aston-martin-ariel-atom-300-porsche-lamborghini.jpg"));

            setLayout(new BorderLayout());

            backgroundPanel = new BackgroundPanel(backgroundImage);

            Container c = getContentPane();

            c.add(backgroundPanel, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] arg) {
        new DealerLogin();
    }



    class BackgroundPanel extends JPanel{

        private BufferedImage backgroundImage;
        private JLabel welcome;

        public BackgroundPanel(BufferedImage backgroundImage) {
            this.backgroundImage = backgroundImage;
            addLoginPanel();

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }


        public void addLoginPanel(){

            welcome =new JLabel("Welcome");
            welcome.setFont(new Font("Times New Roman",Font.BOLD,45));
            JPanel c = new LoginPanel();
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();


//            gc.gridx=0;
//            gc.gridy=0;
//            //gc.weightx=0.1;
//            //gc.weighty=0.1;
//            add(welcome,gc);

            gc.gridx=0;
            gc.gridy=2;
            add(c,gc);
        }
    }


    class LoginPanel extends JPanel{

        private JLabel user_no;
        private JLabel password;
        private JButton btn_login ;
        private JButton btn_exit;
        private JTextField usernameField ;
        private JPasswordField passwordField ;
        private ButtonListener loginPageButtonListener;
        String passwordFromField;



        public LoginPanel(){
            createComponents();
            addComponents();
            createListeners();
            addListeners();
        }


        public void createListeners(){

            loginPageButtonListener = new ButtonListener();

        }

        public void addListeners(){

            btn_login.addActionListener(loginPageButtonListener);
            btn_exit.addActionListener(loginPageButtonListener);
        }

        class ButtonListener implements ActionListener {
            Container c = getContentPane();

            @Override
            public void actionPerformed(ActionEvent e) {

                Object o = e.getSource();
                if (o==btn_login) {

                    passwordFromField = new String(passwordField.getPassword());
                    String dealerName = usernameField.getText();
                    if (dealerName.equals("gmps-curry")&& passwordFromField.equals("HQ1234567") ){

                        //logic for checking password and username

                        dispose();
                        new DealerMainFrame(dealerName);

                    }else {
                        JOptionPane.showMessageDialog(c,"username and password do not match");
                    }
                }

                if (o==btn_exit){
                    System.exit(0);

                }

            }
        }


        public void createComponents(){
            user_no = new JLabel("Username : ");
            user_no.setFont(new Font("Times New Roman",Font.PLAIN,30));
            password = new JLabel("Password : ");
            password.setFont(new Font("Times New Roman",Font.PLAIN,30));

            usernameField = new JTextField(18);
            usernameField.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.8f));

            passwordField = new JPasswordField(18);
            passwordField.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.8f));

            btn_login = new JButton("Sign In");
            btn_login.setFont(new Font("Times New Roman",Font.PLAIN,22));

            btn_exit = new JButton("Exit");
            btn_exit.setFont(new Font("Times New Roman",Font.PLAIN,22));

        }


        private void addComponents() {

            setLayout(new GridBagLayout());
            setBackground(new Color(1.0f, 1.0f, 1.0f, 0.8f));

            GridBagConstraints gc = new GridBagConstraints();

            // First column
            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 0;
            gc.gridy = 1;

            add(user_no, gc);

            gc.gridx = 0;
            gc.gridy = 2;
            add(password, gc);

            // Second column
            gc.anchor = GridBagConstraints.LINE_START;

            gc.gridx = 1;
            gc.gridy = 1;
            add(usernameField, gc);

            gc.gridx = 1;
            gc.gridy = 2;
            add(passwordField, gc);

            // Final row
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.gridx = 1;
            gc.gridy = 3;
            add(btn_login, gc);

            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.gridx = 1;
            gc.gridy = 3;
            add(btn_exit, gc);

        }
    }
}