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

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import jm.com.dpbennett.business.entity.mt.EnergyLabel;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class EnergyLabelFormPanel extends javax.swing.JPanel {

    private Application app;
    private EnergyLabelDataPanel labelDataPanel;

    /**
     * Creates new form LabelFormPanel
     *
     * @param app
     */
    public EnergyLabelFormPanel(Application app) {
        this.app = app;
        initComponents();
        init();
    }

    private void init() {
        labelDataPanel = new EnergyLabelDataPanel(app);
        labelDataPanel.createLabel();
        
        JScrollPane scrollPane = new JScrollPane(labelDataPanel);
        add(scrollPane, java.awt.BorderLayout.CENTER);
    }
    
    
    
    public void createLabel() {
        labelDataPanel.createLabel();
    }
    
    public EnergyLabel getEnergyLabel() {
        return labelDataPanel.getEnergyLabel();
    }
    
    /**
     * Sets the current energy label.
     *
     * @param energyLabel
     */
    public void setEnergyLabel(EnergyLabel energyLabel) {
        labelDataPanel.setEnergyLabel(energyLabel);
    }
    
    public void updateLabelData() {
        labelDataPanel.updateLabelData();
    }

    /**
     * Get the JPanel that displays label data for editing.
     * 
     * @return 
     */
    public EnergyLabelDataPanel getLabelDataPanel() {
        return labelDataPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPanel = new javax.swing.JPanel();
        jViewLabel = new javax.swing.JButton();
        jSaveLabel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(577, 733));
        setLayout(new java.awt.BorderLayout());

        jButtonPanel.setPreferredSize(new java.awt.Dimension(586, 40));

        jViewLabel.setText("View");
        jViewLabel.setToolTipText("View label with validated data");
        jViewLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jViewLabelActionPerformed(evt);
            }
        });

        jSaveLabel.setText("Save");
        jSaveLabel.setToolTipText("Save the label data");
        jSaveLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jViewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSaveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jViewLabel)
                    .addComponent(jSaveLabel))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        add(jButtonPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jViewLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewLabelActionPerformed
        ReturnMessage returnMessage = getEnergyLabel().validate(app.getEntityManager());
        if (returnMessage.isSuccess()) {
            app.getEnergyLabelPanel().updateLabel();
            app.getTabbedPane().setSelectedIndex(1);
        } else {

            JOptionPane.showMessageDialog(this,
                returnMessage.getMessage(),
                returnMessage.getHeader(),
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jViewLabelActionPerformed

    private void jSaveLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveLabelActionPerformed
        app.saveLabel();
    }//GEN-LAST:event_jSaveLabelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jSaveLabel;
    private javax.swing.JButton jViewLabel;
    // End of variables declaration//GEN-END:variables
}
