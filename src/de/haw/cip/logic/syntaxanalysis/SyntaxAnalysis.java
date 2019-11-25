package de.haw.cip.logic.syntaxanalysis;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.Symbol;
import de.haw.cip.logic.scanner.Scanner;
import de.mk.exception.CompilerErrorException;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author Marco Behnke und Martin Koose
 */
public class SyntaxAnalysis {

    private static SyntaxAnalysis syntaxanalysis = null;
    private final String var = "STRVAR";
    private Scanner scan;
    private String ptf;
    private boolean createPTF;
    private int sifc;
    private CIPWindow app;
    private int varcount = 0;

    private SyntaxAnalysis() {
    }

    private SyntaxAnalysis(Scanner s, boolean ptf) {
        createPTF = ptf;
        sifc = 0;
        this.ptf = "";
        this.scan = s;
    }

    private SyntaxAnalysis(Scanner s) {
        this(s, false);
    }

    public static SyntaxAnalysis getInstance() {
        if (syntaxanalysis == null)
            syntaxanalysis = new SyntaxAnalysis();
        return syntaxanalysis;
    }

    private CIPWindow getApp() {
        return this.app;
    }

    public void setApp(CIPWindow app) {
        this.app = app;
    }

    public void initialize(Scanner s, boolean createPtf) {
        this.createPTF = createPtf;
        sifc = 0;
        this.ptf = "";
        this.scan = s;
        this.varcount = 0;
    }

    private void checkParanthesis(int p_onoff, int k_onoff)
            throws CompilerErrorException {
        String result = "Fehler Syntaxanalyse!! Ungültige Klammerzahl\n";
        if ((p_onoff == 0) && (k_onoff == 0)) {
        } else {
            if (p_onoff > 0)
                result += " ( => " + (p_onoff) + " zu viel\n";
            if (p_onoff < 0)
                result += " ) => " + (-p_onoff) + " zu viel\n";
            if (k_onoff > 0)
                result += " { => " + (k_onoff) + " zu viel\n";
            if (k_onoff < 0)
                result += " } => " + (-k_onoff) + " zu viel\n";

            throw new CompilerErrorException(result);

        }

    }

    private void ptf(String s) {
        if (createPTF)
            ptf += s + " ";
    }

    private void ptf(int i) {
        if (createPTF)
            ptf += "" + i + " ";
    }

    private void checkIdentifierUsed() throws CompilerErrorException {
        String msg = "";
        Vector<String> vector = new Vector<>();
        Iterator it = scan.getSymbols().iterator();
        while (it.hasNext()) {
            Symbol next = (Symbol) it.next();
            if (!next.is_used()) {
                vector.addElement(next.get_identifier());
            }
        }

        if (!vector.isEmpty()) {
            msg = "WARNING: The following variables have been declared but never been used:\n";
            for (int i = 0; i < vector.size(); i++) {
                msg += "--> " + vector.elementAt(i) + "\n";
            }
            throw new CompilerErrorException(msg);
        }

    }

    //
    // 				AB   HIER    METHODEN für den rek. Abstieg
    //

    public void analyse() throws CompilerErrorException {
        // Klammerprüfung
        this.checkParanthesis(scan.p_onoff(), scan.k_onoff());
        this.programm();
        this.checkIdentifierUsed();

        this.getApp().printStatus("Syntaxanalyse beendet.");
        //Prt.ln(ptf);
    }

    //EBNF:		<Program>	::= <Ident> { [ <Declare> ; ]  <Statementlist> }
    private void programm() throws CompilerErrorException {
        this.ident();
        scan.mandatory("SPECIAL_CHAR", "{");
        this.declare();
        this.statementList();
        scan.mandatory("SPECIAL_CHAR", "}");

        lastVariable();
    }

    private void lastVariable() throws CompilerErrorException {
        String variable = this.var;
        String identifier = "'*'";
        boolean added = false;
        while (!added) {
            variable = this.var + (this.varcount++);
            if (scan.identifierExists(variable)) {
                scan.addSymbol(variable, "String");
                scan.assignSymbol(variable);
                scan.setSymbolInitialValue(variable, identifier);
                scan.setSymbolUsed(variable);
                added = true;
            }
        }
    }

    //EBNF:		<Ident>	::= <Letter> { <Letter> | <Digit> }
    private void ident() throws CompilerErrorException {
        scan.mandatory("IDENTIFIER");

    }

    //EBNF:		<Declare>	::= [ <ConstDeclare> ] [ <VarDeclare> ]
    private void declare() throws CompilerErrorException {
        this.constDeclare();
        this.varDeclare();

    }

    //EBNF:		<Statementlist>	::= <Statement> { ; <Statement> }
    private void statementList() throws CompilerErrorException {
        this.statement();
        ptf("\n");
        while (scan.optional("SPECIAL_CHAR", ";")) {
            this.statement();
            ptf("\n");
        }
    }

    //EBNF:		<VarDeclare>	::= <Type> <Ident> { , <Ident> } [ = [ - ] <ArithConst> ]
    private void varDeclare() throws CompilerErrorException {
        while (optionalType()) {
            Vector symbols = new Vector();
            this.ident();
            scan.addSymbol();
            symbols.add(scan.nSem());
            while (scan.optional("SPECIAL_CHAR", ",")) {
                this.ident();
                scan.addSymbol();
                symbols.add(scan.nSem());
            }
            if (scan.optional("ASSIGN")) {
                String prefix = "";
                if (scan.optional("SPECIAL_CHAR"))
                    prefix = "-";
                this.arithConst();
                for (Object symbol : symbols) {
                    String identifier = (String) symbol;
                    scan.assignSymbol(identifier);
                    scan.setSymbolInitialValue(identifier, prefix + scan.nSem());
                }
            }
            scan.mandatory("SPECIAL_CHAR");
        }

    }

    //EBNF:		<ConstDeclare>	::= const <Type> <Ident> = [ - ] <ArithConst>
    private void constDeclare() throws CompilerErrorException {
        while (scan.optional("RESERVED_WORD", "const")) {
            this.type();
            this.ident();
            scan.addSymbol();
            String symbol = scan.nSem();
            scan.mandatory("ASSIGN");
            scan.assignSymbol(symbol);
            String prefix = "";
            if (scan.optional("SPECIAL_CHAR"))
                prefix = "-";
            this.arithConst();
            scan.setSymbolInitialValue(symbol, prefix + scan.nSem());
            scan.mandatory("SPECIAL_CHAR");
        }
    }

    //EBNF:		<Type>	::= int
    private void type() throws CompilerErrorException {
        scan.mandatory("RESERVED_WORD");
    }

    private boolean optionalType() throws CompilerErrorException {
        boolean check = false;
        check = scan.optional("RESERVED_WORD", "int");
        return check;
    }

    //EBNF:		<Factor>	::= ( <Variable> | <ArithConst> | (<ArithExpr>) )
    private void factor() throws CompilerErrorException {
        if (scan.optional("IDENTIFIER")) {
            ptf(scan.nSem());
            if (scan.identifierExists(scan.nSem()))
                throw new CompilerErrorException("Variable '" + scan.nSem() + "' must be decared before use!");
            if (!scan.identifierIsAssigned(scan.nSem()))
                throw new CompilerErrorException("Variable '" + scan.nSem() + "' must be assigned a value before use!");
            scan.setSymbolUsed(scan.nSem());
        } else if (scan.optional("INTEGER_CONSTANT")) {
            ptf(scan.nSem());
        } else {
            scan.mandatory("SPECIAL_CHAR", "(");
            this.arithExpr();
            scan.mandatory("SPECIAL_CHAR", ")");
        }

    }

    //EBNF:		<Statement>	::= ( <ConditionalStatement> | <UnconditionalStatement> )
    private void statement() throws CompilerErrorException {
        if (this.conditionalStatement()) {
        } else
            this.unconditionalStatement();

    }

    //EBNF:		<ConditionalStatement>	::=
    //					if ( <Comparison> ) <UnconditionalStatement> [ else <Statement> ] ;
    //					| while ( <Comparison> ) <Statement> ;
    //					| do <Statement> while ( <Comparison> )
    private boolean conditionalStatement() throws CompilerErrorException {

        int loopCount = sifc;

        if (scan.optional("RESERVED_WORD", "if")) {
            // IF STATEMENT
            scan.mandatory("SPECIAL_CHAR", "(");
            this.comparison();
            scan.mandatory("SPECIAL_CHAR", ")");
            ptf(" \n BRF :EB_" + (loopCount++) + "\n");
            sifc++;
            sifc++;
            this.unconditionalStatement();
            ptf(" \n JMP :EE_" + (loopCount--) + "\n");
            if (scan.optional("RESERVED_WORD", "else")) {
                ptf(" \n :EB_" + (loopCount++) + "\n");
                this.statement();
                ptf(" \n :EE_" + (loopCount));
            }
            return true;
        } else if (scan.optional("RESERVED_WORD", "while")) {
            // WHILE STATEMENT
            ptf(" \n :WB_" + (loopCount++));
            scan.mandatory("SPECIAL_CHAR", "(");
            this.comparison();
            scan.mandatory("SPECIAL_CHAR", ")");

            sifc++;
            ptf(" \n BRF :WE_" + (loopCount--) + "\n");
            sifc++;
            this.statement();
            ptf(" \n JMP :WB_" + (loopCount++));
            ptf(" \n :WE_" + (loopCount));
            ptf("\n");
            return true;
        } else if (scan.optional("RESERVED_WORD", "do")) {
            // DO WHILE STATEMENT
            ptf(" \n :DWB_" + (loopCount++) + "\n");
            sifc++;
            sifc++;
            this.statement();
            scan.mandatory("RESERVED_WORD", "while");
            scan.mandatory("SPECIAL_CHAR", "(");
            this.comparison();
            scan.mandatory("SPECIAL_CHAR", ")");
            ptf(" \n BRF :DWE_" + (loopCount--));
            ptf(" \n JMP :DWB_" + (loopCount++));
            ptf(" \n :DWE_" + (loopCount));
            ptf("\n");
            return true;
        }

        return false;
    }

    //EBNF:		<UnconditionalStatement>	::= ( <AssignementStatement>
    //				| <WriteStatement>
    //				| <ReadStatement>
    //				| <CompoundStatement>
    //				| <EmptyStatement> )
    private void unconditionalStatement() throws CompilerErrorException {
        // Vorgriff fehlt auf n�chstes Zeichen.
        if (this.readStatement()) {
        } else if (this.writeStatement()) {
        } else if (this.assignStatement()) {
        } else if (this.compoundStatement()) {
        } else {
            this.emptyStatement();
        }
    }

    //EBNF:		<Comparison>	::= <ArithExpr> <CompareOperator> <ArithExpr>
    private void comparison() throws CompilerErrorException {
        String opt = "";
        this.arithExpr();
        this.compareOperator();
        opt = "" + scan.nSem();
        this.arithExpr();
        ptf(opt);
    }

    //EBNF		<AssignStatement>	::= <Variable> = <ArithExpr>:
    private boolean assignStatement() throws CompilerErrorException {
        if (scan.optional("IDENTIFIER")) {
            scan.assignSymbol(scan.nSem());
            ptf(scan.nSem());
            scan.mandatory("ASSIGN");
            this.arithExpr();
            ptf("=");
            return true;
        } else {
            return false;
        }
    }

    //EBNF:		<ReadStatement>	::= scanf ( <Variable> )
    private boolean readStatement() throws CompilerErrorException {
        if (scan.optional("RESERVED_WORD", "scanf")) {
            int paramc = 0;
            scan.mandatory("SPECIAL_CHAR", "(");
            this.variable();
            ptf(scan.nSem());
            paramc++;
            scan.mandatory("SPECIAL_CHAR", ")");
            ptf("scanf");
            //ptf(paramc++);
            return true;
        }
        return false;

    }

    //EBNF:		<WriteStatement>	::= printf ( <Expression> [ , <Expression> ] )
    private boolean writeStatement() throws CompilerErrorException {
        if (scan.optional("RESERVED_WORD", "printf")) {
            int paramc = 0;
            scan.mandatory("SPECIAL_CHAR", "(");
            this.expression();
            paramc++;
            while (scan.optional("SPECIAL_CHAR", ",")) {
                this.expression();
                paramc++;
            }
            scan.mandatory("SPECIAL_CHAR", ")");
            ptf(paramc);
            ptf("printf");
            return true;
        }
        return false;
    }

    //EBNF:		<CompoundStatement>	::= { <Statementlist> }
    private boolean compoundStatement() throws CompilerErrorException {
        if (scan.optional("SPECIAL_CHAR", "{")) {
            this.statementList();
            scan.mandatory("SPECIAL_CHAR", "}");
            return true;
        } else {
            return false;
        }
    }

    //EBNF:		<EmptyStatement>	::=
    private void emptyStatement() throws CompilerErrorException {
        //men this was easy :-)
    }

    //EBNF:		<CompareOperator>	::= ( == | <> | != | < | > | <= | >= )
    private void compareOperator() throws CompilerErrorException {
        scan.mandatoryCompareOperator();
    }

    //EBNF:		<ArithExpr>	::= [ + | - ] <Term> { ( + | - ) <Term> }
    private void arithExpr() throws CompilerErrorException {
        boolean sign = false;
        String addOp = "";
        boolean temp1 = scan.optional("SPECIAL_CHAR", "+");
        if (!temp1) {
            if (scan.optional("SPECIAL_CHAR", "-")) {
                sign = true;
            }
        }
        this.term();
        if (sign)
            ptf("@");
        while ((scan.optional("SPECIAL_CHAR", "+"))
                || (scan.optional("SPECIAL_CHAR", "-"))) {
            addOp = "" + scan.nSem();
            this.term();
            ptf(addOp);
        }

    }

    //EBNF:		<Variable>	::= <Ident>
    private void variable() throws CompilerErrorException {
        this.ident();
    }

    //EBNF:		<Expression>	::= ( <StrConst> | <ArithExpr> )
    private void expression() throws CompilerErrorException {
        if (scan.optional("STRING_CONSTANT")) {
            boolean added = false;
            String identifier = scan.nSem();
            String variable = this.var;
            while (!added) {
                variable = this.var + (this.varcount++);
                if (scan.identifierExists(variable)) {
                    scan.addSymbol(variable, "String");
                    scan.assignSymbol(variable);
                    identifier = "'" + identifier.substring(1, identifier.length() - 1) + "'";
                    scan.setSymbolInitialValue(variable, identifier);
                    scan.setSymbolUsed(variable);
                    added = true;
                }
            }

            ptf(variable);
        } else
            this.arithExpr();
    }

    //EBNF:		<Term>	::= <Factor> { ( * | / ) <Factor> }
    private void term() throws CompilerErrorException {
        String multOp = "";
        this.factor();
        while ((scan.optional("SPECIAL_CHAR", "*"))
                || (scan.optional("SPECIAL_CHAR", "/"))) {
            multOp = "" + scan.nSem();
            this.factor();
            ptf(multOp);
        }
    }

    //EBNF:		<ArithConst>	::= <IntConst>
    private void arithConst() throws CompilerErrorException {
        this.intConst();
    }

    //EBNF:		<IntConst>	::= <Digit> { <Digit> }
    private void intConst() throws CompilerErrorException {
        scan.mandatory("INTEGER_CONSTANT");
    }

    //EBNF:		<Printable ASCII Character >	::=
    //					<Digit>| <Letter> | <EscCharacter> | � | ^ | ! | $ | % | & | ( | )
    //					| = | ? | � | [ | ] | {	| } | . | , | ; | : | - | + | ` | � | < | >
    //					| @ | ~ | # |  | � | � |  _ | / | *
    private void printable() throws CompilerErrorException {

    }

    private void error(String msg) throws CompilerErrorException {

        throw new CompilerErrorException(
                "Fehler in der Syntaxanalyse:\n" + msg);

    }

    /**
     * Returns the ptf.
     *
     * @return String
     */
    public String getPtf() {
        //hier am besten schon die leerzeilen heraus nehmen!!
        //u.U eigen String classe in utils die Methoden aus semantic... enth�lt
        return ptf;
    }

}
