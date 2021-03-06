package com.yswl.priv.Navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yswl.priv.R;
import com.yswl.priv.shengqianshapping.HomeFragment2;
import com.yswl.priv.shengqianshapping.PinkageFragment;
import com.yswl.priv.shengqianshapping.RebateFragment;
import com.yswl.priv.shengqianshapping.UserInfoFragment2;

import yswl.com.klibrary.base.MFragment;

public class DataGenerator {

    public static final int[] mTabRes = new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
    public static final int[] mTabResPressed = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    public static final String[] mTabTitle = new String[]{"首页", "借款", "认证", "我的"};

    public static MFragment[] getFragments() {
        MFragment fragments[] = new MFragment[4];
        fragments[0] = new HomeFragment2();
        fragments[1] = new RebateFragment();
        fragments[2] = new PinkageFragment();
        fragments[3] = new UserInfoFragment2();
        return fragments;
    }
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}