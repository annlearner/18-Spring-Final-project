package Lesson3;
import java.awt.*;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.*;

public class ImageTutorial extends JFrame{

	private ImageIcon image;
	private JLabel label;
	private String[] dealerString= {"Dealer 1","Dealer 2","Dealer 3","Dealer 4","Dealer 5"};;
	private JComboBox cmbDealerList = new JComboBox(dealerString);
	private JLabel promptlabel=new JLabel("please choose your favoriate dealer");
	private JButton comfirmButton=new JButton("Confirm");
	GridBagConstraints gbc = new GridBagConstraints();
	private JLabel title=new JLabel("Dealer Choice Page");
	ImageTutorial(){
		setLayout(new GridBagLayout());
		
		
		
		image = new ImageIcon(getClass().getResource("dealer.png"));
		label=new JLabel(image);
		gbc.gridx=0;
		gbc.gridy=2;
		add(label,gbc);
		
	
		gbc.gridx=0;
		gbc.gridy=0;
		title.setFont(new Font("",1,30));
		add(title, gbc);

		
		gbc.gridx=0;
		gbc.gridy=5;
		promptlabel.setFont(new Font("",1,30));
		add(promptlabel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=10;
		cmbDealerList.setSize(200,200);
		cmbDealerList.setFont(new Font("",1,30));
		add(cmbDealerList,gbc);
		
		gbc.gridx=0;
		gbc.gridy=20;
		comfirmButton.setFont(new Font("",1,30));
		comfirmButton.setSize(100,100);
		add(comfirmButton,gbc);
		
	}
	
	public static void main(String args[]) {
		ImageTutorial gui = new ImageTutorial();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
		 gui.pack();
		 gui.setTitle("Dealer Choice Page");
	}
}
