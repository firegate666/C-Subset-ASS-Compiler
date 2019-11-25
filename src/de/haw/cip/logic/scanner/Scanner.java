package de.haw.cip.logic.scanner;

/**
 * @author Martin Koose
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.Automaton;
import de.haw.cip.logic.Symboltable;
import de.haw.cip.logic.Token;
import de.haw.cip.logic.Tokens;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

import java.util.Iterator;
import java.util.Vector;

public class Scanner extends Automaton {


    private char actChar = ' ';
    private int nextToken = 0;
    private int lastToken = 0;
    private String nSym = "";
    private String nSem = "";
    private int p_onoff = 0;
    private int k_onoff = 0;
    private LsdClass actClass = null; // (1)
    private Vector token = new Vector();
    private Tokens tokens = new Tokens();
    private Symboltable symbols = new Symboltable();
    private CIPWindow app;
    public Scanner(String n) { // Init auf Anfangszustand
        // => Erzeugung des Anfangszustandes
        super(n);
        actState = Start.handle();
    }

    public Scanner() {
        this("StandardScannerName");
    }

    public static void main(String[] args) throws CompilerErrorException {
        Scanner test = new Scanner("Testautomat");
        String s = "programmname{int name = 17;}" + (char) 0;
        Prt.ln("String: " + s);
        Prt.ln("String: " + s);
        test.accept(s);
    }

    public void resetScanner() {
        super.resetScanner();
        actChar = ' ';
        actState = Start.handle();
        nextToken = 0;
        lastToken = 0;
        nSym = "";
        nSem = "";
        p_onoff = 0;
        k_onoff = 0;
        tokens.clear();
        symbols.clear();
    }

    public CIPWindow getApp() {
        return this.app;
    }

    public void setApp(CIPWindow app) {
        this.app = app;
    }

    public String getSymbolsAsString() {
        return getSymbols().toString();
    }

    public Symboltable getSymbols() {
        return symbols;
    }

    public int p_onoff() {
        return p_onoff;
    }

    public int k_onoff() {
        return k_onoff;
    }

    public void accept(String actString) throws CompilerErrorException {

        String result = "";

        for (int i = 0; i < actString.length(); i++) {

            actChar = actString.charAt(i);

            // Klassifizierung des aktuellen Zeichens
            actClass = LsdClass.arr[actChar];

            // Leider kein switch moeglich

            if (actClass == LsdClass.OTHER)
                actState.acceptOther(this, actChar);
            else if (actClass == LsdClass.LETTER)
                actState.acceptLetter(this, actChar);
            else if (actClass == LsdClass.DIGIT)
                actState.acceptDigit(this, actChar);
            else if (actClass == LsdClass.PRINTABLE)
                actState.acceptPrintable(this, actChar);
            else if (actClass == LsdClass.PLUS)
                actState.acceptPlus(this, actChar);
            else if (actClass == LsdClass.MINUS)
                actState.acceptMinus(this, actChar);
            else if (actClass == LsdClass.MUL)
                actState.acceptMul(this, actChar);
            else if (actClass == LsdClass.DIV)
                actState.acceptDiv(this, actChar);
            else if (actClass == LsdClass.EQUAL)
                actState.acceptEqual(this, actChar);
            else if (actClass == LsdClass.GREATER)
                actState.acceptGreater(this, actChar);
            else if (actClass == LsdClass.LESS)
                actState.acceptLess(this, actChar);
            else if (actClass == LsdClass.PAR_ON)
                actState.acceptPar_On(this, actChar);
            else if (actClass == LsdClass.PAR_OFF)
                actState.acceptPar_Off(this, actChar);
            else if (actClass == LsdClass.K_ON)
                actState.acceptK_On(this, actChar);
            else if (actClass == LsdClass.K_OFF)
                actState.acceptK_Off(this, actChar);
            else if (actClass == LsdClass.SEMICOLON)
                actState.acceptSemicolon(this, actChar);
            else if (actClass == LsdClass.SPACE)
                actState.acceptSpace(this, actChar);
            else if (actClass == LsdClass.ETX)
                actState.acceptETX(this, actChar);
            else if (actClass == LsdClass.LF)
                actState.acceptLF(this, actChar);
            else if (actClass == LsdClass.NOT)
                actState.acceptNOT(this, actChar);
            else if (actClass == LsdClass.GOOSEFEET)
                actState.acceptGoosefeet(this, actChar);
            else if (actClass == LsdClass.KOMMA)
                actState.acceptKomma(this, actChar);
            else
                Prt.ln("LsdClass-Error");
        }
        if (actState == End.handle())
            getApp().printStatus("Der Scanner ist fertig und alle Tokens gelesen.");
        changeState(Start.handle());

    } // End acceptString()

    public void restore(char ch, String foundToken) {
        Iterator it = buffer.iterator();
        String temp = "";
        //Prt.st("TOKEN Found: ");
        while (it.hasNext())
            temp += it.next().toString();
        //Prt.st(temp);
        //System.out.print(" ==> " + foundToken);
        //Prt.ln();

        // Klammerpr�fung
        if (temp.equals("("))
            p_onoff++;
        if (temp.equals(")"))
            p_onoff--;
        if (temp.equals("{"))
            k_onoff++;
        if (temp.equals("}"))
            k_onoff--;
        // Klammerpr�fung Ende

        this.tokens.addToken(new Token(foundToken, temp));
        reset(ch);
    }


    public Tokens getTokens() {
        return this.tokens;
    }

    public void restoreAndStore(char ch, String foundToken) {
        restore(ch, foundToken);
        store(ch);
    }


    public String getTokensAsString() {
        String result = "";
        int size = tokens.size();
        Token nextToken;
        for (int i = 0; i < size; i++) {
            nextToken = tokens.getToken(i);
            result += nextToken.getNSym();
            result += " ==> ";
            result += nextToken.getNSem();
            result += "\n";
        }
        return result;
    }


    public void scan() {
        if (nextToken < tokens.size()) {
            this.nSem = tokens.getToken(nextToken).getNSem();
            this.nSym = tokens.getToken(nextToken).getNSym();
            nextToken++;
        }
    }


    public String nSem() {
        return nSem;
    }

    public void mandatory(String mand) throws CompilerErrorException {
        //Prt.ln("mandatory: "+mand);
        this.scan();
        //Prt.ln("nSym: "+ nSym + "\tmand: "+mand);
        if (nSym.equals(mand)) {
        } else {

            throw new CompilerErrorException(
                    "Fehler SyntaxAnalyse!!"
                            + "\nGefunden: nSym: "
                            + nSym
                            + "\nErwartet: nSym: "
                            + mand);

        }
    }

    public void mandatoryCompareOperator() throws CompilerErrorException {
        String errMsg = "Fehler SyntaxAnalyse!!"
                + "\nGefunden: nSym: "
                + nSym
                + "\tnSem: "
                + nSem
                + "\nErwartet: nSym: "
                + "COMPARE_OPERATOR";
        this.scan();
        if (nSym.equals("COMPARE_OPERATOR")) {
        } else {
            if (nSym.equals("ASSIGN")) errMsg += "\nTry == instead of =";
            throw new CompilerErrorException(errMsg);
        }
    }

    public void mandatory(String mand, String value)
            throws CompilerErrorException {
        this.scan();
        if ((nSym.equals(mand)) && (nSem.equals(value))) {
        } else {

            throw new CompilerErrorException(
                    "Fehler SyntaxAnalyse!!"
                            + "\nGefunden: nSym: "
                            + nSym
                            + "\tnSem: "
                            + nSem
                            + "\nErwartet: nSym: "
                            + mand
                            + "\tnSem: "
                            + value);

        }
    }

    private void firstToken() {
        if (this.nSem.equals("")) {
            this.scan();
        }
    }

    public void addSymbol(String identifier, String type) throws CompilerErrorException {
        if (!symbols.addSymbol(identifier, type))
            throw new CompilerErrorException
                    ("IDENTIFIER "
                            + identifier
                            + " konnte nicht angelegt werden, da bereits eine Variable mit diesem Namen exisitiert!");
    }

    public void addSymbol(String identifier) throws CompilerErrorException {
        this.addSymbol(identifier, "int");
    }

    public void addSymbol() throws CompilerErrorException {
        this.addSymbol(nSem);
    }


    public void setSymbolUsed(String identifier) throws CompilerErrorException {
        if (symbols.identifierExists(identifier)) {
            symbols.getSymbol(identifier).set_used(true);
        } else {
            throw new CompilerErrorException
                    ("IDENTIFIER "
                            + nSem
                            + " muss vor der Benutzung deklariert werden!");
        }
    }

    public void setSymbolInitialValue(String identifier, String value) throws CompilerErrorException {
        if (symbols.identifierExists(identifier)) {
            symbols.setInitialValue(identifier, value);
        } else {
            throw new CompilerErrorException
                    ("IDENTIFIER "
                            + nSem
                            + " muss vor der Initialisierung deklariert werden!");
        }
    }

    public boolean identifierExists(String identifier) {
        return symbols.identifierExists(identifier);
    }

    public boolean identifierIsAssigned(String identifier) {
        return symbols.identifierIsAssigned(identifier);
    }

    public boolean identifierIsUsed(String identifier) {
        return symbols.identifierIsUsed(identifier);
    }

    public void assignSymbol(String identifier) throws CompilerErrorException {
        if (symbols.identifierExists(identifier)) {
            symbols.assignSymbol(identifier);
        } else {
            throw new CompilerErrorException
                    ("IDENTIFIER "
                            + nSem
                            + " muss vor der Benutzung deklariert werden!");
        }
    }


    public boolean optional(String opt) {
        this.scan();

        //Prt.ln("nSym: "+ nSym + "\topt: "+opt);
        if (nSym.equals(opt)) {
            return true;
        } else {
            nextToken--;
            return false;
        }
    }

    public boolean optional(String opt, String value) {
        boolean result = optional(opt);
        if (result) {
            if (nSem.equals(value)) {
                return true;
            } else {
                nextToken--;
            }
        }
        return false;
    }

    final static class LsdClass {

        // LsdClass ist eine innere Klasse des Automaten

        // Klassifizierung der ersten 128 Zeichen mittels arr
        public final static LsdClass[] arr = new LsdClass[256];
        public final static LsdClass // Erzeugung der einzig
                DIGIT = new LsdClass(0), // m�glichen Objekte
                LETTER = new LsdClass(1), // von lsdClass
                PLUS = new LsdClass(2),
                MINUS = new LsdClass(3),
                MUL = new LsdClass(4),
                DIV = new LsdClass(5),
                EQUAL = new LsdClass(6),
                SEMICOLON = new LsdClass(7),
        // ;
        GREATER = new LsdClass(8),
                LESS = new LsdClass(9),
                PAR_ON = new LsdClass(10),
        // (
        PAR_OFF = new LsdClass(11), // )
                OTHER = new LsdClass(12), K_ON = new LsdClass(13), // {
                K_OFF = new LsdClass(14), // }
                GOOSEFEET = new LsdClass(15), // '
                PRINTABLE = new LsdClass(16),
                SPACE = new LsdClass(17),
                ETX = new LsdClass(18),
        // End of Text
        LF = new LsdClass(19), // Line feed
                NOT = new LsdClass(20), // !
                KOMMA = new LsdClass(21); // ,

        // Zuordnung Zeichen  Klasse
        //Alle m�glichen Eingaben des Automaten!!!!
        static { // Static Block, siehe Kap. 4.4.4
            char c;
            for (c = 0; c <= 32; ++c)
                arr[c] = OTHER;
            for (c = 128; c <= 255; ++c)
                arr[c] = OTHER;
            for (c = 32; c < 128; ++c)
                arr[c] = PRINTABLE;
            for (c = '0'; c <= '9'; ++c)
                arr[c] = DIGIT;
            for (c = 'A'; c <= 'Z'; ++c)
                arr[c] = LETTER;
            for (c = 'a'; c <= 'z'; ++c)
                arr[c] = LETTER;
            arr['+'] = PLUS;
            arr['-'] = MINUS;
            arr['*'] = MUL;
            arr['/'] = DIV;
            arr['='] = EQUAL;
            arr['>'] = GREATER;
            arr['<'] = LESS;
            arr['('] = PAR_ON;
            arr[')'] = PAR_OFF;
            arr['{'] = K_ON;
            arr['}'] = K_OFF;
            arr[';'] = SEMICOLON;
            arr[' '] = SPACE;
            arr['!'] = NOT;
            arr[0] = ETX; // ETX = End Of Text
            arr[10] = LF;
            arr[13] = LF;
            arr[34] = GOOSEFEET; // "
            arr[','] = KOMMA;

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

        public String toString() {
            return String.valueOf(value);
        }
    } // End final class LsdClass

}
