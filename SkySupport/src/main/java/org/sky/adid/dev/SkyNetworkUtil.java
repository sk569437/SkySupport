package org.sky.adid.dev;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class SkyNetworkUtil {
	public static final int NETWORN_NONE = -1;
	public static final int NETWORN_MOBILE = ConnectivityManager.TYPE_MOBILE; // 0
	public static final int NETWORN_WIFI = ConnectivityManager.TYPE_WIFI;     // 1


	/**
	 * get Network State by Context
	 * @param context
	 * @return net state
	 * <p>{@link SkyNetworkUtil #NETWORK_NONE,   #NETWORN_MOBILE,  #NETWORN_WIFI}</p>
	 * */
	public static int getNetworkState(Context context) {

		if(context == null)
			return NETWORN_NONE;

		try{
			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if(connManager == null) {
				return NETWORN_NONE;
			}

			// Wifi
			NetworkInfo netInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (netInfo != null) {
				State state = netInfo.getState();
				if ((state == State.CONNECTED || state == State.CONNECTING) && netInfo.isAvailable()) {
					return NETWORN_WIFI;
				}
			}

			// 3G
			netInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (netInfo != null) {
				State state = netInfo.getState();
				if ((state == State.CONNECTED || state == State.CONNECTING) && netInfo.isAvailable()) {
					return NETWORN_MOBILE;
				}
			}
		}catch (Exception e){
		}

		return NETWORN_NONE;
	}
}
