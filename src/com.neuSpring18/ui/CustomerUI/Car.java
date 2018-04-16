
package com.neuSpring18.ui.CustomerUI;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author wenmin
 */
public class Car extends javax.swing.JFrame {
	private JLabel briefDescription;
	private JLabel carDescription;
	private JLabel carName;
	private JLabel carNameInfo;
	private JLabel carPrice;
	private JLabel carPriceInfo;
	private JLabel centerDescription;
	private JLabel centerTitle;
	private JLabel featureContent1;
	private JLabel featureContent2;
	private JLabel mainCarPic;
	private JLabel innerCarPicture;
	private JLabel chairInCarPicture;
	private JLabel outlineOfCarPicture;

	public Car() {
		initComponents();
		addComponents();
		setLayout();

	}

	private void initComponents() {

		mainCarPic = new JLabel();
		carName = new JLabel();
		carPrice = new JLabel();
		briefDescription = new JLabel();
		innerCarPicture = new JLabel();
		chairInCarPicture = new JLabel();
		outlineOfCarPicture = new JLabel();
		carNameInfo = new JLabel();
		carPriceInfo = new JLabel();
		carDescription = new JLabel();
		centerTitle = new JLabel();
		centerDescription = new JLabel();
		featureContent1 = new JLabel();
		featureContent2 = new JLabel();

	}

	private void addPicture(){

		ImageIcon iconLogo = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/mainPic.png");
		mainCarPic.setIcon(iconLogo);

		ImageIcon iconLogo1 = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/carP1.png");
		innerCarPicture.setIcon(iconLogo1);

		ImageIcon iconLogo2 = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/carP2.png");

		chairInCarPicture.setIcon(iconLogo2);

		ImageIcon iconLogo3 = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/carP3.png");

		outlineOfCarPicture.setIcon(iconLogo3);
	}
	private void addText(){
		carName.setText("Car Name:");
		carPrice.setText("Price:");
		briefDescription.setText("Description：");
		carNameInfo.setText("Smart Fortwo Pure");
		carPriceInfo.setText("$20999");
		carDescription.setText(
				"<html>\n<div>\n<h1>Purposeful and beautiful, all in one</h1>\n<div>\nThe electric drive pure features the iconic smart body style, with a wide, athletic stance that provides ample space inside and excellent on-road performance. The two-tone color palette and LED daytime running lights are the icing on the cake.</div>\n</div> \n</html>");
		centerTitle.setText(
				"<html>\n<div>\n<h1><font face=\"verdana\">THE PERFECT TYPE OF POWER TRIP</font></h1>\n\n</div>\n</html>");
		centerDescription.setText(
				"<html>\n<div>\n\n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">For navigating your city, or escaping it altogether,</font></p>\n\n\n</div>\n</html>");
		featureContent1.setText(
				"<html> <p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">The 2018 electric drive features a high-tech interior, \ncustomizable style </font></p> </html>");
		featureContent2.setText(
				"<html> \n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\"> and impressive electric performance.</font></p> </html>");

	}
	private void addComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addPicture();
		addText();


	}

	private void setLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		setHorizontalGroup(layout);
		setVerticalGroup(layout);
		pack();

	}
	private void setHorizontalGroup(GroupLayout layout){
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(mainCarPic,
								GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(innerCarPicture, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(chairInCarPicture, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10).addComponent(outlineOfCarPicture)))
				.addGap(4, 4, 4)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(carName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(carPrice)
						.addComponent(briefDescription, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(carNameInfo))
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addComponent(carPriceInfo))
						.addComponent(carDescription, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
				.addGroup(layout.createSequentialGroup().addGap(50, 50, 50).addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(featureContent1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addComponent(centerTitle,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(80, 80, 80).addComponent(centerDescription,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGroup(layout.createSequentialGroup().addGap(180, 180, 180).addComponent(featureContent2,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
	}

	private void setVerticalGroup(GroupLayout layout){
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(60, 60, 60).addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(mainCarPic, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(innerCarPicture, GroupLayout.PREFERRED_SIZE,
												60, GroupLayout.PREFERRED_SIZE)
										.addComponent(chairInCarPicture, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(outlineOfCarPicture, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(carName).addGap(14, 14, 14)
										.addComponent(carPrice).addGap(14, 14, 14).addComponent(briefDescription))
								.addGroup(layout.createSequentialGroup().addComponent(carNameInfo).addGap(14, 14, 14)
										.addComponent(carPriceInfo).addGap(4, 4, 4).addComponent(carDescription,
												GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))))
						.addGap(40, 40, 40)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(90, 90, 90).addComponent(
										featureContent1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(centerTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addComponent(centerDescription,
										GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGap(8, 8, 8).addComponent(featureContent2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

	}
	public static void main(String args[]) {
		new Car().setVisible(true);
	}

}
