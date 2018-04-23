package com.cn.flyingsong.download;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import com.cn.flyingsong.App;
import com.cn.flyingsong.Constant;
import com.cn.flyingsong.R;
import com.cn.flyingsong.bean.FlySong;
import com.cn.flyingsong.utils.Utils;
import com.cn.flyingsong.bean.SongType;

import java.util.Arrays;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * desc:
 * date: 2018/4/12
 *
 * @author: YangYuKun 04118
 */

public class BottomDialog {
    private static List<String> mStringList;

    public static PromptDialog create(Activity activity, FlySong flySong) {
        if (mStringList == null) {
            mStringList = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.size));
        }

        PromptDialog mPromptDialog = new PromptDialog(activity);
        mPromptDialog.getAlertDefaultBuilder().round(5F);
        PromptButton cancle = new PromptButton(activity.getString(R.string.cancel), null);
        cancle.setTextColor(activity.getResources().getColor(R.color.cancel));
        mPromptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
        mPromptDialog.showAlertSheet(activity.getString(R.string.download), true, cancle,
                new PromptButton(String.format(mStringList.get(0), Utils.formatFileSize(flySong.getSize128())), new SongItemClickListener(flySong.getClone(SongType.MP3_128k),  activity)),
                new PromptButton(String.format( mStringList.get(1), Utils.formatFileSize(flySong.getSize320())), new SongItemClickListener(flySong.getClone(SongType.MP3_320k),activity)),
                new PromptButton(String.format( mStringList.get(2), Utils.formatFileSize(flySong.getSizeape())), new SongItemClickListener(flySong.getClone(SongType.APE), activity)),
                new PromptButton(String.format( mStringList.get(3), Utils.formatFileSize(flySong.getSizeflac())), new SongItemClickListener(flySong.getClone(SongType.FLAC), activity)));
        mPromptDialog.getDefaultBuilder().backAlpha(150);
        return mPromptDialog;
    }

    private static class SongItemClickListener implements PromptButtonListener {
        PromptDialog mDialog;
        FlySong mFlySong;
        Activity mActivity;

        public SongItemClickListener(FlySong flySong, Activity activity) {
            mActivity = activity;
            mFlySong = flySong;
            Log.d(Constant.AppTag,"mFlySong.getSongType().getSuffix() is" + mFlySong.getSongType().getSuffix());
        }

        @Override
        public void onClick(PromptButton promptButton) {
            mDialog = new PromptDialog(mActivity);
            PromptButton confirm = new PromptButton(mActivity.getString(R.string.ok), new PromptButtonListener() {
                @Override
                public void onClick(PromptButton button) {
                    Download.getInstance().downLoad(mFlySong);
                }
            });
            confirm.setFocusBacColor(Color.parseColor("#0076ff"));
            Log.d(Constant.AppTag,"mFlySong.getSongType().getSuffix()  onClick is" + mFlySong.getSongType().getSuffix());
            mDialog.showWarnAlert(String.format(mActivity.getString(R.string.download_title), mFlySong.getSongNameWithSuffix()), new PromptButton(mActivity.getString(R.string.cancel), new PromptButtonListener() {
                @Override
                public void onClick(PromptButton button) {
                    mDialog.dismiss();
                }
            }), confirm);

        }
    }
}
