package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.io.FileReader;
import de.mk.exception.ExceptionHandler;

/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LexicalAnalysisAction extends AbstractAction {

	/**
	 * Constructor for LexicalAnalysisAction.
	 * @param app
	 */
	public LexicalAnalysisAction(CIPWindow app) {
		super(app);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getApp().setScanner();
		try {
			getApp().getScanner().accept(
				FileReader.getString(getApp().getSourceFile()));
			getApp().setScannerOutput(getApp().getScanner().getTokensAsString()+"zieltext");
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}
		getApp().getMenu().deactivateAllAnalysis();
		getApp().getMenu().deactivateLexicalAnalysis();
		getApp().getMenu().activateSyntaxAnalysis();
		getApp().getMenu().deactivateSemanticAnalysis();
		getApp().getMenu().deactivateCodeBuilder();
	}

}
