# Label Design
- Use text to create header instead of importing an image. Do a print and 
  see if the light green background has been fix since setting the image background
  to none.

# Updated standard implementation
- Update EnergyLabel class and LabelPanel with required fields (heatingCapicity etc)
- Update text and font in centre yellow semi-circle based on refrigerator or AC
  See the sample labels. Edit text to show heating/cooling and get the "normal"
  text attribute to change it for refrigerator product type etc.
- Disable and erase heating and cooling capacity fields when frige is selected.
- At Capacity, replace with Heating/Cooling Capacity for AC.
- Defrost type to be replace with Cooling Capacity for AC.
- Change labels "Capacity" and "Defrost" based on the product type.
- Read standard and see how to impl updateEnergyStars().                

# Packaging
- Setup izpack installer 
  * Use "maven copy resources" to copy the "LP.jar" and "LabelProperties" to staging
  * Fork from desbenn repo
  * Ensure installer works on all Windows.
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog.
- Impl option to load label file from external source in addition to the resources folder.

# Documentation
- Edit user manual

# Training
- Prepare training material