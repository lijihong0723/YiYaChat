package com.jerry.yiyachat.login;

import com.jerry.yiyachat.util.XMPPServer;

public class LoginModel implements LoginContract.ILoginModel {


    @Override
    public String login(String username, String password) throws Exception {
        try {
            XMPPServer.getConnection().login(username, password);
            return "Succeed";
        } catch (Exception e) {
            XMPPServer.getConnection().disconnect();
            return e.getMessage();
        }
    }
}
