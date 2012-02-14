package de.haw.cip.examples.parity;

import de.haw.cip.util.Prt;

public final class Even extends State { // Gerader Paritaet

	private static Even handleVar = null;
	// Priv. Konstruktor gegen externe Objekt-Erzeugung
	private Even() {
	}

	static Even handle() {
		// Erzeugung der einzigen Instanz
		if (handleVar == null) {
			handleVar = new Even();
			Prt.ln("\nEven-Zustand erzeugt !!!\n");
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
		changeState(a, Odd.handle());
	}

	@Override
	void acceptEtx(Automaton a) {
		restore(a, "Paritaet gerade..");

	}

}
