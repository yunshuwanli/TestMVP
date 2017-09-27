package com.yswl.priv;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.view.MenuItem;

import com.yswl.priv.Navigation.AuthFragment;
import com.yswl.priv.Navigation.BorrowInfoFragment;
import com.yswl.priv.Navigation.DataGenerator;
import com.yswl.priv.Navigation.HomeFragment;
import com.yswl.priv.Navigation.UserInfoFragment;
import com.yswl.priv.R;

import yswl.com.klibrary.base.MActivity;
import yswl.com.klibrary.base.MFragment;

public class MainActivity extends MActivity implements HomeFragment.OnFragmentInteractionListener {
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
                onTabItemSelected(tab.getPosition());
                //改变Tab 状态
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


    }
    private MFragment[] mFragments;
    private void onTabItemSelected(int id){
        MFragment fragment = null;
        switch (id){
            case R.id.tab_menu_home:
                fragment = mFragments[0] ;
                break;
            case R.id.tab_menu_barrow:
                fragment = mFragments[1];
                break;
            case R.id.tab_menu_auth:
                fragment = mFragments[2];
                break;
            case R.id.tab_menu_user:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
