package gaei.cn.baseapp.applib.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * 懒加载过
     */
    protected boolean isLazyLoaded;

    private boolean isPrepared;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        //只有Fragment onCreateView好了，
        //另外这里调用一次lazyLoad(）
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    @UiThread
    public  void onLazyLoad() {
    }

}
