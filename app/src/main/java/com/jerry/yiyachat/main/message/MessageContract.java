package com.jerry.yiyachat.main.message;

import com.jerry.yiyachat.mvp.BasePresenter;
import com.jerry.yiyachat.mvp.BaseView;

interface MessageContract {

    interface IMessageView extends BaseView {

    }

    interface IMessageModel {

    }

    abstract class IMessagePresenter extends BasePresenter<IMessageView> {

    }
}
