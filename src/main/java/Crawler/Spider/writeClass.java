package Crawler.Spider;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Crawler.control.ControlClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writeClass {

	private static final String FILENAME = ControlClass.getLogLocation(); //"C:\\Users\\bosco\\Desktop\\test.txt";

	public void appendContent(int linksVisited, String message) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String data = linksVisited + " Visited: " + message;
			File file = new File(FILENAME);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(data);
			bw.newLine();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	
}

	