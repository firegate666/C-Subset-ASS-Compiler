package de.haw.cip.logic.scanner;

import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Illegal State of the automaton used for the scanner
 */
public final class Ill extends State {

	public void destroy() {
		handleVar = null;
	}
	private static Ill handleVar = null;
	private String errorMsg="";

	private Ill(String errorMsg) {
		initialize(errorMsg);
	}

	private Ill() {
	}

	private void initialize(String newErrorMsg) {
		errorMsg = newErrorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public static Ill handle() {
		if (handleVar == null) {
			handleVar = new Ill();
			Prt.ln("\nIll -Zustand erzeugt !!!");
		}
		return handleVar;
	}

	public static Ill handle(String errorMsg) {
		if (handleVar == null) {
			handleVar = new Ill(errorMsg);
			Prt.ln("\nIll -Zustand erzeugt !!!");
		}
		return handleVar;
	}

	protected void acceptGoosefeet(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};

	private void error(Scanner a) throws CompilerErrorException {
		a.getApp().printStatus("COMPILER ERROR LEXICAL ANALYSIS:\n"+getErrorMsg());
		changeState(a,IllEnd.handle());
		//throw new CompilerErrorException("COMPILER ERROR LEXICAL ANALYSIS:\n"+getErrorMsg());
	}

	protected void acceptOther(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptLetter(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptDigit(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptPrintable(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptPlus(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptMinus(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptMul(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptDiv(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptEqual(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptGreater(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptLess(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptColon(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptPar_On(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptPar_Off(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptK_On(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptK_Off(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptTick(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptKomma(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptSemicolon(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptSpace(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptDot(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptETX(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
		this.error(a);
	};
	protected void acceptNOT(Scanner a, char ch)
		throws CompilerErrorException {
		this.error(a);
	};

	public static void main(String[] args) {
	}
}
