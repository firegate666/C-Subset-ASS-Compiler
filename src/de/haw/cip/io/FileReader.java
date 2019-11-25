package de.haw.cip.io;

import java.io.*;

/**
 * @author koose_m@informatik.haw-hamburg.de
 */
public final class FileReader {

    private FileReader() {
    }

    public static String getString(File f) throws IOException {
        String result = "";
        BufferedReader buf = null;

        buf = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        while (buf.ready()) {
            result += buf.readLine() + (char) 10; //append LF
        }

        buf.close();

        return result + (char) 0; //attach ETX

    }
}
