package com.cn.flyingsong.top;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.bean.RankList;
import com.cn.flyingsong.bean.TopList;
import com.cn.flyingsong.download.BottomDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gaei.cn.baseapp.applib.baseui.BaseActivity;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */
public class TopListActivity extends BaseActivity implements TopListContract.View {

    public static final String BUNDLE_KEY = "bundle_key";
    @BindView(R.id.list_view)
    RecyclerView mListView;

    private PromptDialog mPromptDialog;

    TopListContract.Presenter mPresenter;
    private TopListAdapter mTopListAdapter;
    private RankList.ListBean mListBean;
    private Context mContext;

    public static void startActivity(Context context, RankList.ListBean listBean) {
        Intent intent = new Intent(context, TopListActivity.class);
        intent.putExtra(BUNDLE_KEY, listBean);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotlist);
        ButterKnife.bind(this);
        mContext = this;
        mListBean = (RankList.ListBean) getIntent().getSerializableExtra(BUNDLE_KEY);

        mPresenter = new TopListPresenter();
        mPresenter.attach(this);

        mListView.setLayoutManager(new LinearLayoutManager(this));
        mTopListAdapter = new TopListAdapter(R.layout.item_toplist_home, null);
        mTopListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mListView.setAdapter(mTopListAdapter);
        if (mListBean != null) {
            mPresenter.getTopList(mListBean.getUpdate_key(), mListBean.getTopID(), 0, 10, mListBean.getType() == 0 ? "top" : "global");
        }
        mTopListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        });

        mTopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                mPromptDialog = BottomDialog.create(TopListActivity.this,new FlySong(mTopListAdapter.getData().get(position)));
            }
        });
    }

    @Override
    public void showList(List<TopList.SonglistBean> list) {
        if (list != null) {
            mTopListAdapter.addData(list);
        }
        mTopListAdapter.loadMoreComplete();
    }

    @Override
    public void onBackPressed() {
        if (mPromptDialog!=null ) {
            mPromptDialog.onBackPressed();
        }
        super.onBackPressed();
    }

}
