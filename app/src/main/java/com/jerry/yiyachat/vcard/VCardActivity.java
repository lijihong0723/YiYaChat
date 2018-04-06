package com.jerry.yiyachat.vcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.chat.ChatActivity;
import com.jerry.yiyachat.entity.VCardEntity;
import com.jerry.yiyachat.mvp.BaseMVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VCardActivity extends BaseMVPActivity<VCardContract.IVCardView, VCardPresenter>
        implements VCardContract.IVCardView, View.OnClickListener {

    @BindView(R.id.roster_tv_yiya_code)
    TextView tvYiyaCode;

    @BindView(R.id.roster_tv_nick_name)
    TextView tvNickName;

    @BindView(R.id.roster_iv_photo)
    ImageView ivPhoto;

    @BindView(R.id.roster_tv_address)
    TextView tvAddress;

    private String jid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vcard_activity);
        ButterKnife.bind(this);
        jid = getIntent().getStringExtra("jid");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadVCard(jid);
    }

    @Override
    protected VCardPresenter createPresent() {
        return new VCardPresenter();
    }

    @Override
    public void onLoadVCardSucceed(VCardEntity vCardEntity) {
        tvYiyaCode.setText(vCardEntity.getJid());
        tvNickName.setText(vCardEntity.getNickName());
        tvAddress.setText(vCardEntity.getAddress());
    }

    @Override
    public void onLoadVCardFailed(String errorInfo) {

    }

    @OnClick({R.id.vcard_btn_send_message, R.id.vcard_btn_video_message})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vcard_btn_send_message:
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
                break;
            case R.id.vcard_btn_video_message:
                break;
        }
    }
}
