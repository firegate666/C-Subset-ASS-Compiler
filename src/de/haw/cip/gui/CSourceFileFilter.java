package de.haw.cip.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CSourceFileFilter extends FileFilter {

    /**
     * @see javax.swing.filechooser.FileFilter#accept(File)
     */
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;

        String s = f.getAbsolutePath();
        int ext = s.lastIndexOf(".");
        if (ext >= 0) s = s.substring(ext);
        return s.equalsIgnoreCase(".c");
    }

    /**
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    @Override
    public String getDescription() {
        return "*.c";
    }

}
