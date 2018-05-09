package com.jerry.yiyachat.main.message;

import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.mvp.BasePresenter;
import com.jerry.yiyachat.mvp.BaseView;

import java.util.List;

interface MessageContract {

    interface IMessageView extends BaseView {
        void onMessageLoaded(List<MessageEntity> messages);
    }

    interface IMessageModel {
        List<MessageEntity> loadMessage();
    }

    abstract class IMessagePresenter extends BasePresenter<IMessageView> {
       abstract void loadMessage();
    }
}
