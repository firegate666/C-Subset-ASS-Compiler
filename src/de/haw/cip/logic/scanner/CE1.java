package de.haw.cip.logic.scanner;

import de.haw.cip.logic.*;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m
 *
 *
 *        |----------------------------------> DIVISION 
 *        |
 *        |               *
 *  /    /-\   *    /-\  --->  /-\   /    /-\
 * ---> | C | ---> |CA |  o   |CE2| ---> |Sta|
 *       \-/        \-/  <---  \-/        \-/
 *        |         | ^
 *       /|         | |                    ^
 *        |          -                     |
 *        \/         o                     |
 *       /-\                               |
 *      |CE1|------------------------------|
 *       \-/
 *       | ^
 *       | |
 *        -
 *        o
 */
public final class CE1 extends State {
	public void destroy() {
		handleVar = null;
	}
	private CE1() {
		initialize();
	}
	
	private void initialize() {
	}

	
	private static CE1 handleVar = null;
	
	public static CE1 handle() {
		if(handleVar==null) {
			handleVar = new CE1();
			//Prt.ln("\nCE1 \"//\" -Zustand erzeugt !!!");
		}
		return handleVar;
	}
	
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException{};

	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n End of text found"));
	};

	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException{
		reset(a,ch);
		changeState(a,Start.handle());
	};

	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException{};

	public static void main(String[] args) {
	}
}
