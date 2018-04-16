package com.jerry.yiyachat.search;

import com.jerry.yiyachat.mvp.BasePresenter;

interface SearchContract {

    interface ISearchView {
        void onSearchSucceed(String jid);
        void onSearchFailed();
    }

    interface ISearchModel {
        String search(String searchString);
        boolean isInRoster(String jid);
    }

    abstract class ISearchPresenter extends BasePresenter<ISearchView> {
        abstract void search(String searchString);
        abstract boolean isInRoster(String jid);
    }
}
