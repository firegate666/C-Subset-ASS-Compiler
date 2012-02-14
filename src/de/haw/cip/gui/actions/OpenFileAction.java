package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.gui.CSourceFileFilter;
import de.haw.cip.gui.FileDialog;
import de.haw.cip.io.FileReader;
import de.mk.exception.CompilerErrorException;
import de.mk.exception.ExceptionHandler;

import java.awt.event.ActionEvent;
import java.io.File;
/**
 * @author Administrator
 *
 */
public class OpenFileAction extends AbstractAction {

	public OpenFileAction(CIPWindow app) {
		super(app);
	}

	private void sharedActions() throws CompilerErrorException {
		//getApp().setScannerOutput("zieltext");
		getApp().resetStatus();
		getApp().printStatus( "Datei "+getApp().getSourceFile().getAbsolutePath()+" ge�ffnet." );
		getApp().getMenu().activateAllAnalysis();
		getApp().getMenu().activateLexicalAnalysis();
		getApp().getMenu().deactivateSyntaxAnalysis();
		getApp().getMenu().deactivateSemanticAnalysis();
		getApp().getMenu().deactivateCodeBuilder();
		//getApp().getScanner().resetScanner();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog fd = new FileDialog(Ressource_Bundle.getString("dialog_choosesourcefile"));
		fd.setFileFilter( new CSourceFileFilter());
		File f = fd.getFile();
		
		if(f==null) return;
		getApp().setSourceFile(f);
		try {
			getApp().setSourceText(
				FileReader.getString(getApp().getSourceFile()));
			//getApp().setScannerOutput("zieltext");
			this.sharedActions();
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}
		getApp().addLastOpenedFile(f);
		getApp().clearOutputs();
	}
	public void actionPerformedAfterSave(ActionEvent e) {
		try{
			getApp().setSourceText(FileReader.getString(getApp().getSourceFile()));
			this.sharedActions();
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}
	}

}
