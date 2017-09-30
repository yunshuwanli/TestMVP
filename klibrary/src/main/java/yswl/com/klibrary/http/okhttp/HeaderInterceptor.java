package yswl.com.klibrary.http.okhttp;

import android.os.Build;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import yswl.com.klibrary.MApplication;
import yswl.com.klibrary.util.MAppInfoUtil;


public class HeaderInterceptor implements Interceptor {
    private static final String TAG = "HeaderInterceptor";
    private static final String AGENT = "User-Agent";
    private static String MAC = null;
    private static final String MAC_KEY = "MobileMac";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
//        builder.addHeader(MAC_KEY, getMAC()).addHeader(AGENT, getDeviceInfo());
        Request newRequest = addHeaders(builder).build();
        return chain.proceed(newRequest);
    }


    /**
     * 统一为请求添加头信息
     *
     * @return ex
     */
    private Request.Builder addHeadersV(Request.Builder builder) {
        return builder
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", MAppInfoUtil.getVersionName(MApplication.getApplication()));
    }
    private Request.Builder addHeaders(Request.Builder builder) {
        return builder
                .addHeader("deviceType", "2")
                .addHeader("deviceToken", getMAC())
                .addHeader("appVersion", MAppInfoUtil.getVersionName(MApplication.getApplication()))
                .addHeader("osVersion", Build.VERSION.RELEASE);
    }

    public static String getMAC() {
        if (MAC == null)
            MAC = MDeviceUtil.getDeviceInfo(MApplication.getApplication());
        Log.e(TAG, "mac " + MAC);
        return MAC;
    }

    public static String getDeviceInfo() {
        String systemInfo = System.getProperty("http.agent")
                + Build.MODEL + Build.VERSION.RELEASE;
        return systemInfo + MScreenUtils.getScreenInfo(MApplication.getApplication());
    }
}
