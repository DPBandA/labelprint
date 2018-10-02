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
package jm.com.dpbennett.labelprint;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author desbenn
 */
public class SVGApplication {

    private Document document;

    public static void main(String[] args) {
        // Create a new JFrame.
        JFrame f = new JFrame("Batik");
        SVGApplication app = new SVGApplication(f);

        // Add components to the frame.
        f.getContentPane().add(app.createComponents());

        // Display the frame.
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setSize(400, 400);
        f.setVisible(true);
    }

    // The frame.
    protected JFrame frame;

    // The "Load" button, which displays up a file chooser upon clicking.
    protected JButton button = new JButton("Load...");

    // The status label.
    protected JLabel label = new JLabel();

    // The SVG canvas.
    protected JSVGCanvas svgCanvas = new JSVGCanvas();

    public SVGApplication(JFrame f) {
        frame = f;
    }

    public JComponent createComponents() {
        // Create a panel and add the button, status label and the SVG canvas.
        final JPanel panel = new JPanel(new BorderLayout());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(button);
        p.add(label);

        // tk my addition
        svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        URL url = getClass().getClassLoader().
                getResource("images/EnergyLabel.svg");
        svgCanvas.setURI(url.toString());
        svgCanvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                // At this time the document is available...
                document = svgCanvas.getSVGDocument();
                //Element elem = document.getElementById("testId");
                //elem.
                System.out.println("Got doc after call to canvas.setURI()");
//                        System.out.println("Setting doc");
//                        getClass().getClassLoader().
//                                getResource("/images/LabelPrint.svg").getFile();
//                        URL url = getClass().getClassLoader().
//                                getResource("/images/LabelPrint.svg");
//                        svgCanvas.setURI(url.toString());
                // ...and the window object too.
                //window = canvas.getUpdateManager().
                //    getScriptingEnvironment().createWindow();
                // Registers the listeners on the document
                // just before the SVGLoad event is
                // dispatched.
                //registerListeners();
                // It is time to pack the frame.
                //frame.pack();
            }
        });

        panel.add("North", p);
        panel.add("Center", svgCanvas);

        // Set the button action.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Element element = document.getElementById("capacity");
            element.setTextContent("yesssss");
//                URL url = getClass().getClassLoader().
//                        getResource("images/EnergyLabel.svg");
//                svgCanvas.setURI(url.toString());
//                JFileChooser fc = new JFileChooser(".");
//                int choice = fc.showOpenDialog(panel);
//                if (choice == JFileChooser.APPROVE_OPTION) {
//                    File f = fc.getSelectedFile();
//                    try {
//                        svgCanvas.setURI(f.toURL().toString());
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
            }
        });

        // Set the JSVGCanvas listeners.
        svgCanvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {
            public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
                label.setText("Document Loading...");
            }

            public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
                label.setText("Document Loaded.");
            }
        });

        svgCanvas.addGVTTreeBuilderListener(new GVTTreeBuilderAdapter() {
            public void gvtBuildStarted(GVTTreeBuilderEvent e) {
                label.setText("Build Started...");
            }

            public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
                label.setText("Build Done.");
                frame.pack();
            }
        });

        svgCanvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {
            public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
                label.setText("Rendering Started...");
            }

            public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
                label.setText("");
            }
        });

        return panel;
    }
}
