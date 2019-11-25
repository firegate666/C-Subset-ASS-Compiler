package de.haw.cip.logic.scanner;

import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 * <p>
 * !    /-\   =    /-\
 * ---> | N | ---> |NE1| ---> NOT EQUAL
 * \-/        \-/
 */

public final class NE1 extends State {

    private static NE1 handleVar = null;
    private String token = "COMPARE_OPERATOR";
    private NE1() {
        initialize();
    }

    public static NE1 handle() {
        if (handleVar == null) {
            handleVar = new NE1();
            Prt.ln("\nNE1 \"!=\" -Zustand erzeugt !!!");
        }
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
        //restore(a, ch, token);
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
        changeState(a, Ill.handle());
    }

    protected void acceptDiv(Scanner a, char ch)
            throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, C.handle());
    }

    protected void acceptEqual(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }

    protected void acceptGreater(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }

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
        changeState(a, Ill.handle());
    }

    protected void acceptKomma(Scanner a, char ch)
            throws CompilerErrorException {
        changeState(a, Ill.handle());
    }
}
