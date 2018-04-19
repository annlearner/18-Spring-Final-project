package com.neuSpring18.ui.DealerUI;

import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.dto.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DealerEdit extends DealerCommonFrame{// implements Runnable
    public int fWidth, fHeight;
    private String meid, mewebId, memake, memodel, metrim, imageURL, oldImgURL;
    private Category mecategory;
    private int meyear;
    private double meprice, picRatio1;// , picRatio2, picRatio3;
    private CarType mebodyType;
    private URL mephotoUrl;
    private ResizeListener c;
    private GridBagLayout centerLayout;
    private GridBagConstraints ccs;
    private Image image;
    private JScrollPane centerScrollPane;
    private JComboBox cateCombo, typeCombo;
    private JPanel northPanel, centerPanel, southPanel, setButtonPanel;
    private JLabel l1, carLabel, cateLabel, yearLabel, typeLabel, makeLabel, trimLabel, priceLabel, modelLabel;
    private JButton save, cancel, delcar, picadd, picdel;// , toLeft, toRight;// last two for move pics, should be
    private JTextField cate, year, type, make, trim, price, model;
    private ImageIcon img1;// , img2, img3;// 3pic shoud be enough

    private Font ft = new Font("Book Antiqua", Font.HANGING_BASELINE, 35);
    private Font ft1 = new Font("Book Antiqua", Font.BOLD, 20);
    private Font ft2 = new Font("Book Antiqua", Font.BOLD, 15);

    // private CListener cl;

    /*
     * edit_view.addComponmentListener(new ComponentAdapter(){ public void
     * componentResized(ComponentEvent e){ // write you code here } }
     */
    /*
     * public edit_view(Car car) throws MalformedURLException, IOException {// make
     * up the constructors
     *
     * image = ImageIO.read(new URL(imageURL));
     *
     *
     * }
     */

    /*
     * private String id; private String webId;
     *
     * private Category category; private int year; private String make; private
     * String model; private String trim; private Type bodyType; private double
     * price; private URL photoUrl;
     */

    /*
     * private void addButton(JPanel panel, GridBagLayout lay, GridBagConstraints c,
     * String name) { JButton bt = new JButton(name); lay.setConstraints(bt, c);
     * panel.add(bt); }
     *
     * private void addlabel(JPanel panel, GridBagLayout lay, GridBagConstraints c,
     * String name) { JButton bt = new JButton(name); lay.setConstraints(bt, c);
     * panel.add(bt); }
     *
     * private void addTextArea(JPanel panel, GridBagLayout lay, GridBagConstraints
     * c, String name) { JTextArea text = new JTextArea(name);
     * lay.setConstraints(text, c); panel.add(text); }
     */

    private void setimageURL(String URL) {
        imageURL = URL;
    }

    private class CListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object o = ae.getSource();
            // System.out.println(o);

            String buttonName = ae.getActionCommand();
            // System.out.println(buttonName);
            if (buttonName.equals("Save")) {// don't know why
                System.out.println("save");
                // saveVehicle();
                JOptionPane.showMessageDialog(null, "Save successed.");
            }

            // exit
            if (buttonName.equals("Cancel")) {// don't know why
                // System.out.println("cancel");
                // new dealermainframe();
                System.exit(0);// done
            }

            if (buttonName.equals("Delete This Vehicle")) {// don't know why
                // send back some info
                // System.out.println("deleting....");
                int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Vehicle",
                        JOptionPane.YES_NO_OPTION); // 0or 1
                if (n == 0) {// yes
                    deleteCar();
                    // System.exit(0);//done
                } else {
                    return;
                }
                // System.exit(0);//done
            }

            // add pic
            if (o == picadd) {// this works again ....... dont know why
                System.out.println("Add pic");
                String inputValue = null;
                System.out.println(imageURL + "    <<<<");
                if (imageURL == null) {
                    inputValue = JOptionPane.showInputDialog("Please input url");
                } else {
                    JOptionPane.showMessageDialog(null, "must delete corrent pic first!");
                }
                if (inputValue != null) {
                    imageURL = inputValue;
                }

            }
            if (o == picdel) {
                if(imageURL != null ) {//|| imageURL != ""
                    int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Picture",
                            JOptionPane.YES_NO_OPTION); // 0or 1
                    if (n == 0) {// yes
                        // System.out.println("del pic");
                        imageURL = null;// how to repaint after update?
                        image = null;
                        deleteCar();
                        // System.exit(0);//done
                    } else {
                        return;
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "There is no picture here!");
                }
            }
            // int index=((JComboBox)o).getSelectedIndex();//get idx
            // String item=((JComboBox)o).getSelectedItem().toString();//get idxtostring
            // System.out.println(index+"<><><>< "+item);//this works
        }
        // private void redraw(JFrame j) {
        // j.repaint();
        // }

        private void deleteCar() {
            // call some method,adn return id ,then.......
            // send back new Vehicle ,on id?
        }
    }

    public void setPanel() throws MalformedURLException, IOException {
        BorderLayout mainLayout = new BorderLayout();
        getContentPane().setLayout(mainLayout);
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(750, 40));
        setNorth();

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.lightGray);
        centerScrollPane = new JScrollPane();
        centerScrollPane.setViewportView(centerPanel);
        setCenter();

        ResizeListener c = new ResizeListener();// inside panel work?
        c.panel = centerPanel;
        c.label = l1;
        centerPanel.addComponentListener(c);

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(750, 30));
        setSouth();
    }

    private void buildListener() {
        // cl = new CListener();
    }

    private void addListener() {
        // thisListener

        // save.addActionListener(cl);
        // cancel.addActionListener(cl);
        // picadd.addActionListener(cl);
        // picdel.addActionListener(cl);
        // cateCombo.addActionListener(cl);
    }

    private void setCenter() throws MalformedURLException, IOException {// textbox[] ?
        getImageAtURL();// maybe new thread?
        String setBorderStr = setBorderStr();
        centerPanel.setBorder(BorderFactory.createTitledBorder(setBorderStr));// can be replace
        // set up the margin of all components
        centerNewAll();
        setCenterMidPanel();
        centerSetFontAll();
        centerAddAll();
        setCenterGridbag();
        setCenterLinePic();
        setCenterButtonPanel();
        setCenterLine1();
        setCenterLine2();
        setCenterLineLast();
    }

    private void getImageAtURL() throws MalformedURLException, IOException {
        // TODO Auto-generated method stub
        //imageURL = "https://images-na.ssl-images-amazon.com/images/I/61N2WlCHzyL.jpg";
        // there is a if
        if (imageURL == null) {
            // imageURL = "";
        } else {
            image = ImageIO.read(new URL(imageURL));
            picRatio1 = (double) image.getWidth(this) / (double) image.getHeight(this);// w/h = ratio
            img1 = new ImageIcon(image);
        }
    }

    private void setCenterLineLast() {
        // 5th line
        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(makeLabel, ccs);

        ccs.gridwidth = 6;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(make, ccs);

        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(modelLabel, ccs);

        ccs.gridwidth = 0;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(model, ccs);

    }

    private void setCenterLine2() {
        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(typeLabel, ccs);

        ccs.gridwidth = 5;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(typeCombo, ccs);
        // centerLayout.setConstraints(type, ccs); // original

        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(trimLabel, ccs);

        ccs.gridwidth = 0;
        ccs.weightx = 0;
        ccs.weighty = 0;// GridBagConstraints.REMAINDER;
        centerLayout.setConstraints(trim, ccs);
    }

    private void setCenterLine1() {
        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(cateLabel, ccs);

        ccs.gridwidth = 2;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(cateCombo, ccs);

        // 3rd line
        ccs.gridwidth = 3;
        ccs.weightx = 0;
        ccs.weighty = 0;

        centerLayout.setConstraints(yearLabel, ccs);

        ccs.gridwidth = 3;
        ccs.weightx = 1;
        ccs.weighty = 0;
        centerLayout.setConstraints(year, ccs);

        ccs.gridwidth = 3;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(priceLabel, ccs);

        ccs.gridwidth = 0;
        ccs.weightx = 1;
        ccs.weighty = 0;
        centerLayout.setConstraints(price, ccs);
    }

    private void setCenterButtonPanel() {
        ccs.gridwidth = 0;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(setButtonPanel, ccs);
    }

    private void setCenterLinePic() {
        if (imageURL == null) {
            l1.setBackground(Color.GRAY);
        } else {
            l1.setIcon(img1);
        }
        l1.setHorizontalAlignment(l1.CENTER);
        ccs.gridwidth = 0;// we need a new panel for this pics parts
        ccs.weightx = 1;
        ccs.weighty = 1;
        centerLayout.setConstraints(l1, ccs);

    }

    private void setLabelListerner() {// mouse listener
        /*
         * l1.addMouseListener(new MouseAdapter() { int count = 0; public void
         * mouseClicked(MouseEvent mleft) {
         * System.out.println(l1.getWidth()+"    "+l1.getHeight()); //
         * l3.setBorder(BorderFactory.createBevelBorder(5,Color.WHITE,Color.CYAN)); //
         * l1.setForeground(Color.red); if (count % 2 == 0) {
         * l1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); } else
         * l1.setBorder(BorderFactory.createLineBorder(Color.red, 5)); count++; if
         * (count > (Integer.MAX_VALUE - 100000000)) count = 0; //
         * System.out.println(count); } });
         */
        // TODO Auto-generated method stub

    }

    private void setCenterGridbag() {
        centerPanel.setLayout(centerLayout);
        ccs.fill = GridBagConstraints.BOTH;
        ccs.anchor = GridBagConstraints.CENTER;
        // ccs.ipadx = 1; // ◊Èº˛ƒ⁄≤øÃÓ≥‰ø’º‰£¨º¥∏¯◊Èº˛µƒ◊Ó–°øÌ∂»ÃÌº”∂‡¥Ûµƒø’º‰
        ccs.ipady = 1; // ◊Èº˛ƒ⁄≤øÃÓ≥‰ø’º‰£¨º¥∏¯◊Èº˛µƒ◊Ó–°∏ﬂ∂»ÃÌº”∂‡¥Ûµƒø’º‰
        ccs.insets = new Insets(15, 10, 15, 10);
    }

    private void setCenterMidPanel() {
        setButtonPanel = new JPanel();
        GridLayout button_Layout = new GridLayout(1, 2);
        CListener centerListener = new CListener();// flat to whole width
        setButtonPanel.setLayout(button_Layout);
        // setButtonPanel.add(picprev);
        setButtonPanel.add(picadd);
        setButtonPanel.add(picdel);
        picadd.addActionListener(centerListener);
        picdel.addActionListener(centerListener);
        cateCombo.addActionListener(centerListener);
        // cateCombo.setSelectedIndex(2);//this worked
    }

    private void centerSetFontAll() {
        picadd.setFont(ft1);
        picdel.setFont(ft1);
        yearLabel.setFont(ft1);
        year.setFont(ft1);
        priceLabel.setFont(ft1);
        price.setFont(ft1);
        cateLabel.setFont(ft1);
        cate.setFont(ft1);
        model.setFont(ft1);
        make.setFont(ft1);
        makeLabel.setFont(ft1);
        modelLabel.setFont(ft1);
        typeLabel.setFont(ft1);
        trimLabel.setFont(ft1);

        // type.setFont(ft1);
        // typeCombo.setFont(ft2);
        trim.setFont(ft1);
    }

    private void centerAddAll() {
        centerPanel.add(l1);

        centerPanel.add(setButtonPanel);
        centerPanel.add(cateLabel);
        centerPanel.add(cateCombo);

        centerPanel.add(yearLabel);
        centerPanel.add(year);
        centerPanel.add(priceLabel);
        centerPanel.add(price);

        centerPanel.add(typeLabel);
        // centerPanel.add(type);
        centerPanel.add(typeCombo);
        centerPanel.add(trimLabel);
        centerPanel.add(trim);

        centerPanel.add(makeLabel);
        centerPanel.add(make);
        centerPanel.add(modelLabel);
        centerPanel.add(model);
    }

    private void centerNewAll() {
        centerLayout = new GridBagLayout();// must this kind of layout
        ccs = new GridBagConstraints();

        l1 = new JLabel();
        picadd = new JButton("Add Picture");
        picdel = new JButton("Delete PIcture");
        // picprev = new JButton("Prev Picture");
        // picnext = new JButton("Next Picture");
        yearLabel = new JLabel("Year");
        year = new JTextField();
        priceLabel = new JLabel("Price");
        price = new JTextField();
        cateLabel = new JLabel("Category");
        cateLabel.setFont(ft1);
        cateCombo = new JComboBox();
        cateCombo.setModel(new DefaultComboBoxModel<>(new String[] { "New", "Certified", "Used" }));
        typeCombo = new JComboBox(new DefaultComboBoxModel<>(
                new String[] { "VAN", "WAGON", "CAR", "SUV", "TRUCK", "CARGOVAN", "COMMERCIAL VEHICLE", "NOTYPE" }));
        // VAN, WAGON, CAR, SUV, TRUCK, CARGOVAN, COMMERCIALVEHICLE, NOTYPE;
        cate = new JTextField();// this change to combo
        model = new JTextField();
        makeLabel = new JLabel("Make");
        make = new JTextField();
        modelLabel = new JLabel("Model");
        typeLabel = new JLabel("Type");
        trimLabel = new JLabel("Trim");
        type = new JTextField();// maybe set to combo?
        trim = new JTextField();
        CListener centerListener = new CListener();
    }

    private String setBorderStr() {
        // TODO Auto-generated method stub
        return null;
    }

    private void setSouth() {// two bottoms
        // TODO Auto-generated method stub
        GridLayout southLayout = new GridLayout(1, 2);// flat to whole width
        southPanel.setLayout(southLayout);
        CListener southListener = new CListener();
        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");
        delcar = new JButton("Delete This Vehicle");

        cancel.setFont(ft1);
        save.setFont(ft1);
        delcar.setFont(ft1);
        southPanel.add(cancel);
        southPanel.add(save);
        southPanel.add(delcar);
        save.addActionListener(southListener);
        cancel.addActionListener(southListener);
        delcar.addActionListener(southListener);
    }

    private void setNorth() {// top, the label ,image bar????
        BorderLayout northLayout = new BorderLayout();
        northPanel.setLayout(northLayout);
        // there is method to get the String
        String northLabel = titleGen();// to generate the title(car1(cate-yar-make -model))
//        JLabel carLabel = new JLabel("car_int_p1_p2...");
//        carLabel.setFont(ft);
//        northPanel.add(carLabel);
    }

    private String titleGen() {// for north panel
        String tmp = "helloword";
        return tmp;
    }

    // --------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
        // TODO Auto-generated method stub
         new DealerEdit();

    }

    // --------------------------------------------------------------------------------------------------------------------

    public void test() throws MalformedURLException, IOException, InterruptedException {
        setLayout();
        addComponents();
        makeItVisible();
        buildListener();
        addListener();
        setData();// set from outter_data
        this.oldImgURL = this.imageURL;
        Thread chk = new Thread(() -> {
            try {
                try {
                    autoChk();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        chk.start();

    }

    private void autoChk() throws InterruptedException, MalformedURLException, IOException {// 0.2s chk if new

        int count = 0;
        while (true) {
            // System.out.println(typeCombo.getSelectedItem().toString());this worked
            Thread.sleep(200);
            System.out.println(count++);
            if (imageURL == oldImgURL) {
                // System.out.println(count--);
                continue;
            } else {
                picReload();
                this.repaint();
            }
            System.out.println(count--);
        }
    }

    private void picReload() throws MalformedURLException, IOException {
        // empty,and when there is new pic replace it

        if (imageURL == null) {
            l1.setBackground(Color.GRAY);
            l1.setIcon(null);
            this.repaint();
        } else {
            System.out.println(imageURL + "<<");
            image = ImageIO.read(new URL(imageURL));
            picRatio1 = (double) image.getWidth(this) / (double) image.getHeight(this);// w/h = ratio
            // img1 = new ImageIcon(image);
            setpicRepaint();
            // img1.setImage(img1.getImage().getScaledInstance(afterWidth1, afterHeight1,
            // Image.SCALE_SMOOTH));
            // l1.setIcon(img1);
            this.repaint();
            oldImgURL = imageURL;
        }
        // img1.setImage(img1.getImage().getScaledInstance(afterWidth1, afterHeight1,
        // Image.SCALE_SMOOTH));
        // l1.setIcon(img1);
    }

    public void setpicRepaint() {
        int lheight = l1.getHeight();
        int lwidth = l1.getWidth();
        // System.out.println(lheight + "[][]" + lwidth);
        int afterWidth1 = 0, afterHeight1 = 0;
        int labelRatio = lwidth / lheight;

        if (picRatio1 < labelRatio) {
            afterHeight1 = lheight;
            afterWidth1 = (int) (afterHeight1 * picRatio1);
        } else {
            afterWidth1 = lwidth;
            afterHeight1 = (int) (afterWidth1 / picRatio1);
        }

        if (imageURL != null) {
            img1 = new ImageIcon(image);
            img1.setImage(img1.getImage().getScaledInstance(afterWidth1, afterHeight1, Image.SCALE_SMOOTH));
            l1.setIcon(img1);
        }
    }

    private void setData() {
        // TODO Auto-generated method stub
    }

    private void addComponents() throws MalformedURLException, IOException {
        setPanel();
        //add(northPanel, "North");
        add(centerPanel, "Center");
        add(southPanel, "South");
    }

    private void setLayout() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
    }

    private String carlabel() {
        String res = null;
        return res;
    }

    class ResizeListener extends ComponentAdapter {
        public JPanel panel;
        public JLabel label;
        private int lwidth;
        private int lheight;
        public void componentResized(ComponentEvent e) {
            getLabelSize();
        }

        public void getLabelSize() {
            this.lheight = label.getHeight();
            this.lwidth = label.getWidth();
            // System.out.println(lheight + "[][]" + lwidth);
            int afterWidth1 = 0, afterHeight1 = 0;
            int labelRatio = lwidth / lheight;

            if (picRatio1 < labelRatio) {
                afterHeight1 = lheight;
                afterWidth1 = (int) (afterHeight1 * picRatio1);
            } else {
                afterWidth1 = lwidth;
                afterHeight1 = (int) (afterWidth1 / picRatio1);
            }

            if (imageURL != null) {
                img1 = new ImageIcon(image);
                img1.setImage(img1.getImage().getScaledInstance(afterWidth1, afterHeight1, Image.SCALE_SMOOTH));
                l1.setIcon(img1);
            }
        }
    }

    public DealerEdit(Vehicle v){
        try {

            test();

            this.meprice = v.getPrice();
            this.memake = v.getMake();
            this.memodel = v.getModel();
            this.metrim = v.getTrim();
            this.meyear = v.getYear();
            this.mecategory = v.getCategory();
            this.meid = v.getId();
            this.mewebId = v.getWebId();
            this.typeCombo.setSelectedIndex(typeToInt(v.getBodyType()));
            // this.mebodyType = v.getBodyType(); // ??
            // types are: VAN, WAGON, CAR, SUV, TRUCK, CARGOVAN, COMMERCIALVEHICLE, NOTYPE;
            this.mephotoUrl = v.getPhotoUrl();
            setToMe();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private int typeToInt(CarType t) {
        int res = 0;
        if (t.equals(CarType.VAN))
            return 0;
        if (t.equals(CarType.WAGON))
            return 1;
        if (t.equals(CarType.CAR))
            return 2;
        if (t.equals(CarType.SUV))
            return 3;
        if (t.equals(CarType.TRUCK))
            return 4;
        if (t.equals(CarType.CARGOVAN))
            return 5;
        if (t.equals(CarType.COMMERCIALVEHICLE))
            return 6;
        if (t.equals(CarType.NOTYPE))
            return 7;
        // types are: VAN, WAGON, CAR, SUV, TRUCK, CARGOVAN, COMMERCIALVEHICLE, NOTYPE;
        return 0;
    }

    private void setToMe() {
        // cateCombo.setSelectedItem(arg0);
        price.setText(String.valueOf(meprice));
        make.setText(memake);
        model.setText(memodel);
        trim.setText(metrim);
        year.setText(String.valueOf(meyear));
        imageURL = mephotoUrl.toString();

        setMeCombo();
        // cateCombo.setSelectedIndex(2);//test
        // /* case("new""used""certified"): return NEW;
        // case("used"): return USED;
        // case("certified"): return CERTIFIED;*/
    }// method

    private void setMeCombo() {
        if (mecategory.toString().equals("new")) {
            cateCombo.setSelectedIndex(0);
        }
        if (mecategory.toString().equals("sed")) {
            cateCombo.setSelectedIndex(2);
        }
        if (mecategory.toString().equals("certified")) {
            cateCombo.setSelectedIndex(1);
        }
    }// method

    public DealerEdit() throws IOException, InterruptedException {// for new
        Vehicle v = new Vehicle();
        test();

        this.meprice = 0;
        this.memake = null;
        this.memodel = null;
        this.metrim = null;
        this.meyear = 0;
        this.mecategory = null;
        this.meid = null;
        this.mewebId = null;
        this.typeCombo.setSelectedIndex(0);
        // this.mebodyType = v.getBodyType(); // ??
        // types are: VAN, WAGON, CAR, SUV, TRUCK, CARGOVAN, COMMERCIALVEHICLE, NOTYPE;
        this.mephotoUrl = null;

/*	    private String id;
	    private String webId;
	    private Category category;
	    private int year;
	    private String make;
	    private String model;
	    private String trim;
	    private Type bodyType;
	    private double price;
	    private URL photoUrl;*/


    }

    private Vehicle saveVehicle() {
        Vehicle v = new Vehicle();
        // private Type mebodyType;
        v.setBodyType(comboToType(typeCombo.getSelectedItem().toString()));
        v.setCategory(mecategory);
        v.setId(meid);//
        v.setWebId(mewebId);//
        v.setMake(memake);
        v.setModel(memodel);
        v.setPhotoUrl(mephotoUrl);
        v.setPrice(meprice);
        v.setTrim(metrim);
        v.setYear(meyear);

        return v;
    }

    private CarType comboToType(String s) {
        if (s.equals(CarType.CAR.toString())) {
            return CarType.CAR;
        }
        if (s.equals(CarType.CARGOVAN.toString())) {
            return CarType.CARGOVAN;
        }
        if (s.equals(CarType.COMMERCIALVEHICLE.toString())) {
            return CarType.COMMERCIALVEHICLE;
        }
        if (s.equals(CarType.NOTYPE.toString())) {
            return CarType.NOTYPE;
        }
        if (s.equals(CarType.SUV.toString())) {
            return CarType.SUV;
        }
        if (s.equals(CarType.TRUCK.toString())) {
            return CarType.TRUCK;
        }
        if (s.equals(CarType.VAN.toString())) {
            return CarType.VAN;
        }
        if (s.equals(CarType.WAGON.toString())) {
            return CarType.WAGON;
        }
        return null;
    }
	
	
	

/*	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}*/

}

/*
 * //modify size class ResizeListener extends ComponentAdapter { public JPanel
 * j; public JLabel l; private static int lwidth; private static int lheight;
 *
 * public void componentResized(ComponentEvent e){
 * System.out.println("MMMMMMMMMMM"); getLabelSize(); }
 *
 * public void getLabelSize() { this.lheight = l.getHeight(); this.lwidth =
 * l.getWidth(); System.out.println(lheight+"[][]"+lwidth); } }
 */
