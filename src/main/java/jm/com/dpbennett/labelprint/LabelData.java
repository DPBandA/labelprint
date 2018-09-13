/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
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
package jm.com.dpbennett.labelprint;

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