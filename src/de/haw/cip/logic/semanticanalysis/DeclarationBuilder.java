package de.haw.cip.logic.semanticanalysis;

import java.util.Iterator;

import de.haw.cip.logic.Singleton;
import de.haw.cip.logic.Symbol;
import de.haw.cip.logic.Symboltable;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DeclarationBuilder implements Singleton {

	private static DeclarationBuilder handleVar=null;
	
	private final String _STRINGCONST = "DC.B";
	private final String _INTCONST    = "DC.W";
	
	public static DeclarationBuilder handle() {
		if(handleVar==null)
			handleVar = new DeclarationBuilder();
		return handleVar;
	}

	public String getPrintfDeclare()  {
		String result = "*\tBEGIN PRINTF-DECLARE\n";
		result += "PRTSTRCLRLF\tEQU\t0\n";
		result += "PRTSTR\tEQU\t1\n";
		result += "NUMIN\tEQU\t4\n";
		result += "NUMOUT\tEQU\t3\n";
		result += "*\tEND PRINTF_DECLARE\n";
		return result;
	}

	private String getVarDeclare(Symbol symbol) throws CompilerErrorException {
		String result="";
		// IDENTIFIER
		result += symbol.get_identifier();

		if(symbol.get_type() == null ? "String" == null : symbol.get_type().equals("String")) result += "\t"+_STRINGCONST;
		else if(symbol.get_type() == null ? "int" == null : symbol.get_type().equals("int")) result += "\t"+_INTCONST;
		else throw new CompilerErrorException("Undefined type >"+symbol.get_type()+"< found");
		
		// VALUE
		result += "\t"+symbol.get_initialValue();

		return result;	
	}

	public String buildVarDeclare(Symboltable symbols)  throws CompilerErrorException {
		String result="*\tBEGIN VAR-DECLARE\n";
		Iterator it = symbols.iterator();

		while(it.hasNext() ) {
			result += getVarDeclare((Symbol)it.next() );
			result += "\n";	
		}
		
		result += "*\tEND VAR-DECLARE\n";
		result += "\tEND\t$1000\n";
		return result;
	}

	private DeclarationBuilder() {
	}

	

}
