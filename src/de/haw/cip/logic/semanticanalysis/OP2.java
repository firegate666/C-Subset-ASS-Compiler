package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

/**
 * @author koose_m@haw.informatik.hamburg.de
 *
 */
public class OP2 extends State {
	private static State handleVar = null;
	private OP2() {
	}
	public static State handle() {
		if (handleVar == null) {
			handleVar = new OP2();
		}
		//Prt.ln("OP2");
		return handleVar;
	}

	
	protected void acceptOperand(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.push(s);
	}
	protected void acceptPlus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd2();
		a.Opnd1ToEac();
		a.eacOPopnd2("ADD.W");
		changeState(a,O2EAC.handle());
	}
	protected void acceptMinus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd2();
		a.Opnd1ToEac();
		a.eacOPopnd2("SUB.W");
		changeState(a,O2EAC.handle());
	}
	protected void acceptMul(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd2();
		a.Opnd1ToEac();
		a.eacOPopnd2("MULS");
		changeState(a,O2EAC.handle());
	}
	protected void acceptDiv(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd2();
		a.Opnd1ToEac();
		a.eacOPopnd2("DIVS");
		changeState(a,O2EAC.handle());
	}
	protected void acceptPrint(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.addPrintString();
		changeState(a,Start.handle());
	}
	protected void acceptCMP(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.pullOpnd2();
		a.Opnd1ToEac();
		a.eacCMPOpnd2(s);
		changeState(a,Start.handle());
	}
	protected void acceptPreMinus(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.OpndToEac();
		a.negEac();
		changeState(a,O2EAC.handle());
	}
	protected void acceptAssign(CodeBuilder a, String s)
		throws CompilerErrorException {
		//a.Opnd2ToEac();
		//a.EacToOpnd1();
		a.addAssignment();
		changeState(a,Start.handle());
	}

	public void destroy() {
	}

	public static void main(String[] args) {
	}
}
