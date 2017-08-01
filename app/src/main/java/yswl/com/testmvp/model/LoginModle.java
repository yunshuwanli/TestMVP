package yswl.com.testmvp.model;


import yswl.com.klibrary.http.CallBack.HttpCallback;

/**
 * Created by kangpAdministrator on 2017/4/27 0027.
 * Emial kangpeng@yunhetong.net
 */

public interface LoginModle {
    boolean validate(String var, String var2);

    void login(String name, String pwd, HttpCallback onHttpCallback);
}
