package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Less < State of the automaton used for the scanner
 * 
 *  <    /-\   =    /-\
 * ---> | L | ---> |LE | ---> LESS EQUAL
 *       \-/        \-/
 *        |
 *        |   >     /-\
 *        |------> |NE | ---> NOT EQUAL
 *        |         \-/
 *        |
 *        |-----------------> LESS
 * 
 */

public final class L extends State {

	public void destroy() {
		handleVar = null;
	}
    private static L handleVar = null;
    private String token = "COMPARE_OPERATOR";
    private L() {
        initialize();
    }

    private void initialize() {
    }

    public static L handle() {
        if (handleVar == null) {
            handleVar = new L();
            //Prt.ln("\nL \"<\" -Zustand erzeugt !!!");
        }
        return handleVar;
    }

    protected void acceptGoosefeet(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SC.handle());
    };
    protected void acceptOther(Scanner a, char ch)
        throws CompilerErrorException {
        //changeState(a, Start.handle());
    }
    protected void acceptLetter(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, Word.handle());
    }
    protected void acceptDigit(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, IC.handle());
    };

    protected void acceptPrintable(Scanner a, char ch)
        throws CompilerErrorException {
        changeState(a, Ill.handle());
    }
    protected void acceptPlus(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }
    protected void acceptMinus(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }
    protected void acceptMul(Scanner a, char ch)
        throws CompilerErrorException {
 		changeState(a, Ill.handle());
    }
    protected void acceptDiv(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, C.handle());
    }
    protected void acceptEqual(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
        changeState(a, LE.handle());
    };
    protected void acceptGreater(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
        changeState(a, NE2.handle());
    };
    protected void acceptLess(Scanner a, char ch)
        throws CompilerErrorException {
 		changeState(a, Ill.handle());
    }
    protected void acceptPar_On(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }
    protected void acceptPar_Off(Scanner a, char ch)
        throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }
    protected void acceptK_On(Scanner a, char ch)
        throws CompilerErrorException {
		changeState(a, Ill.handle());
    }
    protected void acceptK_Off(Scanner a, char ch)
        throws CompilerErrorException {
		changeState(a, Ill.handle());
    }
    protected void acceptSemicolon(Scanner a, char ch)
        throws CompilerErrorException {
 		changeState(a, Ill.handle());
    }
    protected void acceptSpace(Scanner a, char ch)
        throws CompilerErrorException {
        //restore(a, ch, token);
        //changeState(a, Start.handle());
    };

    protected void acceptETX(Scanner a, char ch)
        throws CompilerErrorException {
        changeState(a, Ill.handle());
    }
    protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle());
    }
    protected void acceptNOT(Scanner a, char ch)
        throws CompilerErrorException {
 		changeState(a, Ill.handle());
    }
    protected void acceptKomma(Scanner a, char ch)
        throws CompilerErrorException {
		changeState(a, Ill.handle());
    };

    public static void main(String[] args) {
    }
}
