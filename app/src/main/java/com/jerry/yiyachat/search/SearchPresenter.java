package com.jerry.yiyachat.search;

public class SearchPresenter extends SearchContract.ISearchPresenter {

    SearchContract.ISearchModel model;

    SearchPresenter() {
        model = new SearchModel();
    }

    @Override
    void search(String searchString) {
        if (model.search(searchString)) {
            view.onSearchSucceed();
        } else {
            view.onSearchFailed();
        }
    }
}
