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

import javax.swing.*;
import jm.com.dpbennett.business.entity.fileutils.PropertiesFile;
import jm.com.dpbennett.business.entity.utils.Security;

/**
 * This class manages the system properties file.
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

    public Options(String systemFile) {
        
        super(systemFile);
        
        if (!read()) {

            JOptionPane.showMessageDialog(null,
                    "Error occured while loading system options",
                    "LabelPrint",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public boolean write() {
        return super.writeSystemData("LabelPrint Properties");
    }

    public String[][] getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(String[][] fields) {
        fieldsToSearch = fields;
    }

    public boolean isExportJPEG() {
        return Boolean.parseBoolean(getProperty("ExportJPEG"));
    }

    public boolean isExportGIF() {
        return Boolean.parseBoolean(getProperty("ExportGIF"));
    }

    public boolean isExportPNG() {
        return Boolean.parseBoolean(getProperty("ExportPNG"));
    }

    public boolean isExportPDF() {
        return Boolean.parseBoolean(getProperty("ExportPDF"));
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

}
