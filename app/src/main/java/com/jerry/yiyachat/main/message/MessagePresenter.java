package com.jerry.yiyachat.main.message;

import com.jerry.yiyachat.entity.MessageEntity;

import java.util.List;

class MessagePresenter extends MessageContract.IMessagePresenter {

    MessageContract.IMessageModel model;

    MessagePresenter() {
        model = new MessageModel();
    }

    @Override
    void loadMessage() {
        List<MessageEntity> messageEntities = model.loadMessage();
        view.onMessageLoaded(messageEntities);
    }
}
