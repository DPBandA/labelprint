package jm.org.bsj.labelprint;

import javax.swing.UIManager;

import java.awt.*;
import javax.swing.*;
import jm.org.bsj.labelprint.ui.LabelPrintFrame;

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
