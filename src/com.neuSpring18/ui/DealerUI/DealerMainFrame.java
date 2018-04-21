package com.neuSpring18.ui.DealerUI;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.service.VehicleService;
import com.neuSpring18.service.VehicleServiceImple;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DealerMainFrame extends DealerCommonFrame {

    private JButton addButton, goButton;

    private JButton prePageButton, nextPageButton, jumpPageButton;

    private JTextField searchText;
    private JTextField priceStartText, priceEndText;
    private JTextField yearStartText, yearEndText;
    private JTextField pageNumText;

    private JLabel searchLabel;
    private JLabel filterLabel;
    private JLabel priceLabel, yearLabel, categoryLabel, typeLabel;
    private JLabel totalPagesLabel;

    private JCheckBox carBox, suvBox, truckBox, otherBox;
    private JCheckBox newBox, usedBox, preownedBox;

    private JScrollPane scrollPane;
    private JPanel resultPanel;
    JPanel pagePanel;


    private String dealerName;

    private VehicleService vs;

    private Container c = getContentPane();

    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();

    private int pageNum;
    private Filter filter;

    private ItemListener checkBoxListener;
    private ActionListener buttonListener;

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



    DealerMainFrame(String dealerName){
            this.dealerName = dealerName;
            this.pageNum = 1;
            this.filter = new Filter();
            createComponents();
            addComponents();
            createListeners();
            addListeners();
            makeItVisible();

    }


    private void addListeners() {
        addCheckBoxListeners();
        addButtonLister();
    }

    private void addButtonLister(){
        goButton.addActionListener(buttonListener);
        prePageButton.addActionListener(buttonListener);
        nextPageButton.addActionListener(buttonListener);
        jumpPageButton.addActionListener(buttonListener);
        addButton.addActionListener(buttonListener);
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



    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if(source == goButton){
                setPageNum(1);
                filter.setCategory(category);
                filter.setType(type);
                getTextFields();
                refreshResult(c, getPageNum(), getFilter());


                System.out.println("Total Pages are: "+getTotalPages());
                updateTotalPages();


            }
            if (source == nextPageButton){
                refreshResult(c, getPageNum() + 1, getFilter());
                setPageNum(getPageNum() + 1);
            }
            if (source == prePageButton){
                if(getPageNum() <= 1){
                    setPageNum(1);
                }else {
                    setPageNum(getPageNum() - 1);
                }
                refreshResult(c, getPageNum(), getFilter());
            }
            if(source == jumpPageButton){
                String input = pageNumText.getText();
                setPageNum(input == null || input.isEmpty() ? 1 : Integer.parseInt(input));
                refreshResult(c, getPageNum(), getFilter());
            }
            if (source==addButton){
                try {
                    new DealerEdit();

                }catch (IOException io){
                    io.printStackTrace();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
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

    private void refreshResult(Container c, int pageNum, Filter filter){
        resultPanel.removeAll();
        addResultsPanel(c, pageNum);
        resultPanel.revalidate();
    }

//    private void makeItVisible() {
//        setSize(1100,900);
//        setVisible(true);
//        setResizable(true);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }

    private void addComponents() {
        addFilterPanel(c);
        addTopPanel(c);
        addPageOperatorPanel(c);
        addResultsPanel(c, 1);
    }

    class SingleResultPanel extends JPanel implements ActionListener{
        private JButton carButton;
        private JLabel price;
        private JLabel type;
        private JPanel resultPanel;
        private JLabel imageLabel;
        private JPanel labelResultPanel;
        private JPanel singleResultPanel;
        private Vehicle vehicle;


        private JPanel createLabelResultPanel(String buttonName, double price, String type){
            this.carButton = new JButton(buttonName);
            this.resultPanel = new JPanel();
            this.resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
            this.price = new JLabel(String.valueOf(price)+"$");
            this.type = new JLabel(type);

            resultPanel.add(this.carButton);
            resultPanel.add(this.price);
            resultPanel.add(this.type);

            carButton.addActionListener(this);

            return resultPanel;
        }

        SingleResultPanel(Vehicle vehicle) throws IOException {
            this.labelResultPanel = createLabelResultPanel(vehicle.getModel(), vehicle.getPrice(), vehicle.getBodyType().toString());
            this.singleResultPanel = new JPanel();
            this.singleResultPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            this.vehicle = vehicle;

            Image image;
            try {
                image = ImageIO.read(vehicle.getPhotoUrl());
                imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(300, 250, Image.SCALE_FAST)));
            }catch (IOException e){
                String blankImage = "https://rlv.zcache.com/broken_internet_image_icon_postcard-r579d1199998a41e7a349e8d3a5b1b8d7_vgbaq_8byvr_324.jpg";
                image = ImageIO.read(new URL(blankImage));
                //System.out.println("Image is broken!");
                imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(300, 250, Image.SCALE_FAST)));
            }

            //BufferedImage image = ImageIO.read(new File(imagePath));

            this.singleResultPanel.add(imageLabel);
            this.singleResultPanel.add(this.labelResultPanel);
        }

        public JPanel getSingleResultPanel() {
            return singleResultPanel;
        }

        /*
        car button listener: after clicking, jump to edit page
         */
        @Override
        public void actionPerformed(ActionEvent e){

            new DealerEdit(vehicle);

        }
    }


    private void addResultsPanel(Container c, int pageNum/*, Filter filter*/) {

        ArrayList<SingleResultPanel> singleResultPanels = new ArrayList<>();

        vs = new VehicleServiceImple();

        Sorting s = Sorting.DESCEND_PRICE;
        Paging p = new Paging();
        p.setPageNum(pageNum);
        p.setPerPage(10);

        System.out.println("--------------------");


        int totalPages = (vs.findVehiclesByFilter(dealerName, filter, s, p).getIc().getTotalCount()) / 10;
        System.out.println("Total Cars:"+vs.findVehiclesByFilter(dealerName,filter,s,p).getIc().getTotalCount());
        setTotalPages(totalPages);

        for (Vehicle vehicle : vs.findVehiclesByFilter(dealerName, filter, s, p).getVehicles()) {
            try {
                singleResultPanels.add(new SingleResultPanel(vehicle));
                System.out.println(vehicle);
                System.out.println(filter.getCategory());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (SingleResultPanel sr : singleResultPanels){
            resultPanel.add(sr.getSingleResultPanel());
        }

        c.add(scrollPane, BorderLayout.CENTER);

    }

    private void updateTotalPages(){

        pagePanel.removeAll();
        createPageComponents(getTotalPages());
        addPageOperatorPanel(c);
        pagePanel.revalidate();

    }

    private void addPageOperatorPanel(Container c){

        pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.X_AXIS));
        pagePanel.add(totalPagesLabel);
        pagePanel.add(prePageButton);
        pagePanel.add(nextPageButton);
        pagePanel.add(pageNumText);
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

        JPanel priceInputPanel = new JPanel();
        priceInputPanel.setLayout(new BoxLayout(priceInputPanel, BoxLayout.X_AXIS));
        priceInputPanel.add(priceStartText);
        priceInputPanel.add(new JLabel(" - "));
        priceInputPanel.add(priceEndText);
        priceInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        JPanel yearInputPanel = new JPanel();
        yearInputPanel.setLayout(new BoxLayout(yearInputPanel, BoxLayout.X_AXIS));
        yearInputPanel.add(yearStartText);
        yearInputPanel.add(new JLabel(" - "));
        yearInputPanel.add(yearEndText);
        yearInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel categoryBoxPanel = new JPanel();
        categoryBoxPanel.setLayout(new BoxLayout(categoryBoxPanel, BoxLayout.Y_AXIS));
        categoryBoxPanel.add(newBox);
        categoryBoxPanel.add(usedBox);
        categoryBoxPanel.add(preownedBox);
        categoryBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel typeBoxPanel = new JPanel();
        typeBoxPanel.setLayout(new BoxLayout(typeBoxPanel, BoxLayout.Y_AXIS));
        typeBoxPanel.add(carBox);
        typeBoxPanel.add(suvBox);
        typeBoxPanel.add(truckBox);
        typeBoxPanel.add(otherBox);
        typeBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        filterPanel.add(filterLabel);
        filterPanel.add(new JLabel(" "));

        filterPanel.add(categoryLabel);
        filterPanel.add(categoryBoxPanel);

        filterPanel.add(typeLabel);
        filterPanel.add(typeBoxPanel);

        filterPanel.add(yearLabel);
        filterPanel.add(yearInputPanel);

        filterPanel.add(priceLabel);
        filterPanel.add(priceInputPanel);

        filterPanel.add(goButton);

        c.add(filterPanel,BorderLayout.WEST);
    }

    private void createComponents() {
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(resultPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        createButtons();
        createTextArea();
        createPriceProperty();
        createYearProperty();
        createLabels();
        createCategoryBox();
        createTypeBox();
        createPageComponents(totalPages);
    }

    private void createTextArea(){
        searchText = new JTextField(10);
        searchText.setMaximumSize(new Dimension(100, searchText.getPreferredSize().height));
    }

    private void createButtons(){
        addButton = new JButton("Add Car");
        goButton = new JButton("Go");
    }

    private void createYearProperty(){
        yearStartText = new JTextField(5);
        yearStartText.setMaximumSize(new Dimension(100, yearStartText.getPreferredSize().height));
        yearEndText = new JTextField(5);
        yearEndText.setMaximumSize(new Dimension(100, yearEndText.getPreferredSize().height));
    }

    private void createLabels(){
        searchLabel = new JLabel("Search: ");
        categoryLabel = new JLabel("Category");
        typeLabel = new JLabel("Type");
        filterLabel = new JLabel("Filers");
        priceLabel = new JLabel("Price");
        yearLabel = new JLabel("Year");
    }

    private void createPriceProperty(){
        priceStartText = new JTextField(5);
        priceStartText.setMaximumSize(new Dimension(100, priceStartText.getPreferredSize().height));
        priceEndText = new JTextField(5);
        priceEndText.setMaximumSize(new Dimension(100, priceEndText.getPreferredSize().height));
    }

    private void createTypeBox(){
        carBox = new JCheckBox("Car");
        suvBox = new JCheckBox("SUV");
        truckBox = new JCheckBox("Truck");
        otherBox = new JCheckBox("Other");
    }

    private void createCategoryBox(){
        newBox = new JCheckBox("New");
        usedBox = new JCheckBox("Used");
        preownedBox = new JCheckBox("Certified Preowned");
    }

    private void createPageComponents(int totalPages){
        prePageButton = new JButton("Previous");
        nextPageButton = new JButton("Next");
        jumpPageButton = new JButton("Jump To Page");
        pageNumText = new JTextField(5);
        pageNumText.setMaximumSize(new Dimension(100, pageNumText.getPreferredSize().height));

        String stupidSpace = "                                                               ";
        totalPagesLabel = new JLabel(stupidSpace + "Total " + totalPages + " pages");
    }

    public static void main(String[] args) {

        new DealerMainFrame("gmps-curry");

    }
}
