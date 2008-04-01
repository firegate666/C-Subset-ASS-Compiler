package de.haw.cip.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CSourceFileFilter extends FileFilter {

    /**
     * @see javax.swing.filechooser.FileFilter#accept(File)
     */
    public boolean accept(File f) {
    	if(f.isDirectory()) return true;
    	
    	String s = f.getAbsolutePath();
    	int ext = s.lastIndexOf(".");
    	if(ext>=0)s=s.substring( ext);
    	if(s.equalsIgnoreCase(".c" )) return true;
        return false;
    }

    /**
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    public String getDescription() {
        return "*.c";
    }

}
