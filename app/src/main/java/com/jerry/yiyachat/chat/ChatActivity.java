package com.jerry.yiyachat.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.recyclerviewutil.adapter.MultiItemCommonAdapter;
import com.jerry.recyclerviewutil.adapter.MultiItemTypeSupport;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.mvp.BaseMVPActivity;
import com.jerry.yiyachat.util.AppDataHolder;
import com.jerry.yiyachat.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseMVPActivity<ChatContract.IChatView, ChatContract.IChatPresenter>
        implements ChatContract.IChatView, View.OnClickListener,
        MultiItemTypeSupport<MessageEntity> {

    @BindView(R.id.chat_btn_send)
    Button btnSend;

    @BindView(R.id.chat_et_message_content)
    EditText etMessageContent;

    @BindView(R.id.chat_rv_message)
    RecyclerView rvMessage;

    // jid of current partner
    String jid;
    List<MessageEntity> messages;
    CommonAdapter<MessageEntity> adapter;

    private UserEntity oppositeUserEntity;
    private UserEntity thisUserEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        jid = getIntent().getStringExtra("jid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ButterKnife.bind(this);

        oppositeUserEntity = AppDataHolder.getUserEntity(jid);

        messages = new ArrayList<>();
        adapter = new MultiItemCommonAdapter<MessageEntity>(messages, this) {
            @Override
            protected void bindViewHolder(CommonViewHolder holder, MessageEntity item) {
                TextView tvMessage = holder.getView(R.id.chat_recycle_item_tv_message);
                tvMessage.setText(item.getMessageInfo());

                ImageView ivPhoto = holder.getView(R.id.chat_recycle_item_iv_photo);
                if (item.getUserEntity().getPhotoBitmap() != null) {
                    ivPhoto.setImageBitmap(item.getUserEntity().getPhotoBitmap());
                } else {
                    ivPhoto.setImageResource(R.mipmap.general_default_photo);
                }
            }
        };
        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadMessage(jid);
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
                String messageContent = etMessageContent.getText().toString();
                etMessageContent.setText("");
                presenter.sendMessage(messageContent);
                showMessage(messageContent);
                break;
        }
    }

    /**
     * 显示由我发出的信息内容
     * @param messageContent    消息内容字符串
     */
    private void showMessage(String messageContent) {
        MessageEntity messageEntity = new MessageEntity(messageContent);
        messageEntity.setUserEntity(AppDataHolder.getSelfUserEntity());
        showMessage(messageEntity);
    }

    /**
     * 显示消息，消息封装在消息实体中。
     * @param messageEntity 消息实体
     */
    @Subscribe(tags = { @Tag(Constants.EventType.CHAT_MESSAGE_RECEIVED)})
    public void showMessage(MessageEntity messageEntity) {
        messages.add(messageEntity);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMessageSucceed(List<MessageEntity> messages) {
        this.messages.addAll(messages);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position, MessageEntity messageEntity) {
        if (messageEntity.getUserEntity() == AppDataHolder.getSelfUserEntity()) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return viewType == 1 ? R.layout.chat_recycle_left_item : R.layout.chat_recycle_right_item;
    }
}