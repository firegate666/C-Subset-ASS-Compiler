package de.haw.cip.examples;

public final class Singleton {
	private static Singleton s = new Singleton(47);
	private int i;
	private Singleton(int x) {
		i = x;
	}

	public static Singleton getHandle() {
		return s;
	}

	public int getValue() {
		return i;
	}

	public void setValue(int x) {
		i = x;
	}
}