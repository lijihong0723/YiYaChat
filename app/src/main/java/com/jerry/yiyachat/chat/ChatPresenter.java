package com.jerry.yiyachat.chat;

import com.jerry.yiyachat.entity.MessageEntity;

import java.util.List;

public class ChatPresenter extends ChatContract.IChatPresenter {

    private ChatContract.IChatModel model;

    public ChatPresenter(String jid) {
        model = new ChatModel(jid);
    }

    @Override
    void sendMessage(String messageInfo) {
        model.sendMessage(messageInfo);
    }

    @Override
    void loadMessage(String jid) {
        List<MessageEntity> messageEntities = model.loadMessage(jid);
        view.onLoadMessageSucceed(messageEntities);
    }
}
