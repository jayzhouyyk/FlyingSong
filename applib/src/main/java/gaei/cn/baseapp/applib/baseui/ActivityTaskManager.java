package gaei.cn.baseapp.applib.baseui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import gaei.cn.baseapp.applib.AppLib;

/**
 * desc:
 * date: 2018/3/30
 *
 * @author: YangYuKun 04118
 */

public class ActivityTaskManager {private static final String TAG = "ActivityTaskManager";

    private volatile static ActivityTaskManager mInstance;

    private static HashSet<Activity> mActivitySet = new HashSet<>();

    public static synchronized ActivityTaskManager getInstance() {
        if (mInstance == null) {
            mInstance = new ActivityTaskManager();
        }
        return mInstance;
    }

    private ActivityTaskManager() {
        mActivitySet = new HashSet<>();
    }

    void addActivity(Activity activity) {
        mActivitySet.add(activity);
    }

    void removeActivity(Activity activity) {
        mActivitySet.remove(activity);
    }

    /**
     * 通过指定的activity类型，获取该类型的Activity
     *
     * @param clazz
     */
    public List<Activity> getActivity(Class<? extends Activity> clazz) {
        return getActivity(clazz.getName());
    }

    /**
     * 获取栈顶Activity
     *
     * @return
     */
    public Activity getTopActivity() {
        List<Activity> activities = getActivity(getTopActivityClassName());
        return activities.isEmpty() ? null : activities.get(0);
    }

    /**
     * 获取栈顶Activity的完整类名(含包名)。
     *
     * @return 如果不存在返回null
     */
    private String getTopActivityClassName() {
        try {
            ActivityManager manager = (ActivityManager)
                    AppLib.getGlobeContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
            if (runningTaskInfos != null
                    && runningTaskInfos.size() > 0
                    && runningTaskInfos.get(0) != null
                    && runningTaskInfos.get(0).topActivity != null) {
                return (runningTaskInfos.get(0).topActivity).getClassName();
            } else {
                return "";
            }
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 通过完整类名获取Activity
     *
     * @param className
     */
    private List<Activity> getActivity(String className) {
        List<Activity> activities = new LinkedList<>();
        Iterator<Activity> iterable = mActivitySet.iterator();
        while (iterable.hasNext()) {
            Activity activity = iterable.next();
            if (activity.getClass().getName().equals(className)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    /**
     * 结束某个activity上面所有的activity
     * 就相当于回到这个类的界面
     *
     * @param className
     */
    public void finishToActivity(String className) {
        boolean canFinish = false;
        Iterator<Activity> iterable = mActivitySet.iterator();
        while (iterable.hasNext()) {
            Activity activity = iterable.next();
            if (activity.getClass().getName().equals(className)) {
                canFinish = true;
                continue;
            }
            if (canFinish) {
                activity.finish();
            }
        }
    }
}
