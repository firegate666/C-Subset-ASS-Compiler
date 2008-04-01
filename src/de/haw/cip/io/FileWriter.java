package de.haw.cip.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter; 

/**
 * @author koose_m@informatik.haw-hamburg.de
 *
 */
public final class FileWriter {

	private FileWriter() {
	}

	public static void writeToFile(File f, String s) throws IOException {
		try {
			BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			buf.write(s.trim()/* .substring(0,s.length()-1  )*/);
			buf.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}