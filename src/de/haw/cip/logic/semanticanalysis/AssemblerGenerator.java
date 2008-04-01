package de.haw.cip.logic.semanticanalysis;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.Singleton;
import de.haw.cip.logic.Symboltable;
import de.mb.exception.InstanceNotInitializedException;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AssemblerGenerator implements Singleton{

	private static AssemblerGenerator handleVar =null;
	private CIPWindow app;
	
	public static AssemblerGenerator handle(CIPWindow app) {
		if(handleVar==null) {
			handleVar = new AssemblerGenerator(app);
		}
		return handleVar;
	}
	
	public CIPWindow getApp() {
		return app;
	}

	public String getAsmCode(String ptf) throws InstanceNotInitializedException {
		CodeBuilder cb = new CodeBuilder(ptf,app);
		return cb.getAsm();
	}

	public String getAsmPrinfDeclaration() {
		DeclarationBuilder db = DeclarationBuilder.handle();
		return db.getPrintfDeclare();
	}

	public String getAsmVarDeclaration(Symboltable symbols)   throws CompilerErrorException {
		DeclarationBuilder db = DeclarationBuilder.handle();
		return db.buildVarDeclare( symbols);
	} 
	private AssemblerGenerator(CIPWindow app) {
			this.app = app;
	}

}
