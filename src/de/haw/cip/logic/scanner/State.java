package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * <p>Überschrift: Compiler & Interpreter Praktikum</p>
 * <p>Beschreibung: Compiler für Subset of Pascal</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Organisation: </p>
 *
 * @author unbekannt
 * @version 1.0
 */

public abstract class State {
    // Schnittstellen-Klasse fuer die Zustaende

    // In allen Zustaenden zu implementieren:
    protected abstract void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptOther(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptLetter(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptDigit(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptPrintable(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptPlus(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptMinus(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptMul(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptDiv(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptEqual(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptGreater(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptLess(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptPar_On(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptK_On(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptK_Off(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptSpace(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptETX(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptLF(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptNOT(Scanner a, char ch) throws CompilerErrorException;

    protected abstract void acceptKomma(Scanner a, char ch) throws CompilerErrorException;

    public abstract void destroy();

    // Transformiert die Aufrufe der Zustaende
    // in Aufrufe des Automaten, spez. Up-Casting newState

    protected void changeState(Scanner a, State newState) {
        a.changeState(newState);
    }

    protected void reset(Scanner a, char ch) {
        a.reset(ch);
    }

    protected void resetAndStore(Scanner a, char ch) {
        a.resetAndStore(ch);
    }

    protected void restore(Scanner a, char ch, String token) {
        a.restore(ch, token);
    }

    protected void restoreAndStore(Scanner a, char ch, String token) {
        a.restoreAndStore(ch, token);
    }

    protected void store(Scanner a, char ch) {
        a.store(ch);
    }

}
