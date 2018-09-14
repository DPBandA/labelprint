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
package jm.com.dpbennett.labelprint;

import java.awt.*;
import jm.com.dpbennett.labelprint.ui.LabelPrintFrame;

/**
 * Title:        Refrigerator Energy Label Print
 * Description:  Generates energy consumption labels for refrigerators tested by the Bureau of Standards (Jamaica).
 * Copyright:    Copyright (c) 2001
 * Company:      Bureau of Standards (Jamaica)
 * @author Desmond Bennett
 * @version 1.0
 */
public class LabelPrint {

    boolean packFrame = false;

    /**Construct the application*/
    public LabelPrint() {
        LabelPrintFrame frame = new LabelPrintFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    /**Main metho
     * @param args*/
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | IllegalAccessException 
//                | InstantiationException | UnsupportedLookAndFeelException e) {
//            System.out.println(e);
//        }
        LabelPrint labelPrint = new LabelPrint();

    }
}
