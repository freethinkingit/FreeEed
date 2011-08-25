package org.freeeed.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.configuration.Configuration;
import org.freeeed.main.FreeEedConfiguration;
import org.freeeed.main.FreeEedException;
import org.freeeed.main.FreeEedLogging;
import org.freeeed.main.FreeEedMain;
import org.freeeed.main.LinuxUtil;
import org.freeeed.main.ParameterProcessing;
import org.freeeed.services.History;
import org.freeeed.services.Review;

/**
 *
 * @author mark
 */
public class FreeEedUI extends javax.swing.JFrame {

    private static FreeEedUI instance;

    public static FreeEedUI getInstance() {
        return instance;
    }

    /** Creates new form Main */
    public FreeEedUI() {
        FreeEedLogging.init();
        initComponents();
        showHistory();
        instance = this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        menuItemNewProject = new javax.swing.JMenuItem();
        menuItemOpenProject = new javax.swing.JMenuItem();
        menuItemSaveProject = new javax.swing.JMenuItem();
        menuItemSaveProjectAs = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        menuItemProjectSettings = new javax.swing.JMenuItem();
        processMenu = new javax.swing.JMenu();
        stageMenuItem = new javax.swing.JMenuItem();
        processMenuItem = new javax.swing.JMenuItem();
        processSeparator = new javax.swing.JPopupMenu.Separator();
        historyMenuItem = new javax.swing.JMenuItem();
        reviewMenu = new javax.swing.JMenu();
        menuItemOutputFolder = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FreeEed - Graphical User Interface");

        fileMenu.setText("File");

        menuItemNewProject.setText("New project");
        menuItemNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNewProjectActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemNewProject);

        menuItemOpenProject.setText("Open project");
        menuItemOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenProjectActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemOpenProject);

        menuItemSaveProject.setText("Save project");
        menuItemSaveProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveProjectActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemSaveProject);

        menuItemSaveProjectAs.setText("Save project as");
        menuItemSaveProjectAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveProjectAsActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemSaveProjectAs);

        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemExit);

        mainMenu.add(fileMenu);

        editMenu.setText("Edit");

        menuItemProjectSettings.setText("Project settings");
        menuItemProjectSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemProjectSettingsActionPerformed(evt);
            }
        });
        editMenu.add(menuItemProjectSettings);

        mainMenu.add(editMenu);

        processMenu.setText("Process");

        stageMenuItem.setText("Stage");
        stageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageMenuItemActionPerformed(evt);
            }
        });
        processMenu.add(stageMenuItem);

        processMenuItem.setText("Process");
        processMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processMenuItemActionPerformed(evt);
            }
        });
        processMenu.add(processMenuItem);
        processMenu.add(processSeparator);

        historyMenuItem.setText("History");
        historyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemActionPerformed(evt);
            }
        });
        processMenu.add(historyMenuItem);

        mainMenu.add(processMenu);

        reviewMenu.setText("Review");

        menuItemOutputFolder.setText("Open output folder");
        menuItemOutputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOutputFolderActionPerformed(evt);
            }
        });
        reviewMenu.add(menuItemOutputFolder);

        mainMenu.add(reviewMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenu.add(helpMenu);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        new AboutDialog(this, true).setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        exitApp();
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenProjectActionPerformed
        openProject();
    }//GEN-LAST:event_menuItemOpenProjectActionPerformed

    private void menuItemProjectSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemProjectSettingsActionPerformed
        showProjectSettings();
    }//GEN-LAST:event_menuItemProjectSettingsActionPerformed

	private void menuItemSaveProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveProjectActionPerformed
            saveProjectSettings();
	}//GEN-LAST:event_menuItemSaveProjectActionPerformed

	private void menuItemNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNewProjectActionPerformed
            openNewProject();
	}//GEN-LAST:event_menuItemNewProjectActionPerformed

	private void menuItemSaveProjectAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveProjectAsActionPerformed
            saveProjectSettingsAs();
	}//GEN-LAST:event_menuItemSaveProjectAsActionPerformed

	private void stageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageMenuItemActionPerformed
            stageProject();
	}//GEN-LAST:event_stageMenuItemActionPerformed

	private void processMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processMenuItemActionPerformed
            try {
                runProcessing();
            } catch (FreeEedException e) {
                JOptionPane.showMessageDialog(this, "There were some problems with processing, \""
                        + e.getMessage() + "\n"
                        + "please check console output");
            }
	}//GEN-LAST:event_processMenuItemActionPerformed

	private void historyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemActionPerformed
            showHistory();
	}//GEN-LAST:event_historyMenuItemActionPerformed

	private void menuItemOutputFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOutputFolderActionPerformed
            openOutputFolder();
	}//GEN-LAST:event_menuItemOutputFolderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FreeEedUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem historyMenuItem;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemNewProject;
    private javax.swing.JMenuItem menuItemOpenProject;
    private javax.swing.JMenuItem menuItemOutputFolder;
    private javax.swing.JMenuItem menuItemProjectSettings;
    private javax.swing.JMenuItem menuItemSaveProject;
    private javax.swing.JMenuItem menuItemSaveProjectAs;
    private javax.swing.JMenu processMenu;
    private javax.swing.JMenuItem processMenuItem;
    private javax.swing.JPopupMenu.Separator processSeparator;
    private javax.swing.JMenu reviewMenu;
    private javax.swing.JMenuItem stageMenuItem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisible(boolean b) {
        myInitComponents();
        super.setVisible(b);
    }

    private void myInitComponents() {
        setBounds(64, 40, 640, 400);
        setTitle("FreeEed - Open source eDiscovery - Operator Console");
    }

    private void exitApp() {
        if (!isExitAllowed()) {
            return;
        }
        // TODO verify - is that a standard way to exit?
        setVisible(false);
        System.exit(0);
    }

    private boolean isExitAllowed() {
        return true;
    }

    private void openProject() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new ProjectFilter());
            File f = null;
            try {
                f = new File(new File(".").getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            // Set the current directory
            fileChooser.setCurrentDirectory(f);
            fileChooser.setDialogTitle("Select project file");
            fileChooser.showOpenDialog(this);
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile == null) {
                return;
            }
            History.appendToHistory("Opened project file: " + selectedFile.getPath());
            Configuration processingParameters =
                    ParameterProcessing.collectProcessingParameters(selectedFile.getPath());
            FreeEedMain.getInstance().setProcessingParameters(processingParameters);
            updateTitle(processingParameters);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private class ProjectFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(".project");
        }

        @Override
        public String getDescription() {
            return "Project files";
        }
    }

    public void updateTitle(Configuration processingParameters) {
        String title = processingParameters.getString(ParameterProcessing.PROJECT_NAME);
        if (title != null) {
            setTitle("FreeEed - " + title);
        } else {
            setTitle("FreeEed");
        }
    }

    private void showProjectSettings() {
        ProjectSettingsUI dialog = new ProjectSettingsUI(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void saveProjectSettings() {
        Configuration processingParameters = FreeEedMain.getInstance().getProcessingParameters();
        if (processingParameters == null) {
            JOptionPane.showMessageDialog(rootPane, "You have no project open");
            return;
        }
        String projectFile = null;
        if (processingParameters.containsKey(ParameterProcessing.PROJECT_FILE_NAME)) {
            projectFile = processingParameters.getString(ParameterProcessing.PROJECT_FILE_NAME);
        }
        if (projectFile == null) {
            saveProjectSettingsAs();
            return;
        }
        FreeEedConfiguration configToSave = new FreeEedConfiguration();
        configToSave.append(processingParameters);
        configToSave.cleanup();
        try {
            configToSave.save(projectFile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        configToSave.restore();
    }

    private void saveProjectSettingsAs() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            File f = null;
            try {
                f = new File(new File(".").getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            // Set the current directory
            fileChooser.setCurrentDirectory(f);
            fileChooser.setDialogTitle("Save project");
            fileChooser.showSaveDialog(this);
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile == null) {
                return;
            }
            String projectFile = selectedFile.getPath();
            if (!projectFile.endsWith(".project")) {
                projectFile += ".project";
            }
            History.appendToHistory("Saved project " + projectFile);
            FreeEedConfiguration configToSave = new FreeEedConfiguration();
            Configuration processingParameters =
                    FreeEedMain.getInstance().getProcessingParameters();
            configToSave.append(processingParameters);
            configToSave.cleanup();
            configToSave.save(projectFile);
            configToSave.restore();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void openNewProject() {
        Configuration processingParameters =
                ParameterProcessing.setDefaultParameters();
        processingParameters.setProperty(ParameterProcessing.PROJECT_NAME, "New project");
        FreeEedMain.getInstance().setProcessingParameters(processingParameters);
        updateTitle(processingParameters);
        showProjectSettings();
    }

    private void stageProject() {
        FreeEedMain mainInstance = FreeEedMain.getInstance();
        if (mainInstance.getProcessingParameters() == null) {
            JOptionPane.showMessageDialog(this, "Please open a project first");
            return;
        }
        try {
            mainInstance.runStagePackageInput();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void runProcessing() throws FreeEedException {
        FreeEedMain mainInstance = FreeEedMain.getInstance();
        if (mainInstance.getProcessingParameters() == null) {
            JOptionPane.showMessageDialog(this, "Please open a project first");
            return;
        }
        if (new File(ParameterProcessing.OUTPUT_DIR + "/output").exists()) {
            int reply = JOptionPane.showConfirmDialog(this, "Output directory not empty. Remove it?");
            if (reply == JOptionPane.OK_OPTION) {
                LinuxUtil.runLinuxCommand("rm -fr " + ParameterProcessing.OUTPUT_DIR + "/output");
            }
        }
        String runWhere = mainInstance.getProcessingParameters().getString(ParameterProcessing.PROCESS_WHERE);
        if (runWhere != null) {
            mainInstance.runProcessing(runWhere);
        } else {
            throw new FreeEedException("No processing option selected.");
        }
    }

    private void showHistory() {
        HistoryUI ui = new HistoryUI();
        ui.setVisible(true);
    }

    private void openOutputFolder() {
        try {
            Review.deliverFiles();
            File outputFolder = new File(ParameterProcessing.OUTPUT_DIR + "/output");
            Desktop.getDesktop().open(outputFolder);
        } catch (FreeEedException fe) {
            JOptionPane.showMessageDialog(this, fe.getMessage());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
