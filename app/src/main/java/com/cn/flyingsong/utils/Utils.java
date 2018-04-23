package com.cn.flyingsong.utils;

import java.text.DecimalFormat;

/**
 * desc:
 * date: 2018/4/4
 *
 * @author: YangYuKun 04118
 */

public class Utils {

    final static double ONE_KB = 1024;
    final static double ONE_MB = 1024*1024 ;
    final static double ONE_GB = 1024*1024*1024 ;

    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < ONE_KB) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < ONE_MB) {
            fileSizeString = df.format((double) fileS / ONE_KB) + "K";
        } else if (fileS < ONE_GB) {
            fileSizeString = df.format((double) fileS / ONE_MB) + "M";
        } else {
            fileSizeString = df.format((double) fileS / ONE_GB) + "G";
        }
        return fileSizeString;
    }
}
