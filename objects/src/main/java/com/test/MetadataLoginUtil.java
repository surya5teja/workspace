/**
 *
 */
package com.test;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * @author surteja
 *
 */
public class MetadataLoginUtil {

	static EnterpriseConnection connection;

	public static ConnectorConfig config = new ConnectorConfig();

	public static void main(String[] args) throws ConnectionException {
		login();

	}

	public static MetadataConnection login() throws ConnectionException {

		final String USERNAME = "ssteja_salesforce@dev.com";

		// This is only a sample. Hard coding passwords in source files is a bad
		// practice.

		final String PASSWORD = "Suryateja5";

		final String URL = "https://login.salesforce.com/services/Soap/c/43.0";

		final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);
		return createMetadataConnection(loginResult);

	}

	private static MetadataConnection createMetadataConnection(

			final LoginResult loginResult) throws ConnectionException {

		final ConnectorConfig config = new ConnectorConfig();

		config.setServiceEndpoint(loginResult.getMetadataServerUrl());

		config.setSessionId(loginResult.getSessionId());

		return new MetadataConnection(config);

	}

	private static LoginResult loginToSalesforce(

			final String username,

			final String password,

			final String loginUrl) throws ConnectionException {

		final ConnectorConfig config = new ConnectorConfig();
		config.setAuthEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		config.setServiceEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		config.setUsername("kalmanoor_yashodarshan@gso3.lly.deldev1");
		config.setPassword("yash>>1996" + "SmR1ueDQOyv5GK4W4asS5LODh");
		connection = Connector.newConnection(config);
		boolean done = false;

		String soqlQuery = "SELECT Id,Name, IsDeleted FROM Account";
		QueryResult qr = connection.queryAll(soqlQuery);
		if (qr.getSize() > 0) {

			System.out.println("Logged-in user can see a total of "

					+ qr.getSize()

					+ " contact records (including deleted records).");

			while (!done) {

				SObject[] records = qr.getRecords();

				for (int i = 0; i < records.length; i++) {

					Account account = (Account) records[i];

					boolean isDel = account.getIsDeleted();

					System.out.println("Account " + (i + 1) + ": "

							+ account.getName() + account.getId());

				}

				if (qr.isDone()) {

					done = true;

				} else {

					qr = connection.queryMore(qr.getQueryLocator());

				}

			}

		} else {

			System.out.println("No records found.");

		}

		return (new EnterpriseConnection(config)).login(username, password + "lqalyDVgre1qAMsXgDIVuEgZ");

	}
}
