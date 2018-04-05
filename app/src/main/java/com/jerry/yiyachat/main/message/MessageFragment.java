package com.jerry.yiyachat.main.message;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.mvp.BaseMVPFragment;

public class MessageFragment extends BaseMVPFragment<MessageContract.IMessageView, MessagePresenter> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }
}
