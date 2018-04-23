package com.jerry.yiyachat.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 用户实体，包括通信录好友（Roster）、好友请求者（Subscriber）。
 */
public class UserEntity extends DataSupport {

    // 通过群组得到的用户信息
    public static final int TYPE_GROUP          = 0;
    // 向我发出请求的用户信息
    public static final int TYPE_SUBSCRIBE_FROM = 1;
    // 由我发出请求的用户信息
    public static final int TYPE_SUBSCRIBE_TO   = 2;
    // 已成为好友的用户信息
    public static final int TYPE_ROSTER         = 3;

    @Column(unique = true)
    private String jid;

    private String userName;
    private String address;
    private byte[] photo;
    private List<MessageEntity> messages;

    @Column(ignore = true)
    private Bitmap photoBitmap;

    private int type;

    public UserEntity(VCard vCard) {
        this.setJid(vCard.getJabberId());
        this.userName = vCard.getNickName();
        this.photo = vCard.getAvatar();
    }

    public UserEntity(String userName, String jid) {
        this.userName = userName;
        this.jid = jid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        if (photo != null) {
            this.photo = photo;
            photoBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        }
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public Bitmap getPhotoBitmap() {
        return photoBitmap;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
