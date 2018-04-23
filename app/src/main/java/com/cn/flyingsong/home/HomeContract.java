package com.cn.flyingsong.home;

import com.cn.flyingsong.bean.RankList;

import java.util.List;

import gaei.cn.baseapp.applib.basemvp.BasePresenter;
import gaei.cn.baseapp.applib.basemvp.BaseView;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public class HomeContract {

    public interface Presenter<V extends View> extends BasePresenter<V>{
        void getHotBillBoard();
    }

    public interface View extends BaseView{
        void showList(List<RankList.ListBean> rankList);
    }
}
