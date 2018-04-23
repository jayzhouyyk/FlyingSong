package gaei.cn.baseapp.applib.test;

import gaei.cn.baseapp.applib.basemvp.BasePresenterImpl;
import gaei.cn.baseapp.applib.test.TestContract.Presenter;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public class TestPresenter<V extends TestContract.View> extends BasePresenterImpl<V> implements Presenter<V> {

    TestContract.View mView;
    TestContract.View mView2;

    @Override
    public void getData() {
        getView().showData();
    }


}
