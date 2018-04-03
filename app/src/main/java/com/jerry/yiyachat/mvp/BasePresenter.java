package com.jerry.yiyachat.mvp;

public class BasePresenter<V> {

    protected V view;

    public void attach(V view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }
}
