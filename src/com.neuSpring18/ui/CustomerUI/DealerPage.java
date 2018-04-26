package com.neuSpring18.ui.CustomerUI;
import com.neuSpring18.dto.Dealer;
import com.neuSpring18.service.DealerService;
import com.neuSpring18.service.DealerServiceImple;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class DealerPage extends JFrame{
	private JPanel contentPane;
	//top panel
	private JPanel topPanel;
	private JButton Aboutus;
	private JButton Login;
	private JLabel  topLabel;
	//button panel
	private JPanel buttonPanel;
	private JButton feedback;
	//right panel
	private JPanel rightPanel;
	private JLabel rightLabel;
	//mid panel
	private JPanel midPanel;

	private JLabel promptLabel;
	private JComboBox cmbDealerList;
	private JButton confirmButton;
	//leftPanel
	private JPanel leftPanel;
	private JLabel leftImagea;

	public DealerPage(){
		basicOperation();
		panelSettings();
		pageSwitches();
		setVisible(true);
		setTitle("Dealer Choice Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void basicOperation(){
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void pageSwitches(){
		jumpPage();
		log();
		havefeedback();
	}

	public void panelSettings(){
		designTop();
		designMid();
		designLeft();
		designRight();
		designButtom();
	}

	public String[] getDealerList(){
		DealerService ds = new DealerServiceImple();
		Collection<Dealer> dealers = ds.findAllDealers();
		java.util.List<String> idList = new ArrayList<String>();
		int num=0;
		for(Dealer ele:dealers){
			idList.add(ele.getId());
			num++;
		}
		String[] idDisplay = new String[num];
		idDisplay=idList.toArray(new String[num]);
		return idDisplay;
	}

	public void combox(){

		cmbDealerList = new JComboBox(getDealerList());
	}


	public void designTop() {

		Image TopImage = new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/image/topDown.jpeg")).getImage();
		topPanel=new BackgroundPanel(TopImage);

		topPanel.setBounds(6, 6, 438, 21);
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		Aboutus=new JButton("About us");

		JButton Aboutus = new JButton("About Us");
		Aboutus.setBounds(0, 0, 96, 20);
		topPanel.add(Aboutus);

		Login = new JButton("Log In");
		Login.setBounds(321, 0, 117, 20);
		topPanel.add(Login);

	}

	public void designMid() {
		//Panel
		Image MidImage = new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/image/middle.jpg")).getImage();
		midPanel=new BackgroundPanel(MidImage);
		midPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		midPanel.setBounds(113, 28, 201, 212);
		contentPane.add(midPanel);
		midPanel.setLayout(null);
		//promptLabel
		promptLabel = new JLabel("Select User ID");
		promptLabel.setBounds(43, 6, 98, 16);
		midPanel.add(promptLabel);
		//cmb
		combox();
		cmbDealerList.setBounds(28, 44, 143, 27);
		midPanel.add(cmbDealerList);
		//confirmButton
		confirmButton=new JButton("Confirm");
		confirmButton.setBounds(43, 108, 98, 29);
		midPanel.add(confirmButton);
	}

	public void designLeft(){
		//Panel
		Image leftImage= new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/image/leftRight.jpg")).getImage();
		leftPanel=new BackgroundPanel(leftImage);
		leftPanel.setBounds(6, 28, 101, 212);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
	}

	public void designRight(){
		//Panel
		Image rightImage= new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/image/leftRight.jpg")).getImage();
		rightPanel=new BackgroundPanel(rightImage);
		rightPanel.setBounds(319, 28, 125, 212);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
	}

	public void designButtom(){
		Image buttonImage=new ImageIcon(("src/com.neuSpring18/ui/CustomerUI/image/topDown.jpeg")).getImage();
		buttonPanel=new BackgroundPanel(buttonImage);
		buttonPanel.setBounds(6, 242, 438, 30);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);

		feedback= new JButton("feedback");
		feedback.setBounds(6, 6, 117, 29);
		buttonPanel.add(feedback);
	}
	public class toConfirm implements ActionListener{

		public void actionPerformed(ActionEvent e){
			dispose();
			new SearchPage((String)cmbDealerList.getSelectedItem());
		}
	}

	public void jumpPage(){
		toConfirm e = new toConfirm();//互相调用
		confirmButton.addActionListener(e);
	}

	public class register implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new LOG();
		}
	}

	public void log(){
		register e = new register();
		Login.addActionListener(e);
	}

	public void havefeedback(){
		fb feed=new fb();
		feedback.addActionListener(feed);
	}

	public class fb implements ActionListener{
		public void actionPerformed(ActionEvent feed){
			new FEEDBACK();
		}
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

	public static void main(String args[]) {
		new DealerPage();

	}

}

