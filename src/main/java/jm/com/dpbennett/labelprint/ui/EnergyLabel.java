/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author desbenn
 */
public class EnergyLabel {

    private Document document;
    private Element elt;

    public static void main(String[] args) {
        // Create a new JFrame.
        JFrame f = new JFrame("Batik");
        EnergyLabel app = new EnergyLabel(f);

        // Add components to the frame.
        f.getContentPane().add(app.init());

        // Display the frame.
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setSize(600, 600);
        f.setVisible(true);
    }

    // The frame.
    protected JFrame frame;

    // The "Load" button, which displays up a file chooser upon clicking.
    protected JButton button = new JButton("Update...");

    // The status label.
    protected JLabel label = new JLabel();

    // The SVG canvas.
    protected JSVGCanvas svgCanvas = new JSVGCanvas();

    public EnergyLabel(JFrame f) {
        frame = f;
    }

    public JComponent init() {
        // Create a panel and add the button, status label and the SVG canvas.
        final JPanel panel = new JPanel(new BorderLayout());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(button);
        p.add(label);

        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        URL url = getClass().getClassLoader().
                getResource("images/EnergyLabel.svg");
        svgCanvas.setURI(url.toString());
        svgCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                // At this time the document is available...
                document = svgCanvas.getSVGDocument();
                // tk
                System.out.println("Get elements for later update...");
                elt = document.getElementById("produtTypeId");

            }
        });

        panel.add("North", p);
        panel.add("Center", svgCanvas);

        // Set the button action.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // tk
                System.out.println("Product type: " + elt.getTextContent());
                updateElement("style", "fill:#000080;stroke-width:5.26458332");
                //elt.setTextContent("yes!!!!!");
            }
        });

        return panel;
    }

    public void updateElement(String name, String value) {
        svgCanvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(new Runnable() {
            @Override
            public void run() {
                //elt.setAttribute(name, value);
                //elt.setAttribute("x", "0.0"); //tk
                elt.setTextContent("yes!!!!!");
            }
        });
    }

}
