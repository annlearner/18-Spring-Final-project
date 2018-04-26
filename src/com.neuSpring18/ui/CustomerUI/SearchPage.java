package com.neuSpring18.ui.CustomerUI;

import com.neuSpring18.dto.*;
import com.neuSpring18.service.VehicleServiceImple;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SearchPage {

    private JFrame frame;
    private JPanel dealerPanel;
    private JButton backButton;
    private JLabel lblEmail;
    private JLabel lblDealerID;
    private JTextField dealerIdTF;
    private JPanel searchPanel;
    private JButton searchButton;
    private JTextField searchTF;
    private JPanel filterPanel;
    private JLabel lblBrand;
    private JComboBox cbBrand;
    private JLabel lblCategory;
    private JComboBox cbCategory;
    private JLabel lblPrice;
    private JComboBox cbPriceMin;
    private JComboBox cbPriceMax;
    private JLabel lblYear;
    private JComboBox cbYearMin;
    private JComboBox cbYearMax;
    private JButton btnSearch;
    private JButton btnClear;
    private JPanel sortPanel;
    private JPanel resultPanel;
    private JRadioButton ascendPrice;
    private JRadioButton descendPrice;
    private JRadioButton ascendYear;
    private JRadioButton descendYear;
    private OperatorListener operatorListener;
    private Filter f;
    private Sorting s;
    private Paging p;
    // table
    private JScrollPane tableScrollPane;
    private JTable vehiclesAfterFilter;
    private Object[][] vehicleRow;
    private String dealerID;
    private ArrayList<Vehicle> vehicles;
    private ButtonEditor buttonEditor;
    private String urlForButton;
    private JButton previousPage;
    private JButton nextPage;
    private JComboBox cbPage;
    private JButton turnPage;
    private static final int perPageNum = 6;

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
        showCbbox();
        showResults();
        makeItVisible();
    }

    private void createComponents() {
        frame = new JFrame();
        createDealerPanel();
        createSearchPanel();
        createFilterPanel();
        createSortPanel();
        createResultPanel();
    }

    private  void createDealerPanel() {
        dealerPanel = new JPanel();
        backButton = new JButton("BACK");
        lblEmail = new JLabel("Email us");
        lblEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me");
                SwingEmailSender.invokedBySearchPage();
            }
        });
        lblDealerID = new JLabel("Dealer:");
        dealerIdTF = new JTextField();
        dealerIdTF.setText(dealerID);
        dealerIdTF.setColumns(10);
    }

    private void createSearchPanel() {
        Image searchImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/searchBackGround.jpg").getImage();
        searchPanel = new BackgroundPanel(searchImage);
        searchButton = new JButton("Search");
        searchTF = new JTextField();
        searchTF.setColumns(10);
    }

    private void createFilterPanel() {
        Image filterImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/filterBackGround.jpg").getImage();
        filterPanel = new BackgroundPanel(filterImage);
        lblBrand = new JLabel("Brand:");
        vehicles = findVehicleOfDealer(dealerID);
        lblCategory = new JLabel("Category:");
        lblPrice = new JLabel("Price:");
        lblYear = new JLabel("Year:");
        addComboBox();
        addItemInComboBox();
        cbCategory.setSelectedIndex(-1);
        cbBrand.setSelectedIndex(-1);
        cbPriceMin.setSelectedIndex(-1);
        cbPriceMax.setSelectedIndex(-1);
        cbYearMin.setSelectedIndex(-1);
        cbYearMax.setSelectedIndex(-1);
        btnSearch = new JButton("Search");
        btnClear = new JButton("Clear");
    }

    private void addComboBox() {
        cbBrand = new JComboBox();
        String[] sCategory = {"new", "certified", "used"};
        cbCategory = new JComboBox(sCategory);
        cbPriceMin = new JComboBox();
        cbPriceMax = new JComboBox();
        cbYearMin = new JComboBox();
        cbYearMax = new JComboBox();
    }

    private void addItemInComboBox() {
        Set<String> set = new HashSet<>();
        for (Vehicle vehicle : vehicles) {
            if (!set.contains(vehicle.getMake())) {
                cbBrand.addItem(vehicle.getMake());
            }
            set.add(vehicle.getMake());
        }
        for (int i = 5000; i < 120000; i = i + 5000) {
            cbPriceMin.addItem(String.valueOf(i));
            cbPriceMax.addItem(String.valueOf(i));
        }
        for (int i = 1980; i <= 2018; i++) {
            cbYearMin.addItem(String.valueOf(i));
            cbYearMax.addItem(String.valueOf(i));
        }
    }

    private void createSortPanel() {
        Image SortBgImage = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/sortBackGround.jpg").getImage();
        sortPanel = new BackgroundPanel(SortBgImage);
        ascendYear = new JRadioButton("Older first");
        descendYear = new JRadioButton("Newest first");
        ascendPrice = new JRadioButton("Lowest price first");
        descendPrice = new JRadioButton("Highest price first");
    }

    private void createResultPanel() {
        resultPanel = new JPanel();
        previousPage = new JButton("Previous");
        nextPage = new JButton("Next");
        turnPage = new JButton("Go");
        cbPage = new JComboBox();
        f = new Filter();
        p = new Paging();
        p.setPageNum(1);
        p.setPerPage(perPageNum);
    }

    private void addComponents() {
        addDealerPanel();
        addSearchPanel();
        addFilterPanel();
        addSortPanel();
        addResultPanel();
    }

    private void addDealerPanel() {
        frame.getContentPane().add(dealerPanel);
        dealerPanel.add(backButton);
        dealerPanel.add(lblEmail);
        dealerPanel.add(lblDealerID);
        dealerPanel.add(dealerIdTF);
    }

    private void addSearchPanel() {
        frame.getContentPane().add(searchPanel);
        searchPanel.add(searchButton);
        searchPanel.add(searchTF);
    }

    private void addFilterPanel() {
        frame.getContentPane().add(filterPanel);
        filterPanel.add(lblBrand);
        filterPanel.add(cbBrand);
        filterPanel.add(cbCategory);
        filterPanel.add(lblCategory);
        filterPanel.add(lblPrice);
        filterPanel.add(cbPriceMin);
        filterPanel.add(cbPriceMax);
        filterPanel.add(lblYear);
        filterPanel.add(cbYearMin);
        filterPanel.add(cbYearMax);
        filterPanel.add(btnSearch);
        filterPanel.add(btnClear);
    }

    private void addSortPanel() {
        frame.getContentPane().add(sortPanel);
        sortPanel.add(ascendYear);
        sortPanel.add(descendYear);
        sortPanel.add(ascendPrice);
        sortPanel.add(descendPrice);
        ButtonGroup sortButtonGroup = new ButtonGroup();
        sortButtonGroup.add(ascendYear);
        sortButtonGroup.add(descendYear);
        sortButtonGroup.add(ascendPrice);
        sortButtonGroup.add(descendPrice);
    }

    private void addResultPanel() {
        frame.getContentPane().add(resultPanel);
    }

    private void setBounds() {
        frame.setBounds(100, 100, 900, 700);
        dealerSetBound();
        searchSetBound();
        filterSetBound();
        sortSetBound();
        resultSetBound();
    }

    private void dealerSetBound() {
        dealerPanel.setBounds(18, 6, 862, 27);
        backButton.setBounds(152, 6, 93, 16);
        lblEmail.setBounds(700, 6, 93, 16);
        lblDealerID.setBounds(390, 6, 50, 16);
        dealerIdTF.setBounds(444, 1, 230, 26);
    }

    private void searchSetBound() {
        searchPanel.setBounds(18, 41, 862, 48);
        searchButton.setBounds(475, 6, 123, 36);
        searchTF.setBounds(238, 11, 189, 26);
    }

    private void filterSetBound() {
        filterPanel.setBounds(18, 89, 862, 127);
        lblBrand.setBounds(200, 20, 45, 16);
        cbBrand.setBounds(257, 18, 132, 22);
        cbCategory.setBounds(465, 18, 132, 22);
        lblCategory.setBounds(408, 20, 80, 16);
        lblPrice.setBounds(120, 48, 45, 16);
        cbPriceMin.setBounds(157, 46, 90, 22);
        cbPriceMax.setBounds(267, 46, 90, 22);
        lblYear.setBounds(408, 48, 45, 16);
        cbYearMin.setBounds(465, 46, 90, 22);
        cbYearMax.setBounds(565, 46, 90, 22);
        btnSearch.setBounds(690, 43, 117, 29);
        btnClear.setBounds(690, 83, 117, 29);
    }

    private void sortSetBound() {
        sortPanel.setBounds(18, 215, 862, 52);
        ascendYear.setBounds(31, 16, 150, 16);
        descendYear.setBounds(231, 16, 150, 16);
        ascendPrice.setBounds(431, 16, 150, 16);
        descendPrice.setBounds(631, 16, 150, 16);
    }

    private void resultSetBound() {
        resultPanel.setBounds(18, 264, 862, 414);
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
        if (cbBrand.getSelectedItem() != null) {
            String brand = (String) cbBrand.getSelectedItem();
            f.setMake(brand);
        }
        if (cbCategory.getSelectedItem() != null) {
            List<String> category = new ArrayList<>();
            category.add((String)cbCategory.getSelectedItem());
            f.setCategory(category);
        }
        if (s == null) {
            s = Sorting.DEFAULT;
        }
        for (Vehicle vehicle : vehicleService.findVehiclesByFilter(dealerID, f, s, p).getVehicles()) {
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    private void createVehiclesTable(Object[][] vehicleRow) {
        tableScrollPane = new JScrollPane();
        vehiclesAfterFilter = new JTable();
        vehiclesAfterFilter.setModel(new DefaultTableModel(
                vehicleRow,
                new String[]{
                        "Photo", "Category", "Year", "Make", "Model", "BodyType", "Price", "Detailed Description"
                }
        ) {
            Class[] types = new Class[]{
                    ImageIcon.class, Category.class,Integer.class,String.class,String.class,String.class,Double.class, Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        vehiclesAfterFilter.setRowHeight(60);
        tableScrollPane.setViewportView(vehiclesAfterFilter);
    }

    private void setButtonRender() {
        vehiclesAfterFilter.getColumn("Detailed Description").setCellRenderer(new ButtonRenderer());
        buttonEditor = new ButtonEditor(new JTextField());
        vehiclesAfterFilter.getColumn("Detailed Description").setCellEditor(new ButtonEditor(new JTextField()));
    }

    public Object[][] getVehicleRow() {
        vehicles = getVehicleByFilter();
        vehicleRow = new Object[vehicles.size()][9];
        BufferedImage img;
        Image icon = null;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            try {
                img = ImageIO.read(vehicle.getPhotoUrl());
                icon = img.getScaledInstance(85, 85, Image.SCALE_DEFAULT);
                vehicleRow[i][0]=new ImageIcon(icon);
            } catch (IOException e) {
                vehicleRow[i][0] = new ImageIcon("src/com.neuSpring18/ui/CustomerUI/image/searchBackGround.jpg");
                urlForButton = null;
            }
            if(icon != null){
                urlForButton = vehicle.getPhotoUrl().toString();
            }
            vehicleRow[i][1] = vehicle.getCategory();
            vehicleRow[i][2] = vehicle.getYear();
            vehicleRow[i][3] = vehicle.getMake();
            vehicleRow[i][4] = vehicle.getModel();
            vehicleRow[i][5] = vehicle.getBodyType();
            vehicleRow[i][6] = vehicle.getPrice();
            vehicleRow[i][7] = vehicle.getPhotoUrl();
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
                resultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(resultLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                                .addGap(74, 74, 74))
        );
        resultLayout.setVerticalGroup(
                resultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addComponent(tableScrollPane, 300, 350, 400)
                                .addGap(17, 17, 17))
        );
    }
    private void showCbbox() {
        p.setPerPage(Integer.MAX_VALUE/perPageNum);
        int page = p.getPageNum();
        p.setPageNum(1);
        cbPage.removeAllItems();
        ArrayList<Vehicle> totalVehicle = getVehicleByFilter();
        for (int i = 1; i <= (int) Math.ceil(totalVehicle.size()/(float) perPageNum);i++) {
            cbPage.addItem(i);
        }
        p.setPerPage(perPageNum);
        p.setPageNum(page);
    }

    private  void getPageButton() {
        resultPanel.add(previousPage);
        resultPanel.add(nextPage);
        previousPage.setBounds(200,20,70,20);
        nextPage.setBounds(280,20,70,20);
        cbPage.setBounds(360,20,80,20);
        resultPanel.add(cbPage);
        turnPage.setBounds(450,20,50,20);
        resultPanel.add(turnPage);
    }
    private void showResults() {

        resultPanel.removeAll();
        getPageButton();
        vehicleRow = getVehicleRow();
        createVehiclesTable(vehicleRow);
        setButtonRender();
        setTabelLayout(resultPanel);
        adjustTable();
    }

    private void adjustTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        vehiclesAfterFilter.setDefaultRenderer(String.class, centerRenderer);
        vehiclesAfterFilter.setDefaultRenderer(Integer.class, centerRenderer);
        vehiclesAfterFilter.setDefaultRenderer(Double.class, centerRenderer);
        vehiclesAfterFilter.setDefaultRenderer(Object.class, centerRenderer);
        TableCellRenderer rendererFromHeader = vehiclesAfterFilter.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void backPage() {
        toDealerPage e = new toDealerPage();
        backButton.addActionListener(e);
    }

    public class toDealerPage implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new DealerPage();
        }
    }

    public static void main(String[] args) {
        new SearchPage("gmps-aj-dohmann");
    }

    private void createListeners() {
        operatorListener = new OperatorListener();
    }

    private void addListeners() {
        btnSearch.addActionListener(operatorListener);
        btnClear.addActionListener(operatorListener);
        ascendYear.addActionListener(operatorListener);
        ascendPrice.addActionListener(operatorListener);
        descendYear.addActionListener(operatorListener);
        descendPrice.addActionListener(operatorListener);
        searchButton.addActionListener(operatorListener);
        previousPage.addActionListener(operatorListener);
        nextPage.addActionListener(operatorListener);
        turnPage.addActionListener(operatorListener);
    }

    class BackgroundPanel extends JPanel {
        private Image image = null;
        public BackgroundPanel(Image image) {
            this.image = image;
        }
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    class OperatorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object o = ae.getSource();
            if (o == searchButton) {
                if (searchTF.getText() != null || searchTF.getText() != "") {
                    f.setSearch(searchTF.getText());
                    p.setPageNum(1);
                    p.setPerPage(perPageNum);
                    showCbbox();
                    showResults();
                }
            }
            if (o == btnSearch) {
                f = new Filter();
                p.setPageNum(1);
                p.setPerPage(perPageNum);
                s = Sorting.DEFAULT;
                showCbbox();
                showResults();
            }
            if (o == ascendPrice) {
                s = Sorting.ASCEND_PRICE;
                p.setPageNum((int)cbPage.getSelectedItem());
                showResults();
            }
            if (o == descendPrice) {
                s = Sorting.DESCEND_PRICE;
                p.setPageNum((int)cbPage.getSelectedItem());
                showResults();
            }
            if (o == ascendYear) {
                s = Sorting.ASCEND_YEAR;
                p.setPageNum((int)cbPage.getSelectedItem());
                showResults();
            }
            if (o == descendYear) {
                s = Sorting.DESCEND_YEAR;
                p.setPageNum((int)cbPage.getSelectedItem());
                showResults();
            }
            if (o == btnClear) {
                cbBrand.setSelectedIndex(-1);
                cbCategory.setSelectedIndex(-1);
                cbPriceMax.setSelectedIndex(-1);
                cbPriceMin.setSelectedIndex(-1);
                cbYearMax.setSelectedIndex(-1);
                cbYearMin.setSelectedIndex(-1);
            }
            if (o == previousPage) {
                int curPage = p.getPageNum();
                if (curPage > 1) {
                    p.setPageNum(curPage-1);
                }
                int index = (int) cbPage.getSelectedItem();
                if (index >= 1) {
                    cbPage.setSelectedItem(index - 1);
                }
                showResults();
            }
            if (o == nextPage) {
                int index = cbPage.getSelectedIndex();
                if (index < cbPage.getItemCount() - 1) {
                    int curPage = p.getPageNum();
                    p.setPageNum(curPage+1);
                    cbPage.setSelectedItem((int)cbPage.getSelectedItem() + 1);
                }
                showResults();
            }
            if (o == turnPage) {
                int index = (int) cbPage.getSelectedItem();
                p.setPageNum(index);
                showResults();
            }
        }
    }
}
