package com.cn.flyingsong.home;

import com.cn.flyingsong.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public interface HomeApi {
    @GET(Constant.RANK_LIST_URL)
    Observable<String> getRankList();
}
