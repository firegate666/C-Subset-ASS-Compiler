package de.haw.cip.logic.scanner;

import de.haw.cip.logic.*;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 *
 *        |----------------------------------> DIVISION 
 *        |
 *        |               *
 *  /    /-\   *    /-\  --->  /-\   /    /-\
 * ---> | C | ---> |CA |  o   |CE2| ---> |Sta|
 *       \-/        \-/  <---  \-/        \-/
 *        |         | ^
 *       /|         | |                    ^
 *        |          -                     |
 *        \/         o                     |
 *       /-\                               |
 *      |CE1|------------------------------|
 *       \-/
 *       | ^
 *       | |
 *        -
 *        o
 */

public final class CE2 extends State {
	public void destroy() {
		handleVar = null;
	}
	private CE2() {
		initialize();
	}

	private void initialize() {
	}

	private static CE2 handleVar = null;

	public static CE2 handle() {
		if (handleVar == null) {
			handleVar = new CE2();
			//Prt.ln("\nCE2 \"/* o */\" -Zustand erzeugt !!!");
		}
		return handleVar;
	}

	protected void acceptGoosefeet(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};
	protected void acceptOther(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptLetter(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptDigit(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptPrintable(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptPlus(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptMinus(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptMul(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptDiv(Scanner a, char ch)
		throws CompilerErrorException {
		reset(a, ch);
		changeState(a, Start.handle());
	};

	protected void acceptEqual(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptGreater(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptLess(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptColon(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptPar_On(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptPar_Off(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptK_On(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptK_Off(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptTick(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptKomma(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptSemicolon(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptSpace(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptDot(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptETX(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(
			a,
			Ill.handle(
				"Bufferdump: " + a.getBufferCopy() + "\n Endof text found"));
	};

	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	protected void acceptNOT(Scanner a, char ch)
		throws CompilerErrorException {
		changeState(a, CA.handle());
	};

	public static void main(String[] args) {
	}
}
