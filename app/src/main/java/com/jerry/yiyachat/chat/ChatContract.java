package com.jerry.yiyachat.chat;

import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.mvp.BasePresenter;

interface ChatContract {

    interface IChatView {

    }

    interface IChatModel {
        void sendMessage(String messageInfo);
    }

    abstract class IChatPresenter extends BasePresenter<IChatView> {
        abstract void sendMessage(String messageInfo);
    }
}
