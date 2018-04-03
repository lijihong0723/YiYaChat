package com.jerry.yiyachat.login;

import com.jerry.yiyachat.util.XMPPServer;

public class LoginModel implements LoginContract.ILoginModel {


    @Override
    public void login(String username, String password) throws Exception {
        XMPPServer.getConnection().login(username, password);
    }
}
