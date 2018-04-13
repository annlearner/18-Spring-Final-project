package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public class SearchPage {
	
	private JTextField searchTF;
	
	
	
	private void createComponents() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//panel for dealer
		JPanel dealerPanel = new JPanel();
		dealerPanel.setBackground(new Color(0, 191, 255));
		dealerPanel.setForeground(Color.BLACK);
		dealerPanel.setBounds(18, 6, 862, 27);
		frame.getContentPane().add(dealerPanel);
		dealerPanel.setLayout(null);
		
		
		JLabel lblDealerResult = new JLabel("Results from:");
		lblDealerResult.setBounds(152, 6, 93, 16);
		dealerPanel.add(lblDealerResult);
		
		JTextField dealerResultTF = new JTextField();
		dealerResultTF.setBounds(257, 3, 110, 21);
		dealerPanel.add(dealerResultTF);
		dealerResultTF.setColumns(10);
		
		JLabel lblDealerID = new JLabel("ID:");
		lblDealerID.setBounds(390, 6, 24, 16);
		dealerPanel.add(lblDealerID);
		
		JTextField dealerIdTF = new JTextField();
		dealerIdTF.setBounds(414, 1, 130, 26);
		dealerPanel.add(dealerIdTF);
		dealerIdTF.setColumns(10);
		
		
		// panel for search
		Image searchImage = new ImageIcon("/Users/jingxinhao/Desktop/car_1.jpg").getImage();
		frame.getContentPane().setLayout(null);
		JPanel searchPanel = new BackgroundPanel(searchImage);
		searchPanel.setBounds(18, 41, 862, 228);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(475, 157, 123, 36);
		searchPanel.add(searchButton);
		searchTF = new JTextField();
		searchTF.setBounds(238, 160, 189, 26);
		searchPanel.add(searchTF);
		searchTF.setColumns(10);
		
		
		//panel for filter
		Image filterImage = new ImageIcon("/Users/jingxinhao/Desktop/car_3.jpg").getImage();
		JPanel filterPanel = new BackgroundPanel(filterImage);
		filterPanel.setBounds(18, 269, 862, 227);
		frame.getContentPane().add(filterPanel);
		filterPanel.setLayout(null);
			
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setForeground(Color.BLACK);
		lblBrand.setBounds(200, 20, 45, 16);
		filterPanel.add(lblBrand);
		JComboBox cbBrand = new JComboBox();
		cbBrand.setBounds(257, 18, 132, 22);
		filterPanel.add(cbBrand);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(408, 20, 45, 16);
		filterPanel.add(lblModel);
		JComboBox cbModel = new JComboBox();
		cbModel.setBounds(465, 18, 132, 22);
		filterPanel.add(cbModel);
		
		
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(200, 48, 45, 16);
		filterPanel.add(lblPrice);
		JComboBox cbPrice = new JComboBox();
		cbPrice.setBounds(257, 46, 132, 22);
		filterPanel.add(cbPrice);
		
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(408, 48, 45, 16);
		filterPanel.add(lblYear);
		JComboBox cbYear = new JComboBox();
		cbYear.setBounds(465, 52, 132, 22);
		filterPanel.add(cbYear);

		
		JLabel lblMile = new JLabel("Mileage:");
		lblMile.setBounds(200, 88, 61, 16);
		filterPanel.add(lblMile);
		JComboBox cbMileage = new JComboBox();
		cbMileage.setBounds(257, 86, 132, 22);
		filterPanel.add(cbMileage);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(690, 43, 117, 29);
		filterPanel.add(btnSearch);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(690, 83, 117, 29);
		filterPanel.add(btnCancel);
		
		//panel for sort
		Image SortBgImage = new ImageIcon("/Users/jingxinhao/Desktop/car_4.jpg").getImage();
		JPanel sortPanel = new BackgroundPanel(SortBgImage);
		sortPanel.setBounds(18, 495, 862, 52);
		frame.getContentPane().add(sortPanel);
		sortPanel.setLayout(null);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(25, 16, 61, 16);
		sortPanel.add(lblSortBy);
		JButton btnLatest = new JButton("Latest");
		btnLatest.setBounds(92, 11, 105, 29);
		sortPanel.add(btnLatest);
		JButton btnPriceLowest = new JButton("Price lowest");
		btnPriceLowest.setBounds(213, 11, 117, 29);
		sortPanel.add(btnPriceLowest);
		
		JButton btnPriceHighest = new JButton("Price highest");
		btnPriceHighest.setBounds(347, 11, 117, 29);
		sortPanel.add(btnPriceHighest);
		
		// panel for result
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(18, 544, 855, 134);
		resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Results"));
		frame.getContentPane().add(resultPanel);
		resultPanel.setLayout(null);
		
		JTextArea txtCar = new JTextArea();
		txtCar.setBounds(342, 19, 158, 92);
		txtCar.setText("Car Name: \nSmart Fortwo Pure\nPrice:\n $ 20999\nDescription: \nPurposeful and beautiful, all in one\n");

		resultPanel.add(txtCar);
		
		JLabel lCar = new JLabel();
		ImageIcon car = new ImageIcon("/Users/jingxinhao/Desktop/car.png");
		lCar.setIcon(car);
		lCar.setBounds(100, 20, 200, 100);
		resultPanel.add(lCar);
	
		
		frame.setTitle("Vehicle Trader");
		frame.setVisible(true);
		
		
		
	}
	
	public SearchPage() {
		createComponents();
	}

	
	public static void main(String[] args) {
	
		new SearchPage();
		
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


