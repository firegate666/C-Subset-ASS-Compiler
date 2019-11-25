package de.haw.cip.logic;

import de.haw.cip.logic.scanner.State;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Automaton {

    protected char actChar = ' ';
    protected State actState = null;
    protected String name = "noName";
    protected ArrayList buffer = new ArrayList();
    private ArrayList states = new ArrayList();

    public Automaton(String n) {
        name = n;
    } // Init auf Anfangszustand

    public Automaton() {
        this("no name given");
    }

    public void resetScanner() {
        Iterator i = states.iterator();
        while (i.hasNext())
            ((State) i.next()).destroy();
        buffer.clear();
        actChar = ' ';
        actState = null;
    }

    public String getBufferCopy() {
        String result = "";
        Iterator it = buffer.iterator();
        while (it.hasNext()) result += it.next().toString();
        return result;
    }

    public abstract void accept(String actString) throws CompilerErrorException;


    public void changeState(State newState) {
        // Aktualisierung des Zustands
        actState = newState;
        if (!states.contains(newState))
            states.add(newState);
    }

    public void store(char ch) {
        buffer.add(new Character(ch));
    }

    public void restore(char ch) {
        Iterator it = buffer.iterator();
        Prt.st("TOKEN Found: ");
        while (it.hasNext())
            Prt.st(it.next().toString());
        Prt.ln();
        reset(ch);
    }

    public void restoreAndStore(char ch) {
        restore(ch);
        store(ch);
    }

    public void reset(char ch) {
        buffer.clear();
    }

    public void resetAndStore(char ch) {
        reset(ch);
        store(ch);
    }

}
