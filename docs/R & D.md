- Are 3 units of the model actually tested?
- Should tables 12.5(a) and 12.5(b) be 6A and 6B respectively.
- Verify if the values set for Cf and Cv are valid based on the product type.
JComboBox execBrokerCombobox = new JComboBox();
String[] obj = new String[5];
for (i = 0; i < 5; i++) {
   obj[i] = "Option" + (i+1);
}
AutoCompleteSupport.install(jexecBrokerCombobox, GlazedLists.eventListOf(obj));
