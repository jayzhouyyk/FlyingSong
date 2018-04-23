package com.cn.flyingsong.search;

import com.cn.flyingsong.Constant;
import com.cn.flyingsong.bean.SearchResult;
import com.cn.flyingsong.net.NetUtils;
import com.cn.flyingsong.net.ToStringConverterFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gaei.cn.baseapp.applib.basemvp.BasePresenterImpl;
import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interceptor.Transformer;
import gaei.cn.baseapp.net.observer.StringObserver;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * desc:
 * date: 2018/4/12
 *
 * @author: YangYuKun 04118
 */

class SearchPresenter<V extends SearchContract.View> extends BasePresenterImpl<V> implements SearchContract.Presenter<V> {

    @Override
    public void search(String keyword) {
        getView().showLoading();

        RxHttpManager.getSInstance()
                .baseUrl(Constant.BASE_URL_QQ)
                .addConverterFactory(ToStringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .createSApi(SearchApi.class)
                .getRankList(10,keyword)
                .compose(Transformer.<String>switchSchedulers())
                .subscribe(new StringObserver() {
                    @Override
                    protected void onError(String errorMsg) {
                        getView().hideLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        getView().hideLoading();
                        SearchResult result = (new Gson()).fromJson(NetUtils.getQQHttpResp(data), new TypeToken<SearchResult>() {
                        }.getType());
                        getView().showList(result);
                    }
                });
    }
}
