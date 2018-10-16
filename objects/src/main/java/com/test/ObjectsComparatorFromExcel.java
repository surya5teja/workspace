/**
 *
 */
package com.test;

/**
 * @author surteja
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author surteja
 *
 */
public class ObjectsComparatorFromExcel {

	public static final String MASTER_PATH = "./src/main/resources/master.xlsx";
	public static final String CHILD_PATH = "C:\\Users\\surteja\\eclipse-workspace-j2ee\\ObjectsComparator\\src\\main\\resources\\child.xlsx";
	public static final String LABEL = "Name";
	public static final String USED = "Used";

	public static void listCreator(XSSFSheet sheet, int labelColumnIndex, int usedColumnIndex, List<String> workbook) {
		Iterator<Row> rowIterator1 = sheet.iterator();
		while (rowIterator1.hasNext()) {
			Row row = rowIterator1.next();
			Cell labelCellValue = row.getCell(labelColumnIndex);
			Cell usedCellValue = row.getCell(usedColumnIndex);
			if (labelCellValue != null && !labelCellValue.getStringCellValue().equals("")
					&& !labelCellValue.getStringCellValue().equalsIgnoreCase(LABEL)
					&& usedCellValue.getBooleanCellValue())
				workbook.add(labelCellValue.getStringCellValue());
		}
	}

	public static void main(String[] args) throws IOException {
		try (FileInputStream master = new FileInputStream(new File(MASTER_PATH));
				FileInputStream child = new FileInputStream(new File(CHILD_PATH));) {
			List<String> firstWorkbook = null;
			List<String> secondWorkbook = null;
			XSSFWorkbook masterWorkbook = new XSSFWorkbook(master);
			XSSFWorkbook childWorkbook = new XSSFWorkbook(child);
			int masterWorkbookSheetsCount = masterWorkbook.getNumberOfSheets();
			int childWorkbookSheetsCount = childWorkbook.getNumberOfSheets();
			Map<String, List<String>> firstMap = new LinkedHashMap<>();
			Map<String, List<String>> secondMap = new LinkedHashMap<>();
			for (int i = 0; i < masterWorkbookSheetsCount; i++) {
				XSSFSheet sheet = masterWorkbook.getSheetAt(i);
				System.out.println(sheet.getSheetName());
				firstWorkbook = new ArrayList<>();

				int labelColumnIndex = 0;
				int usedColumnIndex = 0;
				Row firstRow = sheet.getRow(0);

				for (Cell cell : firstRow) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(LABEL)) {
							labelColumnIndex = cell.getColumnIndex();
						}
						if (cell.getRichStringCellValue().getString().trim().equals(USED)) {
							usedColumnIndex = cell.getColumnIndex();
						}
						if (labelColumnIndex == 0 && usedColumnIndex > 0)
							break;
					}

				}
				if (labelColumnIndex == 0 && usedColumnIndex > 0) {
					listCreator(sheet, labelColumnIndex, usedColumnIndex, firstWorkbook);
					firstMap.put(sheet.getSheetName(), firstWorkbook);
				}

			}

			for (int j = 0; j < childWorkbookSheetsCount; j++) {
				XSSFSheet sheet = childWorkbook.getSheetAt(j);
				sheet.getSheetName();
				secondWorkbook = new ArrayList<>();
				int labelColumnIndex = 0;
				int usedColumnIndex = 0;
				Row firstRow = sheet.getRow(0);

				for (Cell cell : firstRow) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(LABEL)) {
							labelColumnIndex = cell.getColumnIndex();
						}
						if (cell.getRichStringCellValue().getString().trim().equals(USED)) {
							usedColumnIndex = cell.getColumnIndex();
						}
						if (labelColumnIndex == 0 && usedColumnIndex > 0)
							break;
					}

				}
				if (labelColumnIndex == 0 && usedColumnIndex > 0) {
					listCreator(sheet, labelColumnIndex, usedColumnIndex, secondWorkbook);
					secondMap.put(sheet.getSheetName(), secondWorkbook);
				}
			}

			System.out.println("first" + firstMap.size() + "sec" + secondMap.size());
			XSSFWorkbook newWork = new XSSFWorkbook();
			for (String custmObj : firstMap.keySet()) {
				List<String> l3 = new ArrayList<>();
				List<String> l1 = firstMap.get(custmObj) != null ? firstMap.get(custmObj) : new ArrayList<>();
				List<String> l2 = secondMap.get(custmObj) != null ? secondMap.get(custmObj) : new ArrayList<>();
				if (!l1.isEmpty() && !l2.isEmpty()) {
					l3.addAll(l2);
					l3.removeIf(u -> l1.contains(u));
					if (!l3.isEmpty()) {
						System.out.println(custmObj + ":" + l3.size());
						XSSFSheet newSheet = newWork.createSheet(custmObj);
						int rowNum3 = 0;
						for (String s3 : l3) {
							Row row = newSheet.createRow(rowNum3++);
							Cell cell3 = row.createCell(2);
							cell3.setCellValue(s3);
						}

					}
				}

			}
			FileOutputStream fout = new FileOutputStream(
					"C:\\\\Users\\\\surteja\\\\eclipse-workspace-j2ee\\\\ObjectsComparator\\\\src\\\\main\\\\resources\\\\final.xlsx");

			newWork.write(fout);
		} catch (

		Exception e) {
			System.out.println("Exception" + e);
		}
	}
}
