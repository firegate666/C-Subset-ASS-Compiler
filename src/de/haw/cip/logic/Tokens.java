package de.haw.cip.logic;

import java.util.ArrayList;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class Tokens {
	
	private ArrayList tokens;
	
	public void clear() {
		tokens.clear();
	}
	
	public Tokens() {
		tokens = new ArrayList();
	}
	
	public void addToken(String nSym,String nSem) {
		this.addToken(new Token(nSym,nSem));
	}
		
	
	public void addToken(Token token) {
		this.tokens.add(token );
	}
	
	public int size() {
		return tokens.size();
	}
	public boolean isEmpty() {
		return tokens.isEmpty();
	}

	public Token getToken(int i) {
		return (Token)tokens.get( i);
	}
	
	
	public static void main(String [] args) {
	}
}
