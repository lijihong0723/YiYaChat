package com.jerry.yiyachat.main.roster;

import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.entity.VCardEntity;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class RosterModel implements RosterContract.IRosterModel {

    @Override
    public List<UserEntity> loadRoster() {
        Roster roster = Roster.getInstanceFor(XMPPServer.getConnection());
        Set<RosterEntry> entries = roster.getEntries();

        List<UserEntity> users = new ArrayList<>();
        for (RosterEntry entry : entries) {
            users.add(new UserEntity(entry.getName(), entry.getUser()));
        }

        try {
            VCardManager vCardManager = VCardManager.getInstanceFor(XMPPServer.getConnection());
            for (UserEntity user : users) {
                VCard vCard = vCardManager.loadVCard(user.getJid());
                VCardEntity vCardEntity = new VCardEntity(vCard);
                user.setvCardEntity(vCardEntity);
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
