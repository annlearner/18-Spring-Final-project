
package cars;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author wenmin
 */
public class Car extends javax.swing.JFrame {
	private JLabel brieflyDescrip;
	private JLabel carDescription;
	private JLabel carName;
	private JLabel carNameInfo;
	private JLabel carPrice;
	private JLabel carPriceInfo;
	private JLabel cenDes;
	private JLabel centerTitle;
	private JLabel featureContent1;
	private JLabel featureContent2;
	private JLabel mainCarPic;
	private JLabel picture1;
	private JLabel picture2;
	private JLabel picture4;

	public Car() {
		initComponents();
		addComponents();
		setLayout();

	}

	private void initComponents() {

		mainCarPic = new JLabel();
		carName = new JLabel();
		carPrice = new JLabel();
		brieflyDescrip = new JLabel();
		picture1 = new JLabel();
		picture2 = new JLabel();
		picture4 = new JLabel();
		carNameInfo = new JLabel();
		carPriceInfo = new JLabel();
		carDescription = new JLabel();
		centerTitle = new JLabel();
		cenDes = new JLabel();
		featureContent1 = new JLabel();
		featureContent2 = new JLabel();

	}

	private void addComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mainCarPic.setIcon(new ImageIcon(getClass().getResource("/cars/mainPic.png")));
		carName.setText("Car Name:");
		carPrice.setText("Price:");
		brieflyDescrip.setText("Description：");

		picture1.setIcon(new ImageIcon(getClass().getResource("/cars/carP1.png")));
		picture1.setText("jLabel4");

		picture2.setIcon(new ImageIcon(getClass().getResource("/cars/carP2.png")));
		picture2.setText("jLabel4");

		picture4.setIcon(new ImageIcon(getClass().getResource("/cars/carP3.png")));

		carNameInfo.setText("Smart Fortwo Pure");

		carPriceInfo.setText("$20999");

		carDescription.setText(
				"<html>\n<div>\n<h1>Purposeful and beautiful, all in one</h1>\n<div>\nThe electric drive pure features the iconic smart body style, with a wide, athletic stance that provides ample space inside and excellent on-road performance. The two-tone color palette and LED daytime running lights are the icing on the cake.</div>\n</div> \n</html>");

		centerTitle.setText(
				"<html>\n<div>\n<h1><font face=\"verdana\">THE PERFECT TYPE OF POWER TRIP</font></h1>\n\n</div>\n</html>");

		cenDes.setText(
				"<html>\n<div>\n\n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">For navigating your city, or escaping it altogether,</font></p>\n\n\n</div>\n</html>");

		featureContent1.setText(
				"<html> <p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\">The 2018 electric drive features a high-tech interior, \ncustomizable style </font></p> </html>");

		featureContent2.setText(
				"<html> \n<p><font size=\"5\" face=\"arial\" color=\"#A9A9A9\"> and impressive electric performance.</font></p> </html>");

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
								.addComponent(picture1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(picture2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10).addComponent(picture4)))
				.addGap(4, 4, 4)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(carName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(carPrice)
						.addComponent(brieflyDescrip, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
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
						.addGroup(layout.createSequentialGroup().addGap(80, 80, 80).addComponent(cenDes,
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
										.addComponent(picture1, GroupLayout.PREFERRED_SIZE,
												60, GroupLayout.PREFERRED_SIZE)
										.addComponent(picture2, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(picture4, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(carName).addGap(14, 14, 14)
										.addComponent(carPrice).addGap(14, 14, 14).addComponent(brieflyDescrip))
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
								.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addComponent(cenDes,
										GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGap(8, 8, 8).addComponent(featureContent2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		
	}
	public static void main(String args[]) {
		new Car().setVisible(true);
	}

}
