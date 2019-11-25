package de.haw.cip.logic.semanticanalysis;

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


    private void error(String s) throws CompilerErrorException {
        throw new CompilerErrorException("[CODEERZEUGUNG] Falsches Zeichen erhalten:" + s);
    }

    void acceptMark(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptOperand(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptPlus(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptMinus(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptMul(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptDiv(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptBRF(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptJMP(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptPrint(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptScan(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptCMP(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptPreMinus(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    void acceptAssign(CodeBuilder a, String s)
            throws CompilerErrorException {
        this.error(s);
    }

    public abstract void destroy();

    // Transformiert die Aufrufe der Zustaende
    // in Aufrufe des Automaten, spez. Up-Casting newState

    void changeState(CodeBuilder cb, State newState) {
        cb.changeState(newState);
    }

    protected void reset(CodeBuilder cb, String s) {
        cb.reset(s);
    }

    protected void resetAndStore(CodeBuilder cb, String s) {
        cb.resetAndStore(s);
    }

    protected void restore(CodeBuilder cb, String s, String token) {
        cb.restore(s, token);
    }

    protected void restoreAndStore(CodeBuilder cb, String s, String tokens) {
        cb.restoreAndStore(s, tokens);
    }

    protected void store(CodeBuilder cb, String s) {
        cb.store(s);
    }

}
