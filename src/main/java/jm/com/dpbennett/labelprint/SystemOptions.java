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

public class SystemOptions {

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
    public String[] mostRecentSearchStrings = {
        "", "", "", "", "",
        "", "", "", "", ""};

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

    public boolean readSystemData() {

        noError = true;
        FileInputStream fis;

        try {

            try {
                fis = new FileInputStream(systemFile);
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

            props.store(new FileOutputStream(systemFile), "LabelPrint System Options");

        } catch (IOException ex) {
            System.out.println(ex);
            noError = false;
        }

        return noError;

    }

    public String getPersistenceManagerFactoryClass() {
        return props.getProperty("PersistenceManagerFactoryClass");
    }

    public void setPersistenceManagerFactoryClass(String pmfc) {
        props.setProperty("PersistenceManagerFactoryClass", pmfc);
    }

    public String getConnectionDriverName() {
        return props.getProperty("ConnectionDriverName");
    }

    public void setConnectionDriverName(String cdn) {
        props.setProperty("ConnectionDriverName", cdn);
    }

    public String getConnectionURL() {
        return props.getProperty("ConnectionURL");
    }

    public void setConnectionURL(String cu) {
        props.setProperty("ConnectionURL", cu);
    }

    public String getDatabaseTable() {
        return props.getProperty("DatabaseTable");
    }

    public void setDatabaseTable(String dt) {
        props.setProperty("DatabaseTable", dt);
    }

    public String getConnectionUserName() {
        return props.getProperty("ConnectionUserName");
    }

    public void setConnectionUserName(String cun) {
        props.setProperty("ConnectionUserName", cun);
    }

    public String getConnectionPassword() {
        return props.getProperty("ConnectionPassword");
    }

    public void setConnectionPassword(String cp) {
        props.setProperty("ConnectionPassword", cp);
    }

    public String getDefaultFieldToSearch() {
        return props.getProperty("DefaultFieldToSearch");
    }

    public void setDefaultFieldToSearch(String dfts) {
        props.setProperty("DefaultFieldToSearch", dfts);
    }

    public String getDefaultFieldToDisplay() {
        return props.getProperty("DefaultFieldToDisplay");
    }

    public void setDefaultFieldToDisplay(String dftd) {
        props.setProperty("DefaultFieldToDisplay", dftd);
    }

    public String getLabelLogoFile() {
        return props.getProperty("LabelLogoFile");
    }

    public void setLabelLogoFile(String file) {
        props.setProperty("LabelLogoFile", file);
    }

    public String getMostRecentSearchString() {
        return props.getProperty("MostRecentSearchString");
    }

    public void setMostRecentSearchString(String mrss) {
        props.setProperty("MostRecentSearchString", mrss);
    }

    public String[] getMostRecentSearchStrings() {
        return mostRecentSearchStrings;
    }

    public void setMostRecentSearchStrings(String[] mrss) {
        mostRecentSearchStrings = mrss;
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

    public String getFontsDirectory() {
        return props.getProperty("FontsDirectory");
    }

    public void setFontsDirectory(String s) {
        props.setProperty("FontsDirectory", s);
    }

    public String getHeadingImage() {
        return props.getProperty("HeadingImage");
    }

    public void setHeadingImage(String s) {
        props.setProperty("HeadingImage", s);
    }

    public String getStandard() {
        return props.getProperty("Standard");
    }

    public void setStandard(String s) {
        props.setProperty("Standard", s);
    }

    public boolean isConnectToDatabase() {
        return Boolean.parseBoolean(props.getProperty("ConnectToDatabase"));
    }

    public void setConnectToDatabase(boolean b) {
        props.setProperty("ConnectToDatabase", Boolean.toString(b));
    }

    // Note properties
    public String getNote1_1() {
        return props.getProperty("Note1");
    }

    public void setNote1(String s) {
        props.setProperty("Note1", s);
    }

    public String getNote2() {
        return props.getProperty("Note2");
    }

    public void setNote2(String s) {
        props.setProperty("Note2", s);
    }

    public String getNote3() {
        return props.getProperty("Note3");
    }

    public void setNote3(String s) {
        props.setProperty("Note3", s);
    }
    
    public String getNote4() {
        return props.getProperty("Note4");
    }

    public void setNote4(String s) {
        props.setProperty("Note4", s);
    }

    @Override
    protected void finalize() throws Throwable {
        writeSystemData();
    }

    public static void main(String[] args) {
        SystemOptions systemOptions1 = new SystemOptions("LabelPrint.properties");
        System.out.print(systemOptions1.getDefaultFieldToSearch() + "\n");
        //System.out.print(systemOptions1.getConnectionDriverName() + "\n");
        //System.out.print(systemOptions1.getLabelLogoFile() + "\n");
    }

}
