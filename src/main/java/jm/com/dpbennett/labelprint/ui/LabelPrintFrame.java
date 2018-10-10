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

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import jm.com.dpbennett.labelprint.SystemOptions;
import jm.com.dpbennett.business.entity.EnergyLabel;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
public class LabelPrintFrame extends javax.swing.JFrame implements Runnable {

    private SystemOptions sysOptions;
    private EntityManagerFactory emf;
    private LabelDataPanel labelDataPanel;
    private SVGLabelPanel labelPanel;

    /**
     * Creates new form LabelPrintFrame
     */
    public LabelPrintFrame() {

        initComponents();
        init();
    }

    private void init() {

        Toolkit toolKit = Toolkit.getDefaultToolkit();
        setIconImage(toolKit.createImage(getClass().getResource("/images/LabelPrintIcon.png")));

        sysOptions = new SystemOptions("LabelPrint.properties");
        enableMenuItems(false);

        doSetup();
    }

    public SystemOptions getSystemOptions() {
        return sysOptions;
    }

    public List<EnergyLabel> findLabels(String searchField,
            String searchPattern) {

        List<EnergyLabel> labelsFound;

        String query = "SELECT r FROM EnergyLabel r WHERE r." + searchField + " LIKE '%" + searchPattern + "%'";

        try {
            labelsFound = (List<EnergyLabel>) getEntityManager().createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this,
                    "An error occured while searching for labels.\n"
                    + "This could occur because you do not have a database connection.\n"
                    + "Try to connect to a database in the options dialog and try again.",
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE);
            labelsFound = new ArrayList<>();
        }

        return labelsFound;
    }

    public final void doSetup() {

        Thread printThread = new Thread() {

            @Override
            public void run() {
                setStatus("Setting up database connection...");
                if (!setupDatabaseConnection()
                        && getSystemOptions().isConnectToDatabase()) {
                    setStatus("A database connection error occurred!");
                } else {
                    setStatus("Ready...");
                }

                createNewLabel();
            }
        };
        printThread.start();
    }

    public void setStatus(String status) {
        jStatusLabel.setText(status);
    }

    public EnergyLabel findLabel(Long id) {
        return getEntityManager().find(EnergyLabel.class, id);
    }

    public boolean isLabelNameUsed(String labelName) {
        try {

            EnergyLabel labelData
                    = (EnergyLabel) getEntityManager().createNamedQuery("EnergyLabel.findByLabelName").setParameter("labelName", labelName).getSingleResult();
            return labelData != null;

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return false;

    }

    public void setDirty(boolean flag) {
        getLabelDataPanel().getEnergyLabel().setIsDirty(flag);

        if (getLabelDataPanel().getEnergyLabel().getIsDirty()) {
            this.setTitle("LabelPrint - "
                    + getLabelDataPanel().getEnergyLabel().getLabelName() + " - MODIFIED");
        } else {
            this.setTitle("LabelPrint - "
                    + getLabelDataPanel().getEnergyLabel().getLabelName());
        }
    }

    public boolean isDirty() {
        return getLabelDataPanel().getEnergyLabel().getIsDirty();
    }

    public final void enableMenuItems(boolean flag) {
        jMenuFileSave.setEnabled(flag);
        SaveLabel.setEnabled(flag);
        jMenuFileExport.setEnabled(flag);
        jMenuFilePrint.setEnabled(flag);
        jMenuEditLabel.setEnabled(flag);
        jMenuFileClose.setEnabled(flag);
        jCheckBoxMenuViewGreenBackground.setEnabled(flag);
        jCheckBoxMenuViewYellowBackground.setEnabled(flag);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar = new javax.swing.JToolBar();
        NewLabel = new javax.swing.JButton();
        OpenLabel = new javax.swing.JButton();
        SaveLabel = new javax.swing.JButton();
        jEnergyLabelPane = new javax.swing.JTabbedPane();
        jStatusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileNew = new javax.swing.JMenuItem();
        jMenuFileOpen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuFileClose = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuFileSave = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuFilePrint = new javax.swing.JMenuItem();
        jMenuFileExport = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuFileExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuEditLabel = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuEditOptions = new javax.swing.JMenuItem();
        jMenuView = new javax.swing.JMenu();
        jCheckBoxMenuViewGreenBackground = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuViewYellowBackground = new javax.swing.JCheckBoxMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LabelPrint");
        setMinimumSize(new java.awt.Dimension(550, 500));
        setPreferredSize(new java.awt.Dimension(800, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jToolBar.setFloatable(false);

        NewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newFile.gif"))); // NOI18N
        NewLabel.setToolTipText("New label");
        NewLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewLabelActionPerformed(evt);
            }
        });
        jToolBar.add(NewLabel);

        OpenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/openFile.gif"))); // NOI18N
        OpenLabel.setToolTipText("Open label");
        OpenLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLabelActionPerformed(evt);
            }
        });
        jToolBar.add(OpenLabel);

        SaveLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saveFile.gif"))); // NOI18N
        SaveLabel.setToolTipText("Save label");
        SaveLabel.setEnabled(false);
        SaveLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveLabelActionPerformed(evt);
            }
        });
        jToolBar.add(SaveLabel);

        getContentPane().add(jToolBar, java.awt.BorderLayout.NORTH);

        jEnergyLabelPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jEnergyLabelPane.setMinimumSize(new java.awt.Dimension(400, 447));
        jEnergyLabelPane.setPreferredSize(new java.awt.Dimension(400, 447));
        jEnergyLabelPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jEnergyLabelPaneStateChanged(evt);
            }
        });
        getContentPane().add(jEnergyLabelPane, java.awt.BorderLayout.CENTER);

        jStatusLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jStatusLabel.setText("Ready...");
        jStatusLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jStatusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(jStatusLabel, java.awt.BorderLayout.SOUTH);

        jMenuFile.setMnemonic('F');
        jMenuFile.setText("File");

        jMenuFileNew.setMnemonic('N');
        jMenuFileNew.setText("New...");
        jMenuFileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileNew);

        jMenuFileOpen.setMnemonic('O');
        jMenuFileOpen.setText("Open...");
        jMenuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileOpen);
        jMenuFile.add(jSeparator1);

        jMenuFileClose.setMnemonic('C');
        jMenuFileClose.setText("Close");
        jMenuFileClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileCloseActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileClose);
        jMenuFile.add(jSeparator2);

        jMenuFileSave.setMnemonic('S');
        jMenuFileSave.setText("Save");
        jMenuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileSave);
        jMenuFile.add(jSeparator3);

        jMenuFilePrint.setMnemonic('P');
        jMenuFilePrint.setText("Print...");
        jMenuFilePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFilePrintActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFilePrint);

        jMenuFileExport.setMnemonic('r');
        jMenuFileExport.setText("Export...");
        jMenuFileExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileExportActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileExport);
        jMenuFile.add(jSeparator4);

        jMenuFileExit.setMnemonic('X');
        jMenuFileExit.setText("Exit");
        jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuFileExit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setMnemonic('E');
        jMenuEdit.setText("Edit");

        jMenuEditLabel.setText("Label");
        jMenuEditLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditLabelActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuEditLabel);
        jMenuEdit.add(jSeparator5);

        jMenuEditOptions.setText("Options...");
        jMenuEditOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditOptionsActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuEditOptions);

        jMenuBar1.add(jMenuEdit);

        jMenuView.setText("View");

        jCheckBoxMenuViewGreenBackground.setSelected(true);
        jCheckBoxMenuViewGreenBackground.setText("Green background");
        jCheckBoxMenuViewGreenBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuViewGreenBackgroundActionPerformed(evt);
            }
        });
        jMenuView.add(jCheckBoxMenuViewGreenBackground);

        jCheckBoxMenuViewYellowBackground.setSelected(true);
        jCheckBoxMenuViewYellowBackground.setText("Yellow background");
        jCheckBoxMenuViewYellowBackground.setToolTipText("");
        jCheckBoxMenuViewYellowBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuViewYellowBackgroundActionPerformed(evt);
            }
        });
        jMenuView.add(jCheckBoxMenuViewYellowBackground);

        jMenuBar1.add(jMenuView);

        jMenuHelp.setText("Help");

        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHelpAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuHelpAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHelpAboutActionPerformed

        JOptionPane.showMessageDialog(this,
                new CustomEditorPane("LabelPrint<br>"
                        + "&copy; 2018 D P Bennett & Associates<br>"
                        + "Website: <a href=\"http://lp.dpbennett.com.jm\">http://lp.dpbennett.com.jm</a>"),
                "About",
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jMenuHelpAboutActionPerformed

    private void jMenuEditLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditLabelActionPerformed
        jEnergyLabelPane.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuEditLabelActionPerformed

    private void NewLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewLabelActionPerformed
        createNewLabel();
    }//GEN-LAST:event_NewLabelActionPerformed

    private void jMenuEditOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditOptionsActionPerformed
        OptionsJDialog odlg = new OptionsJDialog(this, true);
        odlg.setVisible(true);
        if (odlg.hasDatabaseConnectionOptionsChanged()) {
            new Thread(this).start();
        }
    }//GEN-LAST:event_jMenuEditOptionsActionPerformed

    private void jCheckBoxMenuViewGreenBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuViewGreenBackgroundActionPerformed
        getLabelPanel().setShowGreenBackground(jCheckBoxMenuViewGreenBackground.isSelected());
        jEnergyLabelPane.setSelectedIndex(1);
        if (getLabelPanel().isShowGreenBackground()) {
            labelPanel.setElementFill("headerBackground", "#008000");
            labelPanel.setElementFill("violationNoteBackground", "#008000");
        } else {
            labelPanel.setElementFill("headerBackground", "none");
            labelPanel.setElementFill("violationNoteBackground", "none");
        }
    }//GEN-LAST:event_jCheckBoxMenuViewGreenBackgroundActionPerformed

    private void openLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLabelActionPerformed
        openLabel();
    }//GEN-LAST:event_openLabelActionPerformed

    private void jMenuFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileOpenActionPerformed
        openLabel();
    }//GEN-LAST:event_jMenuFileOpenActionPerformed

    private void jMenuFilePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFilePrintActionPerformed

        java.awt.EventQueue.invokeLater(getLabelPanel()::printLabel);

    }//GEN-LAST:event_jMenuFilePrintActionPerformed

    public String getFileAbsolutePath(String action) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter labelPrintFileFilter
                = new FileNameExtensionFilter("Gif, JPEG and PNG Images", "gif", "jpg", "png");

        chooser.setFileFilter(labelPrintFileFilter);
        chooser.setCurrentDirectory(new File("."));
        int retVal = chooser.showDialog(this, action);

        File file = chooser.getSelectedFile();
        if ((file != null) && (retVal != JFileChooser.CANCEL_OPTION)) {
            return file.getAbsolutePath();
        }

        return null;
    }

    private void jMenuFileExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileExportActionPerformed

        jEnergyLabelPane.setSelectedIndex(1);
        doLabelImageExport();
    }//GEN-LAST:event_jMenuFileExportActionPerformed

    public void doLabelImageExport() {
        if (saveFileIfDirty() == JOptionPane.CANCEL_OPTION) {
            return;
        }

        ExportJDialog exportJDialog = new ExportJDialog(this, true);
        exportJDialog.setVisible(true);

    }

    public SVGLabelPanel getLabelPanel() {

        return labelPanel;
    }

    public LabelDataPanel getLabelDataPanel() {

        return labelDataPanel;
    }


    private void SaveLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveLabelActionPerformed
        saveLabel();
    }//GEN-LAST:event_SaveLabelActionPerformed

    public JTabbedPane getjEnergyLabelPane() {
        return jEnergyLabelPane;
    }

    public void loadLabelPanels() {

        getLabelDataPanel().updateLabelData();
        getLabelPanel().updateLabel();

        getjEnergyLabelPane().repaint();
    }

    private void openLabel() {

        if (sysOptions.isConnectToDatabase()) {
            if (saveFileIfDirty() == JOptionPane.CANCEL_OPTION) {
                return;
            }

            OpenLabelDialog oldlg = new OpenLabelDialog(this, true);
            oldlg.setVisible(true);
            if (oldlg.proceedToOpenLabel()) {

                initLabelPanels();

                getLabelDataPanel().setEnergyLabel(findLabel(oldlg.getLabelId()));

                loadLabelPanels();

                getjEnergyLabelPane().setSelectedIndex(1);

                setTitle("LabelPrint - " + getLabelDataPanel().getEnergyLabel().getLabelName());
                enableMenuItems(true);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "You cannot open labels because you\n"
                    + "do not have a database connection.\n"
                    + "Activate this option and try again.",
                    "Label Open Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        setStatus("Ready...");
    }

    public String getPDFFileAbsolutePath(String action) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter labelPrintFileFilter
                = new FileNameExtensionFilter("PDF files", "pdf");
        chooser.setFileFilter(labelPrintFileFilter);
        chooser.setCurrentDirectory(new File("."));
        int retVal = chooser.showDialog(this, action);

        File file = chooser.getSelectedFile();
        if ((file != null) && (retVal != JFileChooser.CANCEL_OPTION)) {
            return file.getAbsolutePath();
        }

        return null;
    }

    public void saveLabel() {

        try {

            if (getLabelDataPanel().getEnergyLabel().validate(getEntityManager()).isSuccess()) {
                if (!getLabelDataPanel().getEnergyLabel().save(getEntityManager()).isSuccess()) {
                    JOptionPane.showMessageDialog(this,
                            "An error occured while saving the current label.\n"
                            + "This could occur because you do not have a database connection.\n"
                            + "Try to connect to a database in the options dialog and try again.",
                            "Save Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    setDirty(false);
                }
            } else {
                 ReturnMessage returnMessage = getLabelDataPanel().getEnergyLabel().validate(getEntityManager());
                
                 JOptionPane.showMessageDialog(this,
                            returnMessage.getMessage(),
                            returnMessage.getHeader(),
                            JOptionPane.ERROR_MESSAGE);   
            }

            
        } catch (HeadlessException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this,
                    "An error occured while saving the current label.\n"
                    + "This could occur because you do not have a database connection.\n"
                    + "Try to connect to a database in the options dialog and try again.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jMenuFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileSaveActionPerformed
        saveLabel();
    }//GEN-LAST:event_jMenuFileSaveActionPerformed

    private void exit() {

        if (saveFileIfDirty() != JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }

    }

    private void jMenuFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileExitActionPerformed
        exit();
    }//GEN-LAST:event_jMenuFileExitActionPerformed

    private void jMenuFileCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileCloseActionPerformed

        if (saveFileIfDirty() == JOptionPane.CANCEL_OPTION) {
            return;
        }

        getjEnergyLabelPane().removeAll();
        labelDataPanel = null;
        labelPanel = null;

        setTitle("LabelPrint");
        enableMenuItems(false);
    }//GEN-LAST:event_jMenuFileCloseActionPerformed

    private int saveFileIfDirty() {
        int choice = JOptionPane.YES_OPTION;

        if (getLabelDataPanel() != null) {
            if (getLabelDataPanel().getEnergyLabel().getIsDirty()) {
                choice = JOptionPane.showConfirmDialog(this,
                        "Label has been changed. Do you want to save it?",
                        "Save",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    saveLabel();
                }

                if (choice == JOptionPane.CANCEL_OPTION) {
                    return choice;
                }
            }
        }

        return choice;
    }

    /**
     * Create and add panels if they do not exist.
     */
    private void initLabelPanels() {

        if (labelDataPanel == null) {
            labelDataPanel = new LabelDataPanel(this);
            getjEnergyLabelPane().add("Label Data", labelDataPanel);
        }

        if (labelPanel == null) {
            labelPanel = new SVGLabelPanel(this);

            getjEnergyLabelPane().add("Label View", labelPanel);

        }

        getjEnergyLabelPane().setSelectedIndex(0);

    }

    private void createNewLabel() {

        if (saveFileIfDirty() == JOptionPane.CANCEL_OPTION) {
            return;
        }

        initLabelPanels();

        getLabelDataPanel().setEnergyLabel(new EnergyLabel());
        getLabelDataPanel().getEnergyLabel().setType(getSystemOptions().getProperty("ProductType"));
        if (getLabelDataPanel().getEnergyLabel().getType().equals("Refrigerator")
                || getLabelDataPanel().getEnergyLabel().getType().equals("Basic Refrigerator")) {
            getLabelDataPanel().getEnergyLabel().setDefrost("Automatic");
        }
        getLabelDataPanel().getEnergyLabel().setStandard(getSystemOptions().getProperty("Standard"));
        getLabelDataPanel().getEnergyLabel().setValidity("" + BusinessEntityUtils.getCurrentYear());

        loadLabelPanels();

        getjEnergyLabelPane().setSelectedIndex(0);

        this.setTitle("LabelPrint - " + getLabelDataPanel().getEnergyLabel().getLabelName());
        enableMenuItems(true);
    }

    private void jMenuFileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileNewActionPerformed
        createNewLabel();
    }//GEN-LAST:event_jMenuFileNewActionPerformed

    private void jEnergyLabelPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jEnergyLabelPaneStateChanged

        if (jEnergyLabelPane.getSelectedIndex() == 1) {
            labelPanel.updateLabel();
        }

    }//GEN-LAST:event_jEnergyLabelPaneStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBoxMenuViewYellowBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuViewYellowBackgroundActionPerformed
        getLabelPanel().setShowYellowBackground(jCheckBoxMenuViewYellowBackground.isSelected());
        jEnergyLabelPane.setSelectedIndex(1);
        if (getLabelPanel().isShowYellowBackground()) {
            labelPanel.setElementFill("mainBackground", "#FFDF00");
        } else {
            labelPanel.setElementFill("mainBackground", "none");
        }
    }//GEN-LAST:event_jCheckBoxMenuViewYellowBackgroundActionPerformed

    public JTabbedPane getTabbedPane() {
        return jEnergyLabelPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LabelPrintFrame().setVisible(true);
        });
    }

    public EntityManager getEntityManager() {
        if (emf != null) {
            return emf.createEntityManager();
        } else if (setupDatabaseConnection()) {
            return emf.createEntityManager();
        } else {
            return null;
        }
    }

    public boolean setupDatabaseConnection() {

        try {
            HashMap prop = new HashMap();

            // Close any existing connection if any
            if (emf != null) {
                emf.close();
                emf = null;
            }

            prop.put("javax.persistence.jdbc.user",
                    sysOptions.getProperty("ConnectionUserName"));
            prop.put("javax.persistence.jdbc.password",
                    sysOptions.getConnectionPassword());
            prop.put("javax.persistence.jdbc.url",
                    sysOptions.getProperty("ConnectionURL"));
            prop.put("javax.persistence.jdbc.driver",
                    sysOptions.getProperty("ConnectionDriverName"));

            emf = Persistence.createEntityManagerFactory("LabelPrintPU", prop);

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void run() {
        // Setup persistence/connect to database        
        if (sysOptions.isConnectToDatabase()) {
            System.out.println("Reconnecting");
            if (!setupDatabaseConnection()) {
                JOptionPane.showMessageDialog(this,
                        "A database connection error occurred.\n"
                        + "Check that the database options are valid",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewLabel;
    private javax.swing.JButton OpenLabel;
    private javax.swing.JButton SaveLabel;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuViewGreenBackground;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuViewYellowBackground;
    private javax.swing.JTabbedPane jEnergyLabelPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuEditLabel;
    private javax.swing.JMenuItem jMenuEditOptions;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileClose;
    private javax.swing.JMenuItem jMenuFileExit;
    private javax.swing.JMenuItem jMenuFileExport;
    private javax.swing.JMenuItem jMenuFileNew;
    private javax.swing.JMenuItem jMenuFileOpen;
    private javax.swing.JMenuItem jMenuFilePrint;
    private javax.swing.JMenuItem jMenuFileSave;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuHelpAbout;
    private javax.swing.JMenu jMenuView;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel jStatusLabel;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
}
