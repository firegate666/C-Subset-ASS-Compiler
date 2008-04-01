package de.haw.cip.logic.semanticanalysis;

import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

public class BRF extends State {

	private static State handleVar = null;
	private BRF() {
	}
	public static State handle() {
		if (handleVar == null) {
			handleVar = new BRF();
		}
		//Prt.ln("BRF");
		return handleVar;
	}

	public void destroy() {
	}

	protected void acceptMark(CodeBuilder a, String s)
		throws CompilerErrorException {
		a.addBRF(s);
		changeState(a, Start.handle());
	}

	public static void main(String[] args) {
	}
}