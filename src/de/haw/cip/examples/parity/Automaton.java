package de.haw.cip.examples.parity;

import de.haw.cip.util.Prt;

import java.util.Iterator;
import java.util.Vector;

public class Automaton { // Der Parity-Automat

    private State actState = null;
    private String name = "noName";
    private Vector buffer = new Vector();

    public Automaton(String n) { // Init auf Anfangszustand
        // => Erzeugung des Anfangszustandes
        actState = Even.handle();
        name = n;
    }

    void changeState(State newState) {
        // Aktualisierung des Zustands
        actState = newState;
    }

    void store(char ch) {
        buffer.addElement(new Character(ch));
    }

    void restore(String parity) {
        Iterator it = buffer.iterator();
        Prt.st(name + " " + parity + ": ");
        while (it.hasNext())
            Prt.st(it.next().toString());
        Prt.ln();
        buffer.clear();
        actState = Even.handle(); // Zustand = Anfangszustand !
    }

    // Delegation der Aufrufe an die Zustands-Klasse

    void acceptZero() {
        actState.acceptZero(this);
    }

    void acceptOne() {
        actState.acceptOne(this);
    }

    void acceptEtx() {
        actState.acceptEtx(this);
    }

}
