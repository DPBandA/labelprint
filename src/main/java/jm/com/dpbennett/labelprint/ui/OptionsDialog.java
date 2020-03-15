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

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.mt.EnergyConsumptionAndEfficiency;
import jm.com.dpbennett.business.entity.su.SwingUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;
import jm.com.dpbennett.labelprint.Options;

/**
 * Uses the system properties file to edit system properties.
 *
 * @author Desmond Bennett
 */
public class OptionsDialog extends javax.swing.JDialog {

    private boolean isDirty;
    private Application app;

    /**
     * Constructs an OptionsDialog
     *
     * @param parent
     * @param modal
     */
    public OptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        init(parent);

    }

    /**
     * Initializes the fields with the saved properties/options.
     *
     * @param parent
     */
    private void init(java.awt.Frame parent) {
        String[][] fieldsToSearch;
        app = (Application) parent;
        setLocationRelativeTo(null);

        // Load existing options
        Options sysOptions = app.getSystemOptions();
        // Notes tab
        jNote1_1_TextArea.setText(sysOptions.getProperty("Note1_1"));
        jNote1_1_AEER_TextArea.setText(sysOptions.getProperty("Note1_1_AEER"));
        jNote1_1_ACOP_TextArea.setText(sysOptions.getProperty("Note1_1_ACOP"));
        jNote1_2_TextArea.setText(sysOptions.getProperty("Note1_2"));
        jNote1_3_TextArea.setText(sysOptions.getProperty("Note1_3"));
        jNote1_4_TextArea.setText(sysOptions.getProperty("Note1_4"));
        jNote1_5_TextArea.setText(sysOptions.getProperty("Note1_5"));
        jNote2_1_TextArea.setText(sysOptions.getProperty("Note2_1"));
        jNote2_2_TextArea.setText(sysOptions.getProperty("Note2_2"));
        jNote3_1_TextArea.setText(sysOptions.getProperty("Note3_1"));
        jNote3_2_TextArea.setText(sysOptions.getProperty("Note3_2"));
        jNote3_3_TextArea.setText(sysOptions.getProperty("Note3_3"));
        // Database tab
        jDatabaseURLTextField.setText(sysOptions.getProperty("ConnectionURL"));
        jUsernameTextField.setText(sysOptions.getProperty("ConnectionUserName"));
        jPasswordField.setText(sysOptions.getConnectionPassword());
        fieldsToSearch = sysOptions.getFieldsToSearch();
        for (int i = 0; i < fieldsToSearch.length; ++i) {
            jDefaultSearchFieldComboBox.addItem(fieldsToSearch[i][0]);
        }
        jDefaultSearchFieldComboBox.setSelectedItem(sysOptions.getProperty("DefaultFieldToSearch"));
        jConnectToDatabaseCheckBox.setSelected(sysOptions.isConnectToDatabase());
        // Label Content tab  
        // Electricity rates
        jElectricityRate1.setText(sysOptions.getProperty("CostPerKWh_1"));
        jElectricityRate2.setText(sysOptions.getProperty("CostPerKWh_2"));
        // Image Export Formats
        jJPEG.setSelected(sysOptions.isExportJPEG());
        jGIF.setSelected(sysOptions.isExportGIF());
        jPNG.setSelected(sysOptions.isExportPNG());
        jPDF.setSelected(sysOptions.isExportPDF());
        // Label defaults
        jRatedVoltage.setSelectedItem(sysOptions.getProperty("DefaultRatedVoltage"));
        jRatedFrequency.setSelectedItem(sysOptions.getProperty("DefaultRatedFrequency"));
        jRefrigeratorStandard.setSelectedItem(sysOptions.getProperty("RefrigeratorStandard"));
        jRoomACStandard.setSelectedItem(sysOptions.getProperty("RoomACStandard"));
        jProductType.setSelectedItem(sysOptions.getProperty("DefaultProductType"));
        // Refrigerator constants
        jTestChamberTemp.setText(sysOptions.getProperty("TestChamberTemp"));
        jFreshFoodComptAvgTemp.setText(sysOptions.getProperty("FreshFoodComptAvgTemp"));
        jBasicRefrigeratorTemp.setText(sysOptions.getProperty("BasicRefrigeratorTemp"));
        jAllRefrigeratorTemp.setText(sysOptions.getProperty("AllRefrigeratorTemp"));
        jRefrigeratorFreezerTemp.setText(sysOptions.getProperty("RefrigeratorFreezerTemp"));
        jFreezerTemp.setText(sysOptions.getProperty("FreezerTemp"));
        jWineChillerTemp.setText(sysOptions.getProperty("WineChillerTemp"));

        loadProductTypeDetailandClassCombos(sysOptions);

        isDirty = false;
    }

    private void loadProductTypeDetailandClassCombos(Options sysOptions) {
        List<BusinessEntity> productTypeDetails = new ArrayList<>();
        List<BusinessEntity> productClasses = new ArrayList<>();

        // Product classes
        productClasses.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                "Room Air-conditioner"));

        // Product type details
        productTypeDetails.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                "Refrigerator"));
        productTypeDetails.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                "Basic Refrigerator"));
        productTypeDetails.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                "Freezer"));

        jProductTypeDetail.setModel(SwingUtils.getBusinessEntityComboBoxModel(jProductTypeDetail,
                (List<BusinessEntity>) productTypeDetails, 4, 1, 5));
        jProductClass.setModel(SwingUtils.getBusinessEntityComboBoxModel(jProductClass,
                (List<BusinessEntity>) productClasses, 2, 1, 5));

        // Set combos' values
        EnergyConsumptionAndEfficiency productTypeDetail
                = EnergyConsumptionAndEfficiency.findById(app.getEntityManager(),
                        sysOptions.getLongProperty("DefaultProductTypeDetailId"));
        jProductTypeDetail.setSelectedItem(productTypeDetail);
        EnergyConsumptionAndEfficiency productClass
                = EnergyConsumptionAndEfficiency.findById(app.getEntityManager(),
                        sysOptions.getLongProperty("DefaultProductClassId"));
        jProductClass.setSelectedItem(productClass);

    }

    public boolean hasDatabaseConnectionOptionsChanged() {
        return isDirty;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionsTabbedPane = new javax.swing.JTabbedPane();
        jLabelNotesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jNote1_1_TextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jNote1_2_TextArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jNote2_1_TextArea = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jNote3_1_TextArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jNote2_2_TextArea = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jNote3_2_TextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jNote3_3_TextArea = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jNote1_3_TextArea = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jNote1_1_AEER_TextArea = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jNote1_1_ACOP_TextArea = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jNote1_4_TextArea = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jNote1_5_TextArea = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabelContentPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jJPEG = new javax.swing.JCheckBox();
        jPNG = new javax.swing.JCheckBox();
        jGIF = new javax.swing.JCheckBox();
        jPDF = new javax.swing.JCheckBox();
        jTIFF = new javax.swing.JCheckBox();
        jSVG = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRatedVoltage = new javax.swing.JComboBox<>();
        jRatedFrequency = new javax.swing.JComboBox<>();
        jProductType = new javax.swing.JComboBox<>();
        jProductTypeDetail = new javax.swing.JComboBox<>();
        jProductClass = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jRefrigeratorStandard = new javax.swing.JComboBox<>();
        jRoomACStandard = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jElectricityRate1 = new javax.swing.JTextField();
        jElectricityRate2 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jDatabasePanel = new javax.swing.JPanel();
        jDatabaseOptionsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDatabaseURLTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jUsernameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jDefaultSearchFieldComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jConnectToDatabaseCheckBox = new javax.swing.JCheckBox();
        jContantsPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTestChamberTemp = new javax.swing.JTextField();
        jFreshFoodComptAvgTemp = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jBasicRefrigeratorTemp = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jAllRefrigeratorTemp = new javax.swing.JTextField();
        jRefrigeratorFreezerTemp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFreezerTemp = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jWineChillerTemp = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jOk = new javax.swing.JButton();
        jCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jNote1_1_TextArea.setColumns(20);
        jNote1_1_TextArea.setLineWrap(true);
        jNote1_1_TextArea.setRows(1);
        jNote1_1_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_1_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jNote1_1_TextArea);

        jNote1_2_TextArea.setColumns(20);
        jNote1_2_TextArea.setLineWrap(true);
        jNote1_2_TextArea.setRows(1);
        jNote1_2_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_2_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jNote1_2_TextArea);

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setText("Standard note line 1:");

        jNote2_1_TextArea.setColumns(20);
        jNote2_1_TextArea.setLineWrap(true);
        jNote2_1_TextArea.setRows(1);
        jNote2_1_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote2_1_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jNote2_1_TextArea);

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setText("Violation note line 1:");

        jNote3_1_TextArea.setColumns(20);
        jNote3_1_TextArea.setLineWrap(true);
        jNote3_1_TextArea.setRows(1);
        jNote3_1_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote3_1_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jNote3_1_TextArea);

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setText("Energy consumption note:");

        jNote2_2_TextArea.setColumns(20);
        jNote2_2_TextArea.setLineWrap(true);
        jNote2_2_TextArea.setRows(1);
        jNote2_2_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote2_2_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jNote2_2_TextArea);

        jNote3_2_TextArea.setColumns(20);
        jNote3_2_TextArea.setLineWrap(true);
        jNote3_2_TextArea.setRows(1);
        jNote3_2_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote3_2_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jNote3_2_TextArea);

        jNote3_3_TextArea.setColumns(20);
        jNote3_3_TextArea.setLineWrap(true);
        jNote3_3_TextArea.setRows(1);
        jNote3_3_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote3_3_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(jNote3_3_TextArea);

        jNote1_3_TextArea.setColumns(20);
        jNote1_3_TextArea.setLineWrap(true);
        jNote1_3_TextArea.setRows(1);
        jNote1_3_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_3_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(jNote1_3_TextArea);

        jNote1_1_AEER_TextArea.setColumns(20);
        jNote1_1_AEER_TextArea.setLineWrap(true);
        jNote1_1_AEER_TextArea.setRows(1);
        jNote1_1_AEER_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_1_AEER_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(jNote1_1_AEER_TextArea);

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setText("Ann. ener. eff. note:");

        jNote1_1_ACOP_TextArea.setColumns(20);
        jNote1_1_ACOP_TextArea.setLineWrap(true);
        jNote1_1_ACOP_TextArea.setRows(1);
        jNote1_1_ACOP_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_1_ACOP_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane10.setViewportView(jNote1_1_ACOP_TextArea);

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setText("Ann. coeff. perf. note:");

        jNote1_4_TextArea.setColumns(20);
        jNote1_4_TextArea.setLineWrap(true);
        jNote1_4_TextArea.setRows(1);
        jNote1_4_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_4_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(jNote1_4_TextArea);

        jNote1_5_TextArea.setColumns(20);
        jNote1_5_TextArea.setLineWrap(true);
        jNote1_5_TextArea.setRows(1);
        jNote1_5_TextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNote1_5_TextAreaKeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(jNote1_5_TextArea);

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setText("Electricity rates note:");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setText("Disclaimer line 1:");

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setText("Disclaimer line 2:");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel29.setText("Disclaimer line 3:");

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel30.setText("Standard note line 2:");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel31.setText("Violation note line 2:");

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel32.setText("Violation note line 3:");

        javax.swing.GroupLayout jLabelNotesPanelLayout = new javax.swing.GroupLayout(jLabelNotesPanel);
        jLabelNotesPanel.setLayout(jLabelNotesPanelLayout);
        jLabelNotesPanelLayout.setHorizontalGroup(
            jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLabelNotesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLabelNotesPanelLayout.setVerticalGroup(
            jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLabelNotesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(7, 7, 7)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(9, 9, 9)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(9, 9, 9)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLabelNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jOptionsTabbedPane.addTab("Label Notes", jLabelNotesPanel);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Label Export Formats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        jJPEG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jJPEG.setText("JPEG");
        jJPEG.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jJPEG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJPEGActionPerformed(evt);
            }
        });

        jPNG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jPNG.setText("PNG");
        jPNG.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPNGActionPerformed(evt);
            }
        });

        jGIF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jGIF.setText("GIF");
        jGIF.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jGIF.setEnabled(false);
        jGIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGIFActionPerformed(evt);
            }
        });

        jPDF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jPDF.setText("PDF");
        jPDF.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPDF.setEnabled(false);
        jPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPDFActionPerformed(evt);
            }
        });

        jTIFF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jTIFF.setText("TIFF");
        jTIFF.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jTIFF.setEnabled(false);
        jTIFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTIFFActionPerformed(evt);
            }
        });

        jSVG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jSVG.setText("SVG");
        jSVG.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jSVG.setEnabled(false);
        jSVG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSVGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jJPEG)
                .addGap(18, 18, 18)
                .addComponent(jPNG)
                .addGap(18, 18, 18)
                .addComponent(jGIF)
                .addGap(18, 18, 18)
                .addComponent(jPDF)
                .addGap(18, 18, 18)
                .addComponent(jTIFF)
                .addGap(18, 18, 18)
                .addComponent(jSVG)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jJPEG)
                    .addComponent(jPNG)
                    .addComponent(jGIF)
                    .addComponent(jPDF)
                    .addComponent(jTIFF)
                    .addComponent(jSVG))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Label Defaults", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Refrigerator Standard (JS):");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Product Type:");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Rated Voltage:");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Rated Frequency:");

        jRatedVoltage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "110", "115", "120", "125", "220", "230", "240", "110/220", "115/230", "120/240" }));
        jRatedVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedVoltageActionPerformed(evt);
            }
        });

        jRatedFrequency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50", "60", "50/60" }));
        jRatedFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedFrequencyActionPerformed(evt);
            }
        });

        jProductType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic Refrigerator", "Freezer", "Refrigerator", "Room Air-conditioner" }));
        jProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeActionPerformed(evt);
            }
        });

        jProductTypeDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeDetailActionPerformed(evt);
            }
        });

        jProductClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductClassActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Product Type Detail:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Product Class (BTU/h):");

        jRefrigeratorStandard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "178", "179" }));
        jRefrigeratorStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefrigeratorStandardActionPerformed(evt);
            }
        });

        jRoomACStandard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "178", "179" }));
        jRoomACStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRoomACStandardActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Room AC Standard (JS):");

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Electricity Rate 1 ($/kWh):");

        jElectricityRate1.setText("0.0");

        jElectricityRate2.setText("0.0");

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Electricity Rate 2 ($/kWh):");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel6)
                                .addComponent(jLabel11)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34)))
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRatedFrequency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRatedVoltage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProductType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProductTypeDetail, 0, 223, Short.MAX_VALUE)
                            .addComponent(jProductClass, 0, 223, Short.MAX_VALUE)
                            .addComponent(jRefrigeratorStandard, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRoomACStandard, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jElectricityRate1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jElectricityRate2)))
                .addGap(235, 235, 235))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRatedVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jRatedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRefrigeratorStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRoomACStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProductTypeDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProductClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jElectricityRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jElectricityRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jLabelContentPanelLayout = new javax.swing.GroupLayout(jLabelContentPanel);
        jLabelContentPanel.setLayout(jLabelContentPanelLayout);
        jLabelContentPanelLayout.setHorizontalGroup(
            jLabelContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLabelContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLabelContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLabelContentPanelLayout.setVerticalGroup(
            jLabelContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLabelContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jOptionsTabbedPane.addTab("Label Content", jLabelContentPanel);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Database URL:");

        jDatabaseURLTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDatabaseURLTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDatabaseURLTextFieldKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Username:");

        jUsernameTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUsernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jUsernameTextFieldKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Password:");

        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Default search field:");

        jDefaultSearchFieldComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDefaultSearchFieldComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDefaultSearchFieldComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Connect to database:");

        jConnectToDatabaseCheckBox.setSelected(true);
        jConnectToDatabaseCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jConnectToDatabaseCheckBox.setEnabled(false);
        jConnectToDatabaseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConnectToDatabaseCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDatabaseOptionsPanelLayout = new javax.swing.GroupLayout(jDatabaseOptionsPanel);
        jDatabaseOptionsPanel.setLayout(jDatabaseOptionsPanelLayout);
        jDatabaseOptionsPanelLayout.setHorizontalGroup(
            jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jDatabaseURLTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jUsernameTextField))
                    .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPasswordField))
                    .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jDefaultSearchFieldComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jConnectToDatabaseCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jDatabaseOptionsPanelLayout.setVerticalGroup(
            jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDatabaseOptionsPanelLayout.createSequentialGroup()
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDatabaseURLTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDefaultSearchFieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDatabaseOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jConnectToDatabaseCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jDatabasePanelLayout = new javax.swing.GroupLayout(jDatabasePanel);
        jDatabasePanel.setLayout(jDatabasePanelLayout);
        jDatabasePanelLayout.setHorizontalGroup(
            jDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDatabasePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jDatabaseOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDatabasePanelLayout.setVerticalGroup(
            jDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDatabaseOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jOptionsTabbedPane.addTab("Database", jDatabasePanel);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Refrigerator, freezer and wine chiller contants", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setText("Test chamber ambient test temperature (⁰C):");

        jTestChamberTemp.setText("43.3");
        jTestChamberTemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTestChamberTempKeyReleased(evt);
            }
        });

        jFreshFoodComptAvgTemp.setText("3.3");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setText("Avg fresh food compar't operat'g temp. (⁰C):");

        jLabel18.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Adjustment factors for refrigerators and refrigerator-freezers:");

        jBasicRefrigeratorTemp.setText("-9.4");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Basic household refrigerator temp. (⁰C):");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("All-refrigerator household refrigerator temp. (⁰C):");

        jAllRefrigeratorTemp.setText("3.3");

        jRefrigeratorFreezerTemp.setText("-15.0");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Refrig'tor-freezer household refrig'tor temp. (⁰C):");

        jFreezerTemp.setText("-18.0");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Household freezer temp. (⁰C):");

        jWineChillerTemp.setText("12.8");

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Household wine chiller temp. (⁰C):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(69, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBasicRefrigeratorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAllRefrigeratorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRefrigeratorFreezerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFreezerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jWineChillerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel17)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFreshFoodComptAvgTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTestChamberTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTestChamberTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFreshFoodComptAvgTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jBasicRefrigeratorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAllRefrigeratorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRefrigeratorFreezerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFreezerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jWineChillerTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jContantsPanelLayout = new javax.swing.GroupLayout(jContantsPanel);
        jContantsPanel.setLayout(jContantsPanelLayout);
        jContantsPanelLayout.setHorizontalGroup(
            jContantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jContantsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jContantsPanelLayout.setVerticalGroup(
            jContantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jContantsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jOptionsTabbedPane.addTab("Constants", jContantsPanel);

        jOk.setText("Ok");
        jOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOkActionPerformed(evt);
            }
        });

        jCancel.setText("Cancel");
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jOptionsTabbedPane)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jOk)
                .addGap(18, 18, 18)
                .addComponent(jCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCancel, jOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jOptionsTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancel)
                    .addComponent(jOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jConnectToDatabaseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConnectToDatabaseCheckBoxActionPerformed

        isDirty = true;
    }//GEN-LAST:event_jConnectToDatabaseCheckBoxActionPerformed

    private void jOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOkActionPerformed
        Options sysOptions = app.getSystemOptions();

        // Database authentication
        char[] password = jPasswordField.getPassword();
        String passwordString = new String(password).trim();
        sysOptions.setConnectionPassword(passwordString);
        sysOptions.setProperty("ConnectionUserName", jUsernameTextField.getText().trim());
        sysOptions.setProperty("ConnectionURL", jDatabaseURLTextField.getText().trim());
        sysOptions.setConnectToDatabase(jConnectToDatabaseCheckBox.isSelected());

        if (app.setupDatabaseConnection()) {
            try {
                // Label Notes
                sysOptions.setProperty("Note1_1", jNote1_1_TextArea.getText());
                sysOptions.setProperty("Note1_1_AEER", jNote1_1_AEER_TextArea.getText());
                sysOptions.setProperty("Note1_1_ACOP", jNote1_1_ACOP_TextArea.getText());
                sysOptions.setProperty("Note1_2", jNote1_2_TextArea.getText());
                sysOptions.setProperty("Note1_3", jNote1_3_TextArea.getText());
                sysOptions.setProperty("Note1_4", jNote1_4_TextArea.getText());
                sysOptions.setProperty("Note1_5", jNote1_5_TextArea.getText());
                sysOptions.setProperty("Note2_1", jNote2_1_TextArea.getText());
                sysOptions.setProperty("Note2_2", jNote2_2_TextArea.getText());
                sysOptions.setProperty("Note3_1", jNote3_1_TextArea.getText());
                sysOptions.setProperty("Note3_2", jNote3_2_TextArea.getText());
                sysOptions.setProperty("Note3_3", jNote3_3_TextArea.getText());
                // Database
                sysOptions.setProperty("DefaultFieldToSearch",
                        jDefaultSearchFieldComboBox.getSelectedItem().toString());
                // Label Content
                // Electricity rates
                sysOptions.setProperty("CostPerKWh_1", jElectricityRate1.getText());
                sysOptions.setProperty("CostPerKWh_2", jElectricityRate2.getText());
                // Image export formats
                sysOptions.setExportPDF(jPDF.isSelected());
                sysOptions.setExportPNG(jPNG.isSelected());
                sysOptions.setExportGIF(jGIF.isSelected());
                sysOptions.setExportJPEG(jJPEG.isSelected());
                // Label defaults
                sysOptions.setProperty("DefaultRatedVoltage",
                        jRatedVoltage.getSelectedItem().toString());
                sysOptions.setProperty("DefaultRatedFrequency",
                        jRatedFrequency.getSelectedItem().toString());
                sysOptions.setProperty("RefrigeratorStandard",
                        jRefrigeratorStandard.getSelectedItem().toString());
                sysOptions.setProperty("RoomACStandard",
                        jRoomACStandard.getSelectedItem().toString());
                sysOptions.setProperty("DefaultProductType",
                        jProductType.getSelectedItem().toString());
                if (jProductTypeDetail.getSelectedItem() != null) {
                    sysOptions.setProperty("DefaultProductTypeDetailId",
                            ((EnergyConsumptionAndEfficiency) jProductTypeDetail.getSelectedItem()).getId().toString());
                }
                if (jProductClass.getSelectedItem() != null) {
                    sysOptions.setProperty("DefaultProductClassId",
                            ((EnergyConsumptionAndEfficiency) jProductClass.getSelectedItem()).getId().toString());
                }
                // Refrigerator constants
                sysOptions.setProperty("TestChamberTemp", jTestChamberTemp.getText());
                sysOptions.setProperty("FreshFoodComptAvgTemp", jFreshFoodComptAvgTemp.getText());
                sysOptions.setProperty("BasicRefrigeratorTemp", jBasicRefrigeratorTemp.getText());
                sysOptions.setProperty("AllRefrigeratorTemp", jAllRefrigeratorTemp.getText());
                sysOptions.setProperty("RefrigeratorFreezerTemp", jRefrigeratorFreezerTemp.getText());
                sysOptions.setProperty("FreezerTemp", jFreezerTemp.getText());
                sysOptions.setProperty("WineChillerTemp", jWineChillerTemp.getText());

                ReturnMessage message = sysOptions.validate(app.getEntityManager());

                if (message.isSuccess()) {
                    sysOptions.save();

                    // Update relevant views that are dependent on system options
                    java.awt.EventQueue.invokeLater(() -> {
                        if (app.getLabelPanel() != null) {
                            app.getLabelPanel().updateLabel();
                        }
                    });

                    app.setStatus("Ready...");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            message.getMessage(),
                            message.getHeader(),
                            JOptionPane.ERROR_MESSAGE);

                    app.setStatus("Invalid options...");
                }

            } catch (HeadlessException e) {
                System.out.println(e);
                app.setStatus("A database connection error occurred!");
                JOptionPane.showMessageDialog(this,
                        "A database connection error occurred\n"
                        + "so the options were not saved. Check that\n"
                        + "the database options are valid",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            app.setStatus("A database connection error occurred!");
            JOptionPane.showMessageDialog(this,
                    "A database connection error occurred\n"
                    + "so the options were not saved. Check that\n"
                    + "the database options are valid",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jOkActionPerformed

    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        isDirty = false;
        dispose();
    }//GEN-LAST:event_jCancelActionPerformed

    private void jDefaultSearchFieldComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefaultSearchFieldComboBoxActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jDefaultSearchFieldComboBoxActionPerformed

    private void jRatedVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedVoltageActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jRatedVoltageActionPerformed

    private void jRatedFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedFrequencyActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jRatedFrequencyActionPerformed

    private void jNote1_1_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_1_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote1_1_TextAreaKeyReleased

    private void jNote1_2_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_2_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote1_2_TextAreaKeyReleased

    private void jNote2_1_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote2_1_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote2_1_TextAreaKeyReleased

    private void jNote2_2_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote2_2_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote2_2_TextAreaKeyReleased

    private void jNote3_1_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote3_1_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote3_1_TextAreaKeyReleased

    private void jNote3_2_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote3_2_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote3_2_TextAreaKeyReleased

    private void jNote3_3_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote3_3_TextAreaKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jNote3_3_TextAreaKeyReleased

    private void jDatabaseURLTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDatabaseURLTextFieldKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jDatabaseURLTextFieldKeyReleased

    private void jUsernameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUsernameTextFieldKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jUsernameTextFieldKeyReleased

    private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jPasswordFieldKeyReleased

    private void jJPEGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJPEGActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jJPEGActionPerformed

    private void jPNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPNGActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jPNGActionPerformed

    private void jGIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGIFActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jGIFActionPerformed

    private void jPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPDFActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jPDFActionPerformed

    private void jProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jProductTypeActionPerformed

    private void jProductTypeDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeDetailActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jProductTypeDetailActionPerformed

    private void jProductClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductClassActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jProductClassActionPerformed

    private void jRefrigeratorStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefrigeratorStandardActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jRefrigeratorStandardActionPerformed

    private void jRoomACStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRoomACStandardActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jRoomACStandardActionPerformed

    private void jTIFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTIFFActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jTIFFActionPerformed

    private void jSVGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSVGActionPerformed
        isDirty = true;
    }//GEN-LAST:event_jSVGActionPerformed

    private void jTestChamberTempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTestChamberTempKeyReleased
        isDirty = true;
    }//GEN-LAST:event_jTestChamberTempKeyReleased

    private void jNote1_3_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_3_TextAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jNote1_3_TextAreaKeyReleased

    private void jNote1_1_AEER_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_1_AEER_TextAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jNote1_1_AEER_TextAreaKeyReleased

    private void jNote1_1_ACOP_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_1_ACOP_TextAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jNote1_1_ACOP_TextAreaKeyReleased

    private void jNote1_4_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_4_TextAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jNote1_4_TextAreaKeyReleased

    private void jNote1_5_TextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNote1_5_TextAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jNote1_5_TextAreaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new OptionsDialog(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAllRefrigeratorTemp;
    private javax.swing.JTextField jBasicRefrigeratorTemp;
    private javax.swing.JButton jCancel;
    private javax.swing.JCheckBox jConnectToDatabaseCheckBox;
    private javax.swing.JPanel jContantsPanel;
    private javax.swing.JPanel jDatabaseOptionsPanel;
    private javax.swing.JPanel jDatabasePanel;
    private javax.swing.JTextField jDatabaseURLTextField;
    private javax.swing.JComboBox jDefaultSearchFieldComboBox;
    private javax.swing.JTextField jElectricityRate1;
    private javax.swing.JTextField jElectricityRate2;
    private javax.swing.JTextField jFreezerTemp;
    private javax.swing.JTextField jFreshFoodComptAvgTemp;
    private javax.swing.JCheckBox jGIF;
    private javax.swing.JCheckBox jJPEG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLabelContentPanel;
    private javax.swing.JPanel jLabelNotesPanel;
    private javax.swing.JTextArea jNote1_1_ACOP_TextArea;
    private javax.swing.JTextArea jNote1_1_AEER_TextArea;
    private javax.swing.JTextArea jNote1_1_TextArea;
    private javax.swing.JTextArea jNote1_2_TextArea;
    private javax.swing.JTextArea jNote1_3_TextArea;
    private javax.swing.JTextArea jNote1_4_TextArea;
    private javax.swing.JTextArea jNote1_5_TextArea;
    private javax.swing.JTextArea jNote2_1_TextArea;
    private javax.swing.JTextArea jNote2_2_TextArea;
    private javax.swing.JTextArea jNote3_1_TextArea;
    private javax.swing.JTextArea jNote3_2_TextArea;
    private javax.swing.JTextArea jNote3_3_TextArea;
    private javax.swing.JButton jOk;
    private javax.swing.JTabbedPane jOptionsTabbedPane;
    private javax.swing.JCheckBox jPDF;
    private javax.swing.JCheckBox jPNG;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JComboBox<String> jProductClass;
    private javax.swing.JComboBox<String> jProductType;
    private javax.swing.JComboBox<String> jProductTypeDetail;
    private javax.swing.JComboBox<String> jRatedFrequency;
    private javax.swing.JComboBox<String> jRatedVoltage;
    private javax.swing.JTextField jRefrigeratorFreezerTemp;
    private javax.swing.JComboBox<String> jRefrigeratorStandard;
    private javax.swing.JComboBox<String> jRoomACStandard;
    private javax.swing.JCheckBox jSVG;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JCheckBox jTIFF;
    private javax.swing.JTextField jTestChamberTemp;
    private javax.swing.JTextField jUsernameTextField;
    private javax.swing.JTextField jWineChillerTemp;
    // End of variables declaration//GEN-END:variables
}
