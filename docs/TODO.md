# General
- Try to make the star points sharper. Make each arm of the star more narrow and
don't make each half of a star overlap.
- Check with Kathy if label should show m3 or litres.
- Make sure that operating cost is properly calculated based on the 2 costs per kwh.
- Use Glazed list for autocomplete.
  * JComboBox execBrokerCombobox = new JComboBox();
   String[] obj = new String[5];
   for (i = 0; i < 5; i++) {
      obj[i] = "Option" + (i+1);
   }
AutoCompleteSupport.install(jexecBrokerCombobox, GlazedLists.eventListOf(obj));
- Consider making all the options such as note 1 fields in the EnergyLabel.
- Add feature to copy a label data to create a new label.
- Do not calculate star rating by default.
- Hide label view content when exporting to avoid flicker of label content.
- By the way, please ensure that all the fields in the top of the label (i.e. manufacturer, capacity, country of origin, etc.) have the colons beside them. When I looked back on the recent sample sent I realized that this was not uniformed throughout.
* Some of the labels don't have colons. Make the colons align vertically?
- Use text instead of image for label header cause header is printing 
  with pail green background.

# Deployment
- It seems the properties file cannot be found when the file path has space. Fix.
- Use InstallForge to setup installation and test installation on APPDEV.
- Copy system options file in the correct place when doing installation.
- Impl option to load label file from external source in addition to the resources folder.
- Setup website to introduce the application and give instructions for installation
(See Jekyll book).
- After deployment put the remaining items the github issue tracker.

# Documentation
- Take screen shots
- Edit user manual
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog. Display status info such as database
  connection status.
