package com.jerry.yiyachat.main.roster;

import com.jerry.yiyachat.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class RosterModel implements RosterContract.IRosterModel {

    @Override
    public List<UserEntity> loadRoster() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity("张三"));
        return users;
    }
}
