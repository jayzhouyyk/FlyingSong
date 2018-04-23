package com.cn.flyingsong.download;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gaei.cn.baseapp.applib.baseui.ActivityTaskManager;

/**
 * desc:
 * date: 2018/4/11
 *
 * @author: YangYuKun 04118
 */

public class SongFile {
    private static String SD_CARD_PATH ;
    private static final String SONG_PATH = "/AFlySong/download";

    public static String getDownloadPath(String fileName) {
        if (TextUtils.isEmpty(SD_CARD_PATH)) {
            SD_CARD_PATH = getSdCardPath();
        }
        String filePath = SD_CARD_PATH + SONG_PATH + File.separator + fileName;

        return filePath;
    }

    public static String createDownloadPath(String fileName){
        String  filePath = getDownloadPath(fileName);
        FileUtils.createOrExistsFile(filePath);
        return filePath;
    }

    public static void checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(ActivityTaskManager.getInstance().getTopActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);


        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    ActivityTaskManager.getInstance().getTopActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static String getSdCardPath() {
        List<String> list = new ArrayList<>();
        if (SDCardUtils.isSDCardEnable()) {
            list = SDCardUtils.getSDCardPaths();
            for (String str :
                    list) {
                Log.d("Download", "getSdCardPath is " + str);
            }
        }
        return list.get(0);
    }
}
