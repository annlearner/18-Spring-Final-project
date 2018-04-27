package com.neuSpring18.ui.DealerUI;


import com.neuSpring18.dto.*;
import com.neuSpring18.service.VehicleService;
import com.neuSpring18.service.VehicleServiceImple;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DealerMainFrame extends DealerCommonFrame{

    private JButton addButton, goButton;

    private JButton prePageButton, nextPageButton, jumpPageButton;

    private JTextField searchText;
    private JTextField priceStartText, priceEndText;
    private JTextField yearStartText, yearEndText;
    private JTextField pageNumText;

    private JLabel searchLabel;
    private JLabel filterLabel;
    private JLabel priceLabel, yearLabel, categoryLabel, typeLabel;
    private JLabel currentPageLabel,pageLable;

    private JCheckBox carBox, suvBox, truckBox, otherBox;
    private JCheckBox newBox, usedBox, preownedBox;

    private JScrollPane scrollPane;
    private JPanel resultPanel;
    private JPanel pagePanel;

    private JComboBox sortingBox;


    private String dealerName;

    private VehicleService vs;

    private Container c = getContentPane();

    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();

    private int pageNum;
    private Filter filter;
    private Sorting sorting;
    private String searchStr;


    private ItemListener checkBoxListener;
    private ActionListener buttonListener;
    private ActionListener sortingBoxListener;
    private KeyAdapter enterKeyListener;



    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private int totalPages;



    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }


    private ArrayList<JButton> carButtons;


    DealerMainFrame(String dealerName){
        this.dealerName = dealerName;
        this.pageNum = 1;
        this.filter = new Filter();
        this.sorting = Sorting.DEFAULT;

        createComponents();
        addComponents();
        createListeners();
        addListeners();
        makeItVisible();

    }

    public void  makeItVisible(){
        setSize(1330,965);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Current Login: "+dealerName);
    }

    private void addListeners() {
        addCheckBoxListeners();
        addButtonLister();
        addSortingBoxListener();
        addTextFieldListener();

    }

    private void addButtonLister(){
        goButton.addActionListener(buttonListener);
        prePageButton.addActionListener(buttonListener);
        nextPageButton.addActionListener(buttonListener);
        jumpPageButton.addActionListener(buttonListener);
        addButton.addActionListener(buttonListener);
    }

    private void addSortingBoxListener(){
        sortingBox.addActionListener(sortingBoxListener);
    }
    private void addTextFieldListener(){
        searchText.addKeyListener(enterKeyListener);
    }


    private void addCheckBoxListeners(){
        carBox.addItemListener(checkBoxListener);
        suvBox.addItemListener(checkBoxListener);
        newBox.addItemListener(checkBoxListener);
        usedBox.addItemListener(checkBoxListener);
        truckBox.addItemListener(checkBoxListener);
        otherBox.addItemListener(checkBoxListener);
        preownedBox.addItemListener(checkBoxListener);
    }

    private void createListeners() {
        checkBoxListener = new CheckBoxListener();
        buttonListener = new ButtonListener();
        sortingBoxListener = new SortingBoxListener();
        enterKeyListener = new EnterKeyListener();


    }

    class SortingBoxListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();

            if((cb.getSelectedItem()).equals(Sorting.DESCEND_PRICE)){
                sorting = Sorting.DESCEND_PRICE;
            }
            if((cb.getSelectedItem()).equals(Sorting.ASCEND_PRICE)){
                sorting = Sorting.ASCEND_PRICE;
            }
            if((cb.getSelectedItem()).equals(Sorting.DESCEND_YEAR)){
                sorting = Sorting.DESCEND_YEAR;
            }
            if((cb.getSelectedItem()).equals(Sorting.ASCEND_YEAR)){
                sorting = Sorting.ASCEND_YEAR;
            }
        }
    }

    class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if(source == newBox){
                if(newBox.isSelected())
                    category.add("new");
                else
                    category.remove("new");
            }
            if(source == usedBox){
                if(usedBox.isSelected())
                    category.add("used");
                else
                    category.remove("used");
            }
            if(source == preownedBox){
                if(preownedBox.isSelected())
                    category.add("certified");
                else
                    category.remove("certified");
            }

            if(source == carBox){
                if(carBox.isSelected())
                    type.add("CAR");
                else
                    type.remove("CAR");
            }
            if(source == suvBox){
                if(suvBox.isSelected())
                    type.add("SUV");
                else
                    type.remove("SUV");
            }
            if(source == truckBox){
                if(truckBox.isSelected())
                    type.add("TRUCK");
                else
                    type.remove("TRUCK");
            }
            if(source == otherBox){
                if(otherBox.isSelected()) {
                    type.add("CARGOVAN");
                    type.add("COMMERCIALVEHICLE");
                    type.add("VAN");
                    type.add("WAGON");
                }
                else {
                    type.remove("CARGOVAN");
                    type.remove("COMMERCIALVEHICLE");
                    type.remove("VAN");
                    type.remove("WAGON");
                }
            }

        }

    }

    class EnterKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent){
            if(keyEvent.getKeyCode()==keyEvent.VK_ENTER){
                searchStr = searchText.getText();
                setPageNum(1);
                filter.setSearch(searchStr);
                refreshResult(c,getPageNum());
                updateTotalPages();
            }
        }
    }

    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if(source == goButton){
                setPageNum(1);
                filter.setCategory(category);
                filter.setType(type);
                getTextFields();
                refreshResult(c, getPageNum());
                System.out.println("Total Pages are: "+getTotalPages());
                updateTotalPages();
            }
            if (source == nextPageButton){
                refreshResult(c, getPageNum() + 1);
                pageNum++;
            }
            if (source == prePageButton){
                if(getPageNum() <= 1){
                    setPageNum(1);
                }else {
                    pageNum--;
                }
                refreshResult(c, getPageNum());
            }
            if(source == jumpPageButton){
                String input = pageNumText.getText();
                setPageNum(input == null || input.isEmpty() ? 1 : Integer.parseInt(input));
                refreshResult(c, getPageNum());
            }
            if (source==addButton) {
                new DealerEdit(dealerName);

            }

        }
    }

    private void getTextFields(){

        String a = yearStartText.getText();
        String minYear = a == null || a.isEmpty() ? "" : a;

        String b = yearEndText.getText();
        String maxYear = b == null || b.isEmpty() ? "" : b;

        String c = priceStartText.getText();
        String minPrice = c == null || c.isEmpty() ? "" : c;

        String d = priceEndText.getText();
        String maxPrice = d == null || d.isEmpty() ? "" : d;
        String e = searchText.getText();
        String search = e == null || e.isEmpty() ? "" : e;

        filter.setMinYear(minYear);
        filter.setMaxYear(maxYear);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setSearch(search);
    }

    private void refreshResult(Container c, int pageNum){
        resultPanel.removeAll();
        addResultsPanel(c, pageNum);
        currentPageLabel.setText("  " + pageNum + "  ");
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void addComponents() {
        addFilterPanel(c);
        addTopPanel(c);
        addPageOperatorPanel(c);
        createCarButtons();
        addResultsPanel(c, pageNum);
        updateTotalPages();
    }


    private JPanel makeAResultPanel(Vehicle vehicle, JButton carButton) throws IOException{
        JPanel labelResultPanel = new JPanel();
        labelResultPanel.setLayout(new BoxLayout(labelResultPanel, BoxLayout.Y_AXIS));

        JLabel priceResultLabel = new JLabel("$" + String.valueOf(vehicle.getPrice()));

        JLabel typeResultLabel = new JLabel(vehicle.getBodyType().toString());
        JLabel modelResultLabel = new JLabel(vehicle.getModel().toUpperCase());

        JLabel vehicleTitleLable = new JLabel((vehicle.getCategory().toString() + " " + String.valueOf(vehicle.getYear()) + " " + vehicle.getMake()).toUpperCase());

        priceResultLabel.setFont(stratumBoldFontSmaller);
        priceResultLabel.setForeground(new Color(0,105,0));
        typeResultLabel.setFont(stratumBoldFontSmaller);
        modelResultLabel.setFont(stratumBoldFontSmaller);
        vehicleTitleLable.setFont(getStratumBoldForCarTitle);

        labelResultPanel.add(modelResultLabel);
        labelResultPanel.add(priceResultLabel);
        labelResultPanel.add(typeResultLabel);
        labelResultPanel.add(carButton);

        labelResultPanel.setBackground(Color.white);

        JLabel imageLabel;

        JPanel singleResultPanel = new JPanel();
        singleResultPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        singleResultPanel.setPreferredSize(new Dimension(350,400));

        Image image;

        try {
            image = ImageIO.read(vehicle.getPhotoUrl());
            imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(350, 250, Image.SCALE_FAST)));
        }catch (IOException e){
            String brokenImage = "src/com.neuSpring18/ui/DealerUI/images/defaultCar.jpg";
            image = ImageIO.read(new File(brokenImage));
            imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(350, 250, Image.SCALE_FAST)));
        }

        for (ActionListener al:carButton.getActionListeners()) {
            carButton.removeActionListener(al);
        }

        carButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DealerEdit(vehicle);
            }
        });

        singleResultPanel.add(vehicleTitleLable);
        singleResultPanel.add(imageLabel);
        singleResultPanel.add(labelResultPanel);
        singleResultPanel.setBackground(Color.white);

        return singleResultPanel;
    }


    private void addResultsPanel(Container c, int pageNum) {

        vs = new VehicleServiceImple();

        Paging p = new Paging();
        p.setPageNum(pageNum);
        p.setPerPage(6);

        System.out.println("--------------------");

        Inventory inventories = vs.findVehiclesByFilter(dealerName, filter, sorting, p);

        int totalVehicles = inventories.getIc().getTotalCount();
        int totalPages = totalVehicles / 6;

        if(totalVehicles % 6 != 0) {
            totalPages +=  1;
        }
        setTotalPages(totalPages);


        ArrayList<Vehicle> vehicles =  (ArrayList<Vehicle>) vs.findVehiclesByFilter(dealerName, filter, sorting, p).getVehicles();

        resultPanel.removeAll();

        for (int i = 0; i < vehicles.size(); i++) {
            carButtons.get(i).setText("EDIT DETAILS");
            carButtons.get(i).setPreferredSize(new Dimension(150,50));
            carButtons.get(i).setFont(stratumBoldFontPlain);
            carButtons.get(i).setForeground(new Color(57,57,57));
            try {
                resultPanel.add(makeAResultPanel(vehicles.get(i), carButtons.get(i)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        c.add(scrollPane, BorderLayout.CENTER);

    }

    private void createCarButtons(){
        carButtons = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            carButtons.add(i, new JButton());
        }
    }

    private void updateTotalPages(){

        pagePanel.removeAll();
        createTotalPagesLabel();
        addPageOperatorPanel(c);
        pagePanel.revalidate();

    }

    private void addPageOperatorPanel(Container c){
        String stupidSpace = "                                                                                                                                         ";
        pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.X_AXIS));
        pagePanel.add(new JLabel(stupidSpace));
        pagePanel.add(prePageButton);
        pagePanel.add(currentPageLabel);
        pagePanel.add(nextPageButton);
        pagePanel.add(new JLabel("        "));
        pagePanel.add(pageNumText);
        pagePanel.add(new JLabel(""));
        pagePanel.add(new JLabel("/" + totalPages));
        pagePanel.add(pageLable);
        pagePanel.add(jumpPageButton);
        pagePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        c.add(pagePanel, BorderLayout.SOUTH);
    }


    private void addTopPanel(Container c) {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.add(searchLabel);
        searchPanel.add(searchText);


        rightPanel.add(addButton, BorderLayout.WEST);
        rightPanel.add(searchPanel, BorderLayout.EAST);

        c.add(rightPanel, BorderLayout.NORTH);
    }

    private void addFilterPanel(Container c) {
        filter = new Filter();

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.setBackground(fiterPanelColor);

        JPanel priceInputPanel = new JPanel();
        priceInputPanel.setBackground(fiterPanelColor);
        priceInputPanel.setLayout(new BoxLayout(priceInputPanel, BoxLayout.X_AXIS));
        priceInputPanel.add(priceStartText);
        priceInputPanel.add(new JLabel(" - "));
        priceInputPanel.add(priceEndText);
        priceInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        JPanel yearInputPanel = new JPanel();
        yearInputPanel.setBackground(fiterPanelColor);
        yearInputPanel.setLayout(new BoxLayout(yearInputPanel, BoxLayout.X_AXIS));
        yearInputPanel.add(yearStartText);
        yearInputPanel.add(new JLabel(" - "));
        yearInputPanel.add(yearEndText);
        yearInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel categoryBoxPanel = new JPanel();
        categoryBoxPanel.setBackground(fiterPanelColor);
        categoryBoxPanel.setLayout(new BoxLayout(categoryBoxPanel, BoxLayout.Y_AXIS));
        categoryBoxPanel.add(newBox);
        categoryBoxPanel.add(usedBox);
        categoryBoxPanel.add(preownedBox);
        categoryBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel typeBoxPanel = new JPanel();
        typeBoxPanel.setBackground(fiterPanelColor);
        typeBoxPanel.setLayout(new BoxLayout(typeBoxPanel, BoxLayout.Y_AXIS));
        typeBoxPanel.add(carBox);
        typeBoxPanel.add(suvBox);
        typeBoxPanel.add(truckBox);
        typeBoxPanel.add(otherBox);
        typeBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //String[] SortingTpyes = {"DEFAULT","ASCEND_PRICE", "DESCEND_PRICE", "ASCEND_YEAR", "DESCEND_YEAR"};
        sortingBox = new JComboBox();
        sortingBox.setModel(new DefaultComboBoxModel(Sorting.values()));
        sortingBox.setMaximumSize( sortingBox.getPreferredSize() );
        JPanel sortingBoxPanel = new JPanel();
        sortingBoxPanel.setLayout(new BoxLayout(sortingBoxPanel, BoxLayout.Y_AXIS));
        sortingBoxPanel.add(sortingBox);
        sortingBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        filterPanel.add(new JLabel(" "));
        filterPanel.add(new JLabel(" "));

        filterPanel.add(filterLabel);
        filterPanel.add(new JLabel(" "));
        filterPanel.add(new JLabel(" "));

        JLabel sortinglable   = new JLabel("SORTING");
        filterPanel.add(sortinglable);
        sortinglable.setFont(LucidaGrande);
        filterPanel.add(new JLabel(" "));

        filterPanel.add(sortingBoxPanel);

        filterPanel.add(new JLabel(" "));
        filterPanel.add(categoryLabel);
        filterPanel.add(new JLabel(" "));

        filterPanel.add(categoryBoxPanel);

        filterPanel.add(new JLabel(" "));

        filterPanel.add(typeLabel);
        filterPanel.add(new JLabel(" "));

        filterPanel.add(typeBoxPanel);

        filterPanel.add(new JLabel(" "));

        filterPanel.add(yearLabel);

        filterPanel.add(yearInputPanel);

        filterPanel.add(new JLabel(" "));

        filterPanel.add(priceLabel);
        filterPanel.add(priceInputPanel);

        filterPanel.add(new JLabel(" "));
        filterPanel.add(new JLabel(" "));

        filterPanel.add(goButton);

        c.add(filterPanel,BorderLayout.WEST);
    }

    private void createComponents() {
        resultPanel = new JPanel();
        //resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        resultPanel.setPreferredSize(new Dimension(320, 1100));
        resultPanel.setBackground(Color.black);

        scrollPane = new JScrollPane(resultPanel);
        scrollPane.setPreferredSize(new Dimension(320,300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.revalidate();

        createButtons();
        createTextArea();
        createPriceProperty();
        createYearProperty();
        createLabels();
        createCategoryBox();
        createTypeBox();
        createPageComponents();
        createTotalPagesLabel();
    }
    private void createTotalPagesLabel(){
        currentPageLabel = new JLabel("  " + pageNum + "  ");
        currentPageLabel.setFont(LucidaGrandeForPage);
    }


    private void createTextArea(){
        searchText = new JTextField(10);
        searchText.setMaximumSize(new Dimension(150, 35));
    }

    private void createButtons(){
        addButton = new JButton("ADD NEW");
        addButton.setFont(LucidaGrande);
        //addButton.setForeground(new Color(14,122,255));
        addButton.setPreferredSize(new Dimension(150,50));
        goButton = new JButton("GO");
        goButton.setForeground(new Color(15,130,200));
        goButton.setFont(LucidaGrande);
    }

    private void createYearProperty(){
        yearStartText = new JTextField(5);
        yearStartText.setMaximumSize(new Dimension(100, yearStartText.getPreferredSize().height));
        yearEndText = new JTextField(5);
        yearEndText.setMaximumSize(new Dimension(100, yearEndText.getPreferredSize().height));
    }

    private void createLabels(){
        searchLabel = new JLabel("SEARCH: ");
        searchLabel.setFont(LucidaGrande);
        categoryLabel = new JLabel("CATEGORY");
        categoryLabel.setFont(LucidaGrande);
        typeLabel = new JLabel("TYPE");
        typeLabel.setFont(LucidaGrande);
        filterLabel = new JLabel("     FILTERS");
        filterLabel.setFont(stratumBoldFont);
        priceLabel = new JLabel("PRICE");
        priceLabel.setFont(LucidaGrande);
        yearLabel = new JLabel("YEAR");
        yearLabel.setFont(LucidaGrande);
    }

    private void createPriceProperty(){
        priceStartText = new JTextField(5);
        priceStartText.setMaximumSize(new Dimension(100, priceStartText.getPreferredSize().height));
        priceEndText = new JTextField(5);
        priceEndText.setMaximumSize(new Dimension(100, priceEndText.getPreferredSize().height));
    }

    private void createTypeBox(){
        carBox = new JCheckBox("Car");
        carBox.setFont(LucidaGrandeSmall);
        //carBox.setBorder(BorderFactory.createLineBorder(Color.red,5));
        suvBox = new JCheckBox("SUV");
        suvBox.setFont(LucidaGrandeSmall);

        truckBox = new JCheckBox("Truck");
        truckBox.setFont(LucidaGrandeSmall);

        otherBox = new JCheckBox("Other");
        otherBox.setFont(LucidaGrandeSmall);

    }

    private void createCategoryBox(){
        newBox = new JCheckBox("New");
        newBox.setFont(LucidaGrandeSmall);


        usedBox = new JCheckBox("Used");
        usedBox.setFont(LucidaGrandeSmall);

        preownedBox = new JCheckBox("Certified Preowned");
        preownedBox.setFont(LucidaGrandeSmall);

    }

    private void createPageComponents(){
        pageLable = new JLabel(" Pages  ");
        pageLable.setFont(LucidaGrandeForPage);
        prePageButton = new JButton("<");
        prePageButton.setFont(LucidaGrandeForPage);
        nextPageButton = new JButton(">");
        nextPageButton.setFont(LucidaGrandeForPage);
        jumpPageButton = new JButton("GO");
        jumpPageButton.setFont(LucidaGrandeForPage);
        jumpPageButton.setForeground(new Color(12,105,172));
        pageNumText = new JTextField(5);
        pageNumText.setMaximumSize(new Dimension(30, 20));


    }

    public static void main(String[] args) {

        new DealerMainFrame("gmps-curry");

    }
}
