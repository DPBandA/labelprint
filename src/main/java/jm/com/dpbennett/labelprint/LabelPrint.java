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
import jm.com.dpbennett.labelprint.ui.Application;

/**
 * The is the main application class where it all begins.
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class LabelPrint {

    private Application app;

    /**
     * The default constructor. The window frame is centred as part of the the
     * initialization.
     */
    public LabelPrint() {
        app = new Application();
        app.pack();

        // Centre the main window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = app.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        app.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Gets the main window (frame) of the application.
     *
     * @return
     */
    public Application getApp() {
        return app;
    }

    /**
     * This main method instantiates the class and makes the main window (frame)
     * visible. It also sets the look and feel of the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // Set the system look and feel by default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Find and use the Nimbus look and feel if it's installed.
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        
        LabelPrint labelPrint = new LabelPrint();
        labelPrint.getApp().setVisible(true);

    }

}
