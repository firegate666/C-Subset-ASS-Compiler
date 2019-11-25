package de.haw.cip.gui;

import javax.swing.*;
import java.io.File;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FileDialog extends JFileChooser {

    public FileDialog(String title) {
        this.initialize(title);
    }


    private void initialize(String title) {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setMultiSelectionEnabled(false);
        this.setDialogTitle(title);
        try {
            this.setCurrentDirectory(new File(System.getProperty("java.class.path")));
        } catch (Exception e) {
        }
    }

    public File getFile() {
        File result = null;
        if (this.showDialog(this, "OK") != JFileChooser.CANCEL_OPTION) {
            result = this.getSelectedFile();
        }
        //if(result!=null)System.out.println("File: "+result.getAbsolutePath() );
        return result;
    }

}
