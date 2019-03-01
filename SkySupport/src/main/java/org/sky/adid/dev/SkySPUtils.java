package org.sky.adid.dev;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sk on 17-11-13.
 */

public class SkySPUtils {

    private static SkySPUtils mInstance = null;
    private Context mContext;


    public static SkySPUtils getInstance() {
        if(mInstance == null) {
            synchronized (SkySPUtils.class) {
                mInstance = new SkySPUtils();
            }
        }
        return mInstance;
    }

    /**
     * new instance SharedPreference, initialize context and set mContext to varibles
     * @param context
     * */
    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * put String value to sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE
     * @param key
     * @param value
     * */
    public void putStringPreference(String key, String value) {
        try {
            if(mContext == null) {
                return;
            }

            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
            if(sp != null) {
                SharedPreferences.Editor et = sp.edit();
                et.putString(key, value);
                et.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * put Integer value to sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE
     * @param key
     * @param value
     * */
    public void putIntPreference(String key, int value) {
        try {
            if(mContext == null) {
                return;
            }

            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
            if(sp != null) {
                SharedPreferences.Editor et = sp.edit();
                et.putInt(key, value);
                et.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * put Boolean value to sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE
     * @param key
     * @param value
     * */
    public void putBooleanPreference(String key, boolean value) {
        try {
            if(mContext == null) {
                return;
            }

            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
            if(sp != null) {
                SharedPreferences.Editor et = sp.edit();
                et.putBoolean(key, value);
                et.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * put Long value to sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE
     * @param key
     * @param value
     * */
    public void putLongPreference(String key, long value) {

        try {
            if(mContext == null) {
                return;
            }

            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
            if(sp != null) {
                SharedPreferences.Editor et = sp.edit();
                et.putLong(key, value);
                et.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * get String value from sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE, default value is ""
     * @param key
     * */
    public String getStringPreference(String key) {
        String value = "";
        try {
            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);

            if(sp != null) {
                value = sp.getString(key, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return value;
    }


    /**
     * get Integer value from sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE, default value is 0
     * @param key
     * */
    public int getIntPreference(String key) {
        int value = 0;
        try {
            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);

            if(sp != null) {
                value = sp.getInt(key, 0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return value;
    }


    /**
     * get Boolean value from sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE, default value is false
     * @param key
     * */
    public boolean getBooleanPreference(String key) {
        boolean value = false;

        try {
            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);

            if(sp != null) {
                value = sp.getBoolean(key, false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return value;
    }


    /**
     * get Long value from sharedPreference,and use key of NAME, mode is: Context.MODE_PRIVATE, default value is 0
     * @param key
     * */
    public long getLongPreference(String key) {
        long value = 0;
        try {
            SharedPreferences sp = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
            if(sp != null) {
                value = sp.getLong(key, 0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return value;
    }


}
