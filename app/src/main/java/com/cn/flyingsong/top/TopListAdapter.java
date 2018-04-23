package com.cn.flyingsong.top;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.TopList;
import com.cn.flyingsong.utils.Utils;

import java.util.List;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class TopListAdapter extends BaseQuickAdapter<TopList.SonglistBean, BaseViewHolder> {
    public TopListAdapter(int layoutResId, @Nullable List<TopList.SonglistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopList.SonglistBean item) {
        helper.setText(R.id.rank,  String.valueOf(helper.getLayoutPosition()+1));
        helper.setText(R.id.song_name, item.getData().getSongname());
        helper.setText(R.id.songer, item.getData().getSinger().get(0).getName() + "·" + item.getData().getAlbumname());
        helper.setText(R.id.size, Utils.formatFileSize(item.getData().getSize320()));
//        Glide.with(mContext).load(item.getData().getSongorig())

    }


}
