package com.cn.flyingsong.search;

import com.cn.flyingsong.bean.SearchResult;

import gaei.cn.baseapp.applib.basemvp.BasePresenter;
import gaei.cn.baseapp.applib.basemvp.BaseView;

/**
 * desc:
 * date: 2018/4/12
 *
 * @author: YangYuKun 04118
 */

interface SearchContract {
    public interface Presenter<V extends View> extends BasePresenter<V> {
        void search(String keyword);
    }

    public interface View extends BaseView {
        void showList(SearchResult result);
    }
}
