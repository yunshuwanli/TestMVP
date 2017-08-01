package yswl.com.testmvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import yswl.com.testmvp.MainActivity;
import yswl.com.testmvp.R;
import yswl.com.testmvp.Util.MKeyBoardUtils;
import yswl.com.testmvp.presenter.LoginPresenter;
import yswl.com.testmvp.view.LoginV;

public class LoginActivity extends AppCompatActivity implements LoginV, View.OnClickListener {
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    EditText mName, mPwd;
    Button mLogin;
    ProgressBar mProgress;
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(this);
        mName = findView(R.id.et_login_name);
        mPwd = findView(R.id.et_login_pwd);
        mLogin = findView(R.id.btn_login);
        mProgress = findView(R.id.progress);
        mProgress.setVisibility(View.GONE);
        mLogin.setOnClickListener(this);

    }

    @Override
    public String getName() {
        return mName.getText().toString().trim();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void hideKeybord() {
        MKeyBoardUtils.hideSoftKeyboard(this);
    }

    @Override
    public String getPwd() {
        return mPwd.getText().toString().trim();
    }

    @Override
    public void setProgress(boolean boo) {
        int flag = boo ? View.VISIBLE : View.GONE;
        mProgress.setVisibility(flag);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login)
            mPresenter.login();
    }

    @Override
    public void next() {
        startActivity(new Intent(this,  MainActivity.class));
    }
}
