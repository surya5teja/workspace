package com.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileRead {

	private static String source = "src/input/";// Copy
	// the
	// file
	// location
	// where
	// source
	// files
	// are
	// residing
	private static String destination = "src/output/output.txt";// Destination
																// location
																// where
																// output
																// files
																// will
																// be
																// residing

	public static void generateQuotes() {
		File directoryFolder = new File(source);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\surteja\\eclipse-workspace-j2ee\\PlatformTasksJava\\src\\input\\inputFile.txt"));
			String st;

			while ((st = br.readLine()) != null) {
				String newString = "\"" + st + "\"" + "\n";
				writeOutputFile(newString);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeOutputFile(String finalString) throws IOException {
		File file = new File(
				"C:\\Users\\surteja\\eclipse-workspace-j2ee\\PlatformTasksJava\\src\\input\\outputFile.txt");
		FileOutputStream fOut;
		try {
			fOut = new FileOutputStream(file, true);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write(finalString.toString());
			osw.flush();
			osw.close();
			fOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		generateQuotes();
	}

}
