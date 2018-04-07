package com.jerry.yiyachat.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.mvp.BaseMVPActivity;
import com.jerry.yiyachat.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseMVPActivity<ChatContract.IChatView, ChatContract.IChatPresenter>
        implements ChatContract.IChatView, View.OnClickListener {

    @BindView(R.id.chat_btn_send)
    Button btnSend;

    @BindView(R.id.chat_et_message_content)
    EditText etMessageContent;

    @BindView(R.id.chat_rv_message)
    RecyclerView rvMessage;

    // jid of current partner
    String jid;
    List<String> messages;
    CommonAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        jid = getIntent().getStringExtra("jid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ButterKnife.bind(this);

        messages = new ArrayList<>();
        adapter = new CommonAdapter<String>(messages, android.R.layout.simple_list_item_1) {
            @Override
            protected void bindViewHolder(CommonViewHolder holder, String item) {
                TextView view = holder.getView(android.R.id.text1);
                view.setText(item);
            }
        };
        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected ChatContract.IChatPresenter createPresent() {
        return new ChatPresenter(jid);
    }

    @OnClick({R.id.chat_btn_send})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_btn_send:
                String messageInfo = etMessageContent.getText().toString();
                etMessageContent.setText("");
                presenter.sendMessage(messageInfo);
                showMessage(new MessageEntity(messageInfo));
                break;
        }
    }

    @Subscribe(tags = { @Tag(Constants.EventType.CHAT_MESSAGE_RECEIVED)})
    public void showMessage(MessageEntity messageEntity) {
        messages.add(messageEntity.getMessageInfo());
        adapter.notifyDataSetChanged();
    }
}