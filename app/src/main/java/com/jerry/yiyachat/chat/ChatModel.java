package com.jerry.yiyachat.chat;

import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.litepal.crud.DataSupport;

import java.util.List;

public class ChatModel implements ChatContract.IChatModel {

    Chat chat;

    public ChatModel(String jid) {
        ChatManager manager = ChatManager.getInstanceFor(XMPPServer.getConnection());
        chat = manager.createChat(jid);
        System.out.println(chat.getThreadID());
    }

    @Override
    public void sendMessage(String messageInfo) {
        try {
            chat.sendMessage(messageInfo);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MessageEntity> loadMessage(String jid) {
        return DataSupport.findAll(MessageEntity.class, true);
    }
}
