package com.cn.flyingsong.top;

import com.cn.flyingsong.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public interface TopListApi {

    @GET(Constant.TOP_LIST_URL)
    Observable<String> getTopList(@Query("date")String date, @Query("topid") int topId, @Query("song_begin") int pageNum, @Query("song_num")int pageSize,@Query("type")String type);
}
