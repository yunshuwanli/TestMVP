package yswl.com.klibrary.permissionUtil;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MPermissionUtil {
    public static final String TAG = "MPermissionUtil";
    public static final int RequestCode = 1;

    /**
     *  权限判断
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        boolean result = (ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED);
        if (!result) {
            Log.e(TAG, "未获得权限" + permission);
        }
        return result;
    }

    /**
     * 权限请求
     * @param ac
     * @param permissions
     * @return
     */
    public static boolean checkSelfPermission(Activity ac, String... permissions) {
        try {
            List<String> pers = new ArrayList<String>();
            for (String p : permissions) {
                if (!checkSelfPermission(ac, p)) {
                    pers.add(p);
                }
            }
            if (pers.size() > 0) {
                Log.e(TAG, "未获得权限");
                if (ac != null) {
                    String temp[] = new String[pers.size()];
                    pers.toArray(temp);
                    requestPermission(ac, temp, RequestCode);
                }
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

            return false;
        }
    }

    private static void requestPermission(Activity ac, String ps[], int requestCode) {
        ActivityCompat.requestPermissions(ac, ps, requestCode);
    }
}
