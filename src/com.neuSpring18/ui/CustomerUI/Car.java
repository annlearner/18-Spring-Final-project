package com.neuSpring18.ui.CustomerUI;

import com.neuSpring18.dto.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Car extends JFrame {

    private JLabel bodyType;
    private JLabel bodyTypeTitle;
    private JPanel briefyInformationPanel;
    private JPanel carDescriptionPanel;
    private JLabel carPicture;
    private JLabel category;
    private JLabel categoryTitle;
    private JLabel centerDescriptionPartOne;
    private JLabel centerDescriptionPartThree;
    private JLabel centerDescriptionPartTwo;
    private JLabel centerInfoTitle;
    private JLabel description;
    private JLabel descriptionTitle;
    private JPanel informationPanel;
    private JLabel make;
    private JLabel makeTitle;
    private JLabel model;
    private JLabel modelTitle;
    private JPanel picturePanel;
    private JLabel price;
    private JLabel priceTitle;

    private Vehicle vehicle;

    public Car(Vehicle vehicle) {
        this.vehicle = vehicle;
        initComponents();
    }

    private void initComponents() {
        initPicturePanel();
        initBriefyInformationPanel();
        initCarDescriptionPanell();
        initInformationPanel();
        pageLayout();
        contentPanelLayout();
        initBriefyInformationPanelText();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }


    private void initPicturePanel() {
        picturePanel = new JPanel();
        String urlPicture = vehicle.getPhotoUrl().toString();
        BufferedImage image = null;
        Image icon = null;
        try {
            URL url = new URL(urlPicture);
            image = ImageIO.read(url);

            icon = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (icon == null) {
            handleNullError(urlPicture, image, icon);
        } else {
            carPicture = new JLabel(new ImageIcon(icon));
        }

    }

    public void handleNullError(String urlPicture, BufferedImage image, Image icon) {
        urlPicture = "http://gfi.fieci-cfecgc.org/wp-content/uploads/sites/30/2014/03/NOK.png";
        try {
            URL urlOnline = new URL(urlPicture);
            image = ImageIO.read(urlOnline);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        carPicture = new JLabel(new ImageIcon(icon));
    }

    private void initBriefyInformationPanel() {
        briefyInformationPanel = new JPanel();
        modelTitle = new JLabel();
        model = new JLabel();
        makeTitle = new JLabel();
        make = new JLabel();
        bodyType = new JLabel();
        bodyTypeTitle = new JLabel();
        categoryTitle = new JLabel();
        category = new JLabel();
        priceTitle = new JLabel();
        price = new JLabel();

    }

    private void initCarDescriptionPanell() {
        carDescriptionPanel = new JPanel();
        description = new JLabel();
        descriptionTitle = new JLabel();
    }

    private void initInformationPanel() {
        informationPanel = new JPanel();
        centerInfoTitle = new JLabel();
        centerDescriptionPartOne = new JLabel();
        centerDescriptionPartTwo = new JLabel();
        centerDescriptionPartThree = new JLabel();
    }

    private void initBriefyInformationPanelText() {

        initModelText();
        initMakeText();
        initCategoryText();
        initBodyTypeText();
        initPriceText();
        initDescriptionText();
        initCenterInfo();

    }

    private void initModelText() {
        modelTitle.setFont(new Font("Arial Black", 1, 13));
        modelTitle.setText("Model:");
        model.setText(vehicle.getModel());

    }

    private void initMakeText() {

        makeTitle.setFont(new Font("Arial Black", 1, 13));
        makeTitle.setText("Make:");
        make.setText(vehicle.getMake());
    }

    private void initCategoryText() {
        categoryTitle.setFont(new Font("Arial Black", 1, 13));
        categoryTitle.setText("Category:");
        category.setText(vehicle.getCategory().toString());
    }

    private void initBodyTypeText() {
        bodyTypeTitle.setFont(new Font("Arial Black", 1, 13));
        bodyTypeTitle.setText("BodyType:");
        bodyType.setText(vehicle.getBodyType().toString());
    }

    private void initPriceText() {
        priceTitle.setFont(new Font("Arial Black", 1, 13));
        priceTitle.setText("Price:");
        price.setText(Double.toString(vehicle.getPrice()));
    }

    private void initDescriptionText() {
        description.setText("<html>\n<div>\n<h1>Purposeful and beautiful, all in one</h1>\n<div>\nThe electric drive pure features the iconic smart body style, with a wide, athletic stance that provides ample space inside and excellent on-road performance. The two-tone color palette and LED daytime running lights are the icing on the cake.</div>\n</div> \n</html>");

        descriptionTitle.setFont(new Font("Arial Black", 1, 13));
        descriptionTitle.setText("Email Me");
        descriptionTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me");
                SwingEmailSender.invokedBySearchPage();
            }

        });


    }

    private void initCenterInfo() {
        centerInfoTitle.setText("<html>\n<div>\n<h1><font face=\"verdana\">THE PERFECT TYPE OF POWER TRIP</font></h1>\n\n</div>\n</html>");
        centerDescriptionPartOne.setFont(new Font("Lucida Grande", 3, 13));
        centerDescriptionPartOne.setText("<html>\n<div>\n\n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">For navigating your city, or escaping it altogether,</font></p>\n\n\n</div>\n</html>");
        centerDescriptionPartTwo.setFont(new Font("Lucida Grande", 3, 13));
        centerDescriptionPartTwo.setText("<html> <p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">The 2018 electric drive features a high-tech interior, \ncustomizable style </font></p> </html>");
        centerDescriptionPartThree.setFont(new Font("Lucida Grande", 3, 13));
        centerDescriptionPartThree.setText("<html> \n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\"> and impressive electric performance..</font></p> </html>");
    }


    private void pageLayout() {
        picturePanelLayout();
        briefyInformationPanelLayout();
        carDescriptionPanelLayout();
        informationPanelLayout();

    }

    private void picturePanelLayout() {
        GroupLayout picturePanelLayout = new GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        setPicturePanelLayoutHorizontalGroup(picturePanelLayout);
        setPicturePanelLayoutVerticalGroup(picturePanelLayout);

    }

    private void setPicturePanelLayoutHorizontalGroup(GroupLayout picturePanelLayout) {
        picturePanelLayout.setHorizontalGroup(
                picturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(picturePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(carPicture, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
        );
    }

    private void setPicturePanelLayoutVerticalGroup(GroupLayout picturePanelLayout) {
        picturePanelLayout.setVerticalGroup(
                picturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(picturePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(carPicture, GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void carDescriptionPanelLayout() {
        GroupLayout carDescriptionPanelLayout = new GroupLayout(carDescriptionPanel);
        carDescriptionPanel.setLayout(carDescriptionPanelLayout);
        setCarDescriptionPanelHorizontalGroup(carDescriptionPanelLayout);
        setCarDescriptionPanelVerticalGroup(carDescriptionPanelLayout);

    }

    private void setCarDescriptionPanelHorizontalGroup(GroupLayout carDescriptionPanelLayout) {
        carDescriptionPanelLayout.setHorizontalGroup(
                carDescriptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(carDescriptionPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(descriptionTitle, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                .addGap(70, 70, 70)
                                .addComponent(description, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
        );
    }

    private void setCarDescriptionPanelVerticalGroup(GroupLayout carDescriptionPanelLayout) {
        carDescriptionPanelLayout.setVerticalGroup(
                carDescriptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, carDescriptionPanelLayout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(description, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, carDescriptionPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(descriptionTitle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))
        );
    }

    private void briefyInformationPanelLayout() {
        GroupLayout briefyInformationPanelLayout = new GroupLayout(briefyInformationPanel);
        briefyInformationPanel.setLayout(briefyInformationPanelLayout);
        setBriefyInformationPanelHorizontalGroup(briefyInformationPanelLayout);
        setBriefyInformationPanelsetVerticalGroup(briefyInformationPanelLayout);

    }

    private void setBriefyInformationPanelHorizontalGroup(GroupLayout briefyInformationPanelLayout) {
        briefyInformationPanelLayout.setHorizontalGroup(
                briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(briefyInformationPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(makeTitle)
                                        .addComponent(modelTitle, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bodyTypeTitle)
                                        .addComponent(priceTitle, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(categoryTitle))
                                .addGap(89, 89, 89)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(price)
                                        .addComponent(bodyType, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(category)
                                        .addComponent(make)
                                        .addComponent(model))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
    }

    private void setBriefyInformationPanelsetVerticalGroup(GroupLayout briefyInformationPanelLayout) {
        briefyInformationPanelLayout.setVerticalGroup(
                briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(briefyInformationPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(modelTitle)
                                        .addComponent(model))
                                .addGap(12, 12, 12)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(makeTitle)
                                        .addComponent(make))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(price)
                                        .addComponent(priceTitle, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bodyTypeTitle)
                                        .addComponent(bodyType))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(briefyInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(category)
                                        .addComponent(categoryTitle))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }

    private void informationPanelLayout() {
        GroupLayout informationPanelLayout = new GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        setInformationPanelLayoutHorizontalGroup(informationPanelLayout);
        setInformationPanelLayoutVerticalGroup(informationPanelLayout);


    }

    private void setInformationPanelLayoutHorizontalGroup(GroupLayout informationPanelLayout) {
        informationPanelLayout.setHorizontalGroup(
                informationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(informationPanelLayout.createSequentialGroup()
                                .addGroup(informationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addComponent(centerInfoTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addGap(106, 106, 106)
                                                .addComponent(centerDescriptionPartOne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(centerDescriptionPartTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addGap(140, 140, 140)
                                                .addComponent(centerDescriptionPartThree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void setInformationPanelLayoutVerticalGroup(GroupLayout informationPanelLayout) {
        informationPanelLayout.setVerticalGroup(
                informationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(informationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(centerInfoTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(centerDescriptionPartOne, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(centerDescriptionPartTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(centerDescriptionPartThree, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(42, Short.MAX_VALUE))
        );
    }

    private void contentPanelLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setGeneralHorizontalGroup(layout);
        setGeneralVerticalGrou(layout);
    }

    private void setGeneralHorizontalGroup(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(picturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(36, 36, 36)
                                                                .addComponent(carDescriptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(briefyInformationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(informationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }

    private void setGeneralVerticalGrou(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(16, Short.MAX_VALUE)
                                                .addComponent(briefyInformationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(carDescriptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(109, 109, 109)
                                                .addComponent(picturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(24, 24, 24)
                                .addComponent(informationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );

    }


}
