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

public class LabelData implements Serializable {
  // Refrigerator Information
  public String ID;
  public String JobNumber;
  public String LabelName;
  public String Type;
  public String Capacity;
  public String Defrost;
  public String Distributor;
  // General Information
  public String Manufacturer;
  public String Brand;
  public String Model;
  public String Country;
  // Costing information
  public String OperatingCost;
  public String AnnualConsumption;
  public String CostPerKwh;
  // Legal Information
  public String Validity;
  public String Standard;

  public LabelData() {
   ID = " ";
   JobNumber = " ";
   LabelName = " ";
   Type = " ";
   Capacity = " ";
   Defrost = " ";
   Distributor = " ";
   Manufacturer = " ";
   Brand = " ";
   Model = " ";
   Country = " ";
   OperatingCost = " ";
   AnnualConsumption = " ";
   CostPerKwh = " ";
   Validity = " ";
   Standard = " ";
  }
}