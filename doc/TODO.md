
# General
- Handle key released instead of key typed for label data panel.
- Modified is not removed from title when label is saved from label data tab.
- Make proper use of isDirty in EnergyLabel and remove "dirty" from everywhere else.
- Get rid of progress bar dialog for database connection and font loading
  and use status text at bottom of main window instead.
- Impl reading system properties file from the resources folder only if the 
  file does not already exist in the "user's directory". Impl saving the file
  to this directory.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Using the save toolbar button results in the label data apparently getting 
  blanked. This happens when the labels opened then saved immediately. Fix!!
- Impl exporting to image with Batik Transcoder API.
- Do not set label data as dirty until the data is actually edited.
- Use text to create header instead of importing an image.
- Make label data scrollable
- Add capacityLabel, capacityUnit to field and use it to append to the capacity instead of 
  using if statements.
- Create and use enerbase as the default database.
- Impl testing database connection in options dialog.
- Impl hide/show label background colors and content.
- Use enerbase as the default database and not test.

# Packaging
- Learn izpack installer (http://izpack.org/) and setup installer on Win 7