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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import jm.com.dpbennett.labelprint.Options;
import jm.com.dpbennett.business.entity.mt.EnergyLabel;

/**
 *
 * @author Desmond Bennett
 */
public class OpenLabelDialog extends java.awt.Dialog {

    private Application app;
    private int numberOfLabelsFound;
    private boolean bLoadLabel;
    private ArrayList labels, labelIDs;
    private Long labelId;
    private String searchString;
    private String defaultFieldToSearch;
    private String defaultFieldToDisplay;
    private String[][] fieldsToSearch;

    /**
     * Creates new form OpenLabelDialog
     *
     * @param parent
     * @param modal
     */
    public OpenLabelDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init(parent);

    }

    private void init(java.awt.Frame parent) {
        app = (Application) parent;

        // Centre
        setLocationRelativeTo(null);

        // Get system options
        Options sysOptions = app.getSystemOptions();
        fieldsToSearch = sysOptions.getFieldsToSearch();
        defaultFieldToSearch = sysOptions.getProperty("DefaultFieldToSearch");
        defaultFieldToDisplay = sysOptions.getProperty("DefaultFieldToDisplay");

        // Init field to search combo box
        for (int i = 0; i < fieldsToSearch.length; ++i) {
            jFieldToSearchComboBox.addItem(fieldsToSearch[i][0]);
        }
        jFieldToSearchComboBox.setSelectedItem(defaultFieldToSearch);

        // Init field to display combo box
        for (int i = 0; i < fieldsToSearch.length; ++i) {
            jFieldToDisplayComboBox.addItem(fieldsToSearch[i][0]);
        }
        jFieldToDisplayComboBox.setSelectedItem(defaultFieldToDisplay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @return 
     */
    public Long getLabelId() {
        return labelId;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSearchButton = new javax.swing.JButton();
        jHelpButton = new javax.swing.JButton();
        jFieldToSearchComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFieldToDisplayComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextToFindTextField = new javax.swing.JTextField();
        FoundRecordsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LabelsFoundList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jManufacturerTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jDistributorTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCountryOfOriginTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jBrandTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jModelTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jDefrostTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jCapacityTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jOperatingCostTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jOpenLabelButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        setTitle("Open Label");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 51), new java.awt.Color(153, 153, 153)), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jSearchButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jHelpButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jHelpButton.setText("Help");
        jHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHelpButtonActionPerformed(evt);
            }
        });

        jFieldToSearchComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Text to find:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Field to search:");

        jFieldToDisplayComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Field to display:");

        jTextToFindTextField.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFieldToDisplayComboBox, 0, 253, Short.MAX_VALUE)
                    .addComponent(jFieldToSearchComboBox, 0, 253, Short.MAX_VALUE)
                    .addComponent(jTextToFindTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jHelpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSearchButton)
                    .addComponent(jTextToFindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFieldToSearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHelpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldToDisplayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FoundRecordsPanel.setBackground(new java.awt.Color(204, 204, 204));
        FoundRecordsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 0), new java.awt.Color(153, 153, 153)), "Label(s) Found", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        LabelsFoundList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelsFoundListMouseClicked(evt);
            }
        });
        LabelsFoundList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LabelsFoundListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(LabelsFoundList);

        javax.swing.GroupLayout FoundRecordsPanelLayout = new javax.swing.GroupLayout(FoundRecordsPanel);
        FoundRecordsPanel.setLayout(FoundRecordsPanelLayout);
        FoundRecordsPanelLayout.setHorizontalGroup(
            FoundRecordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FoundRecordsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        FoundRecordsPanelLayout.setVerticalGroup(
            FoundRecordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FoundRecordsPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 0), new java.awt.Color(153, 153, 153)), "Label Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(8, 2, 2, 5));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Manufacturer:");
        jPanel3.add(jLabel4);

        jManufacturerTextField.setEditable(false);
        jManufacturerTextField.setBackground(java.awt.SystemColor.control);
        jManufacturerTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jManufacturerTextField);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Distributor:");
        jPanel3.add(jLabel5);

        jDistributorTextField.setEditable(false);
        jDistributorTextField.setBackground(java.awt.SystemColor.control);
        jDistributorTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jDistributorTextField);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Country:");
        jPanel3.add(jLabel6);

        jCountryOfOriginTextField.setEditable(false);
        jCountryOfOriginTextField.setBackground(java.awt.SystemColor.control);
        jCountryOfOriginTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jCountryOfOriginTextField);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Brand:");
        jPanel3.add(jLabel7);

        jBrandTextField.setEditable(false);
        jBrandTextField.setBackground(java.awt.SystemColor.control);
        jBrandTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jBrandTextField);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Model:");
        jPanel3.add(jLabel8);

        jModelTextField.setEditable(false);
        jModelTextField.setBackground(java.awt.SystemColor.control);
        jModelTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jModelTextField);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Defrost:");
        jPanel3.add(jLabel9);

        jDefrostTextField.setEditable(false);
        jDefrostTextField.setBackground(java.awt.SystemColor.control);
        jDefrostTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jDefrostTextField);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Cap. (cu. m):");
        jPanel3.add(jLabel10);

        jCapacityTextField.setEditable(false);
        jCapacityTextField.setBackground(java.awt.SystemColor.control);
        jCapacityTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jCapacityTextField);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Oper. cost ($/yr):");
        jPanel3.add(jLabel11);

        jOperatingCostTextField.setEditable(false);
        jOperatingCostTextField.setBackground(java.awt.SystemColor.control);
        jOperatingCostTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jOperatingCostTextField);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 0), new java.awt.Color(153, 153, 153)));

        jOpenLabelButton.setText("Open");
        jOpenLabelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenLabelButtonActionPerformed(evt);
            }
        });

        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jOpenLabelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCancelButton, jOpenLabelButton});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCancelButton)
                    .addComponent(jOpenLabelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(FoundRecordsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FoundRecordsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LabelsFoundListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelsFoundListMouseClicked
        if (evt.getClickCount() > 1) { //Double-click
            openLabel();
        }
    }//GEN-LAST:event_LabelsFoundListMouseClicked

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        bLoadLabel = false;
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void openLabel() {
        if (LabelsFoundList.getSelectedIndex() != -1) {
            java.awt.EventQueue.invokeLater(() -> {
                bLoadLabel = true;
                app.setStatus("Opening label...");
                dispose();
            });

        } else {
            JOptionPane.showMessageDialog(this,
                    "No label selected for opening",
                    "Open Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jOpenLabelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenLabelButtonActionPerformed
        openLabel();
    }//GEN-LAST:event_jOpenLabelButtonActionPerformed

    public boolean proceedToOpenLabel() {
        return bLoadLabel;
    }

    private void LabelsFoundListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_LabelsFoundListValueChanged
        int selectedLabelIndex = LabelsFoundList.getSelectedIndex();

        if (selectedLabelIndex != -1) {
            labelId = (Long) labelIDs.get(selectedLabelIndex);

            EnergyLabel reld = app.findLabel(labelId);
            // Display label record
            jManufacturerTextField.setText(reld.getManufacturer());
            jDistributorTextField.setText(reld.getDistributor());
            jCountryOfOriginTextField.setText(reld.getCountry());
            jBrandTextField.setText(reld.getBrand());
            jModelTextField.setText(reld.getModel());
            jDefrostTextField.setText(reld.getDefrost());
            jCapacityTextField.setText(reld.getCapacity());
            jOperatingCostTextField.setText(reld.getOperatingCost());
        }

    }//GEN-LAST:event_LabelsFoundListValueChanged

    private void jHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHelpButtonActionPerformed
        SearchHelpDialog shdlg = new SearchHelpDialog(app, true);
        shdlg.setVisible(true);
    }//GEN-LAST:event_jHelpButtonActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed

        // Clear list by setting empty vector as list data
        LabelsFoundList.setListData(new ArrayList().toArray());

        jScrollPane1.getViewport().setViewPosition(new Point(0, 0));
        // Clear label details
        jManufacturerTextField.setText("");
        jDistributorTextField.setText("");
        jCountryOfOriginTextField.setText("");
        jBrandTextField.setText("");
        jModelTextField.setText("");
        jDefrostTextField.setText("");
        jCapacityTextField.setText("");
        jOperatingCostTextField.setText("");

        searchString
                = jTextToFindTextField.getText().trim();

        // Search for labels and list them
        int index = jFieldToSearchComboBox.getSelectedIndex();
        int index2 = jFieldToDisplayComboBox.getSelectedIndex();
        loadFoundLabelsList(fieldsToSearch[index][1], // search field
                fieldsToSearch[index2][1], // display field
                searchString);

    }//GEN-LAST:event_jSearchButtonActionPerformed

    void loadFoundLabelsList(String searchField,
            String displayField,
            String searchPattern) {
        String displayFieldValue;
        labelIDs = new ArrayList();
        labels = new ArrayList();
        numberOfLabelsFound = 0;

        List<EnergyLabel> labelsFound = app.findLabels(searchField, searchPattern);

        if (labelsFound != null) {
            ListIterator<EnergyLabel> iter = labelsFound.listIterator();
            while (iter.hasNext()) {
                EnergyLabel reld = iter.next();
                displayFieldValue = getFieldValueToDisplay(reld, displayField);
                labelIDs.add(reld.getId());
                labels.add(displayFieldValue);
                ++numberOfLabelsFound;

            }
            LabelsFoundList.setListData(labels.toArray());
            // Change border title of FoundRecordsPanel to reflect records found
            FoundRecordsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 0),
                    new java.awt.Color(153, 153, 153)), "Label(s) found: " + numberOfLabelsFound, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0)));
        }
    }

    public String getFieldValueToDisplay(EnergyLabel reld, String displayField) {
        if (displayField.equals("brand")) {
            return reld.getBrand();
        }

        if (displayField.equals("capacity")) {
            return reld.getCapacity();
        }

        if (displayField.equals("country")) {
            return reld.getCountry();
        }

        if (displayField.equals("defrost")) {
            return reld.getDefrost();
        }

        if (displayField.equals("distributor")) {
            return reld.getDistributor();
        }

        if (displayField.equals("jobNumber")) {
            return reld.getJobNumber();
        }

        if (displayField.equals("labelName")) {
            return reld.getLabelName();
        }

        if (displayField.equals("manufacturer")) {
            return reld.getManufacturer();
        }

        if (displayField.equals("model")) {
            return reld.getModel();
        }

        if (displayField.equals("operatingCost")) {
            return reld.getOperatingCost();
        }

        return "";
    }

    String[] updateMostRecentSearchStringsComboBox(JComboBox combobox,
            String[] currentList,
            String lastSearchString) {
        String newMostRecentSearchStrings[]
                = new String[currentList.length];
        // Check if string is already in the list. Return current list if so
        for (int i = 0; i < currentList.length; ++i) {
            if (currentList[i].toUpperCase().equals(lastSearchString.toUpperCase())) {
                return currentList;
            }
        }

        newMostRecentSearchStrings[0] = lastSearchString;
        for (int i = 1; i < newMostRecentSearchStrings.length; ++i) {
            newMostRecentSearchStrings[i] = currentList[i - 1];

        }
        combobox.removeAllItems();
        for (int i = 0; i < newMostRecentSearchStrings.length; ++i) {
            combobox.addItem(newMostRecentSearchStrings[i]);

        }
        return newMostRecentSearchStrings;

    }

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new OpenLabelDialog(new java.awt.Frame(), true).setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FoundRecordsPanel;
    private javax.swing.JList LabelsFoundList;
    private javax.swing.JTextField jBrandTextField;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JTextField jCapacityTextField;
    private javax.swing.JTextField jCountryOfOriginTextField;
    private javax.swing.JTextField jDefrostTextField;
    private javax.swing.JTextField jDistributorTextField;
    private javax.swing.JComboBox jFieldToDisplayComboBox;
    private javax.swing.JComboBox jFieldToSearchComboBox;
    private javax.swing.JButton jHelpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jManufacturerTextField;
    private javax.swing.JTextField jModelTextField;
    private javax.swing.JButton jOpenLabelButton;
    private javax.swing.JTextField jOperatingCostTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jTextToFindTextField;
    // End of variables declaration//GEN-END:variables

}
