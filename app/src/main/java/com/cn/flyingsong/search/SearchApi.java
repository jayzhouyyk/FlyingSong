package com.cn.flyingsong.search;

import com.cn.flyingsong.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:
 * date: 2018/4/12
 *
 * @author: YangYuKun 04118
 */

public interface SearchApi {
    @GET(Constant.SEARCH_URL)
    Observable<String> getRankList(@Query("n")int size,@Query("w")String keyword);
}
