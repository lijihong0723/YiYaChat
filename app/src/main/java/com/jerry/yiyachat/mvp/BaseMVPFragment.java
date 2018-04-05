package com.jerry.yiyachat.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseMVPFragment<V, P extends BasePresenter<V>> extends Fragment {

    protected P present;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        present = createPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        present.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        present.detach();
    }

    protected abstract P createPresenter();
}
