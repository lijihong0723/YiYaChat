package com.jerry.yiyachat.util;

import com.jerry.yiyachat.entity.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 虽然Android中尽量少用静态实例来存储一些数据，但为了避免创建大量相同
 * 的共享对象，YiyaChat App仍然会将一些核心数据存储在静态类中，以提高
 * 性能，减少内存使用。大部分场合下，优先从AppDataHolder获取一些数据，
 * 如果没有，则从数据库或网络加载，并同时更新AppDataHolder的相应条目。
 * 关于Android中的静态类，见：
 *
 */
public class AppDataHolder {

    private static Map<String, UserEntity> mapUserEntity = new HashMap<>();
    private static UserEntity selfUserEntity = null;

    public static void AddUserEntities(List<UserEntity> userEntities) {
        for (UserEntity userEntity : userEntities) {
            mapUserEntity.put(userEntity.getJid(), userEntity);
        }
    }

    /**
     * 添加或更新某个用户实体
     * @param userEntity    用户实体实例
     */
    public static void AddUserEntity(UserEntity userEntity) {
        mapUserEntity.put(userEntity.getJid(), userEntity);
    }

    public static UserEntity getUserEntity(String jid) {
        return mapUserEntity.get(jid);
    }

    public static UserEntity getSelfUserEntity() {
        return selfUserEntity;
    }

    public static void setSelfUserEntity(UserEntity userEntity) {
        selfUserEntity = userEntity;
    }
}
