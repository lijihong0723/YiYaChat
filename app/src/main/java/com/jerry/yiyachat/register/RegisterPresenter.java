package com.jerry.yiyachat.register;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends RegisterContract.IRegisterPresenter {

    private RegisterContract.IRegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    @Override
    public void register(final String username, final String password) {
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return model.register(username, password);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {

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
