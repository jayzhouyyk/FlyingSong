package com.cn.flyingsong.top;

import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.bean.TopList;

import java.util.List;

import gaei.cn.baseapp.applib.basemvp.BasePresenter;
import gaei.cn.baseapp.applib.basemvp.BaseView;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class TopListContract {
    public interface View extends BaseView{
        void showList(List<TopList.SonglistBean> list);
    }

    public interface Presenter<V extends View> extends BasePresenter<V>{
        void getTopList(String date,  int topicId, int pageNum, int pageSize,String type);
        void loadMore();
        void downloadSong(FlySong flySong);
        List<String> getSize();
    }
}
