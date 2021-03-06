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

/**
 * This dialog shows the progress when exporting labels to various file formats.
 * @author  Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class ExportDialog extends javax.swing.JDialog implements Runnable {
    private Application app;
    private String fileName;
    
    /** Creates new form ExportJDialog
     * @param parent
     * @param modal */
    public ExportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init(parent);
        
    }
    
    private void init(java.awt.Frame parent) {
        app = (Application)parent;
        
        // Centre
        setLocationRelativeTo(null);
        
        // Do export
        fileName = app.getFileAbsolutePath("Export");
        new Thread(this).start();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(331, 64));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Exporting Label. Please wait...");
        jPanel1.add(jLabel1);

        jProgressBar1.setValue(10);
        jPanel1.add(jProgressBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ExportDialog(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    
    @Override
    public void run() {
        
        if (fileName != null) {

            if (app.getSystemOptions().isExportJPEG()) {
                app.getEnergyLabelPanel().exportLabelToRasterGraphic(fileName, "jpg");
                jProgressBar1.setValue(50);
            }
            if (app.getSystemOptions().isExportPNG()) {
                app.getEnergyLabelPanel().exportLabelToRasterGraphic(fileName, "png");
                jProgressBar1.setValue(70);
            }

            // Finish up
            jProgressBar1.setValue(100);
            try {
                Thread.sleep(2000);
                dispose();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        else
            dispose();
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    
}
