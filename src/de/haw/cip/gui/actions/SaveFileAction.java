package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.io.FileWriter;
import de.mk.exception.ExceptionHandler;
/**
 * @author Administrator
 *
 */
public class SaveFileAction extends AbstractAction {

	public SaveFileAction(CIPWindow app) {
		super(app);
	}

	public void actionPerformed(ActionEvent e) {
		File f = getApp().getSourceFile();

		if(f==null) {
			//(new SaveAsFileAction(getApp())).actionPerformed( e);
			return;
		}

		try{
			FileWriter.writeToFile(f,getApp().getSourceText());
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}		
		new OpenFileAction(getApp()).actionPerformedAfterSave(e );
	}

}
