# Updated standard implementation
- Update EnergyLabel class and LabelPanel with required fields (heatingCapicity etc)
- Update text and font in centre yellow semi-circle based on refrigerator or AC
  See the sample labels. Edit text to show heating/cooling and get the "normal"
  text attribute to change it for refrigerator product type etc.
- At Capacity, replace with Heating/Cooling Capacity for AC.
- Defrost type to be replace with Cooling Capacity for AC.
- Change labels "Capacity" and "Defrost" based on the product type.
- Impl splitting stars in two halves so that the color of each half can be
  displayed in different colors.
- Make background colors system options: mainBackground: #FFDF00
  headerBackground: #008000, violationBackground: #008000
- Should the outer semicircle be displayed even if there are no starts.
- Accommodate displaying capacity and/or size?
- Impl display energy efficiency ratio in the case of room air conditioners? 
- For label top section specs see "Details of top section.png".  
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