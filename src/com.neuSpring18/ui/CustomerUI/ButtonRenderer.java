package com.neuSpring18.ui.CustomerUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}