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

import java.io.*;
import java.awt.print.PrinterException;
import java.net.URL;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.apache.batik.transcoder.TranscoderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGRect;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.print.PrintTranscoder;

/**
 *
 * @author dbennett
 */
public class SVGLabelPanel extends javax.swing.JPanel {

    private boolean showGreenBackground;
    private boolean showYellowBackground;
    private boolean showContents;
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

    public boolean isShowGreenBackground() {
        return showGreenBackground;
    }

    public void setShowGreenBackground(boolean showGreenBackground) {
        this.showGreenBackground = showGreenBackground;
    }

    public boolean isShowYellowBackground() {
        return showYellowBackground;
    }

    public void setShowYellowBackground(boolean showYellowBackground) {
        this.showYellowBackground = showYellowBackground;
    }

    public boolean isShowContents() {
        return showContents;
    }

    public void setShowContents(boolean showContents) {
        this.showContents = showContents;
    }

    private void initLabel() {
        loadSVGLabel();

        showGreenBackground = true;
        showYellowBackground = true;
        showContents = true;
    }

    private void loadSVGLabel() {
        if (svgCanvas != null) {
            remove(svgCanvas);
        }

        svgCanvas = new JSVGCanvas();
        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        URL url = getClass().getClassLoader().getResource("images/ExtendedEnergyLabel.svg");
        svgCanvas.setURI(url.toString());
        svgCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                svgDocument = svgCanvas.getSVGDocument();
                updateLabel();
            }
        });

        add(svgCanvas, java.awt.BorderLayout.CENTER);

        labelPrintFrame.getjEnergyLabelPane().repaint();
    }

    public void updateLabel() {

        if (labelPrintFrame != null && svgCanvas != null && svgDocument != null) {
            svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {
                // Type
                setElementText("type", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getType());
                // Capacity tk impl capacity unit instead of hard code
                setElementText("capacity", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getCapacity() + "m");
                // Set location of the capacity unit power based on width of capacity
                Element svgElement = svgDocument.getElementById("capacity");
                SVGLocatable locatable = (SVGLocatable) svgElement;
                SVGRect rect = locatable.getBBox();
                Element unitPower = svgDocument.getElementById("capacityUnitPowerTextSpan");
                unitPower.setAttribute("x", "" + (rect.getX() + rect.getWidth()));
                // Defrost
                setElementText("defrost", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getDefrost());
                // Distributor
                setElementText("distributor", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getDistributor());
                // Manufacturer
                setElementText("manufacturer", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getManufacturer());
                // Model
                setElementText("model", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getModel());
                // Country
                setElementText("country", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getCountry());
                // Operating cost
                setElementText("operatingCost", "$" + labelPrintFrame.getLabelDataPanel().getEnergyLabel().getOperatingCost());
                // Energy note
                setElementText("note1.1", labelPrintFrame
                        .getSystemOptions().getProperty("Note1_1")
                        .replace("[AnnualConsumption]",
                                labelPrintFrame.getLabelDataPanel().getEnergyLabel().getAnnualConsumption())
                        .replace("[CostPerKwh]",
                                labelPrintFrame.getLabelDataPanel().getEnergyLabel().getCostPerKwh()));
                setElementText("note1.2", labelPrintFrame.
                        getSystemOptions().getProperty("Note1_2"));
                // Validity
                setElementText("validity", labelPrintFrame.getLabelDataPanel().getEnergyLabel().getValidity());
                // Standard note
                setElementText("note2.1", labelPrintFrame
                        .getSystemOptions().getProperty("Note2_1"));
                setElementText("note2.2", labelPrintFrame
                        .getSystemOptions().getProperty("Note2_2")
                        .replace("[Standard]",
                                labelPrintFrame.getLabelDataPanel().getEnergyLabel().getStandard()));
                // Violation note
                setElementText("note3.1", labelPrintFrame
                        .getSystemOptions().getProperty("Note3_1"));
                setElementText("note3.2", labelPrintFrame
                        .getSystemOptions().getProperty("Note3_2"));
                setElementText("note3.3", labelPrintFrame
                        .getSystemOptions().getProperty("Note3_3"));
            });
        }

    }

    private void setElementText(String elementId, String content) {
        if (svgDocument != null) {
            Element element = svgDocument.getElementById(elementId);
            element.setTextContent(content);
        }
    }

    public void setElementFill(String elementId, String fill) {
        if (svgCanvas != null && svgDocument != null) {
            svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {
                Element element = svgDocument.getElementById(elementId);
                element.setAttribute("style", "fill:" + fill);
            });
        }
    }

    public boolean exportLabelToRasterGraphic(String fileName, String formatName) {

        try {

            TranscoderInput input = new TranscoderInput(svgDocument);
            OutputStream ostream;
            TranscoderOutput output;

            switch (formatName) {
                case "jpg":
                    ostream = new FileOutputStream(fileName + ".jpg");
                    output = new TranscoderOutput(ostream);
                    JPEGTranscoder t = new JPEGTranscoder();

                    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                            new Float(.8));
                    t.transcode(input, output);

                    ostream.flush();
                    ostream.close();

                    break;
                case "png":
                    ostream = new FileOutputStream(fileName + ".png");
                    output = new TranscoderOutput(ostream);
                    PNGTranscoder t2 = new PNGTranscoder();

                    t2.transcode(input, output);

                    ostream.flush();
                    ostream.close();
                    break;
                default:
                    return false;
            }

            loadSVGLabel();

            return true;
        } catch (IOException | TranscoderException e) {
            System.out.println(e);
        } finally {
            
        }
        
        return false;

    }

    public void printLabel() {
        PrintTranscoder pt = new PrintTranscoder();
        pt.addTranscodingHint(PrintTranscoder.KEY_MARGIN_TOP, new Float(18.0));
        pt.addTranscodingHint(PrintTranscoder.KEY_MARGIN_LEFT, new Float(18.0));
        pt.addTranscodingHint(PrintTranscoder.KEY_MARGIN_BOTTOM, new Float(18.0));
        pt.addTranscodingHint(PrintTranscoder.KEY_MARGIN_RIGHT, new Float(18.0));
        pt.addTranscodingHint(PrintTranscoder.KEY_SHOW_PRINTER_DIALOG, Boolean.TRUE);

        try {
            pt.transcode(new TranscoderInput(svgDocument), null);
            pt.print();

            loadSVGLabel();
        } catch (PrinterException e) {
            System.out.println(e);
        }
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

        jButtonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 10, 1));

        jEditLabelData.setText("Edit");
        jEditLabelData.setToolTipText("Edit the label data");
        jEditLabelData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditLabelDataActionPerformed(evt);
            }
        });

        jSaveLabelData.setText("Save");
        jSaveLabelData.setToolTipText("Save the label data");
        jSaveLabelData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveLabelDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addComponent(jEditLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSaveLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jEditLabelData)
            .addComponent(jSaveLabelData)
        );

        add(jButtonPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jEditLabelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditLabelDataActionPerformed
        labelPrintFrame.getTabbedPane().setSelectedIndex(0);
    }//GEN-LAST:event_jEditLabelDataActionPerformed

    private void jSaveLabelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveLabelDataActionPerformed
        labelPrintFrame.saveLabel();
    }//GEN-LAST:event_jSaveLabelDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jEditLabelData;
    private javax.swing.JButton jSaveLabelData;
    // End of variables declaration//GEN-END:variables

}
