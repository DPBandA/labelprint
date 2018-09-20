
# General
- Update SVG label when option dialog closes
- Add system option for "refrigerator" and may be "ac" label file.
- Remove edit cancel button and data backup feature.
- Remove export to pdf feature if the fonts mapper issue is not solved.
- Get rid of progress bar dialog for datbase connection and font loading
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
- Remove "Cancel" button from LabelDataPanel
- Do not set label data as dirty until the data is actually edited.
- Use text to create header instead of importing an image.
- Make label data scrollable
- Add capacityUnit to field and use it to append to the capacity instead of 
  using if statements.
- Create and use enerbase as the default database.
- Impl testing database connection in options dialog.
- Remove pwd from properties file
- Impl hide/show label background colors and content.
- In all places get and use system option file opened by LabelPrintFrame.
  Reload the data where necessary.
- Change "Save" to "Edit" in svg panel and change them around.
- Impl notes 3 and 4 as 3 line notes with each line id referred to as "notex.y"
