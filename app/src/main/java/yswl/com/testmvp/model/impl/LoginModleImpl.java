package yswl.com.testmvp.model.impl;


import android.text.TextUtils;

import yswl.com.klibrary.http.CallBack.HttpCallback;
import yswl.com.testmvp.model.LoginModle;

/**
 * Created by kangpAdministrator on 2017/4/27 0027.
 * Emial kangpeng@yunhetong.net
 */

public class LoginModleImpl implements LoginModle {


    @Override
    public boolean validate(String var, String var2) {
        if (TextUtils.isEmpty(var)) return false;
        if (TextUtils.isEmpty(var2)) return false;
        return true;
    }

    @Override
    public void login(String name, String pwd, HttpCallback onHttpCallback) {
        //
    }
}
