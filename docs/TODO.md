# General
- Create label data and SVG panels but do not add them to the tab when the system
  just starts.
- Do not allow blank field when doing search. Change this in the search help text if 
  needed.

# Label Design
- Check that all circles are aligned the svg file.
- Finish adding and setting ids for all stars.
- Add extended label section to ExtendedEnergyLabel
* Lock the layer containing the main section of the label.  
* Add circles on a new layer and hide the lower sections of the circles to
    semi-circles.

# Updated standard implementation
- At Capacity, replace with Heating Capacity for AC.
- Defrost type to be replace with Cooling Capacity for AC.
- Change labels "Capacity" and "Defrost" based on the product type.
- Impl splitting stars in two halves so that the color of each half can be
  displayed in different colors.
- Make background colors system options: mainBackground: #FFDF00
  headerBackground: #008000, violationBackground: #008000
- Should the outer semicircle be displayed even if there are no starts.
- Accommodate displaying capcity and/or size?
- Impl display energy efficiency ratio in the case of room air conditioners? 
- For label top section specs see "Details of top section.png".                 

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