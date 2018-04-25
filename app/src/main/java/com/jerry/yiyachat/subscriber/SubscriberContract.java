package com.jerry.yiyachat.subscriber;

import com.jerry.yiyachat.mvp.BasePresenter;

public interface SubscriberContract {

    interface ISubscriberView {

    }

    interface ISubscriberModel {
        void accpetSubscription(String jid);
    }

    abstract class ISubscriberPresenter extends BasePresenter<ISubscriberView> {
        abstract void acceptSubscription(String jid);
    }
}
