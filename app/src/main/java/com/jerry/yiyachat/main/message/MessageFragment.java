package com.jerry.yiyachat.main.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jerry.recyclerviewutil.RecyclerItemClickListener;
import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.chat.ChatActivity;
import com.jerry.yiyachat.entity.MessageEntity;
import com.jerry.yiyachat.mvp.BaseMVPFragment;
import com.jerry.yiyachat.util.Constants;
import com.jerry.yiyachat.vcard.VCardActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends BaseMVPFragment<MessageContract.IMessageView, MessagePresenter> {

    @BindView(R.id.message_rv_message)
    RecyclerView rvMessages;

    private List<MessageEntity> messageEntities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_message_fragment, container, false);
        ButterKnife.bind(this, view);

        messageEntities = new ArrayList<>();
        rvMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessages.setAdapter(new CommonAdapter<MessageEntity>(messageEntities, R.layout.message_recycler_item) {
            @Override
            protected void bindViewHolder(CommonViewHolder holder, MessageEntity item) {
                ImageView ivPhoto = holder.getView(R.id.message_item_iv_photo);
                ivPhoto.setImageBitmap(item.getUserEntity().getPhotoBitmap());

                TextView tvNickName = holder.getView(R.id.message_item_tv_nick_name);
                tvNickName.setText(item.getUserEntity().getUserName());

                TextView tvLastestMessage = holder.getView(R.id.message_item_tv_latest_message);
                tvLastestMessage.setText(item.getMessageInfo());
            }
        });

        rvMessages.addOnItemTouchListener(new RecyclerItemClickListener(rvMessages) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                MessageEntity messageEntity = messageEntities.get(vh.getAdapterPosition());
                intent.putExtra("jid", messageEntity.getUserEntity().getJid());
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Subscribe(tags = { @Tag(Constants.EventType.CHAT_MESSAGE_RECEIVED)})
    public void showMessageItem(MessageEntity messageEntity) {
        int index = messageEntities.indexOf(messageEntity);
        if (index == -1) {
            messageEntities.add(0, messageEntity);
        } else {
            messageEntities.remove(index);
            messageEntities.add(0, messageEntity);
        }

        rvMessages.getAdapter().notifyDataSetChanged();
    }
}
