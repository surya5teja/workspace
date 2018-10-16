package com.soql.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class XMLTagReader {

	private static String source = "src/input/";// Copy
	// the
	// file
	// location
	// where
	// source
	// files
	// are
	// residing
	private static String destination = "src/output/queries.csv";// Destination
																	// location
																	// where
																	// output
																	// files
																	// will
																	// be
																	// residing
	private static String queryStartStringPv = "<packageversion>";
	private static String queryEndStringPv = "</packageversion>";

	private static String apiStartAv = "<apiversion>";
	private static String apiEndAv = "</apiversion>";

	public static void generateCsvFile(String path, String fileName) throws FileNotFoundException, IOException {
		StringBuilder finalString = new StringBuilder();
		StringBuilder querySubString = new StringBuilder();
		BufferedReader br = null;
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/output/" + fileName)));
			br = new BufferedReader(new FileReader(path));
			String st;
			boolean isQueryEnd = false;
			while ((st = br.readLine()) != null) {

				if (st.contains(queryStartStringPv) && st.contains(queryEndStringPv)) {
					isQueryEnd = true;
					finalString.append(fileName + "\n");
					writeOutputFile(finalString);
				} else if (st.contains(queryStartStringPv) && !st.contains(queryEndStringPv)) {
					isQueryEnd = false;
				} else {
					writer.write(st + "\n");
				}

				if (!isQueryEnd && !st.contains(queryStartStringPv) && st.contains(queryEndStringPv)) {
					finalString.append(fileName + "\n");
					isQueryEnd = true;
					writeOutputFile(finalString);
				}

				if (isQueryEnd)
					break;

			}
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public static void writeOutputFile(StringBuilder finalString) throws IOException {
		File file = new File(destination);
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

	public static void main(String[] args) throws Exception {
		File directoryFolder = new File(source);
		File destinationPath = new File(destination);
		boolean exists = destinationPath.exists();
		if (exists) {
			destinationPath.delete();
		}
		for (File fileObj : directoryFolder.listFiles()) {
			if (fileObj.getName().contains(".xml"))
				generateCsvFile(fileObj.getPath(), fileObj.getName());
		}
	}
}
