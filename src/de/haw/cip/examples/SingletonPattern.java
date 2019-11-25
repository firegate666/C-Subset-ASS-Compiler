package de.haw.cip.examples;


public class SingletonPattern {

    public static void main(String[] args) {

        Singleton s = Singleton.getHandle();
        System.out.println(s.getValue());

        Singleton s2 = Singleton.getHandle();
        s2.setValue(9);

        System.out.println(s.getValue());

        try {
            // Can't do this: compile-time error.
            // Singleton s3 = (Singleton)s2.clone();
        } catch (Exception e) {
        }
    }
}
