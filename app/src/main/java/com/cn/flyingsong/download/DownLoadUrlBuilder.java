package com.cn.flyingsong.download;

import com.cn.flyingsong.Constant;
import com.cn.flyingsong.bean.SongType;
import com.cn.flyingsong.net.NetUtils;
import com.cn.flyingsong.net.ToStringConverterFactory;
import com.cn.flyingsong.download.vkey.VKeyApi;
import com.cn.flyingsong.download.vkey.Vkey;

import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interceptor.Transformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * desc: 根据veky及guid生产下载url
 * date: 2018/4/9
 *
 * @author: YangYuKun 04118
 */

public class DownLoadUrlBuilder {
    private final static String RANDOM_QQID = "1234567890";


    private static Vkey vkey;

    public static String getRandomQqid() {
        return RANDOM_QQID;
    }


    public static Observable<String> getDownloadUrl(String mid, SongType songType) {

        final String songPath = songType.getHead() + mid + songType.getSuffix();

        if (vkey != null) {
            return Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    e.onNext("http://dl.stream.qqmusic.qq.com/" + songPath + "?vkey=" + vkey.getData().getItems().get(0).getVkey() + "&guid=" + DownLoadUrlBuilder.getRandomQqid() + "&uin=0&fromtag=64");
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return RxHttpManager.getSInstance()
                    .baseUrl(Constant.BASE_URL_QQ)
                    .addConverterFactory(ToStringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .createSApi(VKeyApi.class)
                    .getVkey(RANDOM_QQID)
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            vkey = NetUtils.getHttpResult(s, Vkey.class);
                            return "http://dl.stream.qqmusic.qq.com/" + songPath + "?vkey=" + vkey.getData().getItems().get(0).getVkey() + "&guid=" + DownLoadUrlBuilder.getRandomQqid() + "&uin=0&fromtag=64";
                        }
                    })
                    .compose(Transformer.<String>switchSchedulers());
        }

    }


}
