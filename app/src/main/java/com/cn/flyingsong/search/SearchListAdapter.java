package com.cn.flyingsong.search;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.SearchResult;
import com.cn.flyingsong.utils.Utils;

import java.util.List;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class SearchListAdapter extends BaseQuickAdapter<SearchResult.DataBean.SongBean.ListBean, BaseViewHolder> {
    public SearchListAdapter(int layoutResId, @Nullable List<SearchResult.DataBean.SongBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResult.DataBean.SongBean.ListBean item) {
//        helper.setText(R.id.rank,  String.valueOf(helper.getLayoutPosition()+1));
        helper.setText(R.id.song_name, item.getName());
        helper.setText(R.id.songer, item.getSinger().get(0).getName() + "·" + item.getAlbum().getName());
        helper.setText(R.id.size, Utils.formatFileSize(item.getFile().getSize_320()));
//        Glide.with(mContext).load(item.getAlbum().get)

    }


}
