package yswl.com.testmvp.presenter;

import android.os.Handler;

import yswl.com.klibrary.http.CallBack.HttpCallback;
import yswl.com.testmvp.model.LoginModle;
import yswl.com.testmvp.model.impl.LoginModleImpl;
import yswl.com.testmvp.view.LoginV;

/**
 * Created by kangpAdministrator on 2017/4/27 0027.
 * Emial kangpeng@yunhetong.net
 */

public class LoginPresenter implements HttpCallback {

    private LoginV v;
    private LoginModle loginModle;

    public LoginPresenter(LoginV v) {
        this.v = v;
        loginModle = new LoginModleImpl();
    }

    public void login() {
        String name = v.getName();
        String pwd = v.getPwd();
        if (loginModle.validate(name, pwd)) {
            v.setProgress(true);
            v.hideKeybord();
            loginModle.login(name, pwd, this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    v.setProgress(false);
                    v.next();
                }
            }, 2000);
        }

    }


    @Override
    public void onSucceed(int requestId, Object result) {
        v.next();
    }

    @Override
    public void onFail(int requestId, String errorMsg) {
//        v.showToast
    }
}
