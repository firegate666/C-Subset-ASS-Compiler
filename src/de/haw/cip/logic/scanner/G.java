package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 * <p>
 * Greater ">" State of the automaton used for the scanner
 * <p>
 * >    /-\   =    /-\
 * ---> | G | ---> |GE | ---> GREATER EQUAL
 * \-/        \-/
 * |
 * |-----------------> GREATER
 */

public final class G extends State {

    private static G handleVar = null;
    private String token = "COMPARE_OPERATOR";
    private G() {
        initialize();
    }

    public static G handle() {
        if (handleVar == null) {
            handleVar = new G();
            //Prt.ln("\nG \">\" -Zustand erzeugt !!!");
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

    protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SC.handle());
    }

    protected void acceptOther(Scanner a, char ch) throws CompilerErrorException {
    }

    protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, Word.handle());
    }

    protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, IC.handle());
    }

    protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n PrintableASCII found"));
    }

    protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptMul(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n * after > not allowed"));
    }

    protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, C.handle());
    }

    protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException {
        store(a, ch);
        changeState(a, GE.handle());
    }

    protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n > after > not allowed. (Maybe <>?)"));
    }

    protected void acceptLess(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n < after > not allowed (Maybe <>?)"));
    }

    protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException {
        restoreAndStore(a, ch, token);
        changeState(a, SPC.handle());
    }

    protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n { after > not allowed"));
    }

    protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n } after > not allowed"));
    }

    protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n ; after > not allowed"));
    }

    protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException {
    }

    protected void acceptETX(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n End of text found"));
    }

    protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n Line feed found"));
    }

    protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n ! after > not allowed"));
    }

    protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException {
        changeState(a, Ill.handle("Bufferdump: " + a.getBufferCopy() + "\n , after > not allowed"));
    }
}
