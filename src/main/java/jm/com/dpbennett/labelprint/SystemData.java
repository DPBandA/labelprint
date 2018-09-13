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
