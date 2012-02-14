package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.syntaxanalysis.SyntaxAnalysis;
import de.mk.exception.ExceptionHandler;

/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SyntaxAnalysisAction extends AbstractAction {

	public SyntaxAnalysisAction(CIPWindow app) {
		super(app);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			SyntaxAnalysis s = SyntaxAnalysis.getInstance();
			s.initialize(getApp().getScanner(),true);
			s.setApp(getApp() );
			s.analyse();
			getApp().setSymbolOutput(getApp().getScanner().getSymbols() );
			getApp().getMenu().deactivateAllAnalysis();
			getApp().getMenu().deactivateLexicalAnalysis();
			getApp().getMenu().deactivateSyntaxAnalysis();
			getApp().getMenu().activateSemanticAnalysis();
			getApp().getMenu().deactivateCodeBuilder();
		} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}

	}
}