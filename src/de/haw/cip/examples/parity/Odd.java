package de.haw.cip.examples.parity;

import de.haw.cip.util.Prt;

public final class Odd extends State { // Ungerade Paritaet

	private static Odd handleVar = null;

	// Priv. Konstruktor gegen externe Objekt-Erzeugung
	private Odd() {
	}

	static Odd handle() {
		// Erzeugung der einzigen Instanz
		if (handleVar == null) {
			handleVar = new Odd();
			Prt.ln("\nOdd-Zustand erzeugt!!!\n");
		}
		// Liefert globalen Zugriffspunkt
		return handleVar;
	}

	@Override
	void acceptZero(Automaton a) {
		store(a, '0');
	}

	@Override
	void acceptOne(Automaton a) {
		store(a, '1');
		changeState(a, Even.handle());
	}

	@Override
	void acceptEtx(Automaton a) {
		restore(a, "Paritaet ungerade");
	}
}
