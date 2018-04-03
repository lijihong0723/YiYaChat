package com.jerry.yiyachat.login;

import com.jerry.yiyachat.mvp.BasePresenter;
import com.jerry.yiyachat.mvp.BaseView;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

public interface LoginContract {

    interface ILoginView extends BaseView {
        void showError(String error);
    }

    interface ILoginModel {
        public void login(String username, String password) throws IOException, XMPPException, SmackException, Exception;
    }

    abstract class LoginPresenter extends BasePresenter<ILoginView> {
        public abstract void login(String username, String password);
    }
}
