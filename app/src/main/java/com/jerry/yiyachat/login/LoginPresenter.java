package com.jerry.yiyachat.login;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends LoginContract.LoginPresenter {

    private LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(final String username, final String password) {
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    model.login(username, password);
                    return "Succeed";
                } catch (Exception e) {
                    return e.getMessage();
                }
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
