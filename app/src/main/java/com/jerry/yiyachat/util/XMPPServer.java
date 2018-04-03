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
                .setServiceName(HOST_IP_ADDRESS)
                .setPort(5222)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setDebuggerEnabled(true)
                .build();
        SASLAuthentication.blacklistSASLMechanism("CRAM-MD5");
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        SASLAuthentication.unBlacklistSASLMechanism("PLAIN");

        connection = new XMPPTCPConnection(config);
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
