package com.jerry.yiyachat.main.roster;

import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.mvp.BasePresenter;
import com.jerry.yiyachat.mvp.BaseView;

import java.util.List;

interface RosterContract {

    interface IRosterView extends BaseView {
        void onLoadSucceed(List<UserEntity> users);
        void onLoadFailed(String errorInfo);
    }

    interface IRosterModel {
        List<UserEntity> loadRoster();
    }

    abstract class IRosterPresenter extends BasePresenter<IRosterView> {
        public abstract void loadRoster();
    }

}
