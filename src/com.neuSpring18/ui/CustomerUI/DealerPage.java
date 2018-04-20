package com.neuSpring18.ui.CustomerUI;
import com.neuSpring18.dto.Dealer;
import com.neuSpring18.service.DealerService;
import com.neuSpring18.service.DealerServiceImple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class DealerPage extends JFrame{

	private ImageIcon image;
	private JLabel label;
	private String[] dealerString= {"Dealer 1","Dealer 2","Dealer 3","Dealer 4","Dealer 5"};;
	private JComboBox cmbDealerList;
	private JLabel promptlabel;
	private JButton comfirmButton;
//	GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints gbcImage = new GridBagConstraints();
	private GridBagConstraints gbcTitle = new GridBagConstraints();
	private GridBagConstraints gbcPromptlabel = new GridBagConstraints();
	private GridBagConstraints gbcCmb = new GridBagConstraints();
	private GridBagConstraints gbcConfirm = new GridBagConstraints();
	private JLabel title=new JLabel("Dealer Choice Page");
	DealerPage(){
		setLayout(new GridBagLayout());
		setLocation();
		content();
		combox();
		setFont();
		add();
		jumpPage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setTitle("Dealer Choice Page");
//		public void jumpPage(){
//		event e = new event();//互相调用
//		comfirmButton.addActionListener(e);
//	}
//		image = new ImageIcon(getClass().getResource("../../../../dealer.png"));
//		label=new JLabel(image);
//		gbc.gridx=0;
//		gbc.gridy=2;

		
	
//		gbc.gridx=0;
//		gbc.gridy=0;
//		title.setFont(new Font("",1,30));
//		add(title, gbc);

		
//		gbc.gridx=0;
//		gbc.gridy=5;
//		promptlabel.setFont(new Font("",1,30));
//		add(promptlabel,gbc);
		
//		gbc.gridx=0;
//		gbc.gridy=10;
//		cmbDealerList.setSize(200,200);
//		cmbDealerList.setFont(new Font("",1,30));
//		add(cmbDealerList,gbcCmb);
		
//		gbc.gridx=0;
//		gbc.gridy=20;
//		comfirmButton.setFont(new Font("",1,30));
//		comfirmButton.setSize(100,100);
//		add(comfirmButton,gbcConfirm);
		
	}

	public void setLocation(){
		gbcImage.gridx=0;
		gbcImage.gridy=2;
		gbcTitle.gridx=0;
		gbcTitle.gridy=0;
		gbcPromptlabel.gridx=0;
		gbcPromptlabel.gridy=5;
		gbcCmb.gridx=0;
		gbcCmb.gridy=10;
		gbcConfirm.gridx=0;
		gbcConfirm.gridy=20;

	}

	public void content(){
		promptlabel=new JLabel("please choose your favoriate dealer");
		comfirmButton=new JButton("Confirm");
		image = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/dealer.png");
		label=new JLabel(image);
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

	public void setFont(){
		title.setFont(new Font("",1,30));
		promptlabel.setFont(new Font("",1,30));
		cmbDealerList.setSize(200,400);
		cmbDealerList.setFont(new Font("",1,30));
		comfirmButton.setFont(new Font("",1,30));
		comfirmButton.setSize(100,100);
	}

	public void add(){
		add(label,gbcImage);
		add(title, gbcTitle);
		add(promptlabel,gbcPromptlabel);
		add(cmbDealerList,gbcCmb);
		add(comfirmButton,gbcConfirm);

	}
	public void jumpPage(){
		event e = new event();//互相调用
		comfirmButton.addActionListener(e);
	}
	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			new SearchPage((String)cmbDealerList.getSelectedItem());
		}
	}
	
	public static void main(String args[]) {
		DealerPage gui = new DealerPage();
//		gui.setLocation();
//		gui.content();
//		gui.combox();
//		gui.setFont();
//		gui.add();
//		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gui.setVisible(true);
//		gui.pack();
//		gui.setTitle("Dealer Choice Page");
	}
}
