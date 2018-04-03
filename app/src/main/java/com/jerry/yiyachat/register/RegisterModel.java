package com.jerry.yiyachat.register;

import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smackx.iqregister.AccountManager;

public class RegisterModel implements RegisterContract.IRegisterModel {
    @Override
    public String register(String username, String password) {
        AccountManager accountManager = AccountManager.getInstance(XMPPServer.getConnection());
        try {
            accountManager.createAccount(username, password);
            return "Succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
