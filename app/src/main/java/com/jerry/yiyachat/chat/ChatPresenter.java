package com.jerry.yiyachat.chat;

public class ChatPresenter extends ChatContract.IChatPresenter {

    private ChatContract.IChatModel model;

    public ChatPresenter(String jid) {
        model = new ChatModel(jid);
    }

    @Override
    void sendMessage(String messageInfo) {
        model.sendMessage(messageInfo);
    }
}
