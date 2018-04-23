package gaei.cn.baseapp.applib.test;

import gaei.cn.baseapp.applib.basemvp.BasePresenter;
import gaei.cn.baseapp.applib.basemvp.BaseView;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public class TestContract {

    interface Presenter<V extends View> extends BasePresenter<V>{
        void getData();
    }

    interface View extends BaseView{
        void showData();
    }

}
