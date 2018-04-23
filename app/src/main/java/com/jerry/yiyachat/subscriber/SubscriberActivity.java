package com.jerry.yiyachat.subscriber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.entity.UserEntity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 此Activity用于展示向我发出好友请求的用户列表
 */
public class SubscriberActivity extends AppCompatActivity {

    @BindView(R.id.subscriber_rv_subscribers)
    RecyclerView rvSubscribers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscriber_activity);

        ButterKnife.bind(this);

        String type = Integer.toString(UserEntity.TYPE_SUBSCRIBE_FROM);
        List<UserEntity> subscribers
                = DataSupport.where("type=?", type).find(UserEntity.class);
        CommonAdapter<UserEntity> adapter = new CommonAdapter<UserEntity>(
                subscribers, R.layout.subscribe_recycle_item) {
            @Override
            protected void bindViewHolder(CommonViewHolder holder, UserEntity item) {
                ImageView ivPhoto = holder.getView(R.id.subscriber_iv_photo);
                if (item.getPhotoBitmap() != null) {
                    ivPhoto.setImageBitmap(item.getPhotoBitmap());
                } else {
                    ivPhoto.setImageResource(R.mipmap.general_default_photo);
                }

                TextView tvNickName = holder.getView(R.id.subscriber_tv_nick_name);
                tvNickName.setText(item.getUserName());
            }
        };
        rvSubscribers.setLayoutManager(new LinearLayoutManager(this));
        rvSubscribers.setAdapter(adapter);
    }
}
