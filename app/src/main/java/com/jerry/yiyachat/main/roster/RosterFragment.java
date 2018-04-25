package com.jerry.yiyachat.main.roster;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.recyclerviewutil.RecyclerItemClickListener;
import com.jerry.recyclerviewutil.adapter.CommonAdapter;
import com.jerry.recyclerviewutil.adapter.CommonViewHolder;
import com.jerry.yiyachat.R;
import com.jerry.yiyachat.entity.UserEntity;
import com.jerry.yiyachat.mvp.BaseMVPFragment;
import com.jerry.yiyachat.subscriber.SubscriberActivity;
import com.jerry.yiyachat.vcard.VCardActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RosterFragment extends BaseMVPFragment<RosterContract.IRosterView, RosterPresenter>
        implements RosterContract.IRosterView, View.OnClickListener {

    @BindView(R.id.rv_roster)
    RecyclerView rvRoster;

    private View rootView;
    private List<UserEntity> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 以下逻辑是为了防止重复创建View
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_roster, container, false);
            ButterKnife.bind(this, rootView);
            rvRoster.addOnItemTouchListener(new RecyclerItemClickListener(rvRoster) {
                @Override
                public void onItemClick(RecyclerView.ViewHolder vh) {
                    Intent intent = new Intent(getContext(), VCardActivity.class);
                    intent.putExtra("jid", users.get(vh.getAdapterPosition()).getJid());
                    intent.putExtra("type", "exist_in_roster");
                    getContext().startActivity(intent);
                }
            });
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (users == null) {
            present.loadRoster();
        }
    }

    @Override
    protected RosterPresenter createPresenter() {
        return new RosterPresenter();
    }

    @Override
    public void onLoadSucceed(List<UserEntity> users) {
        this.users = users;
        rvRoster.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRoster.setAdapter(new CommonAdapter<UserEntity>(users, R.layout.roster_recycle_item) {
            @Override
            protected void bindViewHolder(CommonViewHolder holder, UserEntity item) {
                TextView tvUserName = holder.getView(R.id.tv_user_name);
                tvUserName.setText(item.getUserName());

                ImageView ivPhoto = holder.getView(R.id.roster_item_iv_photo);
                ivPhoto.setImageBitmap(item.getPhotoBitmap());
            }
        });
    }

    @Override
    public void onLoadFailed(String errorInfo) {
        Toast.makeText(getContext(), errorInfo, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.roster_ll_subscribers})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roster_ll_subscribers:
            {
                Intent intent = new Intent(getActivity(), SubscriberActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void SubscriberAccepted(UserEntity userEntity) {

    }
}