package com.jerry.yiyachat.chat;

import com.jerry.yiyachat.mvp.BasePresenter;

interface ChatContract {

    interface IChatView {

    }

    interface IChatModel {

    }

    abstract class IChatPresenter extends BasePresenter<IChatView> {

    }
}
