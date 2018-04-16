package com.jerry.yiyachat.search;

public class SearchPresenter extends SearchContract.ISearchPresenter {

    SearchContract.ISearchModel model;

    SearchPresenter() {
        model = new SearchModel();
    }

    @Override
    void search(String searchString) {
        String jid = model.search(searchString);
        if (jid != "") {
            view.onSearchSucceed(jid);
        } else {
            view.onSearchFailed();
        }
    }

    @Override
    boolean isInRoster(String jid) {
        return model.isInRoster(jid);
    }
}
