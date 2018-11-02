# General
- Put in the units for CEC, BEC etc in the respective field labels.
- JComboBox execBrokerCombobox = new JComboBox();
String[] obj = new String[5];
for (i = 0; i < 5; i++) {
   obj[i] = "Option" + (i+1);
}
AutoCompleteSupport.install(jexecBrokerCombobox, GlazedLists.eventListOf(obj));
- Create separate dialogs for "Room AC" and "Refrigeration" product?

# Packaging
- Setup izpack installer 
  * Use "maven copy resources" to copy the "LP.jar" and "LabelProperties" to staging
  * Fork from desbenn repo.
  * Ensure installer works on all Windows.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog.
- Impl option to load label file from external source in addition to the resources folder.