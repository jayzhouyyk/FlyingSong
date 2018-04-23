package com.cn.flyingsong.download.vkey;

import com.cn.flyingsong.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * desc:
 * date: 2018/4/9
 *
 * @author: YangYuKun 04118
 */

public interface VKeyApi {
    @GET(Constant.VKEY_URL)
    Observable<String> getVkey(@Query("guid") String guid);
}
