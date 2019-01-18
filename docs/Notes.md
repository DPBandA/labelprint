# Notes on development
- Issue: Batik not rendering font color and opacity (https://issues.apache.org/jira/browse/BATIK-1112)
  Solution: search and remove -inkscape-font-specification attribute.
- Issue: Copying and pasting text in Inkscape causes error dialogs to be displayed.
  Solution: Do not copy and paste. Create text from scratch.