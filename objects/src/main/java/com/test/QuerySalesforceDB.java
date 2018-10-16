/**
 *
 */
package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;

/**
 * @author surteja
 *
 */
public class QuerySalesforceDB {

	Properties prop = new Properties();
	InputStream input = null;
	public static final String UNMASKED_SHEET = "C:\\Users\\surteja\\eclipse-workspace-j2ee\\ObjectsComparator\\src\\main\\resources\\Datamasking_Unmasked.xlsx";
	public static final String NULLFIELDS_SHEET = "C:\\Users\\surteja\\eclipse-workspace-j2ee\\ObjectsComparator\\src\\main\\resources\\Datamasking_NullFields.xlsx";

	public void queryCreatorAndExecutorPreProd() throws ConnectionException, IOException {
		XSSFWorkbook unmaskedDataWorkbook = new XSSFWorkbook();
		SalesforcePreprodConnector.getDbCon();
		EnterpriseConnection preprodConnection = SalesforcePreprodConnector.connector();
		SalesforceProdConnector.getDbCon();
		EnterpriseConnection prodconnection = SalesforceProdConnector.connector();
		Set<String> keySet = sObjects();
		Map<String, Map<String, Map>> finalPreprodMap = new HashMap<String, Map<String, Map>>();
		Map<String, Map<String, Map>> finalProdMap = new HashMap<String, Map<String, Map>>();

		Map<String, Map> preprodMp = null;
		Map<String, Map> prodMp = null;
		ObjectMapper oMapper = new ObjectMapper();
		oMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		for (String sObj : keySet) {
			XSSFSheet unmaskedDataSheet = unmaskedDataWorkbook.createSheet(sObj);
			int rowNum = 0;
			String fields = prop.getProperty(sObj);
			String[] fieldsArr = fields.split(",");
			for (String s : fieldsArr) {
				String soqlQuery = "SELECT Id," + s + " " + "From" + " " + sObj + " where " + s + " !=null";
				QueryResult qrPreprod = preprodConnection.query(soqlQuery + " limit 15");
				String strIds = "";

				if (qrPreprod.getSize() > 0) {
					preprodMp = new HashMap<String, Map>();
					SObject[] preprodDbRecords = qrPreprod.getRecords();
					for (int i = 0; i < preprodDbRecords.length; i++) {
						preprodMp.put(preprodDbRecords[i].getId(),
								oMapper.convertValue(preprodDbRecords[i], Map.class));
						if (strIds.equals("")) {
							strIds = "'" + preprodDbRecords[i].getId() + "'";
						} else {
							strIds += ",'" + preprodDbRecords[i].getId() + "'";
						}
					}
				}
				if (strIds.length() > 0) {
					QueryResult qrProd = prodconnection.query(soqlQuery + " AND id in (" + strIds + ")");
					if (qrProd.getSize() > 0) {
						prodMp = new HashMap<String, Map>();
						SObject[] prodDbRecords = qrProd.getRecords();
						for (int i = 0; i < prodDbRecords.length; i++) {
							prodMp.put(prodDbRecords[i].getId(), oMapper.convertValue(prodDbRecords[i], Map.class));
						}
					}
					if (qrPreprod.getSize() > 0 && qrProd.getSize() > 0) {
						for (String id : prodMp.keySet()) {
							Row row = unmaskedDataSheet.createRow(rowNum);
							Map finalPreprdMap = preprodMp.get(id);
							Map finalPrdMap = prodMp.get(id);
							int cellNum = 0;
							boolean idVal = false;
							Cell cell = row.createCell(cellNum);
							if (!finalPreprdMap.isEmpty() && !finalPrdMap.isEmpty()
									&& finalPreprdMap.containsKey(s.toLowerCase())
									&& finalPrdMap.containsKey(s.toLowerCase())) {
								System.out.println(s + ":" + finalPreprdMap.get(s.toLowerCase()));
								if (finalPreprdMap.get(s.toLowerCase()).equals(finalPrdMap.get(s.toLowerCase()))) {
									if (!idVal) {
										cell.setCellValue(finalPreprdMap.get("id").toString());
										idVal = true;
										rowNum++;
									}
									cellNum++;
									cell = row.createCell(cellNum);
									cell.setCellValue(s);
								}
							}
						}
					}
				}

				// finalPreprodMap.put(sObj, preprodMp);
				// finalProdMap.put(sObj, prodMp);
				// mapComparator(finalPreprodMap, finalProdMap);
			}

			/*
			 * String soqlQuery = "SELECT Id," + prop.getProperty(sObj) + " " +
			 * "From" + " " + sObj; QueryResult qr =
			 * preprodConnection.query(soqlQuery + " limit 40000"); boolean done
			 * = false; boolean doneProd = false; if (qr.getSize() > 0) {
			 * System.out.println("Logged-in user can see a total of " +
			 * qr.getSize()); while (!done) { SObject[] records =
			 * qr.getRecords(); for (int i = 0; i < records.length; i++) {
			 * preprodMp.put(records[i].getId(),
			 * oMapper.convertValue(records[i], Map.class)); } if (qr.isDone())
			 * { done = true; } else { qr =
			 * preprodConnection.queryMore(qr.getQueryLocator()); } } }
			 */
			/*
			 * QueryResult qrProd = prodconnection.query(soqlQuery +
			 * " limit 40000"); if (qrProd.getSize() > 0) {
			 * System.out.println("Logged-in user can see a total of " +
			 * qrProd.getSize()); while (!doneProd) { SObject[] recordsProd =
			 * qrProd.getRecords(); for (int i = 0; i < recordsProd.length; i++)
			 * { prodMp.put(recordsProd[i].getId(),
			 * oMapper.convertValue(recordsProd[i], Map.class)); } if
			 * (qrProd.isDone()) { doneProd = true; } else { qrProd =
			 * preprodConnection.queryMore(qrProd.getQueryLocator()); } } }
			 */
			// finalPreprodMap.put(sObj, preprodMp);
			// finalProdMap.put(sObj, prodMp);
		}
		FileOutputStream outputStreamUnmasked = new FileOutputStream(new File(UNMASKED_SHEET));
		unmaskedDataWorkbook.write(outputStreamUnmasked);
		// mapComparator(finalPreprodMap, finalProdMap);
	}

	public void mapComparator(Map<String, Map<String, Map>> finalPreprodMap, Map<String, Map<String, Map>> finalProdMap)
			throws IOException {
		XSSFWorkbook unmaskedDataWorkbook = new XSSFWorkbook();
		XSSFWorkbook nullFeildsWorkbook = new XSSFWorkbook();
		for (String sObj : finalProdMap.keySet()) {
			XSSFSheet unmaskedDataSheet = unmaskedDataWorkbook.createSheet(sObj);
			XSSFSheet nullFieldsSheet = nullFeildsWorkbook.createSheet(sObj);
			int rowNum = 0;
			int rowNumForNullFields = 0;
			Map<String, Map> preprdMap = finalPreprodMap.get(sObj);
			Map<String, Map> prdMap = finalProdMap.get(sObj);
			String fields = prop.getProperty(sObj);
			String fieldsArr[] = fields.split(",");
			for (String id : prdMap.keySet()) {
				Map finalPreprdMap = preprdMap.get(id);
				Map finalPrdMap = prdMap.get(id);
				Row row = unmaskedDataSheet.createRow(rowNum);
				Row nullRow = nullFieldsSheet.createRow(rowNumForNullFields);
				int cellNum = 0;
				int cellNumForNull = 0;
				boolean idVal = false;
				boolean idValNull = false;
				Cell cell = row.createCell(cellNum);
				Cell nullCell = nullRow.createCell(cellNumForNull);
				for (String field : fieldsArr) {
					if (finalPreprdMap.get(field.toLowerCase()) != null
							&& finalPrdMap.get(field.toLowerCase()) != null) {
						if (finalPreprdMap.get(field.toLowerCase()).equals(finalPrdMap.get(field.toLowerCase()))) {
							if (!idVal) {
								cell.setCellValue(finalPreprdMap.get("id").toString());
								idVal = true;
								rowNum++;
							}
							cellNum++;
							cell = row.createCell(cellNum);
							cell.setCellValue(field);
						}
					} else {
						if (!idValNull) {
							nullCell.setCellValue(finalPreprdMap.get("id").toString());
							idValNull = true;
							rowNumForNullFields++;
						}
						cellNumForNull++;
						nullCell = nullRow.createCell(cellNumForNull);
						nullCell.setCellValue(field);
					}
				}

			}

		}
		FileOutputStream outputStreamUnmasked = new FileOutputStream(new File(UNMASKED_SHEET));
		unmaskedDataWorkbook.write(outputStreamUnmasked);
		FileOutputStream outputStreamNullFields = new FileOutputStream(new File(NULLFIELDS_SHEET));
		nullFeildsWorkbook.write(outputStreamNullFields);
	}

	public Set<String> sObjects() throws IOException {
		input = new FileInputStream(
				"C:\\Users\\surteja\\eclipse-workspace-j2ee\\objects\\src\\main\\resources\\objectMapper.properties");
		prop.load(input);
		return prop.stringPropertyNames();
	}

	public static void main(String[] args) throws ConnectionException, IOException {
		QuerySalesforceDB db = new QuerySalesforceDB();
		db.queryCreatorAndExecutorPreProd();
	}
}
