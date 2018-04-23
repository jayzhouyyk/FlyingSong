package gaei.cn.baseapp.applib.basemvp;

import io.reactivex.disposables.Disposable;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public interface BasePresenter<V extends BaseView> {
     void attach(V v);

    void onDetach();

    void addSubscription(Disposable disposable);
}
