/*
LabelPrint - A general purpose energy label printing application
Copyright (C) 2018  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.labelprint.ui;

import java.awt.Component;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
class MyComboBoxModel extends AbstractListModel implements ComboBoxModel {

    Data[] ComputerComps = {new Data("val1", "Monitor kjkjr erjkej kerkejr rjekrjkejrk rjkejr krjekrjke rkejrkejk rejkrjekrjkejrkejk rkejrkejrk kerkejrkjek rkjrkejrkejk rekrjkejr erj"),
        new Data("val2", "Keyboard"), new Data("val3", "Mouse")};

    Data selection = null;

    @Override
    public Object getElementAt(int index) {
        return ComputerComps[index];
    }

    @Override
    public int getSize() {
        return ComputerComps.length;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Data) anItem; // to select and register an
    } // item from the pull-down list

    // Methods implemented from the interface ComboBoxModel
    @Override
    public Object getSelectedItem() {
        return selection; // to add the selection to the combo box
    }
}

class Data {

    private String name;
    private String value;

    public Data(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}

class ComboBoxRenderer extends JTextArea
        implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
        //Data data = ((Data)value);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        //Set the icon and text.  If icon was null, say so.
        //ImageIcon icon = images[selectedIndex];
        //String pet = petStrings[selectedIndex];
        //setIcon(icon);
        //if (icon != null) {
        setLineWrap(true);
        if (value != null) {
            setText(value.toString());
        }
        //    setFont(list.getFont());
        //} else {
        //    setUhOhText(pet + " (no image available)",
        //                list.getFont());
        //}

        return this;
    }

}

public class JComboBoxModel {

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox cbox = new JComboBox(new MyComboBoxModel());
        cbox.setRenderer(new ComboBoxRenderer());

        cbox.setMaximumRowCount(5);
        frame.add(cbox);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}
