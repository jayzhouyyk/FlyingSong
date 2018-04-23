package gaei.cn.baseapp.applib.basemvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {


    private CompositeDisposable mCompositeDisposable;

    V view;


    @Override
    public void addSubscription(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDetach() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        if(view!=null){
            view = null;
        }
    }

    @Override
    public void attach(V v) {
        this.view = v;
    }

    public V getView() {
        return view;
    }
}
