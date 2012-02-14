package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

/**
 * @author koose_m@haw.informatik.hamburg.de
 *
 */
public class O2EAC extends State {

	private static State handleVar = null;
	private O2EAC() {
	}
	public static State handle() {
		if (handleVar == null) {
			handleVar = new O2EAC();
		}
		//Prt.ln("O2EAC");
		return handleVar;
	}

	
	protected void acceptOperand(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pushDummy();
		a.push(s);
		changeState(a,O1EAC.handle());
	}
	protected void acceptPlus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.eacToHac();
		a.Opnd1ToEac();
		a.eacOPhac("ADD.W" );
	}
	protected void acceptMinus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.eacToHac();
		a.Opnd1ToEac();
		a.eacOPhac("SUB.W" );
	}
	protected void acceptMul(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.eacToHac();
		a.Opnd1ToEac();
		a.eacOPhac("MULS" );
	}
	protected void acceptDiv(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.eacToHac();
		a.Opnd1ToEac();
		a.eacOPhac("DIVS" );
	}
	protected void acceptPrint(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.addPrintString();
		changeState(a,Start.handle());
	}
	protected void acceptCMP(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.eacToHac();
		a.Opnd1ToEac();
		a.eacCMPhac(s);
		changeState(a,Start.handle());
	}
	protected void acceptPreMinus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.negEac();
	}
	protected void acceptAssign(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd1();
		a.EacToOpnd1(); 
		changeState(a,Start.handle());
	}


	public void destroy() {
	}

	public static void main(String[] args) {
	}
}
