package com.jerry.yiyachat.main.message;

import android.database.Cursor;

import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.entity.UserEntity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MessageModel implements MessageContract.IMessageModel {

    @Override
    public List<MessageEntity> loadMessage() {
        //DataSupport.findBySQL("select * from ")
        Cursor cursor = DataSupport.findBySQL("select * from messageentity a where " +
                "a.date in (select max(date) from messageentity group by userentity_id)");

        List<MessageEntity> messageEntities = new ArrayList<>();
        while (cursor.moveToNext()) {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessageInfo(cursor.getString(cursor.getColumnIndex("messageinfo")));
            messageEntity.setDate(new Date(cursor.getInt(cursor.getColumnIndex("date"))));
            messageEntity.setAlreadyRead(cursor.getInt(cursor.getColumnIndex("isalreadyread")) == 0);
            messageEntity.setUserEntity(DataSupport.find(UserEntity.class,
                    cursor.getInt(cursor.getColumnIndex("userentity_id"))));
            messageEntities.add(messageEntity);
        }

        return messageEntities;
    }
}
