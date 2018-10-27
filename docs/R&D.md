JComboBox execBrokerCombobox = new JComboBox();
String[] obj = new String[5];
for (i = 0; i < 5; i++) {
   obj[i] = "Option" + (i+1);
}
AutoCompleteSupport.install(jexecBrokerCombobox, GlazedLists.eventListOf(obj));
