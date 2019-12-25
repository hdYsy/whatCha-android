package com.lianjie.andorid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.lianjie.andorid.utils.ViewPagerAdapter;

import java.util.List;

/**
 * Created by My on 2019/11/22.
 */

public class Adapter_VideoFragment extends FragmentStatePagerAdapter {


    private final List<Fragment> fragments;

    public Adapter_VideoFragment(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
