package com.cn.flyingsong.net;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

/**
 * desc:
 * date: 2018/4/2
 *
 * @author: YangYuKun 04118
 */

public class NetUtils {
    public static <T> T getHttpResult(String rspBody, Class<T> cls) {
        T t = null;
        if (!TextUtils.isEmpty(rspBody)) {
            String jsonStr = rspBody.substring(rspBody.indexOf("(") + 1, rspBody.lastIndexOf(")"));
            Log.d("NetUtils", jsonStr);
            long time = System.currentTimeMillis();
            Gson gson = new Gson();
            t = gson.fromJson(jsonStr, cls);
        }
        return t;
    }

    public static String getQQHttpResp(String rspBody){
        if(!TextUtils.isEmpty(rspBody)){
            rspBody= rspBody.substring(rspBody.indexOf("(") + 1, rspBody.lastIndexOf(")"));
        }
        return rspBody;
    }

}
