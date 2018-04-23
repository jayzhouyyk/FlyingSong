package com.cn.flyingsong.top;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cn.flyingsong.App;
import com.cn.flyingsong.Constant;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.bean.TopList;
import com.cn.flyingsong.download.Download;
import com.cn.flyingsong.download.SongFile;
import com.cn.flyingsong.net.NetUtils;
import com.cn.flyingsong.net.ToStringConverterFactory;
import com.cn.flyingsong.download.DownLoadUrlBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import gaei.cn.baseapp.applib.basemvp.BasePresenterImpl;
import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interceptor.Transformer;
import gaei.cn.baseapp.net.observer.StringObserver;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class TopListPresenter<V extends TopListContract.View> extends BasePresenterImpl<V> implements TopListContract.Presenter<V> {

    private List<String> mStringList;

    String date;
    int topicId;
    int mPageNum;
    int pageSize;
    String type;

    @Override
    public void getTopList(String date, int topicId, int pageNum, int pageSize, String type) {
        if (pageNum < 1) {
            getView().showLoading();
        }

        this.date = date;
        this.topicId = topicId;
        this.mPageNum = pageNum;
        this.pageSize = pageSize;
        this.type = type;

        RxHttpManager.getSInstance()
                .baseUrl(Constant.BASE_URL_QQ)
                .addConverterFactory(ToStringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .createSApi(TopListApi.class)
                .getTopList(date, topicId, pageSize * pageNum+1, pageSize, type)
                .compose(Transformer.<String>switchSchedulers())
                .subscribe(new StringObserver() {
                    @Override
                    protected void onError(String errorMsg) {
                        getView().hideLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        mPageNum++;
                        getView().hideLoading();
                        TopList topList = (new Gson()).fromJson(NetUtils.getQQHttpResp(data), new TypeToken<TopList>() {
                        }.getType());

                        getView().showList(topList.getSonglist());
                    }
                });

    }

    @Override
    public void loadMore() {
        getTopList(date, topicId, mPageNum, pageSize, type);
    }

    @Override
    public void downloadSong(final FlySong flySong) {
        if (checkFileExists(flySong)) {
            ToastUtils.showShort(String.format(App.getInstance().getString(R.string.song_exist), flySong.getSongNameWithSuffix()));
            return;
        }
        getView().showLoading();
        DownLoadUrlBuilder.getDownloadUrl(flySong.getMediaMid(), flySong.getSongType()).subscribe(new StringObserver() {
            @Override
            protected void onError(String errorMsg) {
                getView().hideLoading();
            }

            @Override
            protected void onSuccess(String data) {
                getView().hideLoading();
                Download.getInstance().downloadNet(data, flySong);
            }
        });
    }

    private boolean checkFileExists(FlySong flySong) {
        return FileUtils.isFileExists(SongFile.getDownloadPath(flySong.getSongNameWithSuffix()));
    }


    @Override
    public List<String> getSize() {
        if (mStringList == null) {
            mStringList = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.size));
        }
        return mStringList;
    }
}
