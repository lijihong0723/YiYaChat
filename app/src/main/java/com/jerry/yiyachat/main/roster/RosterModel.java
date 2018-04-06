package com.jerry.yiyachat.main.roster;

import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;

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
        return users;
    }
}
