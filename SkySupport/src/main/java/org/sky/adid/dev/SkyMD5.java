// TOP SECRET
package org.sky.adid.dev;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SkyMD5 {

	/**
	 * input string change to 32bit MD5
	 * 
	 * @param plainText
	 * @return md5    32bit
	 */
	public static String md5Of32(String plainText) {
		String result = "";
		if(!TextUtils.isEmpty(plainText)){
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				return buf.toString();
			} catch (NoSuchAlgorithmException e) {
				Log.e("HostSDK", e.getMessage());
			}
		}
		return result;
	}

	/**
	 * get file MD5 of 32bit
	 * @param file
	 * @return MD5    32bit
	 * */
	public static String md5Of32(File file) {
		if (!file.isFile() || !file.exists()) {
			return null;
		}

		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		String md5code = bigInt.toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
