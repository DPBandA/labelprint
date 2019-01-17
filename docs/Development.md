# General
- Add feature to add "Sample" watermark. Add code to hide or show it.
- Impl feature turn off background colors.
- If necessary, get the radius dimensions from the standard and make corner of label more rounded.
- Use text instead of image for label header cause header is printing 
  with pail green background.
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
- The label image is still getting shifted to the lower right corner. Fix!
- Add ConnectionTimeout=3000 as an option in the options dialog.
- Place super energy efficient stars on label based on maximum allowed energy
  * Appliances or other energy consuming products in the Super Energy Efficient 
  category shall have an energy consumption that is at least 15 % less than the 
  maximum energy efficiency quoted in Table 5A of JS 178 and Table 3B of JS 179.
- Check out the exact color code on the Jamaican flag.
- Try to make the star points sharper. Make each arm of the star more narrow and
don't make each half of a star overlap.
- Check with Kathy if label should show m3 or litres.
- Please take a look at the disclaimer in 4.1 (1) of JS1:Part 21. Can all that 
  sentence be put on the label (except reference to  LPG rates)? We have only been 
  using part of the disclaimer.

# Deployment
- Use InstallForge to setup installation and test installation on APPDEV.
- Impl option to load label file from external source in addition to the resources folder.
- Setup website to introduce the application and give instructions for installation
(See Jekyll book).

# Documentation
- Take screen shots
- Edit user manual
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog.
