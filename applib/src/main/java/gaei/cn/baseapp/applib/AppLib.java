package gaei.cn.baseapp.applib;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public class AppLib {
    private static volatile Application sContext;
    private static volatile AppLib instance = null;
    public static void init(Application context){
        sContext = context;
        Utils.init(context);
    }
    private AppLib() {
        if (instance != null) {
            throw new RuntimeException("you can not create me");
        }
    }
    public static Context getGlobeContext(){
        if(sContext == null) {
            throw new RuntimeException("you must init AppLib");
        }
        return sContext;
    }
}
