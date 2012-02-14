package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

/**
 * @author koose_m@haw.informatik.hamburg.de
 *
 */
public class Start extends State {
	private static State handleVar= null;
	private Start(){}
	public static State handle(){
		if(handleVar==null) {
			handleVar = new Start();
		}
		//Prt.ln("Start");
		return handleVar;
	}

	public void destroy() {
	}
	
	protected void acceptMark(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.addMARK(s);
	}
	protected void acceptOperand(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.push(s);
		changeState(a,OP1.handle() );
	}
	protected void acceptBRF(CodeBuilder a, String s)
		throws CompilerErrorException {
	changeState(a,BRF.handle());
	}
	protected void acceptJMP(CodeBuilder a, String s)
		throws CompilerErrorException {
	changeState(a,JMP.handle());
	}
	
	public static void main(String[] args) {
	}
}
