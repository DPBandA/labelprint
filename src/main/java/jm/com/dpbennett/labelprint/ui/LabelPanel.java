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

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import jm.com.dpbennett.labelprint.SystemOptions;

/**
 *
 * @author dbennett
 */
public class LabelPanel extends javax.swing.JPanel implements Printable {

    //private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("LabelPrintPU");
    private boolean chkGreenBackground;
    private boolean chkYellowBackground;
    private boolean chkContents = true;
    private LabelPrintFrame labelPrintFrame;
    private Image img;
    private Image labelHeadingImg;
    private int defImageWidth = 409;
    private int defImageHeight = 320;
    private int labelOrgX = 1;
    private int labelOrgY = 10;
    private int m_maxNumPage = 1;
    private double imageScaleX = 1.389655;
    private double imageScaleY = 1.389655;

    /**
     * Creates new form LabelPanel
     */
    public LabelPanel() {
        initComponents();
    }

    /**
     * Creates new form LabelPanel
     * @param labelPrintFrame
     */
    public LabelPanel(jm.com.dpbennett.labelprint.ui.LabelPrintFrame labelPrintFrame) {
        this.labelPrintFrame = labelPrintFrame;
        initComponents();

        Toolkit toolKit = Toolkit.getDefaultToolkit();

        SystemOptions sysOptions = new SystemOptions("LabelPrint.properties");

        //img = toolKit.createImage(sysOptions.getLabelLogoFile());
        // getClass().getResource("/system/" + systemFile)
        // tk
        img = toolKit.createImage(getClass().getResource(sysOptions.getLabelLogoFile()));
        labelHeadingImg = toolKit.createImage(getClass().getResource(sysOptions.getHeadingImage()));
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

    public boolean exportLabelToPDF(String fileName) {
        Document document = new Document();
        PdfWriter writer;
        Graphics2D g2;
        PdfContentByte cb;
        PdfTemplate tp;
        int pdfTemplateWidth = 631;
        int pdfTemplateHeight = 494;
//        int pdfTemplateWidth = defImageWidth;
//        int pdfTemplateHeight = defImageHeight;

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));

            document.open();
            document.setPageSize(PageSize.LETTER);

            cb = writer.getDirectContent();
            tp = cb.createTemplate(pdfTemplateWidth, pdfTemplateHeight);

            g2 = tp.createGraphics(pdfTemplateWidth, pdfTemplateHeight, labelPrintFrame.getDefaultFontMapper());
            printContentOfLabel(g2, imageScaleX, imageScaleY, 0, 0);
            g2.dispose();
            cb.addTemplate(tp, 20, 340);
//            cb.addTemplate(tp, 10, 170);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        document.close();

        return true;
    }

    public boolean exportLabelToRasterGraphic(String fileName, String formatName) {
        int scale = 4;
        int width = defImageWidth * scale;
        int height = defImageHeight * scale;
        BufferedImage bufferedImage = (BufferedImage) createImage(width, height);
        Graphics2D jpeg_g2d = bufferedImage.createGraphics();

        jpeg_g2d.setBackground(Color.WHITE);
        jpeg_g2d.clearRect(0, 0, width, height);
        printContentOfLabel(jpeg_g2d, scale * 0.98, scale * 0.98, 3, -7);

        try {
            // Save image in the given format, jpeg,gif,png
            File file = new File(fileName + "." + formatName);
            ImageIO.write(bufferedImage, formatName, file);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    private void printContentOfLabel(Graphics2D g, double xscale, double yscale, int xtrans, int ytrans) {

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // Scale to desired size
        g.scale(xscale, yscale);
        g.translate(xtrans, ytrans);
        g.setBackground(Color.WHITE);

        // Generate and print contents of label - Crude but it works!
        // Label background       
        if (chkYellowBackground) {
            g.setColor(Color.YELLOW); 
            g.fillRoundRect(labelOrgX, labelOrgY, defImageWidth, defImageHeight, 20, 20); 
        } else {
            g.setColor(Color.WHITE); 
            g.fillRoundRect(labelOrgX, labelOrgY, defImageWidth, defImageHeight, 20, 20);
        }
        g.setColor(Color.BLACK);

        //Heading and warning background
        if (chkGreenBackground == true) {
            g.setColor(new Color(0, 128, 0));
//            g.fillRoundRect(2, 11, 407, 88, 19, 19);
            g.fillRoundRect(labelOrgX, labelOrgY, defImageWidth, 88, 20, 20);
            g.fillRoundRect(255, 282, 137, 35, 10, 10);
        }

        if (chkContents == true) {
            g.setColor(Color.black);
            // first line
            g.drawLine(20, 163, 390, 163);
            // second line
            g.drawLine(20, 270, 390, 270);

            // Display label heading
            g.drawImage(labelHeadingImg, 26, 5, 350, 100, this);

            // draw refrigerator general info
            Font f = new Font("Arial", Font.PLAIN, 10);
            g.setFont(f);
//            int RequiredSpace = g.getFontMetrics().stringWidth("aaaaaaaa");  
            int RequiredSpace = g.getFontMetrics().stringWidth("aaaaaaaaaaaaaa"); //tk
            g.drawString(labelPrintFrame.getEnergyLabelData().getType(), 20, 110);
            g.drawString("Heating Capacity", 20, 125); // tk
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getCapacity() + "m",
                    25 + g.getFontMetrics().stringWidth("Heating Capacity")
                    + (RequiredSpace - g.getFontMetrics().stringWidth("Heating Capacity")), 125);
            g.drawString(" 3", 24 + g.getFontMetrics().stringWidth("Heating Capacity")
                    + (RequiredSpace - g.getFontMetrics().stringWidth("Heating Capacity"))
                    + g.getFontMetrics().stringWidth(":"
                            + labelPrintFrame.getEnergyLabelData().getCapacity() + "m"), 122);
            RequiredSpace = g.getFontMetrics().stringWidth("aaaaaaaaaaaaaa"); //tk
            g.drawString("Defrost", 20, 140);
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getDefrost(),
                    25 + g.getFontMetrics().stringWidth("Defrost")
                    + (RequiredSpace - g.getFontMetrics().stringWidth("Defrost")), 140);
            g.drawString("Distributor", 20, 155);
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getDistributor(),
                    25 + g.getFontMetrics().stringWidth("Distributor")
                    + (RequiredSpace
                    - g.getFontMetrics().stringWidth("Distributor")), 155);

            // general info??
            RequiredSpace = g.getFontMetrics().stringWidth("aaaaaaaaaaaaaa");
            g.drawString("Manufacturer", 190, 125);
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getManufacturer(),
                    190 + g.getFontMetrics().stringWidth("Manufacturer")
                    + (RequiredSpace
                    - g.getFontMetrics().stringWidth("Manufacturer")), 125);
            g.drawString("Model No.", 190, 140);
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getModel(),
                    190 + g.getFontMetrics().stringWidth("Model No.")
                    + (RequiredSpace - g.getFontMetrics().stringWidth("Model No.")), 140);
            g.drawString("Country of Origin", 190, 155);
            g.drawString(": " + labelPrintFrame.getEnergyLabelData().getCountry(),
                    190 + g.getFontMetrics().stringWidth("Country of Origin")
                    + (RequiredSpace
                    - g.getFontMetrics().stringWidth("Country of Origin")), 155);

            // operating cost
            f = new Font("Arial", Font.PLAIN, 10);
            g.setFont(f);
            g.drawString("ENERGY OPERATING COST:", 20, 177);
            g.drawString("PER YEAR", 280, 233);

            // cost
            f = new Font("Impact", Font.BOLD, 44);
            g.setFont(f);
            g.drawString("$" + labelPrintFrame.getEnergyLabelData().getOperatingCost(),
                    195
                    - g.getFontMetrics().stringWidth(labelPrintFrame.getEnergyLabelData().getOperatingCost()) / 2,
                    220);

            // costing display
            f = new Font("Arial", Font.PLAIN, 8); // org font size 10
            g.setFont(f);
            String CostingDisplayLine1 = "Based on a total consumption of "
                    + labelPrintFrame.getEnergyLabelData().getAnnualConsumption()
                    + " kWh per year at an *estimated average rate of $" + labelPrintFrame.getEnergyLabelData().getCostPerKwh()
                    + "/kWh.";
            g.drawString(CostingDisplayLine1, 20, 250);

            f = new Font("Arial", Font.PLAIN, 6); // org font size 10
            g.setFont(f);
            String CostingDisplayLine2 = "* Your cost will vary with electricity "
                    + "rates and how you use your product.";
            g.drawString(CostingDisplayLine2, 20, 260);

            // display validity
            f = new Font("Arial", Font.PLAIN, 10);
            g.setFont(f);
            g.drawString("Valid", 20, 290);
            g.drawString("for:", 20, 300);
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.drawString(labelPrintFrame.getEnergyLabelData().getValidity(), 20, 310);

            // display standard info
            f = new Font("Arial", Font.PLAIN, 10);
            g.setFont(f);
            g.drawString("This model has been tested", 115, 290);
            g.drawString("in accordance with JS " + labelPrintFrame.getEnergyLabelData().getStandard(), 115, 300);

            // display logo
            g.drawImage(img, 65, 275, 45, 45, this);

            // warning info
            f = new Font("Arial", Font.PLAIN, 8);
            g.setFont(f);
            g.drawString("Removal of this label before", 260, 292);
            g.drawString("consumer purchase is a violation", 260, 302);
            g.drawString("under the Standards Act.", 260, 312);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(550, 500));
        setPreferredSize(new java.awt.Dimension(550, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        printContentOfLabel((Graphics2D) g, (double) getWidth() / (double) (defImageWidth + 10.0),
                (double) getHeight() / (double) (defImageHeight + 10.0), 4, -5);
    }

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

        printContentOfLabel((Graphics2D) pg, imageScaleX, imageScaleY, 0, 0);

        System.gc();

        return PAGE_EXISTS;
    }
}
