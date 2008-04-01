package de.haw.cip.logic.scanner;

import java.util.Vector;

import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Word State of the automaton used for the scanner
 * 
 *  l    /-\  ---> RESERVERD WORD
 * ---> |Wor|
 *       \-/  ---> IDENTIFIER
 *       | ^
 *       | |
 *        -
 *       l|d 
 */ 
 
 public final class Word extends State {
	
	public void destroy() {
		handleVar = null;
	}
	private static Word handleVar = null;
	private String [] token = {"IDENTIFIER","RESERVED_WORD"};
	
	private Vector keywords = new Vector();
	
	private Word() {
		initialize();
	}
	
	protected void initialize() {
		keywords.add("const");
		keywords.add("int");
		keywords.add("if");
		keywords.add("else");
		keywords.add("while");
		keywords.add("do");
		keywords.add("printf");
		keywords.add("scanf");
	}

	public static Word handle() {
		if(handleVar==null) {
			handleVar = new Word();
			//Prt.ln("\nWord -Zustand erzeugt !!!");
		}
		return handleVar;
	}

	protected void tokenFound(Scanner a, char ch, boolean store) {
		String buffer = a.getBufferCopy();
		String token = "";

		if(keywords.contains(buffer)) {
			token = "RESERVED_WORD";
		} else {
			token = "IDENTIFIER";
		}
		
		if(store) {
			restoreAndStore(a,ch, token);
		} else {
			restore(a,ch, token);
		}
		
		
	}	

	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException {
		changeState(a, Ill.handle());
	};
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException{
	store(a,ch);
	};
	
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,false);
		changeState(a,Start.handle());
	}
	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
	};

	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException {
		changeState(a,Ill.handle());
	}
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,C.handle());
	}
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,ASS.handle());
	}
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,G.handle());
	}
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,L.handle());
	}
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,SPC.handle());
	}
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException
	{
		tokenFound(a,ch,false);
		changeState(a,Start.handle());
	};

	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException {
		changeState(a,Ill.handle());
	}
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException {
		changeState(a,Ill.handle());
	}
	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException {
		tokenFound(a,ch,true);
		changeState(a,N.handle());
	}
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException
	{
		tokenFound(a, ch, true);
		changeState(a, SPC.handle());
	};


}
