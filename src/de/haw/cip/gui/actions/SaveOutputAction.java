package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.gui.FileDialog;
import de.haw.cip.io.FileWriter;
import de.mk.exception.ExceptionHandler;

import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SaveOutputAction extends AbstractAction {

	public SaveOutputAction(CIPWindow app) {
		super(app);
	}

	public void actionPerformed(ActionEvent e) {
		File f = new FileDialog(Ressource_Bundle.getString("dialog_choosedestinationfile")).getFile(); //$NON-NLS-1$
		if(f==null) return;
		String s = (getApp().getSelectedSource());
		if(f==null) {
			return;
		}

		try{
			FileWriter.writeToFile(f,s);
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}		
	}



}
