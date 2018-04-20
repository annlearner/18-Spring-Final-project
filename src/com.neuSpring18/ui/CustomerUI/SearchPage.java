package com.neuSpring18.ui.CustomerUI;

import com.neuSpring18.dto.*;
import com.neuSpring18.service.VehicleServiceImple;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.ArrayList;


public class SearchPage {

	private JFrame frame;

	private JPanel dealerPanel;

	private JButton backButton;
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
	private JButton btnSearch;
	private JButton btnCancel;
	private JPanel sortPanel;
	private JLabel lblSortBy;
	private JButton btnLatest;
	private JButton btnPriceLowest;
	private JButton btnPriceHighest;
	private JPanel resultPanel;

	private JCheckBox ascendPrice;
	private JCheckBox descendPrice;
	private JCheckBox ascendYear;
	private JCheckBox descendYear;
	private JCheckBox defaultOrder;

	private OperatorListener operatorListener;

	Filter f;
	Sorting s;
	Paging p;


	// table
	private JScrollPane tableScrollPane;
	private JTable vehiclesAfterFilter;
	Object[][] vehicleRow;

	String dealerID;
	//  vehicles of the dealer
//	ArrayList<Vehicle> vehicles = findVehicleOfDealer(dealerID);


	public SearchPage(String dealerID) {

		this.dealerID = dealerID;
		createComponents();
		createListeners();
		addListeners();
		backPage();
		addComponents();
		setBounds();
		setBackground();
		setLayout();
		makeItVisible();

	}

	
	private void createComponents() {

		frame = new JFrame();

		dealerPanel = new JPanel();

		backButton=new JButton("BACK");

		lblDealerResult = new JLabel("Results from:");

		dealerResultTF = new JTextField();

		dealerResultTF.setColumns(10);
		
		lblDealerID = new JLabel("ID:");
		
		dealerIdTF = new JTextField();

		dealerIdTF.setColumns(10);

		// panel for search
		Image searchImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/searchBackGround.jpg").getImage();

		searchPanel = new BackgroundPanel(searchImage);

		searchButton = new JButton("Search");

		searchTF = new JTextField();

		searchTF.setColumns(10);

		//panel for filter
		Image filterImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/filterBackGround.jpg").getImage();

		filterPanel = new BackgroundPanel(filterImage);

		lblBrand = new JLabel("Brand:");

		String[] sBrand = {"Ford","Honda","Jeep","Mazda","Nissan","Chrysler"};

		cbBrand = new JComboBox(sBrand);

//		for (Vehicle vehicle: vehicles) {
//			cbBrand.addItem(vehicle.getMake());
//		}

		cbBrand.setSelectedIndex(-1);

		lblModel = new JLabel("Model:");

		cbModel = new JComboBox();

		lblPrice = new JLabel("Price:");

		String[] sPrice = {"10000","20000","30000","40000"};

		cbPriceMin = new JComboBox(sPrice);

		cbPriceMin.setSelectedIndex(-1);

		cbPriceMax =  new JComboBox(sPrice);

		cbPriceMax.setSelectedIndex(-1);
		
		lblYear = new JLabel("Year:");


		cbYearMin = new JComboBox();

		cbYearMax = new JComboBox();

		for (int i = 1980; i<=2018;i++) {
			cbYearMin.addItem(String.valueOf(i));
			cbYearMax.addItem(String.valueOf(i));

		}
		cbYearMin.setSelectedIndex(-1);

		cbYearMax.setSelectedIndex(-1);

		
		btnSearch = new JButton("Search");
		
		btnCancel = new JButton("Cancel");

		//panel for sort
		Image SortBgImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/sortBackGround.jpg").getImage();

		sortPanel = new BackgroundPanel(SortBgImage);

		lblSortBy = new JLabel("Sort by:");

		btnLatest = new JButton("Latest");

		btnPriceLowest = new JButton("Price lowest");

		btnPriceHighest = new JButton("Price highest");


		ascendYear = new JCheckBox("ascendYear");

		descendYear = new JCheckBox("descendYear");

		ascendPrice = new JCheckBox("ascendPrice");

		descendPrice = new JCheckBox("descendPrice");

		defaultOrder = new JCheckBox("defaultOrder");

		// panel for result
		resultPanel = new JPanel();
		
	}

	private void addComponents() {

		//panel for dealer

		frame.getContentPane().add(dealerPanel);

		dealerPanel.add(backButton);

		dealerPanel.add(lblDealerResult);

		dealerPanel.add(dealerResultTF);

		dealerPanel.add(lblDealerID);

		dealerPanel.add(dealerIdTF);

		// panel for search
		frame.getContentPane().add(searchPanel);

		searchPanel.add(searchButton);

		searchPanel.add(searchTF);

		//panel for filter
		frame.getContentPane().add(filterPanel);

		filterPanel.add(lblBrand);

		filterPanel.add(cbBrand);

		filterPanel.add(cbModel);

		filterPanel.add(lblModel);

		filterPanel.add(lblPrice);

		filterPanel.add(cbPriceMin);

		filterPanel.add(cbPriceMax);

		filterPanel.add(lblYear);

		filterPanel.add(cbYearMin);

		filterPanel.add(cbYearMax);

		filterPanel.add(btnSearch);

		filterPanel.add(btnCancel);

		frame.getContentPane().add(sortPanel);

//		sortPanel.add(lblSortBy);
//
//		sortPanel.add(btnLatest);
//
//		sortPanel.add(btnPriceLowest);
//
//		sortPanel.add(btnPriceHighest);

		sortPanel.add(ascendYear);

		sortPanel.add(descendYear);

		sortPanel.add(ascendPrice);
		sortPanel.add(descendPrice);
		sortPanel.add(defaultOrder);

		frame.getContentPane().add(resultPanel);

	}

	private void setBounds() {

		frame.setBounds(100, 100, 900, 700);

		dealerPanel.setBounds(18, 6, 862, 27);

		backButton.setBounds(152, 6, 93, 16);

		lblDealerResult.setBounds(152, 6, 93, 16);

		dealerResultTF.setBounds(257, 3, 110, 21);

		lblDealerID.setBounds(390, 6, 24, 16);

		dealerIdTF.setBounds(414, 1, 130, 26);

		searchPanel.setBounds(18, 41, 862, 128);

		searchButton.setBounds(475, 57, 123, 36);

		searchTF.setBounds(238, 60, 189, 26);

		filterPanel.setBounds(18, 169, 862, 127);

		lblBrand.setBounds(200, 20, 45, 16);

		cbBrand.setBounds(257, 18, 132, 22);

		cbModel.setBounds(465, 18, 132, 22);

		lblModel.setBounds(408, 20, 45, 16);

		lblPrice.setBounds(120, 48, 45, 16);

		cbPriceMin.setBounds(157, 46, 90, 22);

		cbPriceMax.setBounds(267, 46, 90, 22);

		lblYear.setBounds(408, 48, 45, 16);

		cbYearMin.setBounds(465, 46, 90, 22);

		cbYearMax.setBounds(565, 46, 90, 22);

		btnSearch.setBounds(690, 43, 117, 29);

		btnCancel.setBounds(690, 83, 117, 29);

		sortPanel.setBounds(18, 295, 862, 52);

		lblSortBy.setBounds(25, 16, 61, 16);

		btnLatest.setBounds(92, 11, 105, 29);

		btnPriceLowest.setBounds(213, 11, 117, 29);

		btnPriceHighest.setBounds(347, 11, 117, 29);

		ascendYear.setBounds(25, 16, 100, 16);

		descendYear.setBounds(175, 16, 100, 16);

		ascendPrice.setBounds(325, 16, 100, 16);

		descendPrice.setBounds(475, 16, 100, 16);

		defaultOrder.setBounds(625, 16, 100, 16);

		resultPanel.setBounds(18, 344, 855, 334);



		resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Results"));

	}

	private void setBackground() {

		dealerPanel.setBackground(new Color(0, 191, 255));

	}

	private void setLayout() {

		dealerPanel.setLayout(null);
		searchPanel.setLayout(null);
		filterPanel.setLayout(null);
		sortPanel.setLayout(null);
		resultPanel.setLayout(null);
		frame.getContentPane().setLayout(null);

	}

	private void makeItVisible() {
		frame.setTitle("Vehicle Trader");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public ArrayList<Vehicle> findVehicleOfDealer(String dealerID) {
		VehicleServiceImple vehicleService = new VehicleServiceImple();
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleService.findVehiclesByDealer(dealerID).getVehicles();
		return vehicles;
	}


	private ArrayList<Vehicle> getVehicleByFilter() {
		VehicleServiceImple vehicleService = new VehicleServiceImple();

		ArrayList<Vehicle> vehicles = new ArrayList<>();

		f = new Filter();

		if (cbPriceMin.getSelectedItem() != null) {
			String priceMin = (String) cbPriceMin.getSelectedItem();
			System.out.println("this is" + priceMin);
			f.setMinPrice(priceMin);
		}

		if (cbPriceMax.getSelectedItem() != null) {
			String priceMax = (String) cbPriceMax.getSelectedItem();
			System.out.println("this is "+priceMax);
			f.setMaxPrice(priceMax);
		}

		if (cbYearMin.getSelectedItem() != null) {
			String yearMin = (String) cbYearMin.getSelectedItem();
			System.out.println("this is " + yearMin);
			f.setMinYear(yearMin);

		}

		if (cbYearMax.getSelectedItem() != null) {
			String yearMax = (String) cbYearMax.getSelectedItem();
			System.out.println("this is " + yearMax);
			f.setMaxYear(yearMax);
		}

		if (cbBrand.getSelectedItem() != null) {
			String brand = (String) cbBrand.getSelectedItem();
			f.setMake(brand);
		}


//		s = Sorting.ASCEND_PRICE;
		if (s == null) {
			s = Sorting.DEFAULT;
		}
		p = new Paging();
		p.setPageNum(1);
		p.setPerPage(Integer.MAX_VALUE);

		System.out.println("the file is   "+ f.getMinPrice());

		for (Vehicle vehicle : vehicleService.findVehiclesByFilter(dealerID, f, s, p).getVehicles()) {
			vehicles.add(vehicle);
		}


		return vehicles;
	}


	private void createVehiclesTable(Object[][] vehicleRow) {
		tableScrollPane = new JScrollPane();
//		tableScrollPane.setBounds(100,100,300,200);
		//DefaultTableModel tableModel = new DefaultTableModel();
		vehiclesAfterFilter = new JTable();

		vehiclesAfterFilter.setModel(new DefaultTableModel(
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
								.addComponent(tableScrollPane, 200, 300, 300)
								.addGap(17, 17, 17))
		);
	}

	private void showResults() {

		resultPanel.removeAll();

		vehicleRow = getVehicleRow();

		createVehiclesTable(vehicleRow);

		setTabelLayout(resultPanel);

	}

	public void backPage(){
		event e = new event();//互相调用
		backButton.addActionListener(e);
	}

	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e){
			frame.dispose();
			new DealerPage();
		}
	}


	
	public static void main(String[] args) {
	
		new SearchPage("gmps-curry");

	}

	private void createListeners() {

		operatorListener = new OperatorListener();
	}

	private void addListeners() {

		btnSearch.addActionListener(operatorListener);
		GetSort getSort = new GetSort();
		descendYear.addItemListener(getSort);
		ascendYear.addItemListener(getSort);
		descendPrice.addItemListener(getSort);
		ascendPrice.addItemListener(getSort);
		defaultOrder.addItemListener(getSort);

	}

	class GetSort implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if(ascendYear.isSelected()&&(descendYear.isSelected()==false)&&(descendPrice.isSelected()==false)&&(ascendPrice.isSelected()==false)&&(defaultOrder.isSelected()==false))
				s = Sorting.ASCEND_YEAR;
			else if(descendYear.isSelected()&&(ascendYear.isSelected()==false)&&(descendPrice.isSelected()==false)&&(ascendPrice.isSelected()==false)&&(defaultOrder.isSelected()==false))
				s = Sorting.DESCEND_YEAR;
			else if(ascendPrice.isSelected()&&(ascendYear.isSelected()==false)&&(descendYear.isSelected()==false)&&(descendPrice.isSelected()==false)&&(defaultOrder.isSelected()==false))
				s = Sorting.ASCEND_PRICE;
			else if(descendPrice.isSelected()&&(ascendYear.isSelected()==false)&&(descendYear.isSelected()==false)&&(ascendPrice.isSelected()==false)&&(defaultOrder.isSelected()==false))
				s = Sorting.DESCEND_PRICE;
			else if(defaultOrder.isSelected()&&(ascendYear.isSelected()==false)&&(descendYear.isSelected()==false)&&(descendPrice.isSelected()==false)&&(ascendPrice.isSelected()==false))
				s = Sorting.DEFAULT;
			else{
				s = Sorting.DEFAULT;
			}
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


	class OperatorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object o = ae.getSource();
			if (o == btnSearch) {
				System.out.println("this");
				showResults();

			}
//			if (o == btnCancel) {
//				vehicleRow = null;
//			}

		}

	}
	
}


