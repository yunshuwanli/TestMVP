package yswl.com.testmvp;


import yswl.com.klibrary.MApplication;

/**
 * Created by kangpAdministrator on 2017/5/5 0005.
 * Emial kangpeng@yunhetong.net
 */

public class App extends MApplication {


    @Override
    public boolean getDebugSetting() {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public String getBaseUrl_Https() {
        return BuildConfig.base_url;
    }
}
