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
public class EnergyLabelPanelJM extends javax.swing.JPanel {

    private boolean showGreenBackground;
    private boolean showYellowBackground;
    private boolean showContents;
    private Application app;
    private JSVGCanvas svgCanvas;
    private Document svgDocument;

    /**
     * The possible states for all energy stars.
     */
    public enum STARSTATE {
        NONE, HALF, FULL
    }

    /**
     * The default style for all energy stars.
     */
    public static final String DEFAULTSTARSTYLE
            = "opacity:1;fill-opacity:1;stroke:#000000;stroke-width:0;"
            + "stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:0;";
    public static final String MORESTARSTEXTSTYLE
            = "font-style:normal;font-weight:normal;font-size:9.8777771px;"
            + "line-height:125%;font-family:sans-serif;letter-spacing:0px;"
            + "word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;"
            + "stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
    public static final String HEATINGORCOOLINGTEXTSTYLE
            = "font-style:normal;font-variant:normal;font-weight:bold;"
            + "font-stretch:normal;font-size:9.87777805px;line-height:125%;"
            + "font-family:sans-serif;inkscape-font-specification:'sans-serif Bold';"
            + "text-align:start;letter-spacing:0px;word-spacing:0px;"
            + "writing-mode:lr-tb;text-anchor:start;opacity:0.98000004;fill:#000000;"
            + "fill-opacity:1;stroke:none;stroke-width:1px;stroke-linecap:butt;"
            + "stroke-linejoin:miter;stroke-opacity:1";
    public static final String DEFAULTINNERCIRCTEXTSTYLE
            = "font-style:normal;font-weight:normal;font-size:9.8777771px;"
            + "line-height:125%;font-family:sans-serif;letter-spacing:0px;"
            + "word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;"
            + "stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
    public static final String INNERCIRCTEXTLINE
            = "fill:none;fill-opacity:1;fill-rule:nonzero;" // stroke:#000000;
            + "stroke-width:0.56444442;stroke-linecap:butt;"
            + "stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1;";

    /**
     * Creates new SVGLabelPanel
     */
    public EnergyLabelPanelJM() {
        initComponents();
        initLabel();
    }

    /**
     * Creates new SVGLabelPanel
     *
     * @param app
     */
    public EnergyLabelPanelJM(jm.com.dpbennett.labelprint.ui.Application app) {
        this.app = app;
        initComponents();
        initLabel();

    }

    public void updateStarState(String starId, STARSTATE state, String fill) {

        try {
            if (svgCanvas != null && svgDocument != null) {
                svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {

                    Element starFirstHalf = svgDocument.getElementById(starId + ".1");
                    Element starSecondHalf = svgDocument.getElementById(starId + ".2");

                    switch (state) {
                        case NONE:
                            starFirstHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:none");
                            starSecondHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:none");
                            break;
                        case HALF:
                            starFirstHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:#" + fill);
                            starSecondHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:none");
                            break;
                        case FULL:
                            starFirstHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:#" + fill);
                            starSecondHalf.setAttribute("style", DEFAULTSTARSTYLE + "fill:#" + fill);
                            break;
                    }

                });
            }
        } catch (Exception e) {
        }

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

        app.getjEnergyLabelPane().repaint();
    }

    private EnergyLabel getEnergyLabel() {
        return app.getLabelFormPanel().getEnergyLabel();
    }

    public void updateLabel() {

        if (app != null && svgCanvas != null && svgDocument != null) {
            svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(() -> {
                // Sample watermark
                if (getEnergyLabel().getShowSampleWatermark()) {
                   setElementText("sample", "SAMPLE"); 
                }
                else {
                    setElementText("sample", "");
                }
                // Star area inner circle text
                if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
                    setElementStyle("innerCircText_1", HEATINGORCOOLINGTEXTSTYLE);
                    if (getEnergyLabel().getShowCoolingCapacity()) {
                        setElementText("innerCircText_1", "COOLING");
                    } else {
                        setElementText("innerCircText_1", "HEATING");
                    }
                    setElementText("innerCircText_2", "The more stars the");
                    setElementText("innerCircText_3", "more efficient");
                    setElementStyle("innerCircText_line", INNERCIRCTEXTLINE + "stroke:#000000;");
                } else {
                    setElementStyle("innerCircText_1", DEFAULTINNERCIRCTEXTSTYLE);
                    setElementText("innerCircText_1", "The more");
                    setElementText("innerCircText_2", "        stars the");
                    setElementText("innerCircText_3", "more efficient");
                    setElementStyle("innerCircText_line", INNERCIRCTEXTLINE + "stroke:none;");
                }
                // Type
                setElementText("type", getEnergyLabel().getType());
                // Capacity
                if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
                    if (getEnergyLabel().getShowCoolingCapacity()) {
                        setElementText("capacityLabel", "Cooling Capacity:");
                        setElementText("capacity", getEnergyLabel().getCoolingCapacity() + " kW");
                    } else {
                        setElementText("capacityLabel", "Heating Capacity:");
                        setElementText("capacity", getEnergyLabel().getHeatingCapacity() + " kW");
                    }
                    setElementText("capacityUnitPowerTextSpan", " ");
                } else {
                    setElementText("capacity", getEnergyLabel().getCapacity() + " m");
                    setElementText("capacityUnitPowerTextSpan", "3");
                }
                // Set location of the capacity unit power based on width of capacity
                Element capacity = svgDocument.getElementById("capacity");
                SVGLocatable locatable = (SVGLocatable) capacity;
                SVGRect rect = locatable.getBBox();
                Element capacityUnitPower = svgDocument.getElementById("capacityUnitPowerTextSpan");
                capacityUnitPower.setAttribute("x", "" + (rect.getX() + rect.getWidth()));
                // Defrost/Distributor
                if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
                    setElementText("distributorOrDefrostLabel", "Distributor:");
                    setElementText("distributorOrDefrost", getEnergyLabel().getDistributor());
                    setElementText("distributorLabel", " ");
                    setElementText("distributor", " ");
                } else {
                    setElementText("distributorOrDefrostLabel", "Defrost:");
                    setElementText("distributorOrDefrost", getEnergyLabel().getDefrost());
                    setElementText("distributorLabel", "Distributor:");
                    setElementText("distributor", getEnergyLabel().getDistributor());
                }
                // Manufacturer
                setElementText("manufacturer", getEnergyLabel().getManufacturer());
                // Model
                setElementText("model", getEnergyLabel().getModel());
                // Country
                setElementText("country", getEnergyLabel().getCountry());
                // Operating cost
                setElementText("operatingCost", "$" + getEnergyLabel().getOperatingCost());
                // Energy note
                // Line 1 note
                if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
                    if (getEnergyLabel().getShowCoolingCapacity()) {
                        setElementText("note1.1", app
                                .getSystemOptions().getProperty("Note1_1_AEER")
                                .replace("[AEER]",
                                        getEnergyLabel().getAEER()));
                    } else {
                        setElementText("note1.1", app
                                .getSystemOptions().getProperty("Note1_1_ACOP")
                                .replace("[ACOP]",
                                        getEnergyLabel().getACOP()));
                    }
                } else {
                    setElementText("note1.1", app
                            .getSystemOptions().getProperty("Note1_1")
                            .replace("[AnnualConsumption]",
                                    getEnergyLabel().getAnnualConsumption()));
                }
                // Line 2 note
                setElementText("note1.2", app
                        .getSystemOptions().getProperty("Note1_2")
                        .replace("[CostPerKWh1]",
                                getEnergyLabel().getCostPerKwh())
                        .replace("[CostPerKWh2]",
                                getEnergyLabel().getCostPerKwh2()));
                // Disclaimer line 1
                setElementText("note1.3", app.
                        getSystemOptions().getProperty("Note1_3"));
                // Disclaimer line 2
                setElementText("note1.4", app.
                        getSystemOptions().getProperty("Note1_4"));
                // Disclaimer line 3
                setElementText("note1.5", app.
                        getSystemOptions().getProperty("Note1_5"));
                // Validity
                setElementText("validity", getEnergyLabel().getValidity());
                // Standard note
                setElementText("note2.1", app
                        .getSystemOptions().getProperty("Note2_1"));
                setElementText("note2.2", app
                        .getSystemOptions().getProperty("Note2_2")
                        .replace("[Standard]",
                                getEnergyLabel().getStandard()));
                // Violation note
                setElementText("note3.1", app
                        .getSystemOptions().getProperty("Note3_1"));
                setElementText("note3.2", app
                        .getSystemOptions().getProperty("Note3_2"));
                setElementText("note3.3", app
                        .getSystemOptions().getProperty("Note3_3"));
                // Energy stars
                updateEnergyStars();
            });
        }

    }

    private void eraseEnergyStars() {
        for (int i = 1; i < 9; i++) {
            updateStarState("outer.star." + i, STARSTATE.NONE, "008000");
        }
        for (int i = 1; i < 7; i++) {
            updateStarState("inner.star." + i, STARSTATE.NONE, "ffdf00");
        }
    }

    private void addEnergyStars() {

        try {
            String starRating = getEnergyLabel().getStarRating();
            String stars[] = starRating.split("\\.");

            int numFullOuterStars
                    = ((Integer.parseInt(stars[0]) - 6) > 0 ? (Integer.parseInt(stars[0]) - 6) : 0);
            int numFullInnerStars = Integer.parseInt(stars[0]) - numFullOuterStars;

            // Add inner full stars
            for (int i = 1; i < (numFullInnerStars + 1); i++) {
                updateStarState("inner.star." + i, STARSTATE.FULL, "ffdf00");
            }
            // Add inner half star if any
            if (stars[1].equals("5")) {
                updateStarState("inner.star." + (numFullInnerStars + 1), STARSTATE.HALF, "ffdf00");
            }

            // Add outer full stars if any
            if (numFullOuterStars > 0) {
                for (int i = 1; i < (numFullOuterStars + 1); i++) {
                    updateStarState("outer.star." + i, STARSTATE.FULL, "008000");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid star rating!");
        }

    }

    /**
     * Updates the energy stars based on energy efficiency rating.
     */
    private void updateEnergyStars() {
        eraseEnergyStars();
        addEnergyStars();
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
