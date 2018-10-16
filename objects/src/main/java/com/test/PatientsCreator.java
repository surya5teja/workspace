/**
 *
 */
package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.DTPC_Patient__c;
import com.sforce.ws.ConnectionException;

/**
 * @author surteja
 *
 */
public class PatientsCreator {

	public void createPatients() throws ConnectionException, ParseException {
		SalesforcePreprodConnector.getDbCon();
		EnterpriseConnection preprodConnection = SalesforcePreprodConnector.connector();
		DTPC_Patient__c[] sobjs = new DTPC_Patient__c[2];
		for (int i = 1; i <= sobjs.length; i++) {
			DTPC_Patient__c patient = new DTPC_Patient__c();
			patient.setLast_Name__c("Test1Patient");
			patient.setFirst_Name__c(String.valueOf(i) + "Patient");
			patient.setDate_of_Birth__c(dateConvertor());
			patient.setGender__c("F");
			patient.setAffiliates__c("US");
			patient.setPatient_Status__c("Active");
			patient.setPreferred_Phone_Number__c("1234567890");
			patient.setPref_Phone_Type__c("Cell");
			patient.setPatient_Email__c("lillyplusvc20180822+r5p" + String.valueOf(i) + "@gmail.com");
			patient.setLillyPlus_Version__c("Version 3");
			sobjs[i] = patient;
		}
		SaveResult[] saveResults = preprodConnection.create(sobjs);
		for (int i = 0; i < saveResults.length; i++) {

			if (saveResults[i].isSuccess()) {

				System.out.println(i + ". Successfully created record - Id: " + saveResults[i].getId());

			} else {

				com.sforce.soap.enterprise.Error[] errors = saveResults[i].getErrors();

				for (int j = 0; j < errors.length; j++) {

					System.out.println("ERROR creating record: " + errors[j].getMessage());

				}

			}
		}
	}

	public Calendar dateConvertor() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse("06/24/1953");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static void main(String[] args) throws ConnectionException, ParseException {
		PatientsCreator pc = new PatientsCreator();
		pc.createPatients();
	}
}
