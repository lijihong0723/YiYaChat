package com.jerry.yiyachat.subscriber;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jerry.recyclerviewutil.RecyclerItemClickListener;
import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.mvp.BaseMVPActivity;
import com.jerry.yiyachat.util.Constants;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 此Activity用于展示向我发出好友请求的用户列表
 */
public class SubscriberActivity
        extends BaseMVPActivity<SubscriberContract.ISubscriberView, SubscriberContract.ISubscriberPresenter> {

    @BindView(R.id.subscriber_rv_subscribers)
    RecyclerView rvSubscribers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscriber_activity);

        ButterKnife.bind(this);

        String fromType = Integer.toString(UserEntity.TYPE_SUBSCRIBE_FROM);
        String acceptType = Integer.toString(UserEntity.TYPE_SUBSCRIBE_ROSTER);
        final List<UserEntity> subscribers
                = DataSupport.where("type=? or type=?", fromType, acceptType).find(UserEntity.class);
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

                TextView tvAccepted = holder.getView(R.id.subscriber_tv_accepted);
                Button btnAccept = holder.getView(R.id.subscriber_btn_accept);
                if (item.getType() == UserEntity.TYPE_SUBSCRIBE_FROM) {
                    btnAccept.setVisibility(View.VISIBLE);
                    tvAccepted.setVisibility(View.GONE);
                } else {
                    btnAccept.setVisibility(View.GONE);
                    tvAccepted.setVisibility(View.VISIBLE);
                }
            }
        };
        rvSubscribers.setLayoutManager(new LinearLayoutManager(this));
        rvSubscribers.setAdapter(adapter);
        rvSubscribers.addOnItemTouchListener(new RecyclerItemClickListener(rvSubscribers) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                // 接受后，更新本地数据库
                UserEntity subscriber = subscribers.get(vh.getAdapterPosition());
                presenter.acceptSubscription(subscriber.getJid());
                subscriber.setType(UserEntity.TYPE_SUBSCRIBE_ROSTER);
                subscriber.update(subscriber.getId());

                // 接受后，更新控件状态
                CommonViewHolder holder = (CommonViewHolder) vh;
                TextView tvAccepted = holder.getView(R.id.subscriber_tv_accepted);
                Button btnAccept = holder.getView(R.id.subscriber_btn_accept);
                tvAccepted.setVisibility(View.VISIBLE);
                btnAccept.setVisibility(View.GONE);

                // 接受后，更新好友列表数据
                RxBus.get().post(Constants.EventType.SUBSCRIBER_ACCEPTED, subscriber);
            }
        });
    }

    @Override
    protected SubscriberContract.ISubscriberPresenter createPresent() {
        return new SubscriberPresenter();
    }
}
