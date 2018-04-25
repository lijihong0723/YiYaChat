package com.jerry.yiyachat.subscriber;

public class SubscriberPresenter extends SubscriberContract.ISubscriberPresenter {

    SubscriberContract.ISubscriberModel model;

    public SubscriberPresenter() {
        model = new SubscriberModel();
    }

    @Override
    void acceptSubscription(String jid) {
        model.accpetSubscription(jid);
    }
}
