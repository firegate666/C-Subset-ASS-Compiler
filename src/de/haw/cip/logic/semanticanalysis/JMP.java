package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

public class JMP extends State {
	private static State handleVar = null;
	private JMP() {
	}
	public static State handle() {
		if (handleVar == null) {
			handleVar = new JMP();
		}
		//Prt.ln("JMP");
		return handleVar;
	}

	@Override
	public void destroy() {
	}

	@Override
	protected void acceptMark(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.addJMP(s);
		changeState(a, Start.handle());
	}

	public static void main(String[] args) {
	}
}
