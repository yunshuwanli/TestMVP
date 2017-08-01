package yswl.com.klibrary.debug;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import yswl.com.klibrary.MApplication;
import yswl.com.klibrary.http.okhttp.MDeviceUtil;
import yswl.com.klibrary.http.okhttp.MScreenUtils;
import yswl.com.klibrary.util.MAppInfoUtil;
import yswl.com.klibrary.util.TamperCheck;

/**
 * Created by kangpAdministrator on 2017/6/7 0007.
 * Emial kangpeng@yunhetong.net
 */

public class L {
    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 打包注意
     * SessionAjaxCallback  agent svn version
     * LxUrl  地址
     * BaseCasAuthHandler  cas 地址
     * SVNCODE
     */
    public static final boolean DEGUG = MApplication.getApplication().getDebugSetting();

    public static void d(String tag, String msg) {
        if (DEGUG) android.util.Log.d(tag, msg);
    }
    public static void v(String tag, String msg) {
        if (DEGUG) android.util.Log.v(tag, msg);
    }
    public static void e(String tag, String msg) {
        if (DEGUG) {
            android.util.Log.e(tag, msg);
        }
    }


    public static void i(String tag, String msg) {
        if (DEGUG) android.util.Log.i(tag, msg);
    }

    public static void printDeviceInfo(Context ctx) {
        if (DEGUG) {
            int width = MScreenUtils.getScreenWidth(ctx);
            int height = MScreenUtils.getScreenHeight(ctx);
            int statusHeight = MScreenUtils.getStatusHeight(ctx);
            float density = MScreenUtils.getDeviceDensity(ctx);
            double physicalSize = MScreenUtils.getScreenPhysicalSize(ctx);
            L.d("DeviceInfo", "device info "
                    + " | width(px) : " + width
                    + " | height(px) : " + height
                    + " | statusHeight : " + statusHeight
                    + " | density(密度) : " + density
                    + " | physicalSize(尺寸) : " + physicalSize
                    + " | uemeng debug " + MDeviceUtil.getDeviceInfo(ctx));
        }
    }

    public static String getAppInfo(Context context){
        StringBuilder info = new StringBuilder();
//        info.append("LX.DEBUG: "+Lx.DEGUG);
//        info.append("\n");
//        info.append("Lx.SVNCODE: "+Lx.SVNCODE);
//        info.append("\n");
        try {
            String sign = TamperCheck.getAppSign(context);
            info.append("sign: "+sign);
            info.append("\n");
            info.append("sign vaild: "+TamperCheck.validateAppSignature(context));
            info.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.append("\n");
        info.append("version_code: "+ MAppInfoUtil.getVersionCode(context));
        info.append("\n");
        info.append("version_name: "+ MAppInfoUtil.getVersionName(context));
        info.append("\n");
        info.append("w&h: "+ MScreenUtils.getScreenWidth(context)+"&"+MScreenUtils.getScreenHeight(context));
        info.append("\n");
        info.append("dpi: "+ MScreenUtils.getScreenDensityDpi(context)+"   "+MScreenUtils.getScreenDensity(context));
        info.append("\n");
        if( Build.VERSION.SDK_INT>=17){
            boolean alwaysFinishActivity = Settings.Global.getInt(context.getContentResolver(), Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0)==1;
            info.append("alwaysFinishActivity: "+ alwaysFinishActivity);
            info.append("\n");
        }
        return info.toString();
    }
}
