package base.bean;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.AndroidCharacter;

/**
 * Created by nidaye on 2017/7/8.
 */

public class MyApplication extends Application {


    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThreadId;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        super.onCreate();
    }
    public static Context getContext(){
        return mContext;
    }
    public static Handler getMainThreadHandler(){
        return mHandler;
    }
    public static int getmMainThreadId(){
        return mMainThreadId;
    }
    private boolean isInMainProcess(){
        String packageName="";
        try{
            //得到当前应用的包名
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            if (info!=null){
                packageName=info.packageName;
            }
            //得到当前应用的pid
            int pid= android.os.Process.myPid();
            ActivityManager activityManager= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            //得到所有运行的包信息
            for (ActivityManager.RunningAppProcessInfo appProcessInfo:activityManager.getRunningAppProcesses()){
                //为何要拿pid做为判断条件呢
                if (appProcessInfo.pid==pid){
                    String pname=appProcessInfo.processName;
                    if (pname.equalsIgnoreCase(packageName)){
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
