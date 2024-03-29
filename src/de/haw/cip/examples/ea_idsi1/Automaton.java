package de.haw.cip.examples.ea_idsi1;

import de.haw.cip.util.Prt;

import java.util.ArrayList;
import java.util.Iterator;

public class Automaton { // Der IDSI-Automat

    private State actState = null;
    private String name = "noName";
    private ArrayList buffer = new ArrayList();

    public Automaton(String n) { // Init auf Anfangszustand
        // => Erzeugung des Anfangszustandes
        actState = Other.handle();
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void accept(String actString) {

        Prt.ln("Eingabe: \"" + actString + "\"\n");

        for (int i = 0; i < actString.length(); i++) {

            char actChar = actString.charAt(i);

            // Klassifizierung des aktuellen Zeichens
            // (1)
            LsdClass actClass = LsdClass.arr[actChar];

            // Leider kein switch moeglich

            if (actClass == LsdClass.OTHER)
                actState.acceptOther(this, actChar);
            else if (actClass == LsdClass.LETTER)
                actState.acceptLetter(this, actChar);
            else if (actClass == LsdClass.SIGN)
                actState.acceptSign(this, actChar);
            else if (actClass == LsdClass.DIGIT)
                actState.acceptDigit(this, actChar);

            else
                Prt.ln("LsdClass-Error");

        } // End for

        actState.acceptOther(this, ' ');
        actState = Other.handle(); // Zustand = Anfangszustand !

    } // End acceptString()

    public void changeState(State newState) {
        // Aktualisierung des Zustands
        actState = newState;
    }

    public void store(char ch) {
        buffer.add(ch);
        // Prt.ln(buffer);
    }

    public void restore(char ch) {
        // ch ignored
        for (Object o : buffer) Prt.st(o.toString());
        Prt.ln();
        buffer.clear();
        Prt.ln("!!!");
    }

    public void restoreAndStore(char ch) {
        restore(ch);
        store(ch);//buffer.add(new Character(ch));
    }

    public void reset(char ch) {
        // ch ignored
        buffer.clear();
    }

    public void resetAndStore(char ch) {
        reset(ch);//buffer.add(new Character(ch));
        store(ch);
    }

    final static class LsdClass {

        // LsdClass ist eine innere Klasse des Automaten

        // Klassifizierung der ersten 128 Zeichen mittels arr
        final static LsdClass[] arr = new LsdClass[128];
        final static LsdClass // Erzeugung der einzig
                OTHER = new LsdClass(0); // moeglichen Objekte
                final static LsdClass LETTER = new LsdClass(1); // von lsdClass
                final static LsdClass SIGN = new LsdClass(2);
        final static LsdClass DIGIT = new LsdClass(3);
        final static LsdClass ETX = new LsdClass(4);

        static { // Static Block, siehe Kap. 4.4.4
            char c;
            for (c = 0; c < 128; ++c)
                arr[c] = OTHER;
            for (c = '0'; c <= '9'; ++c)
                arr[c] = DIGIT;
            for (c = 'A'; c <= 'Z'; ++c)
                arr[c] = LETTER;
            for (c = 'a'; c <= 'z'; ++c)
                arr[c] = LETTER;
            arr['+'] = SIGN;
            arr['-'] = SIGN;
            arr[0] = ETX; // ETX = End Of Text

        } // End static Initialisation

        private int value; // ungenutzte int-Repraesentation

        // privater Konstruktor
        // verhindert jede Erzeugung von Elementen von aussen
        private LsdClass(int v) {
            value = v;
        }

        public final int toInt() {
            return value;
        }

        // Zuordnung Zeichen  Klasse

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    } // End final class LsdClass

}
