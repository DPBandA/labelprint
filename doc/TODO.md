
# General
- Impl updating label panels when opened or new one is created instead of
  creating new panels each time.
- Remove edit cancel button and data backup feature.
  On Label Data tab change buttons to "View" and "Save"
  On Label View tab change buttons to "Edit" and "Save"
- Get rid of progress bar dialog for database connection and font loading
  and use status text at bottom of main window instead.
- Impl reading system properties file from the resources folder only if the 
  file does not already exist in the "user's directory". Impl saving the file
  to this directory.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Learn izpack installer (http://izpack.org/) and setup installer on Win 7
- Using the save toolbar button results in the label data apparently getting 
  blanked. This happens when the labels opened then saved immediately. Fix!!
- Impl exporting to image with Batik Transcoder API.
- Do not set label data as dirty until the data is actually edited.
- Use text to create header instead of importing an image.
- Make label data scrollable
- Add capacityUnit to field and use it to append to the capacity instead of 
  using if statements.
- Create and use enerbase as the default database.
- Impl testing database connection in options dialog.
- Impl hide/show label background colors and content.
- Use enerbase as the default database and not test.
