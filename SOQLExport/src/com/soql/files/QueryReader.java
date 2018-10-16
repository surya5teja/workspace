package com.soql.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 */

/**
 * @author surteja
 *
 */
public class QueryReader {

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
	private static String queryStartString = "[SELECT";

	public static void generateCsvFile(String path, String fileName) {
		StringBuilder finalString = new StringBuilder();
		StringBuilder querySubString = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String st;
			int lineCount = 1;
			int queryLineNumber = 0;
			boolean isQueryEnd = true;
			while ((st = br.readLine()) != null) {
				if (st.contains(queryStartString) && st.trim().indexOf(']') > -1) {
					querySubString.append(st.trim().replace("[", "").replace("]", ""));
					queryLineNumber = lineCount;
					isQueryEnd = true;
					finalString.append(fileName + ",").append("\"" + querySubString + "\"" + ",")
							.append(queryLineNumber).append("\n");
					writeOutputFile(finalString);
					querySubString = new StringBuilder();
					finalString = new StringBuilder();
				} else if (st.contains(queryStartString) && isQueryEnd) {
					querySubString.append(st.trim().replace("[", ""));
					queryLineNumber = lineCount;
					isQueryEnd = false;

				}
				if (!isQueryEnd && !st.contains(queryStartString)) {
					querySubString.append(" " + st.trim().replace("]", ""));
					if (st.trim().indexOf(']') > -1) {
						finalString.append(fileName + ",").append("\"" + querySubString + "\"" + ",")
								.append(queryLineNumber).append("\n");
						writeOutputFile(finalString);
						isQueryEnd = true;
						querySubString = new StringBuilder();
						finalString = new StringBuilder();
					}
				}
				lineCount++;
			}
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
			if (fileObj.getName().contains(".CLS"))
				generateCsvFile(fileObj.getPath(), fileObj.getName());
		}
	}
}
