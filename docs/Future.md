# General
- Next inv # 000349
- Use Glazed list for autocomplete.
* JComboBox execBrokerCombobox = new JComboBox();
String[] obj = new String[5];
for (i = 0; i < 5; i++) {
   obj[i] = "Option" + (i+1);
}
AutoCompleteSupport.install(jexecBrokerCombobox, GlazedLists.eventListOf(obj));

- Re-add the product type detail/class combo box if required.
- Include the max/min energy for a product to qualify for 
  super efficiency. See labelling standard (JS 1 pt 21).
- Put units beside BEC/CEC.
- Use text to create header instead of importing an image. Do a print and 
  see if the light green background has been fix since setting the image background
  to none.

# Packaging
- Setup izpack installer 
  * Use "maven copy resources" to copy the "LP.jar" and "LabelProperties" to staging
  * Fork from desbenn repo.
  * Ensure installer works on all Windows.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog.
- Impl option to load label file from external source in addition to the resources folder.