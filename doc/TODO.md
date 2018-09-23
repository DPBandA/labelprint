
# General
- Display status in status bar when saving etc.
- It's still prompting to save when closing for label that is not edited. Fix!
- Impl reading system properties file from the resources folder only if the 
  file does not already exist in the "user's directory". Impl saving the file
  to this directory.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Using the save toolbar button results in the label data apparently getting 
  blanked. This happens when the labels opened then saved immediately. Fix!!
- Impl exporting to image with Batik Transcoder API.
- Use text to create header instead of importing an image.
- Make label data scrollable
- Add capacityLabel, capacityUnit to field and use it to append to the capacity instead of 
  using if statements.
- Impl testing database connection in options dialog.
- Impl hide/show label background colors and content.

# Packaging
- Impl splashscreen using existing dialog.
- Learn izpack installer (http://izpack.org/) and setup installer on Win 7