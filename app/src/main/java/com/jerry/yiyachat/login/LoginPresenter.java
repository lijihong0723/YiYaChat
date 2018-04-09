package com.jerry.yiyachat.login;

import com.jerry.yiyachat.util.XMPPServer;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class LoginPresenter extends LoginContract.LoginPresenter {

    private LoginModel model;

    LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(final String username, final String password) {
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return model.login(username, password);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        if (!value.equals("Succeed")) {
                            view.showError(value);
                        } else {
                            view.loginSucceed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
