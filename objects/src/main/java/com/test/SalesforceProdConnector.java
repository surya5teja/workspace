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
public class SalesforceProdConnector {

	private static SalesforceProdConnector connObj;
	static EnterpriseConnection connection;

	private SalesforceProdConnector() {

	}

	public static synchronized SalesforceProdConnector getDbCon() {
		if (connObj == null) {
			connObj = new SalesforceProdConnector();
		}
		return connObj;

	}

	public static EnterpriseConnection connector() {
		final ConnectorConfig config = new ConnectorConfig();
		config.setAuthEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		config.setServiceEndpoint("https://test.salesforce.com/services/Soap/c/43.0");
		config.setUsername("2255073@gso3.lly.gso3full");
		config.setPassword("test#123" + "erFkaStTwecsgoiRFUdau2Cen");
		try {
			connection = Connector.newConnection(config);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
