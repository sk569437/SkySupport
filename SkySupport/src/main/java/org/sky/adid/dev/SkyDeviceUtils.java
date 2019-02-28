package org.sky.adid.dev;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Copyright (c) 2018 Sky. All rights reserved.
 */
public class SkyDeviceUtils {


    /**
     * Change [dp] to [px]
     * @param context
     * @param dpValue
     * @return pixil
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dip
     * @param context
     * @param pxValue
     * @return dip
     * */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * px to sp
     * @param context
     * @param pxValue
     * @return sp
     * */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp to px
     * @param context
     * @param spValue
     * @return px
     * */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * get application package name, eg:com.android.app.test
     * @param context
     * @return package name by String, may null
     * */
    public static String getAppPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get application version name, eg: 1.0.1
     * @param context
     * @return version name by String, may null
     * */
    public static String getAppVersionName(Context context) {
        String name = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    /**
     * get application version code, eg: 1,2...
     * @param context
     * @return version code by integer
     * */
    public static int getAppVersionCode(Context context) {
        int code = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }




}
