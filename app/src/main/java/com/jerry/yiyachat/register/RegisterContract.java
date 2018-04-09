package com.jerry.yiyachat.register;

import com.jerry.yiyachat.mvp.BasePresenter;

public interface RegisterContract {

    interface IRegisterView {
        void onRegisterSucceed();
        void onRegisterFailed(String errorMessage);
    }

    interface IRegisterModel {
        String register(String username, String password);
    }

    abstract class IRegisterPresenter extends BasePresenter<IRegisterView> {
        public abstract void register(String username, String password);
    }

}
