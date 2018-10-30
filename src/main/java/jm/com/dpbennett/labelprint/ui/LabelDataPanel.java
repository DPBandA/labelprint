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
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.EnergyConsumptionAndEfficiency;
import jm.com.dpbennett.business.entity.EnergyLabel;
import jm.com.dpbennett.business.entity.swingutils.SwingUtils;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

public class LabelDataPanel extends javax.swing.JPanel {

    private Application app;
    private EnergyLabel energyLabel;

//    /**
//     * Creates new form LabelDataDialog.
//     */
//    public LabelDataPanel() {
//        initComponents();
//        init();
//    }

    /**
     * Creates new form LabelDataDialog
     *
     * @param app
     */
    public LabelDataPanel(Application app) {
        this.app = app;
        initComponents();
        init();
    }

    /**
     * Initialize the LabelDataPanel and create a new label.
     */
    private void init() {
        createLabel();
    }

    /**
     * Creates a new label using default values from system options.
     */
    public void createLabel() {
        setEnergyLabel(new EnergyLabel());
        getEnergyLabel().setType(app.getSystemOptions().getProperty("DefaultProductType"));
        if (getEnergyLabel().getType().equals("Refrigerator")
                || getEnergyLabel().getType().equals("Basic Refrigerator")) {
            getEnergyLabel().setDefrost("Automatic");
        }
        getEnergyLabel().setRatedVoltage(app.getSystemOptions().getProperty("DefaultRatedVoltage"));
        getEnergyLabel().setRatedFrequency(app.getSystemOptions().getProperty("DefaultRatedFrequency"));
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RoomACStandard"));
        } else {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RefrigeratorStandard"));
        }

        updateLabelDataBasedOnProductType();

        getEnergyLabel().setValidity("" + BusinessEntityUtils.getCurrentYear());
    }

    /**
     * Sets the model (data) of the product type detail/class combo box based on
     * the type of product. Also, the default value of the combo box is set
     * based on the defaults in system options along with other label data if
     * required.
     */
    private void updateLabelDataBasedOnProductType() {
        List<BusinessEntity> data = new ArrayList<>();

        // Setup jProductTypeDetailOrClass combo box model and selected item.
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            // Set model
            data.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                    "Room Air-conditioner"));
            jProductTypeDetailOrClass.setModel(SwingUtils.getBusinessEntityComboBoxModel(jProductTypeDetailOrClass,
                    (List<BusinessEntity>) data, 2, 1, 5));
            // Set item
            EnergyConsumptionAndEfficiency productTypeDetailOrClass
                    = EnergyConsumptionAndEfficiency.findById(app.getEntityManager(),
                            app.getSystemOptions().getLongProperty("DefaultProductClassId"));
            jProductTypeDetailOrClass.setSelectedItem(productTypeDetailOrClass);
        } else {
            // Set model
            data.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                    "Refrigerator"));
            data.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                    "Basic Refrigerator"));
            data.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                    "Freezer"));
            data.addAll(EnergyConsumptionAndEfficiency.findAllByProductType(app.getEntityManager(),
                    "Wine Chiller"));
            jProductTypeDetailOrClass.setModel(SwingUtils.getBusinessEntityComboBoxModel(jProductTypeDetailOrClass,
                    (List<BusinessEntity>) data, 4, 1, 5));
            // Set item
            EnergyConsumptionAndEfficiency productTypeDetailOrClass
                    = EnergyConsumptionAndEfficiency.findById(app.getEntityManager(),
                            app.getSystemOptions().getLongProperty("DefaultProductTypeDetailId"));
            jProductTypeDetailOrClass.setSelectedItem(productTypeDetailOrClass);
        }
        // Set standard
        if (getEnergyLabel().getType().equals("Room Air-conditioner")) {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RoomACStandard"));

        } else {
            getEnergyLabel().setStandard(app.getSystemOptions().getProperty("RefrigeratorStandard"));
        }
        // Set Cf, Cv. NB currently hard-coded but to be put in system options.
        // Verify if these settings are correct.
        switch (getEnergyLabel().getType()) {
            case "Basic Refrigerator":
                getEnergyLabel().setCf("200");
                jCf.setText( getEnergyLabel().getCf());
                
                getEnergyLabel().setCv("4.0");
                jCv.setText( getEnergyLabel().getCv());
                break;
            case "Refrigerator":
                getEnergyLabel().setCf("150");
                jCf.setText( getEnergyLabel().getCf());
                
                getEnergyLabel().setCv("8.8");
                jCv.setText( getEnergyLabel().getCv());
                break;
            case "Freezer":
                getEnergyLabel().setCf("150");
                jCf.setText( getEnergyLabel().getCf());
                
                getEnergyLabel().setCv("7.5");
                jCv.setText( getEnergyLabel().getCv());
                break;
            default:
                getEnergyLabel().setCf("0");
                jCf.setText( getEnergyLabel().getCf());
                
                getEnergyLabel().setCv("0.0");
                jCv.setText( getEnergyLabel().getCv());
                break;
        }
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

        getEnergyLabel().setDistributor(jDistributor.getText());
        getEnergyLabel().setDefrost(jDefrost.getSelectedItem().toString());
        getEnergyLabel().setRatedVoltage(jRatedVoltage.getSelectedItem().toString());
        getEnergyLabel().setRatedFrequency(jRatedFrequency.getSelectedItem().toString());
        getEnergyLabel().setCostPerKwh(jElectricityRate.getText());
        getEnergyLabel().setBrand(jBrand.getText());
        getEnergyLabel().setModel(jModelNo.getText());
        getEnergyLabel().setValidity(jValidity.getText());
        getEnergyLabel().setFreshFoodCompartmentVol(jFreshFoodComptVol.getText());
        getEnergyLabel().setFreezerCompartmentVol(jFreezerComptVol.getText());
        getEnergyLabel().setCapacity(jCapacity.getText());
        getEnergyLabel().setTotalAdjustedVol(jTotalAdjustedVol.getText());
        getEnergyLabel().setCoolingCapacity(jCoolingCapacity.getText());
        getEnergyLabel().setHeatingCapacity(jHeatingCapacity.getText());
        getEnergyLabel().setBEC(jBEC.getText());
        getEnergyLabel().setCEC(jCEC.getText());
        getEnergyLabel().setERF(jERF.getText());
        getEnergyLabel().setCf(jCf.getText());
        getEnergyLabel().setCv(jCv.getText());
        getEnergyLabel().setAEER(jAEER.getText());
        getEnergyLabel().setACOP(jACOP.getText());
        getEnergyLabel().setOperatingCost(jOperatingCost.getText());
        getEnergyLabel().setManufacturer(jManufacturer.getText());
        getEnergyLabel().setCountry(jCountryOfOrigin.getText());
        getEnergyLabel().setAnnualConsumption(jAnnualConsumption.getText());
        getEnergyLabel().setType(jProductType.getSelectedItem().toString());
        getEnergyLabel().setEnergyConsumptionAndEfficiency(
                (EnergyConsumptionAndEfficiency) jProductTypeDetailOrClass.getSelectedItem());
        getEnergyLabel().setJobNumber(jJobNumber.getText());
        getEnergyLabel().setStarRating(jStarRating.getText());

    }

    /**
     * Loads the label data into the data panel fields.
     */
    private void loadEnergyLabelData() {
        jDistributor.setText(getEnergyLabel().getDistributor());
        jDefrost.setSelectedItem(getEnergyLabel().getDefrost());
        jRatedVoltage.setSelectedItem(getEnergyLabel().getRatedVoltage());
        jRatedFrequency.setSelectedItem(getEnergyLabel().getRatedFrequency());
        jElectricityRate.setText(getEnergyLabel().getCostPerKwh());
        jBrand.setText(getEnergyLabel().getBrand());
        jModelNo.setText(getEnergyLabel().getModel());
        jValidity.setText(getEnergyLabel().getValidity());
        jFreshFoodComptVol.setText(getEnergyLabel().getFreshFoodCompartmentVol());
        jFreezerComptVol.setText(getEnergyLabel().getFreezerCompartmentVol());
        jCapacity.setText(getEnergyLabel().getCapacity());
        jTotalAdjustedVol.setText(getEnergyLabel().getTotalAdjustedVol());
        jCoolingCapacity.setText(getEnergyLabel().getCoolingCapacity());
        jHeatingCapacity.setText(getEnergyLabel().getHeatingCapacity());
        jBEC.setText(getEnergyLabel().getBEC());
        jCEC.setText(getEnergyLabel().getCEC());
        jERF.setText(getEnergyLabel().getERF());
        jCf.setText(getEnergyLabel().getCf());
        jCv.setText(getEnergyLabel().getCv());
        jAEER.setText(getEnergyLabel().getAEER());
        jACOP.setText(getEnergyLabel().getACOP());
        jOperatingCost.setText(getEnergyLabel().getOperatingCost());
        jManufacturer.setText(getEnergyLabel().getManufacturer());
        jCountryOfOrigin.setText(getEnergyLabel().getCountry());
        jAnnualConsumption.setText(getEnergyLabel().getAnnualConsumption());
        jProductType.setSelectedItem(getEnergyLabel().getType());
        jProductTypeDetailOrClass.setSelectedItem(getEnergyLabel().getEnergyConsumptionAndEfficiency());
        jJobNumber.setText(getEnergyLabel().getJobNumber());
        jStarRating.setText(getEnergyLabel().getStarRating());
        
        enableFields();
        enableCalcFields();

        getEnergyLabel().setIsDirty(false);
    }
    
    /**
     * Enable/disable fields based on product type.
     * 
     */
    private void enableFields() {
        
    }
    
    private void enableCalcFields() {
        
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

        jLabel14 = new javax.swing.JLabel();
        jStarRating = new javax.swing.JTextField();
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
        jDefrost = new javax.swing.JComboBox();
        jCoolingCapacity = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jHeatingCapacity = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jRatedVoltage = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jRatedFrequency = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jProductTypeDetailOrClass = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jAEER = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jACOP = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jFreshFoodComptVol = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jFreezerComptVol = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jCEC = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTotalAdjustedVol = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jCf = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jCv = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jBEC = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jERF = new javax.swing.JTextField();
        jCalcTotalAdjVol = new javax.swing.JCheckBox();
        jCalcStarRating = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setLabelFor(jProductType);
        jLabel14.setText("Star Rating:");

        jStarRating.setEditable(false);
        jStarRating.setBackground(new java.awt.Color(255, 223, 0));
        jStarRating.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jStarRating.setForeground(new java.awt.Color(0, 128, 0));
        jStarRating.setText("1.0");
        jStarRating.setDisabledTextColor(new java.awt.Color(0, 102, 102));
        jStarRating.setMaximumSize(new java.awt.Dimension(40, 2147483647));
        jStarRating.setPreferredSize(new java.awt.Dimension(40, 29));
        jStarRating.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jStarRatingKeyReleased(evt);
            }
        });

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

        jProductType.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jProductType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Basic Refrigerator", "Freezer", "Refrigerator", "Room Air-conditioner", "Wine Chiller" }));
        jProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Volumetric Capacity (cu. m):");

        jCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCapacityKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Defrost:");

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
        jLabel7.setText("Model No.:");

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
        jLabel10.setText("Anual Consumpt'n (kwh/yr):");

        jAnnualConsumption.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jAnnualConsumption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnnualConsumptionKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Electricity Rate ($/kwh):");

        jElectricityRate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jElectricityRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jElectricityRateKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Validity (year):");

        jValidity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jValidity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jValidityKeyReleased(evt);
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

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Cooling Capacity (kW):");

        jHeatingCapacity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jHeatingCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHeatingCapacityKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Heating Capacity (kW):");

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

        jProductTypeDetailOrClass.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jProductTypeDetailOrClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProductTypeDetailOrClassActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setLabelFor(jProductType);
        jLabel20.setText("Product Type Detail/Class:");

        jAEER.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jAEER.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAEERKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Ann. Ener. Eff. Ratio (AEER):");

        jACOP.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jACOP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jACOPKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Ann. Coeff. of Perf. (ACOP):");

        jFreshFoodComptVol.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jFreshFoodComptVol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFreshFoodComptVolKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setText("F'sh F'd Comp't Vol. (cu. m):");

        jFreezerComptVol.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jFreezerComptVol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFreezerComptVolKeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setText("Fr'zer Comp't Vol. (cu. m):");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Comp. Ener. Cons'n. (CEC):");

        jCEC.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCEC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCECKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Total Adjusted Vol. (cu. m):");

        jTotalAdjustedVol.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTotalAdjustedVol.setPreferredSize(new java.awt.Dimension(40, 27));
        jTotalAdjustedVol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTotalAdjustedVolKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Fixed Allowance Factor (Cf):");

        jCf.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCfKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Var'ble Allow'ce Factor (Cv):");

        jCv.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCvKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Base Ener. Consump. (BEC):");

        jBEC.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jBEC.setMaximumSize(new java.awt.Dimension(40, 2147483647));
        jBEC.setPreferredSize(new java.awt.Dimension(40, 27));
        jBEC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBECKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Ener.  Reduct. Factor (ERF):");

        jERF.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jERF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jERFKeyReleased(evt);
            }
        });

        jCalcTotalAdjVol.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jCalcTotalAdjVol.setText("calculate");

        jCalcStarRating.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jCalcStarRating.setSelected(true);
        jCalcStarRating.setText("calculate");

        jCheckBox1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jCheckBox1.setText("calculate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProductTypeDetailOrClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFreshFoodComptVol)
                            .addComponent(jFreezerComptVol)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jACOP))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jAEER))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jCEC))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jCf))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jCv))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jERF))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTotalAdjustedVol, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCalcTotalAdjVol)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE))
                            .addComponent(jCoolingCapacity)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jStarRating, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCalcStarRating)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jJobNumber)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBEC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE))
                            .addComponent(jHeatingCapacity))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStarRating, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCalcStarRating))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jProductTypeDetailOrClass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFreshFoodComptVol, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFreezerComptVol, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTotalAdjustedVol, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCalcTotalAdjVol))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCoolingCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHeatingCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBEC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCEC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jERF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCv, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAEER, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jACOP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeActionPerformed
        getEnergyLabel().setType((String) jProductType.getSelectedItem());
        updateLabelDataBasedOnProductType();

        app.setDirty(true);
    }//GEN-LAST:event_jProductTypeActionPerformed

    private void jStarRatingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jStarRatingKeyReleased
        getEnergyLabel().setStarRating(jStarRating.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jStarRatingKeyReleased

    private void jJobNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJobNumberKeyReleased
        getEnergyLabel().setJobNumber(jJobNumber.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jJobNumberKeyReleased

    private void jCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCapacityKeyReleased
        getEnergyLabel().setCapacity(jCapacity.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCapacityKeyReleased

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

    private void jValidityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jValidityKeyReleased
        getEnergyLabel().setValidity(jValidity.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jValidityKeyReleased

    private void jDefrostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefrostActionPerformed
        getEnergyLabel().setDefrost((String) jDefrost.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jDefrostActionPerformed

    private void jCoolingCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCoolingCapacityKeyReleased
        getEnergyLabel().setCoolingCapacity((String) jCoolingCapacity.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCoolingCapacityKeyReleased

    private void jHeatingCapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHeatingCapacityKeyReleased
        getEnergyLabel().setHeatingCapacity((String) jHeatingCapacity.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jHeatingCapacityKeyReleased

    private void jRatedVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedVoltageActionPerformed
        getEnergyLabel().setRatedVoltage((String) jRatedVoltage.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jRatedVoltageActionPerformed

    private void jRatedFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatedFrequencyActionPerformed
        getEnergyLabel().setRatedFrequency((String) jRatedFrequency.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jRatedFrequencyActionPerformed

    private void jProductTypeDetailOrClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProductTypeDetailOrClassActionPerformed
        getEnergyLabel().setEnergyConsumptionAndEfficiency(
                (EnergyConsumptionAndEfficiency) jProductTypeDetailOrClass.getSelectedItem());
        app.setDirty(true);
    }//GEN-LAST:event_jProductTypeDetailOrClassActionPerformed

    private void jAEERKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAEERKeyReleased
        getEnergyLabel().setAEER(jAEER.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jAEERKeyReleased

    private void jACOPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jACOPKeyReleased
        getEnergyLabel().setACOP(jACOP.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jACOPKeyReleased

    private void jFreshFoodComptVolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFreshFoodComptVolKeyReleased
        getEnergyLabel().setFreshFoodCompartmentVol(jFreshFoodComptVol.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jFreshFoodComptVolKeyReleased

    private void jFreezerComptVolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFreezerComptVolKeyReleased
        getEnergyLabel().setFreezerCompartmentVol(jFreezerComptVol.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jFreezerComptVolKeyReleased

    private void jCECKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCECKeyReleased
        getEnergyLabel().setCEC(jCEC.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCECKeyReleased

    private void jTotalAdjustedVolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTotalAdjustedVolKeyReleased
        getEnergyLabel().setTotalAdjustedVol(jTotalAdjustedVol.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jTotalAdjustedVolKeyReleased

    private void jCfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCfKeyReleased
        getEnergyLabel().setCf(jCf.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCfKeyReleased

    private void jCvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCvKeyReleased
        getEnergyLabel().setCv(jCv.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jCvKeyReleased

    private void jBECKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBECKeyReleased
        getEnergyLabel().setBEC(jBEC.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jBECKeyReleased

    private void jERFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jERFKeyReleased
        getEnergyLabel().setERF(jERF.getText());
        app.setDirty(true);
    }//GEN-LAST:event_jERFKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jACOP;
    private javax.swing.JTextField jAEER;
    private javax.swing.JTextField jAnnualConsumption;
    private javax.swing.JTextField jBEC;
    private javax.swing.JTextField jBrand;
    private javax.swing.JTextField jCEC;
    private javax.swing.JCheckBox jCalcStarRating;
    private javax.swing.JCheckBox jCalcTotalAdjVol;
    private javax.swing.JTextField jCapacity;
    private javax.swing.JTextField jCf;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JTextField jCoolingCapacity;
    private javax.swing.JTextField jCountryOfOrigin;
    private javax.swing.JTextField jCv;
    private javax.swing.JComboBox jDefrost;
    private javax.swing.JTextField jDistributor;
    private javax.swing.JTextField jERF;
    private javax.swing.JTextField jElectricityRate;
    private javax.swing.JTextField jFreezerComptVol;
    private javax.swing.JTextField jFreshFoodComptVol;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jManufacturer;
    private javax.swing.JTextField jModelNo;
    private javax.swing.JTextField jOperatingCost;
    private javax.swing.JComboBox jProductType;
    private javax.swing.JComboBox jProductTypeDetailOrClass;
    private javax.swing.JComboBox jRatedFrequency;
    private javax.swing.JComboBox jRatedVoltage;
    private javax.swing.JTextField jStarRating;
    private javax.swing.JTextField jTotalAdjustedVol;
    private javax.swing.JTextField jValidity;
    // End of variables declaration//GEN-END:variables
}
