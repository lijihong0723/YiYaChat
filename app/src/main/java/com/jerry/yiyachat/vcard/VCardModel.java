package com.jerry.yiyachat.vcard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jerry.yiyachat.entity.VCardEntity;
import com.jerry.yiyachat.util.XMPPServer;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

class VCardModel implements VCardContract.IVCardModel {
    @Override
    public String loadVCard(String jid, VCardEntity vCardEntity) {
        VCardManager manager = VCardManager.getInstanceFor(XMPPServer.getConnection());
        try {
            VCard vCard = manager.loadVCard(jid);

            vCardEntity.setJid(jid);
            vCardEntity.setNickName(vCard.getNickName());
            vCardEntity.setAddress(
                    vCard.getAddressFieldHome("CTRY") + " " + vCard.getAddressFieldHome("LOCALITY"));
            vCardEntity.setSignature(vCard.getField("signature"));

            byte[] avatarByteArray = vCard.getAvatar();
            if (avatarByteArray != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(
                        avatarByteArray, 0, avatarByteArray.length);
                vCardEntity.setPhotoImage(bitmap);
            }

            return "Succeed";
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
