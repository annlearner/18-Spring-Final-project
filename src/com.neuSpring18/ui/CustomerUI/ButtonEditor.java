package com.neuSpring18.ui.CustomerUI;


import com.neuSpring18.dto.CarType;
import com.neuSpring18.dto.Category;
import com.neuSpring18.dto.Vehicle;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

class ButtonEditor extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    private Vehicle vehicle;
    URL url;


    public ButtonEditor(JTextField txt) {
        super(txt);

        btn = new JButton();

        btn.setOpaque(true);
        setClickCountToStart(1);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clicked = true;
                fireEditingStopped();
            }
        });
    }


    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
                                                 boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        //clicked=true;
        TableModel model = table.getModel();
        vehicle =setVehicle( model,row);


        return btn;
    }


    public Vehicle setVehicle(TableModel model,int row) {

        vehicle = new Vehicle();
        String pictureURL = model.getValueAt(row, 7).toString();
        System.out.println("url is : "+pictureURL);
        try {
            url = new URL(pictureURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
//            url = new URL("http://inventory-dmg.assets-cdk.com/3/1/3/13814368313x90.jpg");
        }
        vehicle.setPhotoUrl(url);
        String category = model.getValueAt(row, 1).toString();
        vehicle.setCategory(Category.getCategory(category));
        vehicle.setYear(Integer.parseInt(model.getValueAt(row, 2).toString()));
        vehicle.setMake(model.getValueAt(row, 3).toString());
        ;
        vehicle.setModel(model.getValueAt(row, 4).toString());
        String bodyType = model.getValueAt(row, 5).toString();
        vehicle.setBodyType(CarType.getType(bodyType));
        vehicle.setPrice(Double.parseDouble(model.getValueAt(row, 6).toString()));
        return vehicle;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if (clicked) {

            new Car(vehicle).setVisible(true);


        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked = false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {

        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }


}
