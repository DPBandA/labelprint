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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jm.com.dpbennett.labelprint.ui.LabelPrintFrame;

/**
 * The is the main application class where it all begins.
 *
 * @author Desmond Bennett
 */
public class LabelPrint {

    private LabelPrintFrame labelPrintFrame;

    /**
     * The default constructor. The window frame is centred as part of the the
     * initialization.
     */
    public LabelPrint() {
        labelPrintFrame = new LabelPrintFrame();
        labelPrintFrame.pack();

        // Centre the main window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = labelPrintFrame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        labelPrintFrame.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Gets the main window (frame) of the application.
     *
     * @return
     */
    public LabelPrintFrame getLabelPrintFrame() {
        return labelPrintFrame;
    }

    /**
     * This main method instantiates the class and makes the main window (frame)
     * visible. It also sets the look and feel of the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        LabelPrint labelPrint = new LabelPrint();
        labelPrint.getLabelPrintFrame().setVisible(true);

    }

}
