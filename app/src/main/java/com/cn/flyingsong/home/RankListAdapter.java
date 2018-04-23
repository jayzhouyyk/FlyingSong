package com.cn.flyingsong.home;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.RankList;

import java.util.List;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class RankListAdapter extends BaseQuickAdapter<RankList.ListBean, BaseViewHolder> {
    public RankListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankList.ListBean item) {
        if (item.getSonglist() != null) {
            if (item.getSonglist().get(0) != null) {
                helper.setText(R.id.tv1_ranklist, "1、" + item.getSonglist().get(0).getSongname() + "-" + item.getSonglist().get(0).getSingername());
            }
            if (item.getSonglist().get(1) != null) {
                helper.setText(R.id.tv2_ranklist, "2、" + item.getSonglist().get(1).getSongname() + "-" + item.getSonglist().get(1).getSingername());
            }
            if (item.getSonglist().get(2) != null) {
                helper.setText(R.id.tv3_ranklist, "3、" + item.getSonglist().get(2).getSongname() + "-" + item.getSonglist().get(2).getSingername());
            }
        }
        // 加载网络图片
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.ranklist_img));

    }
}
