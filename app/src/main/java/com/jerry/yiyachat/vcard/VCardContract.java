package com.jerry.yiyachat.vcard;

import com.jerry.yiyachat.entity.VCardEntity;
import com.jerry.yiyachat.mvp.BasePresenter;

interface VCardContract {

    interface IVCardView {
        void onLoadVCardSucceed(VCardEntity vCardEntity);
        void onLoadVCardFailed(String errorInfo);
    }

    interface IVCardModel {
        String loadVCard(String jid, VCardEntity vCardEntity);
    }

    abstract class IVCardPresenter extends BasePresenter<IVCardView> {
        public abstract void loadVCard(String jid);
    }

}
