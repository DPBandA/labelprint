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

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.net.URL;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGRect;

/**
 *
 * @author dbennett
 */
public class SVGLabelPanel extends javax.swing.JPanel implements Printable {

    private boolean chkGreenBackground;
    private boolean chkYellowBackground;
    private boolean chkContents;
    private LabelPrintFrame labelPrintFrame;
    private int m_maxNumPage = 1;
    private JSVGCanvas svgCanvas;
    private Document svgDocument;

    /**
     * Creates new form LabelPanel
     */
    public SVGLabelPanel() {
        initComponents();
        initLabel();
    }

    /**
     * Creates new form LabelPanel
     *
     * @param labelPrintFrame
     */
    public SVGLabelPanel(jm.com.dpbennett.labelprint.ui.LabelPrintFrame labelPrintFrame) {
        this.labelPrintFrame = labelPrintFrame;
        initComponents();
        initLabel();

    }

    private void initLabel() {
        svgCanvas = new JSVGCanvas();
        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        URL url = getClass().getClassLoader().getResource("images/EnergyLabel.svg");
        svgCanvas.setURI(url.toString());
        svgCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                // At this time the document is available so get it.
                svgDocument = svgCanvas.getSVGDocument();
                updateLabel();
            }
        });
        
        chkGreenBackground = false;
        chkYellowBackground = false;
        chkContents = true;

        add("Center", svgCanvas);
    }

    public void updateLabel() {

        if (svgCanvas != null && svgDocument != null) {
            svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Type
                    setElementText("type", labelPrintFrame.getEnergyLabelData().getType());
                    // Capacity tk impl capacity unit instead of hard code
                    setElementText("capacity", labelPrintFrame.getEnergyLabelData().getCapacity() + "m");
                    // Set location of the capacity unit power based on width of capacity
                    Element svgElement = svgDocument.getElementById("capacity");
                    SVGLocatable locatable = (SVGLocatable) svgElement;
                    SVGRect rect = locatable.getBBox();
                    Element unitPower = svgDocument.getElementById("capacityUnitPowerTextSpan");
                    unitPower.setAttribute("x", "" + (rect.getX() + rect.getWidth()));
                    // Defrost
                    setElementText("defrost", labelPrintFrame.getEnergyLabelData().getDefrost());
                    // Distributor
                    setElementText("distributor", labelPrintFrame.getEnergyLabelData().getDistributor());
                    // Manufacturer
                    setElementText("manufacturer", labelPrintFrame.getEnergyLabelData().getManufacturer());
                    // Model
                    setElementText("model", labelPrintFrame.getEnergyLabelData().getModel());
                    // Country
                    setElementText("country", labelPrintFrame.getEnergyLabelData().getCountry());
                    // Operating cost
                    setElementText("operatingCost", "$" + labelPrintFrame.getEnergyLabelData().getOperatingCost());
                    // Energy note
                    setElementText("note1.1", labelPrintFrame
                            .getSystemOptions().getProperty("Note1_1")
                            .replace("[AnnualConsumption]",
                                    labelPrintFrame.getEnergyLabelData().getAnnualConsumption())
                            .replace("[CostPerKwh]",
                                    labelPrintFrame.getEnergyLabelData().getCostPerKwh()));
                    setElementText("note1.2", labelPrintFrame.
                            getSystemOptions().getProperty("Note1_2"));
                    // Validity
                    setElementText("validity", labelPrintFrame.getEnergyLabelData().getValidity());
                    // Standard note
                    setElementText("note2.1", labelPrintFrame
                            .getSystemOptions().getProperty("Note2_1"));
                    setElementText("note2.2", labelPrintFrame
                            .getSystemOptions().getProperty("Note2_2")
                            .replace("[Standard]",
                                    labelPrintFrame.getEnergyLabelData().getStandard()));
                    // Violation note
                    setElementText("note3.1", labelPrintFrame
                            .getSystemOptions().getProperty("Note3_1"));
                    setElementText("note3.2", labelPrintFrame
                            .getSystemOptions().getProperty("Note3_2"));
                    setElementText("note3.3", labelPrintFrame
                            .getSystemOptions().getProperty("Note3_3"));

                }
            });
        }

    }

    private void setElementText(String elementId, String content) {
        if (svgDocument != null) {
            Element element = svgDocument.getElementById(elementId);
            element.setTextContent(content);
        }
    }

    public void showGreenBackground(boolean flag) {
        chkGreenBackground = flag;
    }

    public void showYellowBackground(boolean flag) {
        chkYellowBackground = flag;
    }

    public void showContents(boolean flag) {
        chkContents = flag;
    }

    public boolean exportLabelToRasterGraphic(String fileName, String formatName) {

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPanel = new javax.swing.JPanel();
        jEditLabelData = new javax.swing.JButton();
        jSaveLabelData = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(550, 500));
        setPreferredSize(new java.awt.Dimension(550, 500));
        setLayout(new java.awt.BorderLayout());

        jButtonPanel.setLayout(new java.awt.GridLayout(1, 2, 2, 5));

        jEditLabelData.setText("Edit");
        jEditLabelData.setToolTipText("Edit the label data");
        jEditLabelData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditLabelDataActionPerformed(evt);
            }
        });
        jButtonPanel.add(jEditLabelData);

        jSaveLabelData.setText("Save");
        jSaveLabelData.setToolTipText("Save the label data");
        jSaveLabelData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveLabelDataActionPerformed(evt);
            }
        });
        jButtonPanel.add(jSaveLabelData);

        add(jButtonPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jEditLabelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditLabelDataActionPerformed
        // tk use to test setting label data
        System.out.println("Impl saving label from SVG Label Panel");
        updateLabel();


    }//GEN-LAST:event_jEditLabelDataActionPerformed

    private void jSaveLabelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveLabelDataActionPerformed
        System.out.println("Impl exporting label from SVG Label Panel");
    }//GEN-LAST:event_jSaveLabelDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jEditLabelData;
    private javax.swing.JButton jSaveLabelData;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics pg, PageFormat pageFormat,
            int pageIndex) throws PrinterException {

        if (pageIndex >= m_maxNumPage) {
            return NO_SUCH_PAGE;
        }

        pg.translate((int) pageFormat.getImageableX(),
                (int) pageFormat.getImageableY());

        // not used
        int wPage = (int) pageFormat.getImageableWidth();
        int hPage = (int) pageFormat.getImageableHeight();

        //printContentOfLabel((Graphics2D) pg, imageScaleX, imageScaleY, 0, 0);
        System.gc();

        return PAGE_EXISTS;
    }

}
