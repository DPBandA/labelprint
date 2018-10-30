/*
LabelPrint - A general purpose energy label printing application 
Copyright (C) 2018  D P Bennett & Associates Limited

This program is free software: you can  redistribute it and/or modify
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
package jm.com.dpbennett.labelprint;

import javax.persistence.EntityManager;
import javax.swing.*;
import jm.com.dpbennett.business.entity.fileutils.PropertiesFile;
import jm.com.dpbennett.business.entity.utils.NumberUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;
import jm.com.dpbennett.business.entity.utils.Security;

/**
 * This class manages the options file.
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class Options extends PropertiesFile {

    private String[][] fieldsToSearch = {
        {
            "Brand", "brand"},
        {
            "Capacity", "capacity"},
        {
            "Country of origin", "country"},
        {
            "Defrost", "defrost"},
        {
            "Distributor", "distributor"},
        {
            "Job/Ref. Number", "jobNumber"},
        {
            "Label name", "labelName"},
        {
            "Manufacturer", "manufacturer"},
        {
            "Model", "model"},
        {
            "Operating cost", "operatingCost"}
    };

    /**
     * Constructs an Options object and attempts to load the properties from the
 properties file.
     * @param systemFile 
     */
    public Options(String systemFile) {
        
        super(systemFile);
        
        if (!load()) {

            JOptionPane.showMessageDialog(null,
                    "Error occured while loading system options",
                    "LabelPrint",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Writes the properties to the properties file with the given header.
     * @return 
     */
    @Override
    public boolean save() {
        return super.save("LabelPrint Properties");
    }

    public String[][] getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(String[][] fields) {
        fieldsToSearch = fields;
    }

    public boolean isExportJPEG() {
        return getBooleanProperty("ExportJPEG");
    }

    public boolean isExportGIF() {
        return getBooleanProperty("ExportGIF");
    }

    public boolean isExportPNG() {
        return getBooleanProperty("ExportPNG");
    }

    public boolean isExportPDF() {
        return getBooleanProperty("ExportPDF");
    }

    public void setExportJPEG(boolean b) {
        setProperty("ExportJPEG", Boolean.toString(b));
    }

    public void setExportGIF(boolean b) {
        setProperty("ExportGIF", Boolean.toString(b));
    }

    public void setExportPNG(boolean b) {
        setProperty("ExportPNG", Boolean.toString(b));
    }

    public void setExportPDF(boolean b) {
        setProperty("ExportPDF", Boolean.toString(b));
    }

    public boolean isConnectToDatabase() {
        return Boolean.parseBoolean(getProperty("ConnectToDatabase"));
    }

    public void setConnectToDatabase(boolean b) {
        setProperty("ConnectToDatabase", Boolean.toString(b));
    }

    public String getConnectionPassword() {
        return (Security.decrypt(getProperty("ConnectionPassword")));
    }

    public void setConnectionPassword(String connectionPassword) {
        setProperty("ConnectionPassword", Security.encrypt(connectionPassword));
    }
    
    public ReturnMessage validate(EntityManager em) {
        try {
            // Validate double values
            // Test chamber ambient test temperature
            if (!NumberUtils.validateDoubleValue(getProperty("TestChamberTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid test chamber ambient test temperature",
                        "The test chamber ambient test temperature is invalid", null);
            }
            // Average fresh food compartment operating temperature
            if (!NumberUtils.validateDoubleValue(getProperty("FreshFoodComptAvgTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid average fresh food compartment operating temperature",
                        "The average fresh food compartment operating temperature is invalid", null);
            }
            // Basic household refrigerator temperature
            if (!NumberUtils.validateDoubleValue(getProperty("BasicRefrigeratorTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid basic household refrigerator temperature",
                        "The basic household refrigerator temperature is invalid", null);
            }
            // All-refrigerator household refrigerator temperature.
            if (!NumberUtils.validateDoubleValue(getProperty("AllRefrigeratorTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid all-refrigerator household refrigerator temperature",
                        "The all-refrigerator household refrigerator temperature is invalid", null);
            }
            // Refrigerator-freezer household refrigerator temperature
            if (!NumberUtils.validateDoubleValue(getProperty("RefrigeratorFreezerTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid refrigerator-freezer household refrigerator temperature",
                        "The refrigerator-freezer household refrigerator temperature is invalid", null);
            }
            // Household freezer temperature
            if (!NumberUtils.validateDoubleValue(getProperty("FreezerTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid household freezer temperature",
                        "The household freezer temperature is invalid", null);
            }
            // Household wine chiller temperature
            if (!NumberUtils.validateDoubleValue(getProperty("WineChillerTemp")).
                    isSuccess()) {
                return new ReturnMessage(false, 
                        "Invalid household wine chiller temperature",
                        "The household wine chiller temperature is invalid", null);
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage();
    }

}
