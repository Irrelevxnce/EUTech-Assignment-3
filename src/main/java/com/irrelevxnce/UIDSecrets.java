package com.irrelevxnce;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

public class UIDSecrets {

	HashMap<String, String> loginInfo = new HashMap<String, String>();
	EmbeddedDatabaseConnector edc = new EmbeddedDatabaseConnector();
	
	public UIDSecrets() throws NoSuchAlgorithmException {
		try {
			loginInfo = edc.connect(false, true, false, "", "", false, "0", "", "0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected HashMap<String, String> getLoginInfo() {
		return loginInfo;
	}
}
