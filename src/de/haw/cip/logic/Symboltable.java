package de.haw.cip.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author behnke_m
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class Symboltable {

    private ArrayList symbols;

    public Symboltable() {
        symbols = new ArrayList();
    }

    public static void main(String[] args) {
        Symboltable test = new Symboltable();
        System.out.println("add: " + test.addSymbol("hallo", "int"));
        System.out.println("add: " + test.addSymbol("multiplier", "int"));
        System.out.println("add: " + test.addSymbol("hallo", "float"));
        System.out.println("is in: " + test.identifierExists("hallo"));
        System.out.println("is in: " + test.identifierExists("hold"));
        System.out.println(test.toString());
    }

    public Vector rowData() {
        Vector result = new Vector();
        Iterator it = this.iterator();
        while (it.hasNext())
            result.add(((Symbol) it.next()).rowData());
        return result;
    }

    public Vector columnNames() {
        Vector result = new Vector();
        result.add("ID");
        result.add("TYPE");
        result.add("ASSIGNED");
        result.add("INI-VAL");
        result.add("USED");
        return result;
    }

    public void clear() {
        symbols.clear();
    }

    public boolean addSymbol(String identifier, String type) {
        return this.addSymbol(new Symbol(identifier, type));
    }

    public void assignSymbol(String identifier) {
        getSymbol(identifier).set_assigned(true);
    }

    public void setInitialValue(String identifier, String value) {
        getSymbol(identifier).set_initialValue(value);
    }

    public Symbol getSymbol(String identifier) {
        for (int i = 0; i < this.size(); i++) {
            if (this.getSymbol(i).get_identifier().equals(identifier))
                return this.getSymbol(i);
        }
        return null;
    }

    private boolean addSymbol(Symbol symbol) {
        if (this.size() == 0) {
            symbols.add(symbol);
            return true;
        }

        if (!this.identifierExists(symbol)) {
            symbols.add(symbol);
            return true;
        } else {
            return false;
        }
    }

    public Iterator iterator() {
        return symbols.iterator();
    }

    private int size() {
        return symbols.size();
    }

    @Override
    public String toString() {
        String result = "ID\tTYPE\tASSIGNED\tINI-VAL\tUSED\n";
        for (int i = 0; i < this.size(); i++) {
            result += getSymbol(i).toString() + "\n";
        }
        return result;
    }

    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Method getSymbol.
     *
     * @param i
     * @return Symbol
     */
    private Symbol getSymbol(int i) {
        return (Symbol) symbols.get(i);
    }

    public boolean identifierIsAssigned(String identifier) {
        return this.getSymbol(identifier).is_assigned();
    }

    public boolean identifierIsUsed(String identifier) {
        return this.getSymbol(identifier).is_used();
    }

    private boolean identifierExists(Symbol symbol) {
        return this.identifierExists(symbol.get_identifier());
    }

    public boolean identifierExists(String identifier) {
        boolean result = false;
        int counter = 0;
        int size = this.size();

        if (size == 0) return result;

        do {
            if (this.getSymbol(counter).get_identifier().equals(identifier)) {
                result = true;
            }
            counter++;
        } while (!result && counter < size);

        return result;

    }
}
