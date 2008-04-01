package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.semanticanalysis.AssemblerGenerator;
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
public class CodeBuilderAction  extends AbstractAction {

	/**
	 * Constructor for LexicalAnalysisAction.
	 * @param app
	 */
	public CodeBuilderAction(CIPWindow app) {
		super(app);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			SyntaxAnalysis s = SyntaxAnalysis.getInstance();

			AssemblerGenerator asm = AssemblerGenerator.handle(getApp());
			String result="";
			result += (asm.getAsmCode( s.getPtf() ));
			result += (asm.getAsmPrinfDeclaration()  );
			result += (asm.getAsmVarDeclaration(getApp().getScanner().getSymbols()));
			getApp().setAsmOutput( result);

			getApp().getMenu().deactivateAllAnalysis();
			getApp().getMenu().deactivateLexicalAnalysis();
			getApp().getMenu().deactivateSyntaxAnalysis();
			getApp().getMenu().deactivateSemanticAnalysis();
			getApp().getMenu().deactivateCodeBuilder();
					
			} catch (Exception ex) {
			ExceptionHandler.instance(ex);
		}
		getApp().printStatus("Assembler Generierung beendet.");
	}

}
