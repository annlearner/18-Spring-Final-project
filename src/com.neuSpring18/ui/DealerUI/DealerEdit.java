package com.neuSpring18.ui.DealerUI;

import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.dto.*;
import com.neuSpring18.service.VehicleServiceImple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DealerEdit extends DealerCommonFrame {


    private String meid, mewebId, memake, memodel, metrim, imageURL, oldImgURL;
    private Category mecategory;
    private int meyear;
    private double meprice, picRatio1;
    private CarType mebodyType;
    private URL firstPic;
    private List mephotoUrl;
    private List tempPhotoUrl;
    private List newPics;
    private int currentPicIdx = 0;
    private Image image;
    private ResizeListener c;
    private GridBagLayout centerLayout;
    private GridBagConstraints ccs;

    private JScrollPane centerScrollPane;
    private JComboBox cateCombo, typeCombo;
    private List cateItems, typeItems;
    private JPanel northPanel, centerPanel, southPanel, setButtonPanel;
    private JLabel l1, carLabel, picinfo, cateLabel, yearLabel, typeLabel, makeLabel, trimLabel, priceLabel, modelLabel;

    private JButton addURL, save, cancel, delcar, picadd, picdel, picprev, picnext;
    private JTextField cate, year, type, make, trim, price, model;
    private ImageIcon img1;
    private boolean isNewCar;
    private VehicleServiceImple vsi = new VehicleServiceImple();

    private Font ft1 = new Font("Book Antiqua", Font.BOLD, 20);


    private void fillListImg() {

        mephotoUrl.add("https://gocdkeys.com/images/captures/diablo-3-ultimate-edition-xbox-one-4.jpg");
        mephotoUrl.add("https://www.instant-gaming.com/images/products/1795/screenshot/1795-4.jpg");

        mephotoUrl.add("src/img/038434b5654131c32f87512f60b05d8.jpg");
        mephotoUrl.add("src/img/sy_65918593531.jpg");

    }

    private Image URLtoImg(String URL) {
        Image res = null;
        try {
            res = ImageIO.read(new URL(URL));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("url read error at load!");
        }
        return res;
    }

    private void setimageURL(String URL) {
        imageURL = URL;
    }

    private class CListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Object o = ae.getSource();
            if (o == save) {
                System.out.println(isNewCar);
                saveVehicle();
            }
            if (o == cancel) {
                dispose();
            }
            if (o == delcar) {
                delCurrCar();
            }
            if (o == picadd) {
                addpicFunc();
            }
            if (o == picdel) {
                delCurrPic();
            }
            if (o == picprev) {
                showPrev();
            }
            if (o == picnext) {
                showNext();
            }
            if (o == addURL) {
                popaddurl();
            }
        }
    }

    private void popaddurl() {
        String str = JOptionPane.showInputDialog("Input URL for Show Picture:");
        try {
            URL u = new URL(str);
            this.firstPic = u;
            mephotoUrl.add(u);
            imageURL = u.toString();
            setPicInfo();//then set to the new car to make different
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Error:Invalid URL!");
            this.firstPic = null;
        }
    }


    private void delCurrCar() {
        int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Vehicle",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            if (!isNewCar) {
                if (vsi.removeVehicle(mewebId, meid)) {
                    JOptionPane.showMessageDialog(null, "delete vehicle successed.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "delete vehicle Failed!");
                }
            } else if (isNewCar) {
                JOptionPane.showMessageDialog(null, "delete vehicle successed.");
                dispose();
            }
        } else {
            return;
        }
    }

    private void delCurrPic() {
        if (mephotoUrl.size() > 0) {//|| imageURL != ""
            int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Picture",
                    JOptionPane.YES_NO_OPTION); // 0 or 1
            if (n == 0) {// yes
                newPics.remove(mephotoUrl.get(currentPicIdx));
                mephotoUrl.remove(currentPicIdx);
                imageURL = mephotoUrl.get(0).toString();
                currentPicIdx = 0;
                setPicInfo();
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no picture here!");
        }
    }

    private void addpicFunc() {
        fileChooser();
//       Thread t = new Thread(()-> fileChooser());  this will fail on MAC-OS
//       t.start();
    }

    private void fileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "picture type", "jpg", "gif", "jpeg", "bmp");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(null);
        File[] files = chooser.getSelectedFiles();
        for (File f : files) {
            mephotoUrl.add(f);
            newPics.add(f);
            System.out.println(f);
            setPicInfo();
        }
    }

    private void showPrev() {
        int temp = currentPicIdx;
        if (temp - 1 >= 0) {
            imageURL = mephotoUrl.get(temp - 1).toString();
            currentPicIdx = temp - 1;
            setPicInfo();
        }
        if (currentPicIdx == 0) {
            picprev.setEnabled(false);
        }
        if (currentPicIdx < mephotoUrl.size() - 1) {
            picnext.setEnabled(true);
        }

    }

    private void showNext() {
        int temp = currentPicIdx;
        if (temp + 1 <= mephotoUrl.size() - 1 && temp >= 0) {
            imageURL = mephotoUrl.get(temp + 1).toString();
            currentPicIdx = temp + 1;
            setPicInfo();
        }
        if (currentPicIdx == mephotoUrl.size() - 1) {
            picnext.setEnabled(false);

        }
        if (currentPicIdx > 0) {
            picprev.setEnabled(true);
        }
    }

    private void setPicInfo() {
        int idx = (currentPicIdx + 1);
        int all = mephotoUrl.size();
        if(mephotoUrl.size()==0){
            idx=0;
        }
        String str = "Picture " + idx + "/" + all;
        picinfo.setText(str);
    }

    public void setPanel() {
        BorderLayout mainLayout = new BorderLayout();
        getContentPane().setLayout(mainLayout);
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(750, 40));
        setNorth();

        centerPanel = new JPanel();
        centerPanel.setBackground(fiterPanelColor);
        centerScrollPane = new JScrollPane();
        centerScrollPane.setViewportView(centerPanel);
        setCenter();

        ResizeListener c = new ResizeListener();
        c.panel = centerPanel;
        c.label = l1;
        centerPanel.addComponentListener(c);

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(750, 30));
        setSouth();
    }

    private void setCenter() {
        getImageAtURL();
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

    private void getImageAtURL() {

        if (imageURL == null) {
        } else {
            try {
                image = ImageIO.read(new URL(imageURL));
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("url read error at load");
            }
            picRatio1 = (double) image.getWidth(this) / (double) image.getHeight(this);// w/h = ratio
            img1 = new ImageIcon(image);
        }
    }

    private void setCenterLineLast() {

        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(modelLabel, ccs);

        ccs.gridwidth = 6;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(model, ccs);

        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(trimLabel, ccs);

        ccs.gridwidth = 0;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(trim, ccs);

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

        ccs.gridwidth = 1;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(makeLabel, ccs);

        ccs.gridwidth = 0;
        ccs.weightx = 0;
        ccs.weighty = 0;
        centerLayout.setConstraints(make, ccs);
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
            l1.setBackground(fiterPanelColor);
        } else {
            l1.setIcon(img1);
        }
        l1.setHorizontalAlignment(l1.CENTER);
        ccs.gridwidth = 0;
        ccs.weightx = 1;
        ccs.weighty = 1;
        centerLayout.setConstraints(l1, ccs);
    }

    private void setCenterGridbag() {
        centerPanel.setLayout(centerLayout);
        ccs.fill = GridBagConstraints.BOTH;
        ccs.anchor = GridBagConstraints.CENTER;
        ccs.ipady = 1;
        ccs.insets = new Insets(15, 10, 15, 10);
    }

    private void setCenterMidPanel() {
        setButtonPanel = new JPanel();
        GridLayout button_Layout = new GridLayout(1, 2);
        CListener centerListener = new CListener();
        setButtonPanel.setLayout(button_Layout);
        setButtonPanel.add(picprev);

        setButtonPanel.add(picadd);

        if (isNewCar) {
            setButtonPanel.add(addURL);
            addURL.addActionListener(centerListener);
        }
        setButtonPanel.add(picdel);
        setButtonPanel.add(picnext);
        picprev.addActionListener(centerListener);
        picnext.addActionListener(centerListener);
        picadd.addActionListener(centerListener);
        picdel.addActionListener(centerListener);
        cateCombo.addActionListener(centerListener);
    }

    private void centerSetFontAll() {
        picadd.setFont(stratumBoldFont);
        picdel.setFont(stratumBoldFont);
        picprev.setFont(stratumBoldFont);
        picnext.setFont(stratumBoldFont);
        yearLabel.setFont(stratumBoldFont);
        year.setFont(stratumBoldFont);
        priceLabel.setFont(stratumBoldFont);
        price.setFont(stratumBoldFont);
        cateLabel.setFont(stratumBoldFont);
        cate.setFont(stratumBoldFont);
        model.setFont(stratumBoldFont);
        make.setFont(stratumBoldFont);
        makeLabel.setFont(stratumBoldFont);
        modelLabel.setFont(stratumBoldFont);
        typeLabel.setFont(stratumBoldFont);
        trimLabel.setFont(stratumBoldFont);
        type.setFont(stratumBoldFont);
        typeCombo.setFont(stratumBoldFont);
        trim.setFont(stratumBoldFont);
        cateCombo.setFont(stratumBoldFont);
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
        centerPanel.add(typeCombo);
        centerPanel.add(makeLabel);
        centerPanel.add(make);

        centerPanel.add(modelLabel);
        centerPanel.add(model);
        centerPanel.add(trimLabel);
        centerPanel.add(trim);
    }

    private void centerNewAll() {
        centerLayout = new GridBagLayout();
        ccs = new GridBagConstraints();

        l1 = new JLabel();
        if (isNewCar) {
            addURL = new JButton("Add Picture's URL");
        }

        picadd = new JButton("Upload Picture");
        picdel = new JButton("Delete Picture");
        picprev = new JButton("<");
        picnext = new JButton(">");
        yearLabel = new JLabel("Year");
        year = new JTextField();
        priceLabel = new JLabel("Price");
        price = new JTextField();
        cateLabel = new JLabel("Category");
        cateLabel.setFont(ft1);
        cateCombo = new JComboBox();
        typeCombo = new JComboBox();
        getComboItem();
        cate = new JTextField();
        model = new JTextField();
        makeLabel = new JLabel("Make");
        make = new JTextField();
        modelLabel = new JLabel("Model");
        typeLabel = new JLabel("Type");
        trimLabel = new JLabel("Trim");
        type = new JTextField();
        trim = new JTextField();
    }

    private void getComboItem() {
        typeItems = new ArrayList<String>();

        typeCombo = new JComboBox(new DefaultComboBoxModel<>(CarType.values()));

        cateCombo = new JComboBox((new DefaultComboBoxModel<>(Category.values())));
    }


    private void setSouth() {
        GridLayout southLayout = new GridLayout(1, 2);
        southPanel.setLayout(southLayout);
        CListener southListener = new CListener();
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(100, 50));
        save = new JButton("Save");
        save.setPreferredSize(new Dimension(100, 50));

        delcar = new JButton("Delete Vehicle");
        delcar.setPreferredSize(new Dimension(100, 50));


        cancel.setFont(StratumBoldFontForButton);
        cancel.setForeground(buttonColor);
        save.setFont(StratumBoldFontForButton);
        save.setForeground(buttonColor);

        delcar.setFont(StratumBoldFontForButton);
        delcar.setForeground(buttonColor);

        southPanel.add(cancel);
        southPanel.add(save);
        southPanel.add(delcar);
        save.addActionListener(southListener);
        cancel.addActionListener(southListener);
        delcar.addActionListener(southListener);
    }

    private void setNorth() {
        GridLayout northLayout = new GridLayout(1, 2);
        northPanel.setLayout(northLayout);
        carLabel = new JLabel("this car");
        picinfo = new JLabel("why here ");
        picinfo.setHorizontalAlignment(SwingConstants.CENTER);
        picinfo.setText("Picture 0/0");
        northPanel.add(carLabel);
        northPanel.add(picinfo);
        picinfo.setFont(ft1);
        carLabel.setFont(ft1);
        carLabel.setForeground(Color.GRAY);
    }


    private void test() {
        newPics = new ArrayList<File>();
        setLayout();
        try {
            addComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setData();
        Thread chk = new Thread(() -> {
            try {
                try {
                    autoChk();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        chk.start();
    }

    protected void makeItVisible() {
        setSize(1330, 965);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Current Login: " + mewebId);
    }

    private void setCarLabel() {
        if (isNewCar) {
            meid = "New Car";
        }
        String str = "CarID: " + meid + "   DealerID: " + mewebId;
        carLabel.setText(str);
    }

    private void autoChk() throws InterruptedException, MalformedURLException, IOException {
        while (true) {
            Thread.sleep(200);
           // chkButton();
            if (imageURL == oldImgURL) {
                continue;
            } else {
                // System.out.println(oldImgURL);
                System.out.println(imageURL);
                picReload();
                l1.repaint();
                oldImgURL = imageURL;
            }
        }
    }

    private void chkButton() {
        if (currentPicIdx == mephotoUrl.size() - 1) {
            picnext.setEnabled(false);
        }
        if (currentPicIdx > 0) {
            picprev.setEnabled(true);
        }
        if (currentPicIdx == 0) {
            picprev.setEnabled(false);
        }
        if (currentPicIdx < mephotoUrl.size() - 1) {
            picnext.setEnabled(true);
        }
    }

    private void picReload() throws MalformedURLException {
        if (imageURL == null) {

            //l1.setBackground(Color.red);
            l1.setIcon(null);
            this.repaint();
        } else {
            //if(imageURL)
            try {
                image = ImageIO.read(new URL(imageURL));
            } catch (IOException e) {
                try {
                    image = ImageIO.read(new File(imageURL));
                } catch (IOException d) {


                    System.out.println("this url is neither url or file...");
                    try {

                        String brokenImage = "src/com.neuSpring18/ui/DealerUI/images/defaultCar.jpg";
                        image = ImageIO.read(new File(brokenImage));
                        l1.setIcon(new ImageIcon(image));
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                    return;
                }
                System.out.println("not url?");


            }
            picRatio1 = (double) image.getWidth(this) / (double) image.getHeight(this);
            setpicRepaint();
            l1.repaint();
            oldImgURL = imageURL;
        }
    }

    public void setpicRepaint() {
        int lheight = l1.getHeight();
        int lwidth = l1.getWidth();
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
    }

    private void addComponents() throws MalformedURLException, IOException {
        setPanel();
        add(northPanel, "North");
        add(centerPanel, "Center");
        add(southPanel, "South");
    }

    private void setLayout() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
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
            int afterWidth1 = 0, afterHeight1 = 0;
            int labelRatio = lwidth / lheight;
            if (picRatio1 < labelRatio) {
                afterHeight1 = lheight;
                afterWidth1 = (int) (afterHeight1 * picRatio1);
            } else {
                afterWidth1 = lwidth;
                afterHeight1 = (int) (afterWidth1 / picRatio1);
            }
            /*

            Please Handle the condition that is null --- from Q
             */
            if (imageURL != null && image != null) {
                img1 = new ImageIcon(image);
                img1.setImage(img1.getImage().getScaledInstance(afterWidth1, afterHeight1, Image.SCALE_SMOOTH));
                l1.setIcon(img1);
            } else if (imageURL == null || image == null) {
                return;
            }
        }
    }

    public DealerEdit(Vehicle v) {
        mephotoUrl = new ArrayList<String>();
        setSystem();
        test();
        this.meprice = v.getPrice();
        this.memake = v.getMake();
        this.memodel = v.getModel();
        this.metrim = v.getTrim();
        this.meyear = v.getYear();
        this.mecategory = v.getCategory();
        this.meid = v.getId();
        this.mewebId = v.getWebId();
        this.mebodyType = v.getBodyType();
        this.firstPic = v.getPhotoUrl();
        this.tempPhotoUrl = v.getMorePhotos();
        this.isNewCar = false;
        mephotoUrl.add(0, firstPic);
        mephotoUrl.addAll(tempPhotoUrl);
        setToMe();
        setCarLabel();
        setPicInfo();
        makeItVisible();
    }

    private void filldata() {
        this.fillListImg();
    }

    private void setToMe() {
        price.setText(String.valueOf(meprice));
        make.setText(memake);
        model.setText(memodel);
        trim.setText(metrim);
        year.setText(String.valueOf(meyear));
        imageURL = mephotoUrl.get(0).toString();
        picprev.setEnabled(false);
        setMeCombo();
    }


    private void setMeCombo() {
        cateCombo.setSelectedItem(this.mecategory);
        typeCombo.setSelectedItem(this.mebodyType);
    }

    private void setSystem() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    public DealerEdit(String dealerID) {
        mephotoUrl = new ArrayList<String>();
        this.mewebId = dealerID;
        this.isNewCar = true;
        setSystem();
        test();
        makeItVisible();



        //setCarLabel();
        // mephotoUrl.addAll(tempPhotoUrl);
        //setToMe();

        setPicInfo();
        setCarLabel();
        this.delcar.setEnabled(false);


    }

    private void saveVehicle() {
        Vehicle v = new Vehicle();
        getAllVehicleData();
        v.setBodyType(mebodyType);
        v.setCategory(mecategory);
        v.setId(meid);
        v.setWebId(mewebId);
        v.setMake(memake);
        v.setModel(memodel);
        v.setPrice(meprice);
        v.setTrim(metrim);
        v.setYear(meyear);
        if (newPics.size() > 0) {
            mephotoUrl.addAll(saveNewPics());
            firstPic = (URL) mephotoUrl.get(0);
            //v.setPhotoUrl((URL)mephotoUrl.get(0));
            v.setPhotoUrl(firstPic);
            mephotoUrl.remove(0);
            v.setMorePhotos(mephotoUrl);
            //v.setPhotoUrl(mephotoUrl);
        } else {
            URL s = (URL) mephotoUrl.get(0);
            v.setPhotoUrl(s);
            mephotoUrl.remove(0);
            v.setMorePhotos(mephotoUrl);
        }
        if (this.isNewCar == true) {
            String s = vsi.addVehicle(mewebId, v);
            JOptionPane.showMessageDialog(null, "New car save sucessful at id:" + s);
            //isNewCar = false;
            this.meid = s;
            this.setPicInfo();
            dispose();
        } else {
            if (vsi.editVehicle(mewebId, v)) {
                JOptionPane.showMessageDialog(null, "Save successed.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Save failed!!", "ERROR0x001", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }
    }

    private List saveNewPics() {
        File dir1 = new File("src/img");
        if (dir1.mkdir()) {
            System.out.println("success");
        } else {
            System.out.println("fail! on :already there!");
        }
        List<File> newPicsTmp = new ArrayList<File>();
        List<String> newPicsNameChanged = new ArrayList<>();
        newPicsTmp = newPics;
        for (File f : newPicsTmp) {
            try {
                Files.copy(Paths.get(f.toURI()), new FileOutputStream(dir1 + File.separator + f.getName()));//now this line works"
            } catch (IOException e) {
                System.out.println("copy error! on file :");
                System.out.println(f);
                System.out.println("jump to next");
                continue;
            }
            File tmp = new File(dir1 + File.separator + f.getName());
            newPicsNameChanged.add(tmp.toString());
        }
        while (newPics.size() > 0) {
            mephotoUrl.remove(newPics.get(0));
            newPics.remove(0);
        }
        return newPicsNameChanged;
    }


    private String pathModify(String str) {
        return null;
    }

    private void getAllVehicleData() {
        this.meprice = Double.valueOf(price.getText());
        this.memake = make.getText();
        this.memodel = model.getText();
        this.metrim = trim.getText();
        this.meyear = Integer.valueOf(year.getText());
        mecategory = Category.getCategory(cateCombo.getSelectedItem().toString());
        this.mebodyType = CarType.getType(typeCombo.getSelectedItem().toString());
    }
}

