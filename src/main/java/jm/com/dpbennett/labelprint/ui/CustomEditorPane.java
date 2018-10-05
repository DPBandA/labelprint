/*
LabelPrint - A general purpose energy label printing application
Copyright (C) 2018  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.labelprint.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;

/**
 * This creates a custom editor pane that displays a hyperlink.
 * @author Desmond Bennett
 */
public class CustomEditorPane extends JEditorPane {

    public CustomEditorPane(String htmlBody) {
        super("text/html", "<html><body style=\"" + getStyle() + "\">" + htmlBody + "</body></html>");
        init();
    }
    
    /**
     * Helper method for initializing the class.
     */
    private void init() {
        addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                try {
                    java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (URISyntaxException | IOException ex) {
                    System.out.println(ex);
                }
            }
        });
        setEditable(false);
        setBorder(null);
    }
    

    /**
     * This gets the style for the hyperlink by using a JLabel as the basis.
     * @return 
     */
    static String getStyle() {
       
        JLabel label = new JLabel();
        Font font = label.getFont();
        Color color = label.getBackground();        
        String style = "font-family:" + font.getFamily() + ";";
        
        style = style + "font-weight:" + (font.isBold() ? "bold" : "normal") + ";";
        style = style + "font-size:" + font.getSize() + "pt;";
        style = style + "background-color: rgb(" + color.getRed() + "," + 
                color.getGreen() + "," + color.getBlue() + ");";
        
        return style;
    }
}
