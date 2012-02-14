package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.io.FileReader;
import de.mb.swing.JMenuItemLastOpenedFile;
import de.mk.exception.ExceptionHandler;

import java.awt.event.ActionEvent;
import java.io.File;
/**
 * @author Administrator
 *
 */
public class OpenLastFileAction extends AbstractAction {

	public OpenLastFileAction(CIPWindow app) {
		super(app);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = new File(((JMenuItemLastOpenedFile)e.getSource()).getFilename());
		getApp().setSourceFile(f);
		try {
			getApp().setSourceText(
				FileReader.getString(getApp().getSourceFile()));
			getApp().setScannerOutput("zieltext");
			getApp().resetStatus();
			getApp().printStatus( "Datei "+getApp().getSourceFile().getAbsolutePath()+" ge�ffnet." );
			getApp().getMenu().activateAllAnalysis();
			getApp().getMenu().activateLexicalAnalysis();
			getApp().getMenu().deactivateSyntaxAnalysis();
			getApp().getMenu().deactivateSemanticAnalysis();
			getApp().getMenu().deactivateCodeBuilder();
			getApp().clearOutputs();
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}
	}
}
