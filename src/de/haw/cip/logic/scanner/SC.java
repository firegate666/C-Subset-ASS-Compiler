package de.haw.cip.logic.scanner;

import de.haw.cip.logic.*;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class SC extends State {

	public void destroy() {
		handleVar = null;
	}
    private static SC handleVar = null;
    private String token = "STRING_CONSTANT";
    private SC() {
        initialize();
    }

    private void initialize() {
    }

    public static SC handle() {
        if (handleVar == null) {
            handleVar = new SC();
            //Prt.ln("\nSC \"\"\" -Zustand erzeugt !!!");
        }
        return handleVar;
    }

    protected void acceptGoosefeet(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
        changeState(a, SCE.handle());
    };
    protected void acceptDigit(Scanner a, char ch) {
        store(a, ch);
    };
    protected void acceptLetter(Scanner a, char ch) {
        store(a, ch);
    };
    protected void acceptOther(Scanner a, char ch) {
        store(a, ch);
    };

    protected void acceptPrintable(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptPlus(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptMinus(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptMul(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptDiv(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptEqual(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptGreater(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptLess(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptPar_On(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptPar_Off(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptK_On(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptK_Off(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptSemicolon(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptSpace(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptETX(Scanner a, char ch)
        throws CompilerErrorException {
        changeState(a, Ill.handle());
    };
    protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle());
    };

    protected void acceptNOT(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };
    protected void acceptKomma(Scanner a, char ch)
        throws CompilerErrorException {
        store(a, ch);
    };

    public static void main(String[] args) {
    }
}
