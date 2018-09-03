package jm.org.bsj.labelprint;

import java.io.*;

/**
 * Title:        Refrigerator Energy Label Print
 * Description:  Generates energy consumption labels for refrigerators tested by the Bureau of Standards (Jamaica).
 * Copyright:    Copyright (c) 2001
 * Company:      Bureau of Standards (Jamaica)
 * @author Desmond Bennett
 * @version 1.0
 */

public class SystemData implements Serializable{
  public String DatabaseServer = "localhost"; // op file
  public String Database = "ElectDbaseTst"; // op file
  public String DatabaseTable = "tblProducts"; // op file
  public String defaultFieldToSearch = "Model";
  public String defaultFieldToDisplay = "EnergyLabel";
  public String labelLogoFile = "JBSLogoInGIFIcon.gif";
  String [][] fieldsToSearch =
      {{"Brand", "Brand"},
      {"Capacity", "Capacity"},
      {"Country of origin", "Country"},
      {"Defrost", "Defrost"},
      {"Distributor", "Distributor"},
      {"Job/Ref. Number", "JobNumber"},
      {"Label name", "LabelName"} ,
      {"Manufacturer", "Manufacturer"},
      {"Model", "Model"},
      {"Operating cost", "OperatingCost"}
      };
  public String [] mostRecentSearchStrings = {"", "", "", "", "",
                                              "", "", "", "", ""};
  public String mostRecentSearchString = "";
  public SystemData() {
  }
}
