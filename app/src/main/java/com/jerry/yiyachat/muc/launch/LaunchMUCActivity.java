package com.jerry.yiyachat.muc.launch;

import android.os.Bundle;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.mvp.BaseMVPActivity;

public class LaunchMUCActivity
        extends BaseMVPActivity<LaunchMUCContract.ILaunchMUCView, LaunchMUCContract.ILaunchMUCPresenter>
        implements LaunchMUCContract.ILaunchMUCView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_muc_activity);
    }

    @Override
    protected LaunchMUCContract.ILaunchMUCPresenter createPresent() {
        return new LaunchMUCPresenter();
    }
}
