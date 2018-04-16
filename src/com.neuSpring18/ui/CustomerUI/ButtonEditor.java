package com.neuSpring18.ui.CustomerUI;


import com.neuSpring18.dto.Category;
import com.neuSpring18.dto.Vehicle;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonEditor extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    Vehicle vehicle;

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
        TableModel tableModel = table.getModel();
        vehicle = new Vehicle();
        vehicle.setId(tableModel.getValueAt(row, 0).toString());
        vehicle.setWebId(tableModel.getValueAt(row, 1).toString());
        vehicle.setCategory(Category.getCategory(tableModel.getValueAt(row, 2).toString()));
        vehicle.setYear(Integer.parseInt(tableModel.getValueAt(row, 3).toString()));
        vehicle.setMake(tableModel.getValueAt(row, 4).toString());
        vehicle.setModel(tableModel.getValueAt(row, 5).toString());
        vehicle.setBodyType(tableModel.getValueAt(row, 6).toString());
        vehicle.setPrice(Double.parseDouble(tableModel.getValueAt(row, 7).toString()));

        System.out.println("-----" + vehicle.getId() + vehicle.getWebId() + vehicle.getCategory() + vehicle.getYear() + vehicle.getModel()
                + vehicle.getModel() + vehicle.getBodyType() + vehicle.getPrice());

        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if (clicked) {
            //SHOW US SOME MESSAGE
            System.out.println("Now, we need to go to the third page");
            //new UIForTable().setVisible(true);
            JOptionPane.showMessageDialog(btn, lbl + " Clicked");

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