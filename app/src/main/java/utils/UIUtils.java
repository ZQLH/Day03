package utils;

import android.content.Context;
import android.content.res.Resources;

import base.bean.MyApplication;

/**
 * Created by nidaye on 2017/7/8.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }

    public static Resources getResources(){
        return getContext().getResources();
    }
    public static String getString(int resId){
        return getResources().getString(resId);
    }
    public static String[] getStrings(int resId){
        return getResources().getStringArray(resId);
    }
    public static int getColor(int resId){
        return getResources().getColor(resId);
    }

    public static String getPackageName(){
        return getContext().getPackageName();
    }

}
