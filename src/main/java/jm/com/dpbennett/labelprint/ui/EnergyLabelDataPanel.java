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

import java.util.List;
import jm.com.dpbennett.business.entity.mt.EnergyLabel;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.StringUtils;

public class EnergyLabelDataPanel extends javax.swing.JPanel {

    private final Application app;
    private EnergyLabel energyLabel;

    /**
     * Creates new form LabelDataPanel
     *
     * @param app
     */
    public EnergyLabelDataPanel(Application app) {
        this.app = app;
        initComponents();
        init();
    }

    /**
     * Initialize the EnergyLabelDataPanel and create a new label.
     */
    private void init() {
        // tk init combos
        List<String> items = StringUtils.splitString(
                app.getSystemOptions().getProperty("Features"), ";");
        
        items.forEach((item) -> {
            jFeature1.addItem(item);
            jFeature2.addItem(item);
        });
       
        
        createLabel();
    }

    /**
     * Creates a new label using default values from system options.
     */
    public void createLabel() {
        setEnergyLabel(new EnergyLabel());
        getEnergyLabel().setType(app.getSystemOptions().getProperty("DefaultProductType"));
        if (getEnergyLabel().getType().equals("Refrigerator")
                || getEnergyLabel().getType().equals("Basic Refrigerator")
                || getEnergyLabel().getType().equals("Refrigerator-Freezer")) {

            getEnergyLabel().setDefrost("Automatic");
        }
        getEnergyLabel().setRatedVoltage(app.getSystemOptions().getProperty("DefaultRatedVoltage"));
        getEnergyLabel().setRatedFrequency(app.getSystemOptions().getProperty("DefaultRatedFrequency"));
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RoomACStandard"));
        } else {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RefrigeratorStandard"));
        }
        getEnergyLabel().setCostPerKwh(app.getSystemOptions().getProperty("CostPerKWh_1"));
        getEnergyLabel().setCostPerKwh2(app.getSystemOptions().getProperty("CostPerKWh_2"));

        updateLabelDataBasedOnProductType();

        getEnergyLabel().setValidity("" + BusinessEntityUtils.getCurrentYear());

        getEnergyLabel().setYearOfEvaluation("" + BusinessEntityUtils.getCurrentYear());

        getEnergyLabel().setFeature1("--");
        getEnergyLabel().setFeature2("--");
        
        getEnergyLabel().setManufacturer("");

    }

    /**
     * Sets the model (data) of the product type detail/class combo box based on
     * the type of product. Also, the default value of the combo box is set
     * based on the defaults in system options along with other label data if
     * required.
     */
    private void updateLabelDataBasedOnProductType() {

//        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
//            if (app.getEnergyLabelPanel() != null) {
//                app.getEnergyLabelPanel().loadSVGLabel("images/CROSQACEnergyLabel.svg");
//            }
//        } else {
//            if (app.getEnergyLabelPanel() != null) {
//                app.getEnergyLabelPanel().loadSVGLabel("images/CROSQFridgeEnergyLabel.svg");
//            }
//        }
        if (app.getEnergyLabelPanel() != null) {
            app.getEnergyLabelPanel().loadSVGLabel();
        }

        // Enable/disable fields/calulated as required.
        enableFields();
    }

    /**
     * Gets the current energy label.
     *
     * @return
     */
    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }

    /**
     * Sets the current energy label.
     *
     * @param energyLabel
     */
    public void setEnergyLabel(EnergyLabel energyLabel) {
        this.energyLabel = energyLabel;
    }

    /**
     * Get the label data from the data panel fields.
     *
     */
    public void getEnergyLabelData() {

        getEnergyLabel().setJobNumber(jJobNumber.getText());
        getEnergyLabel().setEfficiencyRatio(jEfficiencyRatio.getText());
        getEnergyLabel().setDistributor(jDistributor.getText());
        getEnergyLabel().setDefrost(jDefrost.getSelectedItem().toString());
        getEnergyLabel().setRatedCurrent(jRatedCurrent.getText());
        getEnergyLabel().setBatchCode(jBatchCode.getText());
        getEnergyLabel().setRatedVoltage(jRatedVoltage.getSelectedItem().toString());
        getEnergyLabel().setFeature1(jFeature1.getSelectedItem().toString());
        getEnergyLabel().setFeature2(jFeature2.getSelectedItem().toString());
        getEnergyLabel().setLetterRating(jLetterRating.getSelectedItem().toString());
        getEnergyLabel().setRatedFrequency(jRatedFrequency.getSelectedItem().toString());
        getEnergyLabel().setCostPerKwh(jElectricityRate.getText());
        getEnergyLabel().setCostPerKwh2(jElectricityRate2.getText());
        getEnergyLabel().setBrand(jBrand.getText());
        getEnergyLabel().setModel(jModelNo.getText());
        getEnergyLabel().setCapacity(jCapacity.getText());
        getEnergyLabel().setYearOfEvaluation(jYearOfEvaluation.getText());
        getEnergyLabel().setOperatingCost(jOperatingCost.getText());
        getEnergyLabel().setManufacturer(jManufacturer.getText());
        getEnergyLabel().setCountry(jCountryOfOrigin.getText());
        getEnergyLabel().setAnnualConsumption(jAnnualConsumption.getText());
        getEnergyLabel().setType(jProductType.getSelectedItem().toString());

    }

    /**
     * Loads the label data into the data panel fields.
     */
    private void loadEnergyLabelData() {
        jDistributor.setText(getEnergyLabel().getDistributor());
        jDefrost.setSelectedItem(getEnergyLabel().getDefrost());
        jRatedCurrent.setText(getEnergyLabel().getRatedCurrent());
        jBatchCode.setText(getEnergyLabel().getBatchCode());
        jRatedVoltage.setSelectedItem(getEnergyLabel().getRatedVoltage());
        jFeature1.setSelectedItem(getEnergyLabel().getFeature1());
        jFeature2.setSelectedItem(getEnergyLabel().getFeature2());
        jLetterRating.setSelectedItem(getEnergyLabel().getLetterRating());
        jRatedFrequency.setSelectedItem(getEnergyLabel().getRatedFrequency());
        jElectricityRate.setText(getEnergyLabel().getCostPerKwh());
        jElectricityRate2.setText(getEnergyLabel().getCostPerKwh2());
        jBrand.setText(getEnergyLabel().getBrand());
        jModelNo.setText(getEnergyLabel().getModel());
        jCapacity.setText(getEnergyLabel().getCapacity());
        jYearOfEvaluation.setText(getEnergyLabel().getYearOfEvaluation());
        jOperatingCost.setText(getEnergyLabel().getOperatingCost());
        jManufacturer.setText(getEnergyLabel().getManufacturer());
        jCountryOfOrigin.setText(getEnergyLabel().getCountry());
        jAnnualConsumption.setText(getEnergyLabel().getAnnualConsumption());
        jProductType.setSelectedItem(getEnergyLabel().getType());
        jJobNumber.setText(getEnergyLabel().getJobNumber());
        jEfficiencyRatio.setText(getEnergyLabel().getEfficiencyRatio());

        enableFields();

        getEnergyLabel().setIsDirty(false);
    }

    /**
     * Enable/disable fields based on product type.
     *
     */
    private void enableFields() {
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            jDefrost.setEnabled(false);
            jDefrostLabel.setEnabled(false);
            jCapacity.setEnabled(false);
            jCapacityLabel.setEnabled(false);
            jEfficiencyRatio.setEnabled(true);
            jEfficiencyRatioLabel.setEnabled(true);
            jFeature1.setEnabled(false);
            jFeature2.setEnabled(false);
            jFeature1Label.setEnabled(false);
            jFeature2Label.setEnabled(false);
        } else {
            jDefrost.setEnabled(true);
            jDefrostLabel.setEnabled(true);
            jCapacity.setEnabled(true);
            jCapacityLabel.setEnabled(true);
            jEfficiencyRatio.setEnabled(false);
            jEfficiencyRatioLabel.setEnabled(false);
            jFeature1.setEnabled(true);
            jFeature2.setEnabled(true);
            jFeature1Label.setEnabled(true);
            jFeature2Label.setEnabled(true);
        }
    }

    /**
     * Updates the label panel fields with the energy label object.
     */
    public void updateLabelData() {
        loadEnergyLabelData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capacityGroup = new javax.swing.ButtonGroup();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jJobNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jProductType = new javax.swing.JComboBox();
        jDefrostLabel = new javax.swing.JLabel();
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
        jYearOfEvaluation = new javax.swing.JTextField();
        jDefrost = new javax.swing.JComboBox();
        jRatedVoltage = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jRatedFrequency = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jElectricityRate2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLetterRating = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jRatedCurrent = new javax.swing.JTextField();
        jFeature1Label = new javax.swing.JLabel();
        jFeature1 = new javax.swing.JComboBox();
        jFeature2Label = new javax.swing.JLabel();
        jFeature2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jBatchCode = new javax.swing.JTextField();
        jEfficiencyRatioLabel = new javax.swing.JLabel();
        jEfficiencyRatio = new javax.swing.JTextField();
        jCapacityLabel = new javax.swing.JLabel();
        jCapacity = new javax.swing.JTextField();

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setLabelFor(jJobNumber);
        jLabel17.setText("Job Number:");

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setLabelFor(jProductType);
        jLabel14.setText("Energy Efficiency Class:");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setLabelFor(jJobNumber);
        jLabel15.setText("Job Number:");

        jJobNumber.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jJobNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jJobNumberKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setLabelFor(jProductType);
        jLabel1.setText("Product Type:");

        jProductType.setEditable(true);
        jProductType.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jProductType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Basic Refrigerator", "Refrigerator", "Freezer", "Refrigerator-Freezer", "Room Air-conditioner", "Wine Chiller" }));
        jProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeActionPerformed(evt);
            }
        });

        jDefrostLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jDefrostLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jDefrostLabel.setText("Defrost:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Distributor:");

        jDistributor.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jDistributor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDistributorKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Manufacturer:");

        jManufacturer.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jManufacturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jManufacturerKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Brand:");

        jBrand.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jBrand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBrandKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Model:");

        jModelNo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jModelNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jModelNoKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Country of Origin:");

        jCountryOfOrigin.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCountryOfOrigin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCountryOfOriginKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Operating Cost ($/yr):");

        jOperatingCost.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jOperatingCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jOperatingCostKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Ann. Consumpt'n (kwh/yr):");

        jAnnualConsumption.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jAnnualConsumption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnnualConsumptionKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Electricity Rate 1 ($/kwh):");

        jElectricityRate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jElectricityRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jElectricityRateKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Year of Evaluation:");

        jYearOfEvaluation.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jYearOfEvaluation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jYearOfEvaluationKeyReleased(evt);
            }
        });

        jDefrost.setEditable(true);
        jDefrost.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jDefrost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Automatic", "Manual", " " }));
        jDefrost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDefrostActionPerformed(evt);
            }
        });

        jRatedVoltage.setEditable(true);
        jRatedVoltage.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jRatedVoltage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "110", "115", "120", "125", "220", "230", "240", "110/220", "115/230", "120/240" }));
        jRatedVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedVoltageActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Rated Voltage (V):");

        jRatedFrequency.setEditable(true);
        jRatedFrequency.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jRatedFrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "60", "50/60" }));
        jRatedFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatedFrequencyActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Rated Frequency (Hz):");

        jElectricityRate2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jElectricityRate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jElectricityRate2KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Electricity Rate 2 ($/kwh):");

        jLetterRating.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLetterRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F" }));
        jLetterRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLetterRatingActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Rated Current (A):");

        jRatedCurrent.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jRatedCurrent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRatedCurrentKeyReleased(evt);
            }
        });

        jFeature1Label.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jFeature1Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jFeature1Label.setText("Feature 1:");

        jFeature1.setEditable(true);
        jFeature1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jFeature1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        jFeature1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFeature1ActionPerformed(evt);
            }
        });

        jFeature2Label.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jFeature2Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jFeature2Label.setText("Feature 2:");

        jFeature2.setEditable(true);
        jFeature2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jFeature2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        jFeature2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFeature2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Batch code:");

        jBatchCode.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jBatchCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBatchCodeKeyReleased(evt);
            }
        });

        jEfficiencyRatioLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jEfficiencyRatioLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEfficiencyRatioLabel.setLabelFor(jJobNumber);
        jEfficiencyRatioLabel.setText("Efficiency Ratio:");

        jEfficiencyRatio.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jEfficiencyRatio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jEfficiencyRatioKeyReleased(evt);
            }
        });

        jCapacityLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jCapacityLabel.setText("Capacity (litres):");

        jCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCapacityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jEfficiencyRatioLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLetterRating, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jJobNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEfficiencyRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRatedCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRatedVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRatedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jBatchCode))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jYearOfEvaluation))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jElectricityRate))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jAnnualConsumption))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jOperatingCost))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jCountryOfOrigin))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jModelNo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jBrand))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jManufacturer))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jElectricityRate2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCapacityLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDefrostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFeature1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFeature2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFeature2, 0, 204, Short.MAX_VALUE)
                            .addComponent(jFeature1, 0, 204, Short.MAX_VALUE)
                            .addComponent(jDefrost, 0, 204, Short.MAX_VALUE)
                            .addComponent(jCapacity))))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jJobNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLetterRating, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEfficiencyRatioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEfficiencyRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDefrost, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDefrostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCapacityLabel)
                    .addComponent(jCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFeature1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFeature1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFeature2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFeature2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRatedVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRatedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRatedCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jElectricityRate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jElectricityRate2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearOfEvaluation, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeActionPerformed
        getEnergyLabel().setType((String) jProductType.getSelectedItem());
        updateLabelDataBasedOnProductType();
        enableFields();

        app.setDirty(true);
    }//GEN-LAST:event_jProductTypeActionPerformed

    private void jJobNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJobNumberKeyReleased
        getEnergyLabel().setJobNumber(jJobNumber.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jJobNumberKeyReleased

    private void jDistributorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDistributorKeyReleased
        getEnergyLabel().setDistributor(jDistributor.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jDistributorKeyReleased

    private void jManufacturerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jManufacturerKeyReleased
        getEnergyLabel().setManufacturer(jManufacturer.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jManufacturerKeyReleased

    private void jBrandKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBrandKeyReleased
        getEnergyLabel().setBrand(jBrand.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jBrandKeyReleased

    private void jModelNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jModelNoKeyReleased
        getEnergyLabel().setModel(jModelNo.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jModelNoKeyReleased

    private void jCountryOfOriginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCountryOfOriginKeyReleased
        getEnergyLabel().setCountry(jCountryOfOrigin.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCountryOfOriginKeyReleased

    private void jOperatingCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jOperatingCostKeyReleased
        getEnergyLabel().setOperatingCost(jOperatingCost.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jOperatingCostKeyReleased

    private void jAnnualConsumptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnnualConsumptionKeyReleased
        getEnergyLabel().setAnnualConsumption(jAnnualConsumption.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jAnnualConsumptionKeyReleased

    private void jElectricityRateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jElectricityRateKeyReleased
        getEnergyLabel().setCostPerKwh(jElectricityRate.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jElectricityRateKeyReleased

    private void jYearOfEvaluationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jYearOfEvaluationKeyReleased
        getEnergyLabel().setYearOfEvaluation(jYearOfEvaluation.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jYearOfEvaluationKeyReleased

    private void jDefrostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefrostActionPerformed
        getEnergyLabel().setDefrost((String) jDefrost.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jDefrostActionPerformed

    private void jRatedVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedVoltageActionPerformed
        getEnergyLabel().setRatedVoltage((String) jRatedVoltage.getSelectedItem());

        app.setDirty(true);
    }//GEN-LAST:event_jRatedVoltageActionPerformed

    private void jRatedFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedFrequencyActionPerformed
        getEnergyLabel().setRatedFrequency((String) jRatedFrequency.getSelectedItem());

        app.setDirty(true);
    }//GEN-LAST:event_jRatedFrequencyActionPerformed

    private void jElectricityRate2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jElectricityRate2KeyReleased
        getEnergyLabel().setCostPerKwh2(jElectricityRate2.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jElectricityRate2KeyReleased

    private void jLetterRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLetterRatingActionPerformed
        getEnergyLabel().setLetterRating((String) jLetterRating.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jLetterRatingActionPerformed

    private void jRatedCurrentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRatedCurrentKeyReleased
        getEnergyLabel().setRatedCurrent(jRatedCurrent.getText());

        app.setDirty(true);
    }//GEN-LAST:event_jRatedCurrentKeyReleased

    private void jFeature1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFeature1ActionPerformed
        getEnergyLabel().setFeature1((String) jFeature1.getSelectedItem());

        app.setDirty(true);
    }//GEN-LAST:event_jFeature1ActionPerformed

    private void jFeature2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFeature2ActionPerformed
        getEnergyLabel().setFeature2((String) jFeature2.getSelectedItem());

        app.setDirty(true);
    }//GEN-LAST:event_jFeature2ActionPerformed

    private void jBatchCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBatchCodeKeyReleased
        getEnergyLabel().setBatchCode(jBatchCode.getText());

        app.setDirty(true);
    }//GEN-LAST:event_jBatchCodeKeyReleased

    private void jEfficiencyRatioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEfficiencyRatioKeyReleased
        getEnergyLabel().setEfficiencyRatio(jEfficiencyRatio.getText());

        app.setDirty(true);
    }//GEN-LAST:event_jEfficiencyRatioKeyReleased

    private void jCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCapacityKeyReleased
        getEnergyLabel().setCapacity(jCapacity.getText());
        
        app.setDirty(true);
    }//GEN-LAST:event_jCapacityKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup capacityGroup;
    private javax.swing.JTextField jAnnualConsumption;
    private javax.swing.JTextField jBatchCode;
    private javax.swing.JTextField jBrand;
    private javax.swing.JTextField jCapacity;
    private javax.swing.JLabel jCapacityLabel;
    private javax.swing.JTextField jCountryOfOrigin;
    private javax.swing.JComboBox jDefrost;
    private javax.swing.JLabel jDefrostLabel;
    private javax.swing.JTextField jDistributor;
    private javax.swing.JTextField jEfficiencyRatio;
    private javax.swing.JLabel jEfficiencyRatioLabel;
    private javax.swing.JTextField jElectricityRate;
    private javax.swing.JTextField jElectricityRate2;
    private javax.swing.JComboBox jFeature1;
    private javax.swing.JLabel jFeature1Label;
    private javax.swing.JComboBox jFeature2;
    private javax.swing.JLabel jFeature2Label;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox jLetterRating;
    private javax.swing.JTextField jManufacturer;
    private javax.swing.JTextField jModelNo;
    private javax.swing.JTextField jOperatingCost;
    private javax.swing.JComboBox jProductType;
    private javax.swing.JTextField jRatedCurrent;
    private javax.swing.JComboBox jRatedFrequency;
    private javax.swing.JComboBox jRatedVoltage;
    private javax.swing.JTextField jYearOfEvaluation;
    // End of variables declaration//GEN-END:variables
}
