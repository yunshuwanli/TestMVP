package yswl.com.testmvp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import yswl.com.klibrary.base.MActivity;
import yswl.com.testmvp.R;
import yswl.com.testmvp.fragment.Main4Fragment;

public class Main4Activity extends MActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new Adapter(getSupportFragmentManager());
        String[] titles = new String[]{"Google", "Custom"};
        for (int i = 0; i < titles.length - 1; i++) {
            mAdapter.addFragment(new Main4Fragment(), titles[i]);
        }
        tabLayout = findView(R.id.tabs);
        viewPager = findView(R.id.viewPager);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    protected static class Adapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<CharSequence> titles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, CharSequence title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
