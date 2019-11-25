package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class SPC extends State {

    private static SPC handleVar = null;
    private String token = "SPECIAL_CHAR";
    private SPC() {
        initialize();
    }

    public static SPC handle() {
        if (handleVar == null) {
            handleVar = new SPC();
            //Prt.ln("\nSPC -Zustand erzeugt !!!");
        }
        //Prt.ln("SPC-Zustand");
        return handleVar;
    }

    public static void main(String[] args) {
    }

    public void destroy() {
        handleVar = null;
    }

    private void initialize() {
    }

    protected void acceptGoosefeet(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SC.handle());
    }

    protected void acceptOther(Scanner a, char ch)
            throws CompilerErrorException {
        restore(a, ch, token);
        changeState(a, Start.handle());
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
    }

    protected void acceptPrintable(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Start.handle());
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
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptDiv(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, C.handle());
    }

    protected void acceptEqual(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, ASS.handle());
    }

    protected void acceptGreater(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, G.handle());
    }

    protected void acceptLess(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, L.handle());
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
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptK_Off(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptSemicolon(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptSpace(Scanner a, char ch)
            throws CompilerErrorException {
        restore(a, ch, token);
        changeState(a, Start.handle());
    }

    protected void acceptETX(Scanner a, char ch)
            throws CompilerErrorException {
        if (a.getBufferCopy().endsWith("}")) {
            restore(a, ch, token);
            changeState(a, End.handle());
        } else {
            changeState(a, Ill.handle());
        }
    }

    protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
        if (a.getBufferCopy().endsWith(";")) {
            restore(a, ch, token);
            changeState(a, Start.handle());
        } else if (a.getBufferCopy().endsWith("}")) {
            restore(a, ch, token);
            changeState(a, Start.handle());
        } else if (a.getBufferCopy().endsWith("{")) {
            restore(a, ch, token);
            changeState(a, Start.handle());
        } else {
            changeState(a, Ill.handle("No line break after " + a.getBufferCopy() + " allowed!!"));
        }
    }

    protected void acceptNOT(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, N.handle());
    }

    protected void acceptKomma(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }
}
