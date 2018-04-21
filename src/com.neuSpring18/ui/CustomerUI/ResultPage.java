package com.neuSpring18.ui.CustomerUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPage extends JFrame {
//
//    JLabel carID,webPageId,category,year,make,model, bodyType, price, pictureOfcar;
//    JFrame ResultPage;
//    JTextField IDOfCar,webPageIdOfCar,categoryfCar,yearOfCar,makeOfCar,modelOfCar,bodyTypeOfCar,pricefCar;
//    JButton backButton,exitButton;
//    ImageIcon picture;
//    Image bgimage;
//    String dealerId;

    JLabel carID,webPageId,category,year,make,model, bodyType, price, pictureOfcar,IDOfCar,webPageIdOfCar,categoryfCar,yearOfCar,makeOfCar,modelOfCar,bodyTypeOfCar,pricefCar;
    JFrame ResultPage;
    JButton backButton,exitButton;
    ImageIcon picture;
    Image bgimage;
    String dealerId;

    public ResultPage(String dealerId){
        this.dealerId = dealerId;
        createComponents();
        addComponents();
        makeVisible();
        Createlisteners();
        addListeners();
    }

    private void addListeners() {
        backButton.addActionListener(new buttonclick());
        exitButton.addActionListener(new buttonclick());
    }

    private void Createlisteners() {
        new buttonclick();
    }



    public class buttonclick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object click=e.getSource();
            if(click == backButton){

                new SearchPage(dealerId);
            }
            if (click == exitButton){
                ResultPage.setVisible(false);
            }
        }
    }

    private void makeVisible() {
        ResultPage.setBounds(100,200,200,100);
        backButton.setBounds(1,2,2,2);
//        ResultPage.setBackground(Color.blue);
        ResultPage.setIconImage(bgimage);
        ResultPage.setTitle("ResultPage");
        ResultPage.setVisible(true);
        ResultPage.pack();
    }

    private void addComponents() {

        JPanel panel=new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(new GridLayout(8,2,2,8));

        panel.add(carID);
        panel.add(IDOfCar);
        panel.add(webPageId);
        panel.add(webPageIdOfCar);
        panel.add(category);
        panel.add(categoryfCar);
        panel.add(year);
        panel.add(yearOfCar);
        panel.add(make);
        panel.add(makeOfCar);
        panel.add(model);
        panel.add(modelOfCar);
        panel.add(price);
        panel.add(pricefCar);
        panel.add(bodyType);
        panel.add(bodyTypeOfCar);

        JPanel panel1= new BackgroundPanel(bgimage);
        panel1.add(pictureOfcar);
        panel1.add(panel);
        panel1.add(backButton);
        panel1.add(exitButton);

        ResultPage=new JFrame();
        ResultPage.add(panel1,"Center");
       // ResultPage.add(panel,"East");
       // ResultPage.add(backButton,BorderLayout.BEFORE_FIRST_LINE);// ResultPage.add(backButton);
       // ResultPage.add(exitButton,BorderLayout.BEFORE_LINE_BEGINS);
       // ResultPage.add(pictureOfcar,"West");


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

    private void createComponents() {

//      Creating Labels
//        carID=new JLabel("Car ID");
//        webPageId=new JLabel("Link to page");
//        category=new JLabel("Category");
//        year=new JLabel("Year");
//        make=new JLabel("Make");
//        model=new JLabel("Model");
//        bodyType=new JLabel("Body Type");
//        price=new JLabel("Price");
//        pictureOfcar=new JLabel();
//
////      Creating Textfields
//        IDOfCar=new JTextField(20);
//        webPageIdOfCar=new JTextField(20);
//        categoryfCar=new JTextField(20);
//        yearOfCar=new JTextField(20);
//        makeOfCar=new JTextField(20);
//        modelOfCar=new JTextField(20);
//        bodyTypeOfCar=new JTextField(20);
//        pricefCar=new JTextField(20);

        carID=new JLabel("Car ID",0);
        carID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        //carID.setForeground(Color.BLACK);
        webPageId=new JLabel("Link to page",0);
        webPageId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        category=new JLabel("Category",0);
        category.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        year=new JLabel("Year",0);
        year.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        make=new JLabel("Make",0);
        make.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        model=new JLabel("Model",0);
        model.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        bodyType=new JLabel("Body Type",0);
        bodyType.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        price=new JLabel("Price",0);
        price.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        pictureOfcar=new JLabel();
        IDOfCar=new JLabel();
        webPageIdOfCar=new JLabel();
        categoryfCar=new JLabel();
        yearOfCar=new JLabel();
        makeOfCar=new JLabel();
        modelOfCar=new JLabel();
        bodyTypeOfCar=new JLabel();
        pricefCar=new JLabel();

//      Creating Buttons
        backButton=new JButton("Back");
        backButton.setBounds(1,2,2,1);
        exitButton=new JButton("Exit");

        picture=new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/mainPic.png");
        pictureOfcar.setIcon(picture);
        bgimage=new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/car-logo.jpg").getImage();

    }
//    public static void main(String args[]){
//        new ResultPage();
//    }

}
