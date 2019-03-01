package org.sky.adid.dev;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Copyright (c) 2018 Sky. All rights reserved.
 */

/**
 * {@link UncaughtExceptionHandler} Send an log file with
 * some debug information to the developer.
 * <p/>
 * In the Activity or Application of onCreate calling methods:
 * <p/>
 * CrashHandler crashHandler = CrashHandler.getInstance();
 * crashHandler.init(this);
 */
@SuppressLint("SimpleDateFormat")
public class SkyCrashHandle implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    //private static final String RECIPIENT = "yourname@gmail.com";
    private static final boolean isRecordLog = true;
    //private static final boolean isSendEmail = false;
    private static final String LOG_SUFFIX = ".log";
    private static final String CRASH_DIR = "HostSDK";

    private static SkyCrashHandle instance = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private SkyCrashHandle() {

    }

    public static SkyCrashHandle getInstance() {
        if (instance == null) {
            synchronized (SkyCrashHandle.class) {
                if(instance==null){
                    instance = new SkyCrashHandle();
                }
            }
        }
        return instance;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private String getEnterChart(){
        return "\r\n";
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            Date curDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String formatTime = format.format(curDate);

            StringBuilder report = new StringBuilder();
            report.append("Error report collected on: ").append(formatTime).append(getEnterChart()).append(getEnterChart());
            report.append("Information: ").append(getEnterChart());
            addInformation(report);
            report.append(getEnterChart()).append(getEnterChart());
            report.append("Stack: \r\n");

            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);

            e.printStackTrace(printWriter);
            report.append(result.toString());
            printWriter.close();
            report.append(getEnterChart());
            report.append("---  End of current Report ---");

            saveCrashInfo2File(report.toString());
            sendErrorMail(report);

            //if(e!=null)
            //	mDefaultHandler.uncaughtException(t, e);

        } catch (Throwable ignore) {

        }
    }

    private StatFs getStatFs() {
        File path = Environment.getDataDirectory();
        return new StatFs(path.getPath());
    }

    @SuppressWarnings("deprecation")
    private long getAvailableInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    @SuppressWarnings("deprecation")
    private long getTotalInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    private void addInformation(StringBuilder message) {
        try{
            message.append("Locale: ").append(Locale.getDefault()).append(getEnterChart());
        }catch(Exception e){

            message.append("Could not get Locale information for ").append(mContext.getPackageName()).append(getEnterChart());
        }

        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            message.append("Version: ").append(pi.versionName).append(getEnterChart());
            message.append("Package: ").append(pi.packageName).append(getEnterChart());
        } catch (Exception e) {

            message.append("Could not get Version information for ").append(mContext.getPackageName()).append(getEnterChart());
        }
        try{
            message.append("Phone IMEI: ").append("000001234562222").append(getEnterChart());
            message.append("Phone IMSI: ").append("999998888800000").append(getEnterChart());
        }catch(Exception e){

            message.append("Could not get IMEI, IMSI information for ").append(mContext.getPackageName()).append(getEnterChart());
        }

        try{
            message.append("Phone Model: ").append(Build.MODEL).append(getEnterChart());
            message.append("Android Version: ").append(Build.VERSION.RELEASE).append(getEnterChart());
            message.append("Board: ").append(Build.BOARD).append(getEnterChart());
            message.append("Brand: ").append(Build.BRAND).append(getEnterChart());
            message.append("Device: ").append(Build.DEVICE).append(getEnterChart());
            message.append("Host: ").append(Build.HOST).append(getEnterChart());
            message.append("ID: ").append(Build.ID).append(getEnterChart());
            message.append("Model: ").append(Build.MODEL).append(getEnterChart());
            message.append("Product: ").append(Build.PRODUCT).append(getEnterChart());
            message.append("Type: ").append(Build.TYPE).append(getEnterChart());
        }catch(Exception e){

            message.append("Could not get Build information for ").append(mContext.getPackageName()).append(getEnterChart());
        }

        try{
            StatFs stat = getStatFs();
            message.append("Total Internal memory: ").append(getTotalInternalMemorySize(stat)).append(getEnterChart());
            message.append("Available Internal memory: ").append(getAvailableInternalMemorySize(stat)).append(getEnterChart());
        }catch(Exception e){

            message.append("Could not get SDCard storage information for ").append(mContext.getPackageName()).append(getEnterChart());
        }
    }

    /**
     * This method for call alert dialog when application crashed!
     *
     * @param err
     */
    private void sendErrorMail(final StringBuilder err) {

    }

    public String saveCrashInfo2File(String result) {
        if (isRecordLog && isHasSDCard()) {
            String versionName = "";
            try {
                PackageManager pm = mContext.getPackageManager();
                PackageInfo pi;
                pi = pm.getPackageInfo(mContext.getPackageName(), 0);
                versionName = pi.versionName;
            } catch (Exception e) {

            }

            StringBuffer sb = new StringBuffer();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd-HH-mm-ss");
            String time = format.format(new Date());
            sb.append(time + "==  ==  ==" + result);

            long timetamp = System.currentTimeMillis();

            String fileName = versionName + "-crash-" + time + "-" + timetamp + LOG_SUFFIX;

            try {
                File dir = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + File.separator
                        + CRASH_DIR);
                // Log.i("CrashHandler", dir.toString());
                if (!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, fileName);
                if(file!=null && !file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String saveCrashInfo2File(Throwable ex) {
        if (isRecordLog && isHasSDCard()) {
            String versionName = "";
            try {
                PackageManager pm = mContext.getPackageManager();
                PackageInfo pi;
                pi = pm.getPackageInfo(mContext.getPackageName(), 0);
                versionName = pi.versionName;
            } catch (Exception e) {

            }

            StringBuffer sb = new StringBuffer();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd-HH-mm-ss");
            Writer writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            ex.printStackTrace(pw);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(pw);
                cause = cause.getCause();
            }
            pw.close();
            String result = writer.toString();
            String time = format.format(new Date());
            sb.append(time + "==  ==  ==" + result);

            long timetamp = System.currentTimeMillis();

            String fileName = versionName + "-crash-" + time + "-" + timetamp + LOG_SUFFIX;

            try {
                File dir = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + File.separator
                        + CRASH_DIR);
                // Log.i("CrashHandler", dir.toString());
                if (!dir.exists())
                    dir.mkdirs();
                FileOutputStream fos = new FileOutputStream(new File(dir,
                        fileName));
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return ex==null?null:ex.toString();
    }

    public boolean isHasSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

}