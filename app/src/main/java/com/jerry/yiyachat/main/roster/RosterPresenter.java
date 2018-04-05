package com.jerry.yiyachat.main.roster;

public class RosterPresenter extends RosterContract.IRosterPresenter {

    private RosterContract.IRosterModel model;

    public RosterPresenter() {
        model = new RosterModel();
    }

    @Override
    public void loadRoster() {
        view.onLoadSucceed(model.loadRoster());
    }
}
