package com.jerry.yiyachat.subscriber;

import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Presence;

public class SubscriberModel implements SubscriberContract.ISubscriberModel {
    @Override
    public void accpetSubscription(String jid) {
        try {
            System.out.println("==============" + jid);
            Presence presence = new Presence(Presence.Type.subscribed);
            presence.setTo(jid);
            XMPPServer.getConnection().sendStanza(presence);

            presence = new Presence(Presence.Type.subscribe);
            presence.setTo(jid);
            XMPPServer.getConnection().sendStanza(presence);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }
}
