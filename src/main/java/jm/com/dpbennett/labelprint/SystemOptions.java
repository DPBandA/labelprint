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

import java.io.*;
import javax.swing.*;
import java.util.Properties;
import java.io.FileInputStream;
import jm.com.dpbennett.business.entity.fileutils.FileUtils;
import jm.com.dpbennett.business.entity.utils.Security;

/**
 * This class manages the system properties file.
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public final class SystemOptions {

    private String systemFile;
    private Properties props;
    private boolean noError;
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

    public SystemOptions() {
    }

    public SystemOptions(String systemFile) {
        this.systemFile = systemFile;
        props = new Properties();
        if (!readSystemData()) {

            JOptionPane.showMessageDialog(null,
                    "Error occured while loading system options",
                    "LabelPrint",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void setProperty(String name, String value) {
        props.setProperty(name, value);
    }

    public String getProperty(String name) {
        return props.getProperty(name);
    }

    public boolean readSystemData() {

        noError = true;
        FileInputStream fis;

        try {

            try {
                fis = new FileInputStream(FileUtils.getAbsoluteFilePath(systemFile, getClass()));
            } catch (FileNotFoundException e) {
                System.out.println("Properties file not found. Loading default...");
                fis = new FileInputStream(getClass().
                        getResource("/system/" + systemFile).getFile());
            }

            props.load(fis);

        } catch (IOException ex) {
            System.out.println(ex);
            noError = false;
        }

        return noError;
    }

    public boolean writeSystemData() {

        noError = true;

        try {

            props.store(new FileOutputStream(FileUtils.getAbsoluteFilePath(systemFile, getClass())), 
                    "LabelPrint System Options");

        } catch (IOException ex) {
            System.out.println(ex);
            noError = false;
        }

        return noError;

    }

    public String[][] getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(String[][] fields) {
        fieldsToSearch = fields;
    }

    public boolean isExportJPEG() {
        return Boolean.parseBoolean(props.getProperty("ExportJPEG"));
    }

    public boolean isExportGIF() {
        return Boolean.parseBoolean(props.getProperty("ExportGIF"));
    }

    public boolean isExportPNG() {
        return Boolean.parseBoolean(props.getProperty("ExportPNG"));
    }

    public boolean isExportPDF() {
        return Boolean.parseBoolean(props.getProperty("ExportPDF"));
    }

    public void setExportJPEG(boolean b) {
        props.setProperty("ExportJPEG", Boolean.toString(b));
    }

    public void setExportGIF(boolean b) {
        props.setProperty("ExportGIF", Boolean.toString(b));
    }

    public void setExportPNG(boolean b) {
        props.setProperty("ExportPNG", Boolean.toString(b));
    }

    public void setExportPDF(boolean b) {
        props.setProperty("ExportPDF", Boolean.toString(b));
    }

    public boolean isConnectToDatabase() {
        return Boolean.parseBoolean(props.getProperty("ConnectToDatabase"));
    }

    public void setConnectToDatabase(boolean b) {
        props.setProperty("ConnectToDatabase", Boolean.toString(b));
    }

    public String getConnectionPassword() {
        return (Security.decrypt(props.getProperty("ConnectionPassword")));
    }

    public void setConnectionPassword(String connectionPassword) {
        props.setProperty("ConnectionPassword", Security.encrypt(connectionPassword));
    }

    public static void main(String[] args) {
        SystemOptions systemOptions1 = new SystemOptions("LabelPrint.properties");
        System.out.print(systemOptions1.getProperty("DefaultFieldToSearch") + "\n");
    }

}
