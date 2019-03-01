package org.sky.adid.dev;

import android.util.Log;

public final class SkyLogUtils {

	private static boolean DEBUG = false;
	private static final String DEFAULT_TAG="[SkyLogger]";

	public static void isDebug(boolean isDebug) {
		DEBUG = isDebug;
	}


	public static void setDebug() {
		DEBUG = true;
	}



	/**
	 * show log by DEBUG model with TAG
	 * @param TAG
	 * @param msg
	 * */
	public static void d(String TAG, String msg) {
		if (DEBUG) {
			Log.d(DEFAULT_TAG+TAG+"=>",  getLineMethod()+ msg);
		}
	}


	/**
	 * show log by INFO model with TAG
	 * @param TAG
	 * @param msg
	 * */
	public static void i(String TAG,  String msg) {
		if (DEBUG) {
			Log.d(DEFAULT_TAG+TAG+"=>",  getLineMethod()+ msg);
		}
	}

	/**
	 * show log by ERROR model with TAG
	 * @param TAG
	 * @param msg
	 * */
	public static void e(String TAG, String msg){
		if (DEBUG) {
			Log.e(DEFAULT_TAG+TAG+"=>", getLineMethod() + msg);
		}
	}


	/**
	 * show log by DEBUG model with default TAG (SkyLogger)
	 * @param msg
	 * */
	public static void d(String msg){
		if (DEBUG) {

			Log.d(_FILELINE_(),  getLineMethod() + msg);
		}
	}

	/**
	 * show log by INFO model with default TAG (SkyLogger)
	 * @param msg
	 * */
	public static void i(String msg){
		if (DEBUG) {
			Log.i(_FILELINE_(),  getLineMethod() + msg);
		}
	}


	/**
	 * show log by ERROR model with default TAG (SkyLogger)
	 * @param msg
	 * */
	public static void e(String msg){
		if (DEBUG) {
			Log.e(_FILELINE_(),  getLineMethod() + msg);
		}
	}



	private static String getLineMethod() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
		StringBuffer toStringBuffer = new StringBuffer("")
				.append("[")
				.append(traceElement.getMethodName())
				.append("]");
		return toStringBuffer.toString();
	}

	private static String _FILE_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
		return traceElement.getFileName();
	}

	private static String _FILELINE_() {
		String dst = "";
		try {
			StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
			dst = traceElement.getFileName() + " " + traceElement.getLineNumber() + " " + DEFAULT_TAG;
		}catch (Exception e){
		}
		return dst;
	}

}

