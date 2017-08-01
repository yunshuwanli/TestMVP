package yswl.com.klibrary.util;

import java.security.MessageDigest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

/**
 * 防破解
 */
public class TamperCheck {

	// we store the hash of the signture for a little more protection
	//正式发布时签名的MD5
	private static final String APP_SIGNATURE = "5838CD6305AA2BDF65F89B574EDF22429C98371D";

	public static boolean validateAppSignatureSafe(Context context) {
		try {
			return validateAppSignature(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Query the signature for this application to detect whether it matches the
	 * signature of the real developer. If it doesn't the app must have been
	 * resigned, which indicates it may been tampered with.
	 * 
	 * @param context
	 * @return true if the app's signature matches the expected signature.
	 * @throws NameNotFoundException
	 */
	public static boolean validateAppSignature(Context context)
			throws Exception {
		PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
				context.getPackageName(), PackageManager.GET_SIGNATURES);
		// note sample just checks the first signature
		for (Signature signature : packageInfo.signatures) {
			// SHA1 the signature
			String sha1 = getSHA1(signature.toByteArray());
			// check is matches hardcoded value
			return APP_SIGNATURE.equals(sha1);
		}
		return false;
	}

	public static String getAppSign(Context context)throws Exception{
		PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
				context.getPackageName(), PackageManager.GET_SIGNATURES);
		// note sample just checks the first signature
		for (Signature signature : packageInfo.signatures) {
			// SHA1 the signature
			String sha1 = getSHA1(signature.toByteArray());
			// check is matches hardcoded value
			return sha1;
		}
		return null;
	}

	// computed the sha1 hash of the signature
	public static String getSHA1(byte[] sig) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA1", "BC");
		digest.update(sig);
		byte[] hashtext = digest.digest();
		return bytesToHex(hashtext);
	}

	// util method to convert byte array to hex string
	public static String bytesToHex(byte[] bytes) {
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
