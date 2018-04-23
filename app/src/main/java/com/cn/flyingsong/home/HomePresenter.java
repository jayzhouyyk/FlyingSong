package com.cn.flyingsong.home;

import com.cn.flyingsong.Constant;
import com.cn.flyingsong.bean.RankList;
import com.cn.flyingsong.net.NetUtils;
import com.cn.flyingsong.net.ToStringConverterFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import gaei.cn.baseapp.applib.basemvp.BasePresenterImpl;
import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interceptor.Transformer;
import gaei.cn.baseapp.net.observer.StringObserver;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public class HomePresenter<V extends HomeContract.View> extends BasePresenterImpl<V> implements HomeContract.Presenter<V> {

    @Override
    public void getHotBillBoard() {
        getView().showLoading();

        RxHttpManager.getSInstance()
                .baseUrl(Constant.BASE_URL_QQ)
                .addConverterFactory(ToStringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .createSApi(HomeApi.class)
                .getRankList()
                .compose(Transformer.<String>switchSchedulers())
                .subscribe(new StringObserver() {
                    @Override
                    protected void onError(String errorMsg) {
                        getView().hideLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        getView().hideLoading();
                        List<RankList> lists = (new Gson()).fromJson(NetUtils.getQQHttpResp(data), new TypeToken<List<RankList>>() {
                        }.getType());
                        List<RankList.ListBean> listBeans = new ArrayList<>();
                        if(lists!=null && lists.size()>0){
                            for (RankList rankList:
                                 lists) {
                                listBeans.addAll(rankList.getList());
                            }
                        }
                        getView().showList(listBeans);
                    }
                });

    }
}
