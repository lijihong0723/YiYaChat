package com.jerry.yiyachat.chat;

import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.mvp.BasePresenter;

import java.util.List;

interface ChatContract {

    interface IChatView {
        void onLoadMessageSucceed(List<MessageEntity> messages);
    }

    interface IChatModel {
        void sendMessage(String messageInfo);
        List<MessageEntity> loadMessage(String jid);
    }

    abstract class IChatPresenter extends BasePresenter<IChatView> {
        abstract void sendMessage(String messageInfo);
        abstract void loadMessage(String jid);
    }
}
