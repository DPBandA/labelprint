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

import java.util.ArrayList;
import javax.swing.JOptionPane;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.EnergyConsumptionAndEfficiency;
import jm.com.dpbennett.business.entity.EnergyLabel;
import jm.com.dpbennett.business.entity.swingutils.SwingUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

public class LabelDataPanel extends javax.swing.JPanel {

    private LabelPrintFrame labelPrintFrame;
    private EnergyLabel energyLabel;

    /**
     * Creates new form LabelDataDialog.
     */
    public LabelDataPanel() {
        initComponents();
        init();
    }

    /**
     * Creates new form LabelDataDialog
     *
     * @param labelPrintFrame
     */
    public LabelDataPanel(LabelPrintFrame labelPrintFrame) {
        this.labelPrintFrame = labelPrintFrame;
        initComponents();
        init();
    }

    private void init() {
        energyLabel = new EnergyLabel();

        // tk
        ArrayList<BusinessEntity> data = new ArrayList<>();
        data.add(new EnergyConsumptionAndEfficiency(1L, "Iya!!2323 232ui32i i23u2i 3i2ui3u i2u3i2i3u2iui32iu3i2u iu2iu3i 2ui32i3i 2323 yyytt tioeori eorieoiro roeior oerioeir oeoreo iroeiroeiorioe iroeiorieo roe ??"));
        data.add(new EnergyConsumptionAndEfficiency(2L, "Seen!!"));
        data.add(new EnergyConsumptionAndEfficiency(3L, "Yes"));
        
        jProductTypeDetail.setModel(SwingUtils.getBusinessEntityComboBoxModel(jProductTypeDetail,
                data, 4, 1, 5));
    }

    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(EnergyLabel energyLabel) {
        this.energyLabel = energyLabel;
    }

    /**
     * Get label data from the data panel
     */
    public void getLabelData() {

        getEnergyLabel().setDistributor(jDistributor.getText());
        getEnergyLabel().setDefrost(jDefrost.getSelectedItem().toString());
        getEnergyLabel().setCostPerKwh(jElectricityRate.getText());
        getEnergyLabel().setBrand(jBrand.getText());
        getEnergyLabel().setModel(jModelNo.getText());
        getEnergyLabel().setValidity(jValidity.getText());
        getEnergyLabel().setCapacity(jCapacity.getText());
        getEnergyLabel().setCoolingCapacity(jCoolingCapacity.getText());
        getEnergyLabel().setHeatingCapacity(jHeatingCapacity.getText());
        getEnergyLabel().setStandard(jStandardNo.getText());
        getEnergyLabel().setOperatingCost(jOperatingCost.getText());
        getEnergyLabel().setManufacturer(jManufacturer.getText());
        getEnergyLabel().setCountry(jCountryOfOrigin.getText());
        getEnergyLabel().setAnnualConsumption(jAnnualConsumption.getText());
        getEnergyLabel().setType(jProductType.getSelectedItem().toString());
        getEnergyLabel().setJobNumber(jJobNumber.getText());
        getEnergyLabel().setLabelName(jLabelName.getText());

    }

    /**
     * Load data into the data panel
     */
    private void loadLabelData() {
        jDistributor.setText(getEnergyLabel().getDistributor());
        jDefrost.setSelectedItem(getEnergyLabel().getDefrost());
        jElectricityRate.setText(getEnergyLabel().getCostPerKwh());
        jBrand.setText(getEnergyLabel().getBrand());
        jModelNo.setText(getEnergyLabel().getModel());
        jValidity.setText(getEnergyLabel().getValidity());
        jCapacity.setText(getEnergyLabel().getCapacity());
        jCoolingCapacity.setText(getEnergyLabel().getCoolingCapacity());
        jHeatingCapacity.setText(getEnergyLabel().getHeatingCapacity());
        jStandardNo.setText(getEnergyLabel().getStandard());
        jOperatingCost.setText(getEnergyLabel().getOperatingCost());
        jManufacturer.setText(getEnergyLabel().getManufacturer());
        jCountryOfOrigin.setText(getEnergyLabel().getCountry());
        jAnnualConsumption.setText(getEnergyLabel().getAnnualConsumption());
        jProductType.setSelectedItem(getEnergyLabel().getType());
        jJobNumber.setText(getEnergyLabel().getJobNumber());
        jLabelName.setText(getEnergyLabel().getLabelName());

        getEnergyLabel().setIsDirty(false);
    }

    public void updateLabelData() {
        loadLabelData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jJobNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jProductType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jCapacity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDistributor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jManufacturer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jBrand = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jModelNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCountryOfOrigin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jOperatingCost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jAnnualConsumption = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jElectricityRate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jValidity = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jStandardNo = new javax.swing.JTextField();
        jViewLabel = new javax.swing.JButton();
        jSaveLabel = new javax.swing.JButton();
        jDefrost = new javax.swing.JComboBox();
        jCoolingCapacity = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jHeatingCapacity = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jRatedVoltage = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jRatedFrequency = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jProductTypeDetail = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setLabelFor(jProductType);
        jLabel14.setText("Label Name:");

        jLabelName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabelName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabelNameKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setLabelFor(jJobNumber);
        jLabel15.setText("Job Number:");

        jJobNumber.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jJobNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jJobNumberKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setLabelFor(jProductType);
        jLabel1.setText("Product Type:");

        jProductType.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jProductType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Basic Refrigerator", "Freezer", "Refrigerator", "Room Air-conditioner", " " }));
        jProductType.setSelectedIndex(2);
        jProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Volumetric Capacity (cu. m):");

        jCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCapacityKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Defrost:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Distributor:");

        jDistributor.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jDistributor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDistributorKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Manufacturer:");

        jManufacturer.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jManufacturer.setBorder(null);
        jManufacturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jManufacturerKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Brand:");

        jBrand.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jBrand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBrandKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Model No.:");

        jModelNo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jModelNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jModelNoKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Country of Origin:");

        jCountryOfOrigin.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCountryOfOrigin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCountryOfOriginKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Operating Cost ($/yr):");

        jOperatingCost.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jOperatingCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jOperatingCostKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Anual Consumpt'n (kwh/yr):");

        jAnnualConsumption.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jAnnualConsumption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnnualConsumptionKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Electricity Rate ($/kwh):");

        jElectricityRate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jElectricityRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jElectricityRateKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Validity (year):");

        jValidity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jValidity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jValidityKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Standard No.:");

        jStandardNo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jStandardNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jStandardNoKeyReleased(evt);
            }
        });

        jViewLabel.setText("View");
        jViewLabel.setToolTipText("View label with current data");
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

        jDefrost.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jDefrost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Automatic", "Manual", " " }));
        jDefrost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDefrostActionPerformed(evt);
            }
        });

        jCoolingCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCoolingCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCoolingCapacityKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Cooling Capacity (kW):");

        jHeatingCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jHeatingCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHeatingCapacityKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Heating Capacity (kW):");

        jRatedVoltage.setEditable(true);
        jRatedVoltage.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jRatedVoltage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "110", "115", "120", "125", "220", "230", "240", "110/220", "115/230", "120/240" }));
        jRatedVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedVoltageActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Rated Voltage (V):");

        jRatedFrequency.setEditable(true);
        jRatedFrequency.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jRatedFrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "60", "50/60", " " }));
        jRatedFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedFrequencyActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Rated Frequency (Hz):");

        jProductTypeDetail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jProductTypeDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeDetailActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setLabelFor(jProductType);
        jLabel20.setText("Product type detail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabelName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jJobNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jProductType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jCapacity))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jManufacturer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jBrand))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jModelNo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jCountryOfOrigin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jOperatingCost))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jAnnualConsumption))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jElectricityRate))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jValidity))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jStandardNo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jViewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSaveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDistributor)
                            .addComponent(jDefrost, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRatedVoltage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRatedFrequency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(225, 225, 225))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCoolingCapacity)
                            .addComponent(jHeatingCapacity)
                            .addComponent(jProductTypeDetail, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jJobNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jProductTypeDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCoolingCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jHeatingCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDefrost, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRatedVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRatedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jModelNo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCountryOfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOperatingCost, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAnnualConsumption, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jElectricityRate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jValidity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStandardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jViewLabel)
                    .addComponent(jSaveLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jSaveLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveLabelActionPerformed
        labelPrintFrame.saveLabel();
    }//GEN-LAST:event_jSaveLabelActionPerformed

    private void jViewLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewLabelActionPerformed
        ReturnMessage returnMessage = getEnergyLabel().validate(labelPrintFrame.getEntityManager());
        if (returnMessage.isSuccess()) {
            labelPrintFrame.getLabelPanel().updateLabel();
            labelPrintFrame.getTabbedPane().setSelectedIndex(1);
        } else {
            
            JOptionPane.showMessageDialog(this,
                            returnMessage.getMessage(),
                            returnMessage.getHeader(),
                            JOptionPane.ERROR_MESSAGE);   
        }

    }//GEN-LAST:event_jViewLabelActionPerformed

    private void jProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeActionPerformed
        getEnergyLabel().setType((String) jProductType.getSelectedItem());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jProductTypeActionPerformed

    private void jLabelNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelNameKeyReleased
        getEnergyLabel().setLabelName(jLabelName.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jLabelNameKeyReleased

    private void jJobNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJobNumberKeyReleased
        getEnergyLabel().setJobNumber(jJobNumber.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jJobNumberKeyReleased

    private void jCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCapacityKeyReleased
        getEnergyLabel().setCapacity(jCapacity.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jCapacityKeyReleased

    private void jDistributorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDistributorKeyReleased
        getEnergyLabel().setDistributor(jDistributor.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jDistributorKeyReleased

    private void jManufacturerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jManufacturerKeyReleased
        getEnergyLabel().setManufacturer(jManufacturer.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jManufacturerKeyReleased

    private void jBrandKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBrandKeyReleased
        getEnergyLabel().setBrand(jBrand.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jBrandKeyReleased

    private void jModelNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jModelNoKeyReleased
        getEnergyLabel().setModel(jModelNo.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jModelNoKeyReleased

    private void jCountryOfOriginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCountryOfOriginKeyReleased
        getEnergyLabel().setCountry(jCountryOfOrigin.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jCountryOfOriginKeyReleased

    private void jOperatingCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jOperatingCostKeyReleased
        getEnergyLabel().setOperatingCost(jOperatingCost.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jOperatingCostKeyReleased

    private void jAnnualConsumptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnnualConsumptionKeyReleased
        getEnergyLabel().setAnnualConsumption(jAnnualConsumption.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jAnnualConsumptionKeyReleased

    private void jElectricityRateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jElectricityRateKeyReleased
        getEnergyLabel().setCostPerKwh(jElectricityRate.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jElectricityRateKeyReleased

    private void jValidityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jValidityKeyReleased
        getEnergyLabel().setValidity(jValidity.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jValidityKeyReleased

    private void jStandardNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jStandardNoKeyReleased
        getEnergyLabel().setStandard(jStandardNo.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jStandardNoKeyReleased

    private void jDefrostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefrostActionPerformed
        getEnergyLabel().setDefrost((String) jDefrost.getSelectedItem());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jDefrostActionPerformed

    private void jCoolingCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCoolingCapacityKeyReleased
        getEnergyLabel().setCoolingCapacity((String) jCoolingCapacity.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jCoolingCapacityKeyReleased

    private void jHeatingCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHeatingCapacityKeyReleased
        getEnergyLabel().setHeatingCapacity((String) jHeatingCapacity.getText());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jHeatingCapacityKeyReleased

    private void jRatedVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedVoltageActionPerformed
        getEnergyLabel().setRatedFrequency((String) jRatedVoltage.getSelectedItem());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jRatedVoltageActionPerformed

    private void jRatedFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedFrequencyActionPerformed
        getEnergyLabel().setRatedFrequency((String) jRatedFrequency.getSelectedItem());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jRatedFrequencyActionPerformed

    private void jProductTypeDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeDetailActionPerformed
        getEnergyLabel().setEnergyConsumptionAndEfficiency(
                (EnergyConsumptionAndEfficiency) jProductTypeDetail.getSelectedItem());
        labelPrintFrame.setDirty(true);
    }//GEN-LAST:event_jProductTypeDetailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAnnualConsumption;
    private javax.swing.JTextField jBrand;
    private javax.swing.JTextField jCapacity;
    private javax.swing.JTextField jCoolingCapacity;
    private javax.swing.JTextField jCountryOfOrigin;
    private javax.swing.JComboBox jDefrost;
    private javax.swing.JTextField jDistributor;
    private javax.swing.JTextField jElectricityRate;
    private javax.swing.JTextField jHeatingCapacity;
    private javax.swing.JTextField jJobNumber;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLabelName;
    private javax.swing.JTextField jManufacturer;
    private javax.swing.JTextField jModelNo;
    private javax.swing.JTextField jOperatingCost;
    private javax.swing.JComboBox jProductType;
    private javax.swing.JComboBox jProductTypeDetail;
    private javax.swing.JComboBox jRatedFrequency;
    private javax.swing.JComboBox jRatedVoltage;
    private javax.swing.JButton jSaveLabel;
    private javax.swing.JTextField jStandardNo;
    private javax.swing.JTextField jValidity;
    private javax.swing.JButton jViewLabel;
    // End of variables declaration//GEN-END:variables
}
