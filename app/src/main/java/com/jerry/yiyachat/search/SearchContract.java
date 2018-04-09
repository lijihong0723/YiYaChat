package com.jerry.yiyachat.search;

import com.jerry.yiyachat.mvp.BasePresenter;

interface SearchContract {

    interface ISearchView {
        void onSearchSucceed();
        void onSearchFailed();
    }

    interface ISearchModel {
        boolean search(String searchString);
    }

    abstract class ISearchPresenter extends BasePresenter<ISearchView> {
        abstract void search(String searchString);
    }
}
