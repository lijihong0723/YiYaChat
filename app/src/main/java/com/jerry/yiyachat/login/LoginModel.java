package com.jerry.yiyachat.login;

import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.util.AppDataHolder;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class LoginModel implements LoginContract.ILoginModel {


    @Override
    public String login(String username, String password) throws Exception {
        try {
            XMPPServer.getConnection().login(username, password);

            String user = XMPPServer.getConnection().getUser();
            String selfJid = user.substring(0, user.indexOf('/'));

            VCardManager vCardManager = VCardManager.getInstanceFor(XMPPServer.getConnection());
            VCard vCard = vCardManager.loadVCard(selfJid);
            UserEntity userEntity = new UserEntity(vCard);
            userEntity.setUserName(vCard.getNickName());

            AppDataHolder.setSelfUserEntity(userEntity);

            return "Succeed";
        } catch (Exception e) {
            XMPPServer.getConnection().disconnect();
            return e.getMessage();
        }
    }
}
