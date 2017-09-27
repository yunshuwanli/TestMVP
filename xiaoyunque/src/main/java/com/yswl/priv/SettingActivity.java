package com.yswl.priv;

import android.os.Bundle;

import com.yswl.priv.base.MToolBarActivity;


public class SettingActivity extends MToolBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setTitle("设置");
    }
}
