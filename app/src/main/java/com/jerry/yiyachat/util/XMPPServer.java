package com.jerry.yiyachat.util;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

public class XMPPServer {

    private static AbstractXMPPConnection connection;

    static {
        SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
    }

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
                .setHost("101.132.178.248")
                .setServiceName("101.132.178.248")
                .setPort(5222)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setDebuggerEnabled(true)
                .build();

        connection = new XMPPTCPConnection(config);
    }

    private static void openConnection() {
        if (connection.isConnected()) {
            return;
        }

        try {
            connection.connect();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }
}
