package com.jerry.yiyachat.main.roster;

import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class RosterModel implements RosterContract.IRosterModel {

    @Override
    public List<UserEntity> loadRoster() {
        List<UserEntity> users = loadFromDB();
        if (users == null) {
            users = loadFromServer();
        }

        return users;
    }

    private List<UserEntity> loadFromDB() {
        return DataSupport.findAll(UserEntity.class);
    }

    private List<UserEntity> loadFromServer() {

        // 加载花名册的相关信息，但只有用户名和Jid。
        Roster roster = Roster.getInstanceFor(XMPPServer.getConnection());
        Set<RosterEntry> entries = roster.getEntries();
        List<UserEntity> users = new ArrayList<>();
        for (RosterEntry entry : entries) {
            users.add(new UserEntity(entry.getName(), entry.getUser()));
        }

        // 加载每个人的详细信息
        try {
            VCardManager vCardManager = VCardManager.getInstanceFor(XMPPServer.getConnection());
            for (UserEntity user : users) {
                VCard vCard = vCardManager.loadVCard(user.getJid());
                user.setAddress(
                        vCard.getAddressFieldHome("CTRY") + " " + vCard.getAddressFieldHome("LOCALITY"));
                user.setPhoto(vCard.getAvatar());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSupport.saveAll(users);
        return users;
    }
}
