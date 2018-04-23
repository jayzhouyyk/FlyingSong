package gaei.cn.baseapp.applib.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gaei.cn.baseapp.applib.baseui.BaseActivity;
import io.reactivex.disposables.Disposable;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public class TestActivity extends BaseActivity implements TestContract.View {
    TestContract.Presenter mPresenter;
    Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TestPresenter();
        mPresenter.attach(this);
        mPresenter.getData();
        mPresenter.addSubscription(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showData() {

    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }
}
