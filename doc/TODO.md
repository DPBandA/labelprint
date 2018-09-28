
# Updated standard implementation
- Use capacityLabel and capacityUnit to field and change their values based on 
  the type of product.
- At Capacity, replace with Heating Capacity for AC.
- Defrost type to be deleted and replace with Cooling Capacity fpr AC.
- Change label "Type", "Capacity" and "Defrost" based on the product type.
- Impl splitting stars in two halves so that the color of each half can be
  displayed in different colors.
- Make background colors system options: mainBackground: #FFDF00
  headerBackground: #008000, violationBackground: #008000
- Should the outer semicircle be displayed even if there are no starts.
- Accommodate displaying capcity and/or size?
- Impl display energy efficiency ratio in the case of room air conditioners? 
- For label top section specs see "Details of top section.png".                 

# Packaging
- Edit LabelPrintIcon.png to create a different image. Try a using letter e
  in green on a yellow background.
- Impl splashscreen using existing dialog.
- Impl option to load label file from external source in addition to the resources folder.
- Learn izpack installer (http://izpack.org/) and setup installer on Win 7

# Documentation
- Edit user manual

# Training
- Prepare training material