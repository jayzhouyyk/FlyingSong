package com.cn.flyingsong.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.RankList;
import com.cn.flyingsong.download.SongFile;
import com.cn.flyingsong.search.SearchActivity;
import com.cn.flyingsong.top.TopListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaei.cn.baseapp.applib.baseui.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {


    @BindView(R.id.list_view)
    RecyclerView mListView;

    private HomeContract.Presenter mPresenter;
    private RankListAdapter mRankListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        mPresenter = new HomePresenter();
        mPresenter.attach(this);

        mListView.setLayoutManager(new LinearLayoutManager(this));
        mRankListAdapter = new RankListAdapter(R.layout.item_ranklist_home, null);
        mRankListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mListView.setAdapter(mRankListAdapter);
        mPresenter.getHotBillBoard();

        mRankListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TopListActivity.startActivity(HomeActivity.this, mRankListAdapter.getData().get(position));
            }
        });
        SongFile.checkPermission();


    }

    @Override
    public void showList(List<RankList.ListBean> rankList) {
        mRankListAdapter.addData(rankList);

    }

    @OnClick(R.id.to_search)
    public void onViewClicked() {
        this.startActivity(new Intent(this, SearchActivity.class));
    }
}

