package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 * <p>
 * Integer Constant State of the automaton used for the scanner
 * <p>
 * d    /-\
 * ---> |IC | ---> INTEGER CONSTANT
 * \-/
 * | ^
 * | |
 * -
 * d
 */

public final class IC extends State {

    private static IC handleVar = null;
    private String token = "INTEGER_CONSTANT";
    private IC() {
        initialize();
    }

    public static IC handle() {
        if (handleVar == null) {
            handleVar = new IC();
            //Prt.ln("\nIC -Zustand erzeugt !!!");
        }
        return handleVar;
    }

    public void destroy() {
        handleVar = null;
    }

    private void initialize() {
    }

    protected void acceptGoosefeet(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }

    protected void acceptDigit(Scanner a, char ch)
            throws CompilerErrorException {
        store(a, ch);
    }

    protected void acceptOther(Scanner a, char ch)
            throws CompilerErrorException {
        //restore(a, ch, token);
        //changeState(a, Start.handle());
    }

    protected void acceptLetter(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }

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
        changeState(a, Ill.handle());
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
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptSpace(Scanner a, char ch)
            throws CompilerErrorException {
        //restore(a, ch, token);
        //changeState(a, Start.handle());
    }

    protected void acceptETX(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }

    protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle());
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
