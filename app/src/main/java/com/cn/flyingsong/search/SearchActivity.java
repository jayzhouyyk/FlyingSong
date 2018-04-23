package com.cn.flyingsong.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.bean.SearchResult;
import com.cn.flyingsong.download.BottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaei.cn.baseapp.applib.baseui.BaseActivity;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * desc:
 * date: 2018/4/12
 *
 * @author: YangYuKun 04118
 */

public class SearchActivity extends BaseActivity implements SearchContract.View {
    @BindView(R.id.list_view)
    RecyclerView mListView;
    @BindView(R.id.edittext)
    EditText mEdittext;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;

    private SearchContract.Presenter mPresenter;
    private SearchListAdapter mSearchListAdapter;

    private PromptDialog mPromptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUnBinder(ButterKnife.bind(this));
        mPresenter = new SearchPresenter();
        mPresenter.attach(this);

        mListView.setLayoutManager(new LinearLayoutManager(this));
        mSearchListAdapter = new SearchListAdapter(R.layout.item_toplist_home, null);
        mSearchListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mListView.setAdapter(mSearchListAdapter);

        mSearchListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPromptDialog = BottomDialog.create(SearchActivity.this,new FlySong(mSearchListAdapter.getItem(position)));
            }
        });

    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mEdittext.getText().toString())) {
            ToastUtils.showShort("输入不能为空");
        } else {
            mPresenter.search(String.valueOf(mEdittext.getText().toString()));
            mSearchListAdapter.getData().clear();
            mSearchListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showList(SearchResult result) {
        mSearchListAdapter.addData(result.getData().getSong().getList());
    }
    @Override
    public void onBackPressed() {
        if (mPromptDialog!=null ) {
            mPromptDialog.onBackPressed();
        }
        super.onBackPressed();
    }
}
