package com.jerry.yiyachat.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.mvp.BaseMVPActivity;
import com.jerry.yiyachat.mvp.BasePresenter;
import com.jerry.yiyachat.mvp.BaseView;
import com.jerry.yiyachat.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity
        extends BaseMVPActivity<LoginContract.ILoginView, LoginPresenter>
        implements LoginContract.ILoginView, View.OnClickListener {

    @BindView(R.id.et_yiya_code) EditText etYiyaCode;
    @BindView(R.id.et_pass_word) EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected LoginPresenter createPresent() {
        return new LoginPresenter();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = etYiyaCode.getText().toString();
                String password = etPassword.getText().toString();
                presenter.login(username, password);
                break;
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
