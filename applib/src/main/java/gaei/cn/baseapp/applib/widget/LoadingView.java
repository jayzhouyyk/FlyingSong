package gaei.cn.baseapp.applib.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.wang.avi.AVLoadingIndicatorView;

import gaei.cn.baseapp.applib.R;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public class LoadingView extends Dialog {

    private Context mContext;
    private AVLoadingIndicatorView mLoadingIndicatorView;

    public LoadingView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public LoadingView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected LoadingView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingIndicatorView = (AVLoadingIndicatorView) LayoutInflater.from(mContext).inflate(R.layout.progress_dialog,null);
        setContentView(mLoadingIndicatorView);

        if (this.getWindow() != null) {
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show(){
        super.show();
        mLoadingIndicatorView.show();
    }
    @Override
    public void cancel(){
        if(mLoadingIndicatorView!=null){
            mLoadingIndicatorView.hide();
        }
        super.cancel();
    }
}
