package com.jerry.yiyachat.util;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.entity.VCardEntity;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.litepal.crud.DataSupport;

import java.io.IOException;

public class XMPPServer {

    private static AbstractXMPPConnection connection;
    private static String HOST_IP_ADDRESS = "39.107.248.129";

    public static AbstractXMPPConnection getConnection() {

        if (connection == null) {
            createConnection();
        }

        if (!connection.isConnected()) {
            openConnection();
        }

        return connection;
    }


    private static void createConnection() {
        XMPPTCPConnectionConfiguration config
                = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST_IP_ADDRESS)
                .setServiceName("jerry-aliyun-01")
                .setPort(5222)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setDebuggerEnabled(true)
                .build();
        SASLAuthentication.blacklistSASLMechanism("CRAM-MD5");
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        SASLAuthentication.unBlacklistSASLMechanism("PLAIN");

        connection = new XMPPTCPConnection(config);
        connection.addAsyncStanzaListener(
                new ChatMessageStenzaListener(), MessageTypeFilter.CHAT);
    }

    private static void openConnection() {
        if (connection.isConnected()) {
            return;
        }

        try {
            connection.connect();
        } catch (SmackException | XMPPException | IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatMessageStenzaListener implements StanzaListener
{
    @Override
    public void processPacket(Stanza packet) throws SmackException.NotConnectedException {
        Message message = (Message) packet;
        if (message.getBody() != null && !message.getBody().equals("")) {
            MessageEntity messageEntity = new MessageEntity(message.getBody());
            UserEntity userEntity = DataSupport.where(
                    "jid = ?", message.getFrom().substring(0, message.getFrom().indexOf('/'))).findFirst(UserEntity.class);
            messageEntity.setUserEntity(userEntity);
            messageEntity.save();

            RxBus.get().post(Constants.EventType.CHAT_MESSAGE_RECEIVED, messageEntity);
        }
    }
}