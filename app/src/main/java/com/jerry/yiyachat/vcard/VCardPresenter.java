package com.jerry.yiyachat.vcard;

import com.jerry.yiyachat.entity.VCardEntity;

class VCardPresenter extends VCardContract.IVCardPresenter {

    private VCardContract.IVCardModel model;

    VCardPresenter() {
        model = new VCardModel();
    }

    @Override
    public void loadVCard(String jid) {
        VCardEntity vCardEntity = new VCardEntity();
        String result = model.loadVCard(jid, vCardEntity);
        if (result.equals("Succeed")) {
            view.onLoadVCardSucceed(vCardEntity);
        } else {
            view.onLoadVCardFailed(result);
        }
    }

    @Override
    public void addToRoster(VCardEntity vCardEntity) {
        model.addToRoster(vCardEntity);
    }
}
