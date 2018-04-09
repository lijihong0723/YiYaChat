package com.jerry.yiyachat.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.mvp.BaseMVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity
        extends BaseMVPActivity<RegisterContract.IRegisterView, RegisterPresenter>
        implements RegisterContract.IRegisterView, View.OnClickListener {

    @BindView(R.id.et_yiya_code)
    EditText etYiyaCode;

    @BindView(R.id.et_pass_word)
    EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected RegisterPresenter createPresent() {
        return new RegisterPresenter();
    }

    @OnClick({R.id.btn_register})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String username = etYiyaCode.getText().toString();
                String password = etPassword.getText().toString();
                presenter.register(username, password);
                break;
        }
    }

    @Override
    public void onRegisterSucceed() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFailed(String errorMessage) {
        Toast.makeText(this, "注册失败:" + errorMessage, Toast.LENGTH_SHORT).show();
    }
}
