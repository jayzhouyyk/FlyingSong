package com.cn.flyingsong.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.cn.flyingsong.App;
import com.cn.flyingsong.Constant;

import java.util.LinkedHashMap;

/**
 * desc:
 * date: 2018/4/11
 *
 * @author: YangYuKun 04118
 */

public class NotifyCenter {

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private LinkedHashMap<Integer, NotificationCompat.Builder> sLinkedHashMap;
    private NotificationManager notificationManager = (NotificationManager) App.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);

    private final int OP_PROGRESS = 0X007;
    private final int OP_FINISH = 0X008;

    private static volatile NotifyCenter instance = null;

    private NotifyCenter() {
        if (instance != null) {
            throw new RuntimeException("you can not create me");
        }
        mHandlerThread = new HandlerThread("NotifyCenterHandlerThread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case OP_PROGRESS:
                        if (sLinkedHashMap.get(msg.arg1) != null) {
                            sLinkedHashMap.get(msg.arg1).setProgress(100, msg.arg2, false);
                            sLinkedHashMap.get(msg.arg1).setContentText(msg.arg2 + "%");
                            notificationManager.notify(msg.arg1, sLinkedHashMap.get(msg.arg1).build());
                        }
                        break;
                    case OP_FINISH:
                        notificationManager.cancel(msg.arg1);
                        sLinkedHashMap.remove(msg.arg1);
                        break;
                }

            }
        };
    }

    public static NotifyCenter getInstance() {
        if (instance == null) {
            synchronized (NotifyCenter.class) {
                if (instance == null) {
                    instance = new NotifyCenter();
                }
            }
        }
        return instance;
    }


    public void addNotify(int id, NotificationCompat.Builder builder) {
        if (sLinkedHashMap == null) {
            sLinkedHashMap = new LinkedHashMap<>();
        }
        if (sLinkedHashMap.get(id) != null) {
            ToastUtils.showShort("任务已经创建");
            Log.d(Constant.AppTag, "任务已经创建");
        } else {
            Log.d(Constant.AppTag, "创建任务");
            Notification notification = builder.build();
            notificationManager.notify(id, notification);
            sLinkedHashMap.put(id, builder);
        }
    }


    public void notifyProgress(int progress, int id) {
        Message message = mHandler.obtainMessage();
        message.what = OP_PROGRESS;
        message.arg1 = id;
        message.arg2 = progress;
        mHandler.sendMessage(message);
    }

    public void cancel(int id) {
        Message message = mHandler.obtainMessage();
        message.what = OP_FINISH;
        message.arg1 = id;
        mHandler.sendMessage(message);
    }


}
