package com.neuSpring18.ui.CustomerUI;

import com.neuSpring18.dto.*;
import com.neuSpring18.service.VehicleServiceImple;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;


public class SearchPage {

	private JFrame frame;

	private JPanel dealerPanel;
	private JLabel lblDealerResult;
	private JTextField dealerResultTF;
	private JLabel lblDealerID;
	private JTextField dealerIdTF;
	private JPanel searchPanel;
	private JButton searchButton;
	private JTextField searchTF;
	private JPanel filterPanel;
	private JLabel lblBrand;
	private JComboBox cbBrand;
	private JLabel lblModel;
	private JComboBox cbModel;
	private JLabel lblPrice;
	private JComboBox cbPriceMin;
	private JComboBox cbPriceMax;
	private JLabel lblYear;
	private JComboBox cbYearMin;
	private JComboBox cbYearMax;
	private JLabel lblMile;
	private JComboBox cbMileage;
	private JButton btnSearch;
	private JButton btnCancel;
	private JPanel sortPanel;
	private JLabel lblSortBy;
	private JButton btnLatest;
	private JButton btnPriceLowest;
	private JButton btnPriceHighest;
	private JPanel resultPanel;
	private OperatorListener operatorListener;


	// table
    //	private JPanel result;
	private JScrollPane tableScrollPane;
	private JTable vehiclesAfterFilter;
	Object[][] vehicleRow;

	String dealerID;
	//  vehicles of the dealer
	ArrayList<Vehicle> vehicles;


	public SearchPage(String dealerID) {
		this.dealerID = dealerID;
		createComponents();
		createListeners();
		addListeners();


	}

	
	
	
	private void createComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//panel for dealer
		dealerPanel = new JPanel();
		dealerPanel.setBackground(new Color(0, 191, 255));
		dealerPanel.setForeground(Color.BLACK);
		dealerPanel.setBounds(18, 6, 862, 27);
		frame.getContentPane().add(dealerPanel);
		dealerPanel.setLayout(null);
		
		
		lblDealerResult = new JLabel("Results from:");
		lblDealerResult.setBounds(152, 6, 93, 16);
		dealerPanel.add(lblDealerResult);
		
		dealerResultTF = new JTextField();
		dealerResultTF.setBounds(257, 3, 110, 21);
		dealerPanel.add(dealerResultTF);
		dealerResultTF.setColumns(10);
		
		lblDealerID = new JLabel("ID:");
		lblDealerID.setBounds(390, 6, 24, 16);
		dealerPanel.add(lblDealerID);
		
		dealerIdTF = new JTextField();
		dealerIdTF.setBounds(414, 1, 130, 26);
		dealerPanel.add(dealerIdTF);
		dealerIdTF.setColumns(10);
		
		
		// panel for search
		Image searchImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/searchBackGround.jpg").getImage();
		frame.getContentPane().setLayout(null);
		searchPanel = new BackgroundPanel(searchImage);
		searchPanel.setBounds(18, 41, 862, 128);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		searchButton = new JButton("Search");
		searchButton.setBounds(475, 57, 123, 36);
		searchPanel.add(searchButton);
		searchTF = new JTextField();
		searchTF.setBounds(238, 60, 189, 26);
		searchPanel.add(searchTF);
		searchTF.setColumns(10);
		
		
		//panel for filter
		Image filterImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/filterBackGround.jpg").getImage();
		filterPanel = new BackgroundPanel(filterImage);
		filterPanel.setBounds(18, 169, 862, 127);
		frame.getContentPane().add(filterPanel);
		filterPanel.setLayout(null);
			
		lblBrand = new JLabel("Brand:");
		lblBrand.setForeground(Color.BLACK);
		lblBrand.setBounds(200, 20, 45, 16);
		filterPanel.add(lblBrand);



		String[] sBrand = {"Ford","Honda","Jeep","Mazda","Nissan","Chrysler"};

		cbBrand = new JComboBox(sBrand);
		cbBrand.setSelectedIndex(-1);
		cbBrand.setBounds(257, 18, 132, 22);
		filterPanel.add(cbBrand);
		
		lblModel = new JLabel("Model:");
		lblModel.setBounds(408, 20, 45, 16);
		filterPanel.add(lblModel);


		cbModel = new JComboBox();
		cbModel.setBounds(465, 18, 132, 22);
		filterPanel.add(cbModel);
		
		
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(120, 48, 45, 16);
		filterPanel.add(lblPrice);

		String[] sPrice = {"10000","20000","30000","40000"};
		cbPriceMin = new JComboBox(sPrice);
		cbPriceMin.setSelectedIndex(-1);
		cbPriceMin.setBounds(157, 46, 90, 22);
		cbPriceMax =  new JComboBox(sPrice);
		cbPriceMax.setSelectedIndex(-1);
		cbPriceMax.setBounds(267, 46, 90, 22);

		filterPanel.add(cbPriceMin);
		filterPanel.add(cbPriceMax);
		
		
		lblYear = new JLabel("Year:");
		lblYear.setBounds(408, 48, 45, 16);
		filterPanel.add(lblYear);
		cbYearMin = new JComboBox();
		cbYearMax = new JComboBox();

		for (int i = 1980; i<=2018;i++) {
			cbYearMin.addItem(String.valueOf(i));
			cbYearMax.addItem(String.valueOf(i));

		}
		cbYearMin.setSelectedIndex(-1);
		cbYearMin.setBounds(465, 46, 90, 22);
		cbYearMax.setSelectedIndex(-1);
		cbYearMax.setBounds(565, 46, 90, 22);
		filterPanel.add(cbYearMin);
		filterPanel.add(cbYearMax);

//
//		lblMile = new JLabel("Mileage:");
//		lblMile.setBounds(200, 88, 61, 16);
//		filterPanel.add(lblMile);
//		cbMileage = new JComboBox();
//		cbMileage.setBounds(257, 86, 132, 22);
//		filterPanel.add(cbMileage);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(690, 43, 117, 29);
		filterPanel.add(btnSearch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(690, 83, 117, 29);
		filterPanel.add(btnCancel);
		
		//panel for sort
		Image SortBgImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/sortBackGround.jpg").getImage();
		sortPanel = new BackgroundPanel(SortBgImage);
		sortPanel.setBounds(18, 295, 862, 52);
		frame.getContentPane().add(sortPanel);
		sortPanel.setLayout(null);
		
		lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(25, 16, 61, 16);
		sortPanel.add(lblSortBy);
		btnLatest = new JButton("Latest");
		btnLatest.setBounds(92, 11, 105, 29);
		sortPanel.add(btnLatest);
		btnPriceLowest = new JButton("Price lowest");
		btnPriceLowest.setBounds(213, 11, 117, 29);
		sortPanel.add(btnPriceLowest);
		
		btnPriceHighest = new JButton("Price highest");
		btnPriceHighest.setBounds(347, 11, 117, 29);
		sortPanel.add(btnPriceHighest);
		
		// panel for result
		resultPanel = new JPanel();
		resultPanel.setBounds(18, 344, 855, 334);
		resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Results"));
		frame.getContentPane().add(resultPanel);
		resultPanel.setLayout(null);

		
		frame.setTitle("Vehicle Trader");
		frame.setVisible(true);
		
		
		
	}
	


//	public ArrayList<Vehicle> findVehicleOfDealer(String dealerID) {
//		VehicleServiceImple vehicleService = new VehicleServiceImple();
//		vehicles = (ArrayList<Vehicle>) vehicleService.findVehiclesByDealer(dealerID).getVehicles();
//		return vehicles;
//	}


	private ArrayList<Vehicle> getVehicleByFilter() {
		VehicleServiceImple vehicleService = new VehicleServiceImple();
		ArrayList<Vehicle> vehicles = new ArrayList<>();





		Filter f = new Filter();

		if (cbPriceMin.getSelectedItem() != null) {
			String priceMin = (String) cbPriceMin.getSelectedItem();
			f.setMinPrice(priceMin);
		}

		if (cbPriceMax.getSelectedItem() != null) {
			String priceMax = (String) cbPriceMax.getSelectedItem();
			f.setMaxPrice(priceMax);
		}

		if (cbYearMin.getSelectedItem() != null) {
			String yearMin = (String) cbYearMin.getSelectedItem();
			f.setMinYear(yearMin);

		}

		if (cbYearMax.getSelectedItem() != null) {
			String yearMax = (String) cbYearMax.getSelectedItem();
			f.setMaxYear(yearMax);

		}





		Sorting s = Sorting.ASCEND_PRICE;
		Paging p = new Paging();
		p.setPageNum(1);
		p.setPerPage(Integer.MAX_VALUE);

		for (Vehicle vehicle : vehicleService.findVehiclesByFilter(dealerID, f, s, p).getVehicles()) {
			vehicles.add(vehicle);
		}
//        for (Vehicle vehicle : vehicleService.findVehiclesByDealer("gmps-curry").getVehicles()) {
//            vehicles.add(vehicle);
//        }

		return vehicles;
	}


	private void createVehiclesTable(Object[][] vehicleRow) {
//		JPanel result = new JPanel();
		tableScrollPane = new JScrollPane();
		vehiclesAfterFilter = new JTable();
		System.out.println(vehicleRow.length);

		vehiclesAfterFilter.setModel(new javax.swing.table.DefaultTableModel(
				vehicleRow,
				new String[]{
						"id", "webId", "category", "year", "make", "model", "bodyType", "price", "photoUrl"
				}

		) {
			Class[] types = new Class[]{
					java.lang.String.class, java.lang.String.class, Category.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, URL.class, java.lang.Object.class
			};

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		tableScrollPane.setViewportView(vehiclesAfterFilter);

	}


	public Object[][] getVehicleRow() {

		ArrayList<Vehicle> vehicleList = getVehicleByFilter();

		vehicleRow = new Object[vehicleList.size()][10];
		for (int i = 0; i < vehicleList.size(); i++) {
			Vehicle vehicle = vehicleList.get(i);
			vehicleRow[i][0] = vehicle.getId();
			vehicleRow[i][1] = vehicle.getWebId();
			vehicleRow[i][2] = vehicle.getCategory();
			vehicleRow[i][3] = vehicle.getYear();
			vehicleRow[i][4] = vehicle.getMake();
			vehicleRow[i][5] = vehicle.getModel();
			vehicleRow[i][6] = vehicle.getBodyType();
			vehicleRow[i][7] = vehicle.getPrice();
			vehicleRow[i][8] = vehicle.getPhotoUrl();
//			vehicleRow[i][9] = "Button";

		}
		return vehicleRow;
	}


	private GroupLayout setTabelLayout(JPanel resultPanel) {
		GroupLayout resultLayout = new GroupLayout(resultPanel);
		resultPanel.setLayout(resultLayout);
		setHorizontalAndVerticalGroup(resultLayout);
//        setContentPaneLayout();
		return resultLayout;
	}

	private void setHorizontalAndVerticalGroup(GroupLayout resultLayout) {
		resultLayout.setHorizontalGroup(
				resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(resultLayout.createSequentialGroup()
								.addGap(40, 40, 40)
								.addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
								.addGap(74, 74, 74))
		);
		resultLayout.setVerticalGroup(
				resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
								.addContainerGap(29, Short.MAX_VALUE)
								.addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(17, 17, 17))
		);
	}



	
	public static void main(String[] args) {
	
		new SearchPage("gmps-curry");

		
	}

	private void createListeners() {
		operatorListener = new OperatorListener();

	}

	private void addListeners() {
		btnSearch.addActionListener(operatorListener);

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



	class OperatorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object o = ae.getSource();
			if (o == btnSearch) {


				vehicleRow = getVehicleRow();

				createVehiclesTable(vehicleRow);
				setTabelLayout(resultPanel);

			}
//			if (o == btnCancel) {
//				vehicleRow = null;
//			}

		}

	}
	
}


