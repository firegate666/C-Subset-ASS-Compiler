package de.haw.cip.examples.parity;

public abstract class State {
    // Schnittstellen-Klasse fuer die Zustaende

    // In allen Zustaenden zu implementieren:
    abstract void acceptZero(Automaton a);

    abstract void acceptOne(Automaton a);

    abstract void acceptEtx(Automaton a);

    // Transformiert die Aufrufe der Zustaende
    // in Aufrufe des Automaten, spez. Up-Casting newState

    void changeState(Automaton a, State newState) {
        a.changeState(newState);
    }

    void store(Automaton a, char ch) {
        a.store(ch);
    }

    void restore(Automaton a, String parity) {
        a.restore(parity);
    }

}
