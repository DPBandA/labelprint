/*
LabelPrint - A general purpose energy label printing application 
Copyright (C) 2020  D P Bennett & Associates Limited

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
import jm.com.dpbennett.business.entity.mt.EnergyLabel;
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
 * Displays and manage SVG type of labels.
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class EnergyLabelPanel extends javax.swing.JPanel {

    private Application app;
    private JSVGCanvas svgCanvas;
    private Document svgDocument;
    private String labelFile;

    /**
     * The default SVG styles.
     */
    public static final String HEATINGORCOOLINGTEXTSTYLE
            = "font-style:normal;font-variant:normal;font-weight:bold;"
            + "font-stretch:normal;font-size:9.87777805px;line-height:125%;"
            + "font-family:sans-serif;inkscape-font-specification:'sans-serif Bold';"
            + "text-align:start;letter-spacing:0px;word-spacing:0px;"
            + "writing-mode:lr-tb;text-anchor:start;opacity:0.98000004;fill:#000000;"
            + "fill-opacity:1;stroke:none;stroke-width:1px;stroke-linecap:butt;"
            + "stroke-linejoin:miter;stroke-opacity:1";

    /**
     * Creates new SVGLabelPanel
     */
    public EnergyLabelPanel() {
        initComponents();
        initLabel();
    }

    /**
     * Creates new SVGLabelPanel
     *
     * @param app
     */
    public EnergyLabelPanel(jm.com.dpbennett.labelprint.ui.Application app) {
        this.app = app;
        initComponents();
        initLabel();

    }

    public String getLabelFile() {
        return labelFile;
    }

    public void setLabelFile(String labelFile) {
        this.labelFile = labelFile;
    }

    public void renderRating(String ratingLetter, Boolean render) {

        try {
            if (svgCanvas != null && svgDocument != null) {
                svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {

                    Element rating = svgDocument.getElementById("rating" + ratingLetter);

                    if (render) {
                        rating.setAttribute("visibility", "visible");
                    } else {
                        rating.setAttribute("visibility", "hidden");
                    }

                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void initLabel() {
        loadSVGLabel();
    }

    public void loadSVGLabel() {
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            loadSVGLabel("images/CROSQACEnergyLabel.svg");
        } else {
            loadSVGLabel("images/CROSQFridgeEnergyLabel.svg");
        }
    }

    private void loadSVGLabel(String labelFile) {
        if (svgCanvas != null) {
            remove(svgCanvas);
        }

        svgCanvas = new JSVGCanvas();
        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        URL url = getClass().getClassLoader().getResource(labelFile);
        svgCanvas.setURI(url.toString());
        svgCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                svgDocument = svgCanvas.getSVGDocument();
                updateLabel();
            }
        });

        add(svgCanvas, java.awt.BorderLayout.CENTER);

        app.getjEnergyLabelPane().repaint();
    }

    private EnergyLabel getEnergyLabel() {
        return app.getLabelFormPanel().getEnergyLabel();
    }

    public void updateLabel() {

        if (app != null && svgCanvas != null && svgDocument != null) {
            try {
                svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {

                    if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
                        // Year of evaluation
                        setElementText("yearOfEvaluation", getEnergyLabel().getYearOfEvaluation(), "start");
                        // Letter rating                
                        eraseAllRatingLetters();
                        renderRating(getEnergyLabel().getLetterRating(), true);
                        // Annual consumption
                        setElementText("annualConsumption", getEnergyLabel().getAnnualConsumption(), "middle");
                        // Batch code
                        setElementText("batchCode", getEnergyLabel().getBatchCode(), "middle");
                        // Efficiency ratio
                        setElementText("efficiencyRatio", getEnergyLabel().getEfficiencyRatio(), "middle");
                        // Carrier //tk
                        setElementText("carrier", getEnergyLabel().getManufacturer(), "end");
                        // Code //tk
                        setElementText("code", getEnergyLabel().getModel(), "end");

                    } else {
                        // Year of evaluation
                        setElementText("yearOfEvaluation", getEnergyLabel().getYearOfEvaluation(), "start");
                        // Manufacturer
                        setElementText("manufacturer", getEnergyLabel().getManufacturer(), "end");
                        // Model(s)
                        setElementText("models", "Model(s) " + getEnergyLabel().getModel(), "end");
                        // Capacity
                        setElementText("capacity",
                                "Capacity "
                                + getEnergyLabel().getCapacity()
                                + " Cubic Litres", "end");
                        // Electrical ratings
                        setElementText("electricalRatings",
                                getEnergyLabel().getRatedVoltage() + "V, "
                                + getEnergyLabel().getRatedFrequency() + "Hz, "
                                + getEnergyLabel().getRatedCurrent() + "A", "end");
                        // Type
                        setElementText("type", getEnergyLabel().getType(), "start");
                        // Defrost
                        setElementText("defrost", "- " + getEnergyLabel().getDefrost(), "start");
                        // Feature 1
                        setElementText("feature1", "- " + getEnergyLabel().getFeature1(), "start");
                        // Feature 2
                        setElementText("feature2", "- " + getEnergyLabel().getFeature2(), "start");
                        // Letter rating                
                        eraseAllRatingLetters();
                        renderRating(getEnergyLabel().getLetterRating(), true);
                        // Operating cost
                        setElementText("operatingCost", getEnergyLabel().getOperatingCost(), "start");
                        // Annual consumption
                        setElementText("annualConsumption", getEnergyLabel().getAnnualConsumption(), "start");
                        // Annual consumption unit
                        Element annualConsumption = svgDocument.getElementById("annualConsumption");
                        SVGLocatable locatable = (SVGLocatable) annualConsumption;
                        SVGRect rect = locatable.getBBox();
                        Element annualConsumptionUnit = svgDocument.getElementById("annualConsumptionUnitSpan");
                        annualConsumptionUnit.setAttribute("x", "" + (rect.getX() + rect.getWidth()));
                        // Batch code
                        setElementText("batchCode", getEnergyLabel().getBatchCode(), "middle");
                        
                    }

                });
            } catch (Exception e) {
                System.out.println("Error updating label..." + e);
            }

        }

    }

    private void eraseAllRatingLetters() {
        renderRating("A", false);
        renderRating("B", false);
        renderRating("C", false);
        renderRating("D", false);
        renderRating("E", false);
        renderRating("F", false);
    }

    private void setElementText(String elementId, String content, String anchor) {
        if (svgDocument != null) {
            Element element = svgDocument.getElementById(elementId);
            element.setAttribute("text-anchor", anchor);
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

    public void setElementStyle(String elementId, String style) {
        if (svgCanvas != null && svgDocument != null) {
            svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {
                Element element = svgDocument.getElementById(elementId);
                element.setAttribute("style", style);
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
                    
                    //t2.addTranscodingHint(PNGTranscoder.KEY_QUALITY,
                    //        new Float(.8));

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
        app.getTabbedPane().setSelectedIndex(0);
    }//GEN-LAST:event_jEditLabelDataActionPerformed

    private void jSaveLabelDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveLabelDataActionPerformed
        app.saveLabel();
    }//GEN-LAST:event_jSaveLabelDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jEditLabelData;
    private javax.swing.JButton jSaveLabelData;
    // End of variables declaration//GEN-END:variables

}
