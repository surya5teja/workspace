/**
 *
 */
package com.test;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * @author surteja
 *
 */
public class SalesforcePreprodConnector {

	private static SalesforcePreprodConnector connObj;
	static EnterpriseConnection connection;

	private SalesforcePreprodConnector() {

	}

	public static synchronized SalesforcePreprodConnector getDbCon() {
		if (connObj == null) {
			connObj = new SalesforcePreprodConnector();
		}
		return connObj;

	}

	public static EnterpriseConnection connector() {
		final ConnectorConfig config = new ConnectorConfig();
		config.setAuthEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		config.setServiceEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		/*
		 * config.setUsername("2255073@gso3.lly.gso3full");
		 * config.setPassword("test#123" + "erFkaStTwecsgoiRFUdau2Cen");
		 */

		config.setUsername("kalmanoor_yashodarshan@gso3.lly.deldev1");
		config.setPassword("yash>>1996");
		try {
			connection = Connector.newConnection(config);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
