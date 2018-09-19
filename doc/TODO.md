
# General
- Impl setting the text elements based on element id and energy label data.
- Impl label update when data is updated.
- Remove edit cancel button and data backup feature.
- Load images from resource. (org folder jm.org.bsj.labelprint)
- Put all images in resources/images
- Load LabelPrint.properties file from resource.
- Remove export to pdf feature if the fonts mapper issue is not solved.
- Get rid of progress bar dialog for datbase connection and font loading
  and use status text at bottom of main window instead.
- Impl reading system properties file from the resources folder only if the 
  file does not already exist in the "user's directory". Impl saving the file
  to this directory.
- Edit LabelPrintIcon.png to create a different image.
- Learn izpack installer (http://izpack.org/) and setup installer on Win 7
- Using the save toolbar button results in the label data apparently getting 
  blanked. This happens when the labels opened then saved immediately. Fix!!
- Impl exporting to image with Batik Transcoder API.
- Remove "Cancel" button from LabelDataPanel
- Do not set label data as dirty until the data is actually edited.
- Use text to create header instead of importing an image.