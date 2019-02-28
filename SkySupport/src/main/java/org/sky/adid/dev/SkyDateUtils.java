package org.sky.adid.dev;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2018 Sky. All rights reserved.
 */
public class SkyDateUtils {

    private static final String TAG = "DataUtils";


    // format：Year-Month-Day Hour:Minite:Second
    public static final String FORMAT_SIMPLE = "yyyy-MM-dd HH:mm:ss";
    // format：Year-Month-Day Hour:Minite
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    // format：Year-Month-Day
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    // format：Month-Day
    public static final String FORMAT_MD = "MM-dd";
    // format：Hour:Minite:Second
    public static final String FORMAT_HMS = "HH:mm:ss";





    /**
     * get time stamp by date time and format
     * @param formatDateTime  format date time string   eg: 2019-02-27 17:36:02,  2019-02-27 17:36,  2019-02-27 and so on
     * @param format          format type
     * @return time stamp: long type
     */
    public static long getTimeStampMsByFormat(String formatDateTime, String format){
        if (TextUtils.isEmpty(format)) {
            format = FORMAT_SIMPLE;
        }

        long stamp = 0L;
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(formatDateTime);
            stamp = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stamp;
    }


    /**
     * get time stamp by simple format date time
     * @param formatDateTime  format date time string, eg: 2019-02-27 17:14:54
     * @return time stamp:  long type
     */
    public static long getSimpleTimeStamp(String formatDateTime){
        return getTimeStampMsByFormat(formatDateTime, FORMAT_SIMPLE);
    }

    /**
     * get time stamp by short format date time
     * @param formatDateTime  format date time string, eg: 2019-02-27 17:14
     * @return time stamp:  long type
     */
    public static long getShortTimeStamp(String formatDateTime){
        return getTimeStampMsByFormat(formatDateTime, FORMAT_YMDHM);
    }

    /**
     * get time stamp by short format date time
     * @param formatDateTime  format date time string, eg: 2019-02-27
     * @return time stamp:  long type
     */
    public static long getYMDTimeStamp(String formatDateTime){
        return getTimeStampMsByFormat(formatDateTime, FORMAT_YMD);
    }


    /**
     * get date time format string by stamp ms
     * @param timeMillis
     * @param format   default format is: 2019-02-27 17:27:41
     * @return date time ,default format: 2019-02-27 17:27:41
     */
    public String getDateByStampFormat(long timeMillis, String format){
        String dateTime = null;
        try {
            if(TextUtils.isEmpty(format)) {
                format = FORMAT_SIMPLE;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            Date date = new Date(timeMillis);
            dateTime = simpleDateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dateTime;
    }

    /**
     * get simple date time format by stamp ms
     * @param timeMillis
     * @return date time ,format: 2019-02-27 17:27:41
     */
    public String getSimpleDateByStamp(long timeMillis){
        return getDateByStampFormat(timeMillis, FORMAT_SIMPLE);
    }

    /**
     * get short date time format by stamp ms
     * @param timeMillis
     * @return date time ,format: 2019-02-27 17:27
     */
    public String getShortDateByStamp(long timeMillis){
        return getDateByStampFormat(timeMillis, FORMAT_YMDHM);
    }

    /**
     * get YMD date time format by stamp ms
     * @param timeMillis
     * @return date time ,format: 2019-02-27
     */
    public String getYMDDateByStamp(long timeMillis){
        return getDateByStampFormat(timeMillis, FORMAT_YMD);
    }


    /**
     * get today day
     *
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }


    /**
     * check date(y-M-d) is validate, include [Leap Year]
     *
     * @param date
     *          YYYY-mm-dd
     * @return true: validate,   false:invalidate
     */
    public static boolean isDateValide(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }




    /**
     * get customer define format date time for today
     * @param format
     * @return date time
     */
    public static String getTodayDate(String format) {
        return new java.text.SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }





}
