package gaei.cn.baseapp.applib.baseui;

import android.content.Context;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import gaei.cn.baseapp.applib.basemvp.BaseView;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    @Override
    public void showLoading() {
        mActivity.showLoading();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoading();

    }
}
