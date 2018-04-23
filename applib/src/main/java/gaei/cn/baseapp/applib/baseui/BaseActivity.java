package gaei.cn.baseapp.applib.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import gaei.cn.baseapp.applib.basemvp.BaseView;
import gaei.cn.baseapp.applib.widget.LoadingView;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.Callback, BaseView {

    private LoadingView mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityTaskManager.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
    }

    private Unbinder mUnBinder;

    @Override
    protected void onDestroy() {
        ActivityTaskManager.getInstance().removeActivity(this);
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void showLoading() {
        hideLoading();
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingView(this);
        }
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
