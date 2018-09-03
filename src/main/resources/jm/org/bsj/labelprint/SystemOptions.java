package jm.org.bsj.labelprint;

import java.io.*;
import javax.swing.*;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * <p>Title: Refrigerator Energy Label Print</p>
 * <p>Description: Generates energy consumption labels for refrigerators tested by the Bureau of Standards (Jamaica).</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: Bureau of Standards (Jamaica)</p>
 * @author Desmond Bennett
 * @version 1.0
 */

public class SystemOptions {
    private String systemFile;
    //SystemData sd;
    private Properties props;
    private boolean noError;
    private String[][] fieldsToSearch = {
        {
            "Brand", "brand"}
            , {
                "Capacity", "capacity"}
            , {
                "Country of origin", "country"}
            , {
                "Defrost", "defrost"}
            , {
                "Distributor", "distributor"}
            , {
                "Job/Ref. Number", "jobNumber"}
            , {
                "Label name", "labelName"}
            , {
                "Manufacturer", "manufacturer"}
            , {
                "Model", "model"}
            , {
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
        //FileInputStream fileInputStream;
        noError = true;
        
        try {
            //fileInputStream = new FileInputStream(systemFile);
            props.load(new FileInputStream(systemFile));
            //fileInputStream.close();
        } catch (Exception ex) {
            noError = false;
        }
        
        return noError;
    }
    
    public boolean writeSystemData() {
        //FileOutputStream fileOutputStream;
        noError = true;
        
        try {
            //fileOutputStream = new FileOutputStream(systemFile);
            props.store(new FileOutputStream(systemFile), "LabelPrint System Options");
            //fileOutputStream.close();
        } catch (Exception ex) {
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
    
    
    public String [] getMostRecentSearchStrings() {
        return mostRecentSearchStrings;
    }
    
    public void setMostRecentSearchStrings(String [] mrss) {
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
    
    
    protected void finalize() throws Throwable {
        writeSystemData();
    }
    
    
    public static void main(String[] args) {
        SystemOptions systemOptions1 = new SystemOptions("System.properties");
        System.out.print(systemOptions1.getDefaultFieldToSearch() + "\n");
        //System.out.print(systemOptions1.getConnectionDriverName() + "\n");
        //System.out.print(systemOptions1.getLabelLogoFile() + "\n");
    }
    
}
