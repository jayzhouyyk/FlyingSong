package com.cn.flyingsong.download;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cn.flyingsong.App;
import com.cn.flyingsong.Constant;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.utils.NotificationUtil;

import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.download.DownloadObserver;
import gaei.cn.baseapp.net.observer.StringObserver;
import io.reactivex.disposables.Disposable;

/**
 * desc:
 * date: 2018/4/9
 *
 * @author: YangYuKun 04118
 */
public class Download {

    private static volatile Download instance = null;

    private Download() {
        if (instance != null) {
            throw new RuntimeException("you can not create me");
        }
    }

    public static Download getInstance() {
        if (instance == null) {
            synchronized (Download.class) {
                if (instance == null) {
                    instance = new Download();
                }
            }
        }
        return instance;
    }


    public void downloadNet(String url, final FlySong flySong) {
        Log.d(Constant.AppTag,flySong.getSongNameWithSuffix() + " download url is "+url);
        RxHttpManager.downloadFile(url)
                .subscribe(new DownloadObserver(SongFile.createDownloadPath(flySong.getSongNameWithSuffix())) {
                    NotificationCompat.Builder mBuilder;

                    @Override
                    protected void getDisposable(Disposable d) {
                        //方法暴露出来使用者根据需求去取消订阅
                        //d.dispose();在onDestroy方法中调用
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        Log.d(Download.class.getSimpleName(), "errorMsg :" + errorMsg);
                    }

                    @Override
                    protected void onSuccess(long bytesRead, long contentLength, final float progress, boolean done, String filePath) {
                        if (mBuilder == null) {
                            mBuilder = NotificationUtil.showNotificationProgress(flySong.getDownloadId(), flySong.getSongNameWithSuffix());
                            NotifyCenter.getInstance().addNotify(flySong.getDownloadId(), mBuilder);
                        }
                        if (done) {
                            NotifyCenter.getInstance().cancel(flySong.getDownloadId());
                            Log.d(Download.class.getSimpleName(), filePath + " done ");
                        } else {
                            NotifyCenter.getInstance().notifyProgress((int) progress, flySong.getDownloadId());
                        }

                    }
                });
    }

    public void downLoad(final FlySong flySong) {
        if (checkFileExists(flySong)) {
            ToastUtils.showShort(String.format(App.getInstance().getString(R.string.song_exist), flySong.getSongNameWithSuffix()));
            return;
        }
        DownLoadUrlBuilder.getDownloadUrl(flySong.getMediaMid(), flySong.getSongType()).subscribe(new StringObserver() {
            @Override
            protected void onError(String errorMsg) {
            }

            @Override
            protected void onSuccess(String data) {
                Download.getInstance().downloadNet(data, flySong);
            }
        });
    }

    private boolean checkFileExists(FlySong flySong) {
        return FileUtils.isFileExists(SongFile.getDownloadPath(flySong.getSongNameWithSuffix()));
    }

}
