package com.jerry.yiyachat.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.mvp.BaseMVPActivity;
import com.jerry.yiyachat.vcard.VCardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseMVPActivity<SearchContract.ISearchView, SearchContract.ISearchPresenter>
        implements SearchContract.ISearchView, View.OnClickListener, TextWatcher{

    @BindView(R.id.search_btn_cancel)
    Button btnCancel;

    @BindView(R.id.search_btn_search)
    Button btnSearch;

    @BindView(R.id.search_et_search_string)
    EditText etSearchString;

    @BindView(R.id.search_tv_not_exist)
    TextView tvNotExist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected SearchContract.ISearchPresenter createPresent() {
        return new SearchPresenter();
    }

    @OnClick({R.id.search_btn_search})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn_search:
                String searchString = etSearchString.getText().toString();
                presenter.search(searchString);
                break;
        }
    }

    @Override
    public void onSearchSucceed() {
        Intent intent = new Intent(this, VCardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSearchFailed() {
        tvNotExist.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            tvNotExist.setVisibility(View.GONE);
        } else {

        }
    }
}
