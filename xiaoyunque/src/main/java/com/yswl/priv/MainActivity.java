package com.yswl.priv;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.yswl.priv.Navigation.DataGenerator;

import yswl.com.klibrary.base.MActivity;
import yswl.com.klibrary.base.MFragment;
import yswl.com.klibrary.debug.L;

/**
 * 主页集成无Toolbar MActivity
 */
public class MainActivity extends MActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = findView(R.id.bottom_view);
        mFragments = DataGenerator.getFragments();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                L.e(TAG,"postion :"+tab.getPosition());
                onTabItemSelected(tab.getPosition());
                for(int i=0;i< mTabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    }else{
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round)).setText(DataGenerator.mTabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round)).setText(DataGenerator.mTabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round)).setText(DataGenerator.mTabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round)).setText(DataGenerator.mTabTitle[3]));
        onTabItemSelected(0);

    }
    private MFragment[] mFragments;
    private void onTabItemSelected(int postion){
        MFragment fragment = null;
        switch (postion){
            case 0:
                fragment = mFragments[0] ;
                break;
            case 1:
                fragment = mFragments[1];
                break;
            case 2:
                fragment = mFragments[2];
                break;
            case 3:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            L.e(TAG,"come to");
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    }


}
