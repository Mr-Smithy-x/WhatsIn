package com.charlton.whatsin.adapters.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.charlton.whatsin.fragment.HomeFragment;

/**
 * Created by cj on 11/4/16.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private HomeFragment[] fragments = new HomeFragment[]{
            HomeFragment.create(HomeFragment.HOME),
            HomeFragment.create(HomeFragment.RESULT)
    };

    public String[] title = new String[]{
            "HOME", "RESULTS"
    };

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    public void searchUPC(String contents) {
        fragments[1].Search(contents);
    }
}
