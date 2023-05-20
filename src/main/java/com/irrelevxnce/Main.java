package com.irrelevxnce;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.irrelevxnce.LoginSystem.LoginForm;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
    	UIDSecrets uidss = new UIDSecrets();
    	LoginForm login = new LoginForm(uidss.getLoginInfo());
    	login.main(args);
    	
    }
}