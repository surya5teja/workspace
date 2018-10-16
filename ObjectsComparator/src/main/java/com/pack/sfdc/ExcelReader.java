/**
 *
 */
package com.pack.sfdc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author surteja
 *
 */
public class ExcelReader {

	public static final String masterPath = "C:\\Users\\surteja\\eclipse-workspace-j2ee\\ObjectsComparator\\src\\main\\resources\\master.xlsx";
	public static final String childPath = "C:\\Users\\surteja\\eclipse-workspace-j2ee\\ObjectsComparator\\src\\main\\resources\\child.xlsx";

	public static void main(String[] args) throws IOException {
		try (FileInputStream master = new FileInputStream(new File(masterPath));
				FileInputStream child = new FileInputStream(new File(childPath));) {
			List<String> firstWorkbook = null;
			XSSFWorkbook masterWorkbook = new XSSFWorkbook(master);
			XSSFWorkbook childWorkbook = new XSSFWorkbook(child);
			int masterWorkbookSheetsCount = masterWorkbook.getNumberOfSheets();
			int childWorkbookSheetsCount = childWorkbook.getNumberOfSheets();

			for (int i = 0; i < masterWorkbookSheetsCount; i++) {
				XSSFSheet sheet = masterWorkbook.getSheetAt(i);
				firstWorkbook = new ArrayList<>();
				Iterator<Row> rowIterator1 = sheet.iterator();
				while (rowIterator1.hasNext()) {
					Row row = rowIterator1.next();
					Cell cell = row.getCell(1);
					System.out.println("cell value" + cell.getStringCellValue());
				}
			}

		} catch (Exception e) {
			System.out.println("Exception" + e);
		}
	}
}
